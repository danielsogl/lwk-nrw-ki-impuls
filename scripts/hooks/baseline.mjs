#!/usr/bin/env node
// Baseline-Check für Copilot-Agenten (VS Code und Copilot CLI).
//   start: hält beim Start einer Sitzung fest, welche Tests grün sind, und sagt es dem Agenten.
//   stop:  bevor der Agent fertig meldet. Hat er nur Tests geändert (Rot-Phase, Skill tests-zuerst),
//          darf er anhalten. Hat er Produktionscode geändert, muss alles grün sein, was vorher nicht
//          schon rot war. Sonst schickt der Hook ihn einmal zurück an die Arbeit.
// Ergebnisse liegen in .git/agent-baseline/, nicht im Repository.
import { execSync, spawnSync } from 'node:child_process';
import { existsSync, mkdirSync, readdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { join } from 'node:path';

const git = (args) => execSync(`git ${args}`, { encoding: 'utf8' }).trim();
process.chdir(git('rev-parse --show-toplevel'));

const mode = process.argv[2];
const stdin = process.stdin.isTTY ? '' : readFileSync(0, 'utf8');
const input = stdin.trim() ? JSON.parse(stdin) : {};
const dir = join(git('rev-parse --path-format=absolute --git-common-dir'), 'agent-baseline');
mkdirSync(dir, { recursive: true });
const sessionFile = join(dir, `session-${input.session_id ?? input.sessionId ?? 'last'}.json`);
const win = process.platform === 'win32';

function runTests() {
  const results = {};
  const reports = 'backend/target/surefire-reports';
  rmSync(reports, { recursive: true, force: true });
  spawnSync(win ? 'mvnw.cmd' : './mvnw', ['-q', 'test'], { cwd: 'backend', stdio: 'ignore', shell: win });
  const files = existsSync(reports) ? readdirSync(reports).filter((f) => /^TEST-.*\.xml$/.test(f)) : [];
  if (!files.length) results['Backend-Build'] = 'rot';
  for (const f of files) {
    const xml = readFileSync(join(reports, f), 'utf8');
    for (const [, attrs, , body = ''] of xml.matchAll(/<testcase\b([^>]*?)(\/>|>([\s\S]*?)<\/testcase>)/g)) {
      const name = attrs.match(/\bname="([^"]+)"/)[1];
      const cls = attrs.match(/\bclassname="([^"]+)"/)[1].split('.').pop();
      results[`${cls}.${name}`] = /<(failure|error)\b/.test(body) ? 'rot' : /<skipped\b/.test(body) ? 'übersprungen' : 'grün';
    }
  }
  const fe = spawnSync('npm', ['test', '--', '--watch=false'], { cwd: 'frontend', stdio: 'ignore', shell: win });
  results['Frontend-Tests'] = fe.status === 0 ? 'grün' : 'rot';
  return results;
}

if (mode === 'start') {
  const head = git('rev-parse --short HEAD');
  const cache = join(dir, `${head}.json`);
  let results;
  if (existsSync(cache)) {
    results = JSON.parse(readFileSync(cache, 'utf8'));
  } else {
    results = runTests();
    // ponytail: nur ein sauberer Stand landet im Cache, sonst gilt die Baseline nur für diese Sitzung
    if (git('status --porcelain') === '') writeFileSync(cache, JSON.stringify(results));
  }
  writeFileSync(sessionFile, JSON.stringify({ head, results }));
  writeFileSync(join(dir, 'session-last.json'), JSON.stringify({ head, results }));
  const red = Object.keys(results).filter((t) => results[t] !== 'grün');
  const green = Object.keys(results).length - red.length;
  const context =
    `Baseline vor dieser Aufgabe (Stand ${head}): ${green} Prüfungen grün` +
    (red.length ? `, schon vorher rot: ${red.join(', ')}` : ', keine rot') +
    '. Ändert die Aufgabe Produktionscode, prüft ein Hook vor dem Abschluss, dass alles grün ist, was vorher nicht schon rot war.';
  console.log(JSON.stringify({ additionalContext: context, hookSpecificOutput: { hookEventName: 'SessionStart', additionalContext: context } }));
}

if (mode === 'stop') {
  if (input.stop_hook_active) process.exit(0); // nur einmal nachsteuern, sonst Endlosschleife
  const file = existsSync(sessionFile) ? sessionFile : join(dir, 'session-last.json');
  if (!existsSync(file)) process.exit(0);
  const base = JSON.parse(readFileSync(file, 'utf8'));
  const changed = `${git(`diff --name-only ${base.head}`)}\n${git('ls-files --others --exclude-standard')}`.split('\n');
  // Nur Tests oder Doku geändert: Rot-Phase, die Freigabe der Tests liegt beim Menschen.
  if (!changed.some(isProductionCode)) process.exit(0);
  const now = runTests();
  const broken = Object.keys(now).filter((t) => now[t] !== 'grün' && base.results[t] !== 'rot');
  if (!broken.length) process.exit(0);
  const reason =
    `Baseline-Check: Produktionscode ist geändert, aber ${broken.map((t) => `${t} ist ${now[t]}`).join(', ')}. ` +
    'Vor der Aufgabe war das nicht rot. Behebe die Ursache im Code, ändere dafür keine Tests, und melde erst danach fertig.';
  console.log(JSON.stringify({ decision: 'block', reason, hookSpecificOutput: { hookEventName: 'Stop', decision: 'block', reason } }));
}

function isProductionCode(path) {
  return /^(api\/|backend\/pom\.xml$|backend\/src\/main\/|frontend\/src\/)/.test(path) && !/\.spec\.ts$/.test(path);
}
