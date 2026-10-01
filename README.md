# Demo: Seminaranmeldung

Beispielanwendung für den Impuls „KI in der Softwareentwicklung“. Angular 22 im Frontend,
Spring Boot 4 (Java 25) im Backend, dazwischen ein OpenAPI-Vertrag.

## Starten

Voraussetzungen: JDK 25, Node 24.

```bash
npm install                                   # installiert die Git-Hooks (lefthook)
cd backend && ./mvnw spring-boot:run          # http://localhost:8080
cd frontend && npm install && npm start       # http://localhost:4200
```

Die Datenbank ist eine H2-In-Memory-Datenbank; `backend/src/main/resources/data.sql` legt drei Kurse an,
einer davon ist ausgebucht.

## Der Aufbau der Demo

| Station | Was passiert | Stand |
|---|---|---|
| 1 · Verstehen | Der Agent erklärt die bestehende Anwendung | `main` |
| 2 · Planen | Aus `docs/warteliste/intent.md` entsteht ein Plan zur Freigabe | `demo/2-plan` |
| 3 · Umsetzen | Warteliste über Vertrag, Backend und Frontend, testgetrieben | `demo/3-umsetzung` |
| 4 · Prüfen | Ein plausibler, aber fehlerhafter Stand: Was fangen die Gates, was das Review? | `demo/4-review` |

Den vorbereiteten Stand einer Station lädt `scripts/station.sh <1-4>`. Das Skript sichert offene Änderungen
im Stash, wechselt auf eine Arbeitskopie des Stations-Branches und startet das Backend neu. Das Frontend
(`npm start`) lädt Änderungen selbst nach.

## Leitplanken im Repository

- **`AGENTS.md`**: Architektur- und Coding-Vorgaben für KI-Agenten (GitHub Copilot, Codex, Cursor u. a.).
- **`REVIEW.md`**: Review-Vorgaben mit drei Durchgängen (Fehler, Sicherheit, Abgleich mit Intent und Plan).
- **`api/openapi.yaml`**: der Vertrag. Backend-Tests prüfen jede Antwort dagegen.
- **`.github/agents/`**: zwei Custom Agents. `planer` schreibt aus dem Intent einen Plan und ändert keinen Code,
  `reviewer` reviewt nach `REVIEW.md` und ändert nichts.
- **`.github/skills/api-vertrag-aendern/`**: ein Skill mit dem Ablauf für Änderungen am Vertrag. Der Agent lädt ihn
  nur, wenn eine Änderung die Schnittstelle betrifft.
- **`.github/skills/intent-erfassen/`**, **`tests-zuerst/`**, **`altcode-verstehen/`**: eigene Skills für den
  Ablauf Intent → Tests zuerst und für Bestandscode (Fachregeln herausarbeiten, Risiko je Methode,
  Characterization Tests statt Korrekturen). Angepasst aus den Übungen des BASTA-Workshops „AI Coding“.
- **`.github/skills/angular-developer/`**: der offizielle Skill des Angular-Teams (MIT-Lizenz, Quelle
  [angular/skills](https://github.com/angular/skills), Stand v22.2.1, Commit `9466d891`). Unverändert übernommen.
  Aktualisieren: den Ordner gegen einen neuen Release-Stand von `angular/skills` austauschen und den Diff im
  Pull Request prüfen wie jede andere Abhängigkeit.
- **`.github/hooks/leitplanken.json`**: drei Hooks für jede Agenten-Sitzung (VS Code und Copilot CLI).
  Eine Regel in `AGENTS.md` ist ein Rat, ein Hook eine Sperre.
  - `sessionStart`: Baseline. Hält fest, welche Tests vor der Aufgabe grün sind, und sagt es dem Agenten
    (`scripts/hooks/baseline.mjs`, Ergebnis in `.git/agent-baseline/`).
  - `preToolUse`: blockiert `--no-verify` und `lefthook uninstall`.
  - `agentStop`: Bevor der Agent fertig meldet. Hat er nur Tests geändert (erst rot), darf er anhalten, die Freigabe
    der Tests liegt beim Menschen. Hat er Produktionscode geändert, laufen alle Tests: Was vorher nicht schon rot war,
    muss grün sein, sonst schickt der Hook ihn einmal zurück an die Arbeit.
- **`ArchitekturTest`** (ArchUnit, `backend/src/test`): Regeln aus `AGENTS.md` als Test, das Gegenstück zu angular-eslint.
  Nur freigegebene Bibliotheken, keine Feldinjektion. Läuft mit `./mvnw test` überall mit: Hook, lefthook, CI.
- **`lefthook.yml`**: Lint und Tests vor jedem Commit, lokal und umgehbar.
- **`.github/workflows/checks.yml`** und **`.gitlab-ci.yml`**: dieselben Prüfungen in der CI. Der Branch-Schutz verlangt grüne CI und eine menschliche Freigabe, auch für Admins.
- **`.github/CODEOWNERS`**: Änderungen an CI, Hooks, `AGENTS.md` und `REVIEW.md` brauchen die Freigabe der Verantwortlichen. Sonst könnte ein Agent im selben Pull Request die Prüfung entschärfen, die ihn prüft.
