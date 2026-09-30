---
layout: section
number: 2
subtitle: Ein Feature quer durch Angular, REST-Schnittstelle und Spring Boot, gebaut mit GitHub Copilot im Agent Mode.
---

# Live-Demo

---

# Die Demo-Anwendung

<div class="grid grid-cols-[1.1fr_1fr] gap-8 mt-4">

<div>

<img src="/demo-warteliste.png" alt="Kursliste der Demo-Anwendung mit einem ausgebuchten Kurs" class="rounded border border-[var(--shi-border)]" />

</div>

<div>

<Steps :items="[
  { title: 'Angular 22', body: 'Kursliste und Anmeldeformular, Signals und Signal Forms.' },
  { title: 'OpenAPI-Vertrag', body: 'Eine Datei beschreibt die Schnittstelle. Tests prüfen jede Antwort dagegen.' },
  { title: 'Spring Boot 4', body: 'Kurse, Anmeldungen, Stornierung. Java 25, H2-Datenbank.' },
]" />

<Callout type="info" title="Das Feature" class="mt-4">
Ist ein Kurs ausgebucht, können sich Interessierte auf eine <strong>Warteliste</strong> setzen.
Sagt jemand ab, rückt die älteste Anmeldung nach.
</Callout>

</div>

</div>

<!--
⏱ 2 min

**Kern:** Klein, aber echt: drei Schichten, ein Vertrag dazwischen, Tests auf beiden Seiten.

**Zeigen:** Browser mit localhost:4200, dort den ausgebuchten Kurs „Betriebsnachfolge“.

**Sagen:**
- Das Repository bekommen Sie. Es läuft mit JDK 25 und Node 24.
- Vier Stationen, jede hat einen vorbereiteten Stand. Wenn live etwas hakt, springe ich dorthin. Auch das gehört zur Realität.
-->

---

# Station 1 · Bestehenden Code verstehen

<div class="grid grid-cols-[1.2fr_1fr] gap-8 mt-6">

<div>

<Transcript tool="Copilot · Agent Mode" :lines="[
  { kind: 'prompt', text: 'Erkläre mir die Anwendung: Welche Bausteine gibt es, wie läuft eine Anmeldung vom Button bis in die Datenbank, und wo wird geprüft, ob ein Kurs voll ist?' },
  { kind: 'tool', text: 'read  AGENTS.md · api/openapi.yaml' },
  { kind: 'tool', text: 'read  registration-form.ts · RegistrationService.java' },
  { kind: 'out', text: 'Die Kapazität prüft RegistrationService.register() …' },
]" />

</div>

<div>

<Callout type="tip" title="Für die Führung">
Einarbeitung in fremden oder alten Code wird billig. Das betrifft neue Kolleg:innen,
Vertretungen und Altsysteme, die niemand mehr kennt.
</Callout>

<Callout type="warn" title="Aber" class="mt-4">
Die Erklärung klingt immer überzeugend. Ob sie stimmt, zeigt erst der Blick in den Code.
</Callout>

</div>

</div>

<!--
⏱ 5 min (live)

**Branch:** main

**Prompt (in Copilot Chat, Agent Mode):**
Erkläre mir die Anwendung: Welche Bausteine gibt es, wie läuft eine Anmeldung vom Button im Frontend bis in die Datenbank, und wo wird geprüft, ob ein Kurs voll ist?

**Zeigen:**
- Welche Dateien der Agent liest (Werkzeugaufrufe aufklappen).
- Eine Aussage stichprobenartig im Code nachprüfen: RegistrationService.register().

**Fallback:** Antwort vorlesen aus README.md, Abschnitt „Der Aufbau der Demo“.
-->

---

# Station 2 · Erst der Plan, dann der Code

<div class="grid grid-cols-2 gap-8 mt-4">

<div class="code-sm">

```md
## Was
Ist ein Kurs ausgebucht, können sich Interessierte
mit Name und E-Mail auf eine Warteliste setzen. …

## Warum
Gefragte Lehrgänge sind oft Wochen vorher voll. …

## Rahmenbedingungen
- Freie Plätze zählen nur feste Anmeldungen.
- Name und E-Mail bleiben die einzigen
  personenbezogenen Daten.

## Nicht-Ziele
- Keine E-Mail-Benachrichtigung beim Nachrücken
- Keine Verwaltungsoberfläche
```

<div class="text-xs text-[var(--shi-fg-dim)] mt-1"><code>docs/warteliste/intent.md</code></div>

</div>

<div>

<Transcript tool="Copilot · Agent Mode" :lines="[
  { kind: 'prompt', text: 'Lies intent.md und AGENTS.md. Stelle Rückfragen zu allem, was mehrdeutig ist. Dann schreibe einen Plan. Noch kein Code.' },
  { kind: 'out', text: 'Rückfrage: Rückt beim Storno eines Wartelistenplatzes jemand nach?' },
  { kind: 'tool', text: 'write docs/warteliste/plan.md' },
]" />

<Callout type="tip" title="Für die Führung" class="mt-4">
Die Qualität der Aufgabe bestimmt das Ergebnis. Fachliche Klarheit wird damit zur
Entwicklungsarbeit, und die Fachseite schreibt mit.
</Callout>

</div>

</div>

<!--
⏱ 8 min (live)

**Branch:** main → danach demo/2-plan als Referenz

**Prompt:**
Lies docs/warteliste/intent.md und AGENTS.md. Stelle mir zuerst Rückfragen zu allem, was im Intent mehrdeutig ist. Schreibe danach einen Plan nach docs/warteliste/plan.md. Noch keinen Code.

**Zeigen:**
- Rückfragen beantworten (z. B. doppelte Anmeldungen: nicht Teil dieses Features).
- Plan durchgehen: Annahmen, Schritte, Nicht-Ziele, Risiken. demo/2-plan hat einen guten Referenzplan.
- Nicht-Ziele: Ohne sie baut der Agent gern ungefragt Benachrichtigungen und Admin-Oberflächen.

**Sagen:**
- Der Intent ist eine Seite, ohne Technik. Die Fachseite kann ihn schreiben und per Review freigeben.
- „Könnten zwei Teams aus diesem Text zwei verschiedene Dinge bauen? Dann fehlt eine Antwort.“
-->

---

# Station 3 · Umsetzen mit Vorgaben

<div class="grid grid-cols-[1fr_1.1fr] gap-8 mt-4">

<div class="code-sm">

```md
## API-Vertrag
- api/openapi.yaml ist die einzige Quelle
  der Wahrheit für die Schnittstelle.
- API-Änderungen beginnen im Vertrag. Danach
  Backend-DTOs und course.model.ts anpassen.

## Frontend (Angular 22)
- Templates mit @if/@for, nicht *ngIf/*ngFor.
- inject() statt Konstruktor-Injection.

## Regeln
- Bestehende Tests nie löschen oder abschwächen.
- Keine neuen Abhängigkeiten ohne Rückfrage.
```

<div class="text-xs text-[var(--shi-fg-dim)] mt-1">Auszug aus <code>AGENTS.md</code></div>

</div>

<div>

<Transcript tool="Copilot · Agent Mode" :lines="[
  { kind: 'prompt', text: 'Setze Schritt 1 und 2 aus plan.md um. Erst die Tests, dann der Code.' },
  { kind: 'tool', text: 'edit  api/openapi.yaml' },
  { kind: 'tool', text: 'edit  SeminareApiTest.java' },
  { kind: 'err', text: 'FAIL  Tests rot, Warteliste fehlt' },
  { kind: 'tool', text: 'edit  RegistrationService.java · …' },
  { kind: 'ok', text: '9 Tests grün' },
]" />

<Callout type="tip" title="Für die Führung" class="mt-4">
Architektur- und Coding-Vorgaben gehören ins Repository. Dort gelten sie für Menschen und
Agenten gleich.
</Callout>

</div>

</div>

<!--
⏱ 10 min (live)

**Branch:** demo/2-plan → danach demo/3-umsetzung als Referenz

**Prompt:**
Setze Schritt 1 und 2 aus docs/warteliste/plan.md um: zuerst die Tests, dann den Code. Melde dich erst, wenn `./mvnw test` grün ist.

**Zeigen:**
- Test zuerst rot, dann grün. Der Agent führt die Tests selbst aus.
- Vertrag geändert, bevor Code geändert wurde.
- Wenn Zeit bleibt: Schritt 3 (Frontend), sonst `git switch demo/3-umsetzung` und im Browser „Auf die Warteliste“ zeigen.

**Sagen:**
- AGENTS.md ist eine normale Datei im Repository. Copilot, Claude Code, Codex und Cursor lesen sie alle.
- Beim Commit laufen Lint und Tests automatisch (lefthook). Das gilt für Menschen und Agenten.
-->

---

# Station 4 · Ergebnisse kritisch prüfen

<div class="grid grid-cols-[1fr_1.35fr] gap-8 mt-4">

<div>

<Transcript tool="Commit eines Agenten" :lines="[
  { kind: 'out', text: 'feat: Warteliste für ausgebuchte Kurse' },
  { kind: 'out', text: '- Nachrücken bei Stornierung' },
  { kind: 'out', text: '- Robustere Validierung der Eingaben' },
  { kind: 'ok', text: 'Alle Tests grün.' },
]" />

<Callout type="warn" title="Klingt gut" class="mt-4">
Fünf Fehler stecken drin. Wer findet was?
</Callout>

</div>

<div v-click>

<div class="trap">
  <div class="trap__h">Fehler</div><div class="trap__h">Gefunden von</div>
  <div>Backend liefert <code>position</code>, Frontend erwartet <code>waitlistPosition</code></div><div class="trap__gate">Vertragstest</div>
  <div>Veraltetes Angular: <code>*ngIf</code>, Konstruktor-Injection</div><div class="trap__gate">Lint</div>
  <div>Neue Bibliothek für eine Zeile Validierung, die es schon gibt</div><div class="trap__human">Review</div>
  <div>Beim Storno rückt die <em>jüngste</em> statt der ältesten Anmeldung nach</div><div class="trap__human">Review gegen Intent</div>
  <div>Wartelistenplatz ist im Test richtig, im Betrieb immer 0</div><div class="trap__human">Review, Betrieb</div>
</div>

</div>

</div>

<style>
.trap { display: grid; grid-template-columns: 1fr 9.5rem; font-size: 0.82rem; }
.trap > div { padding: 0.42rem 0.55rem; border-bottom: 1px solid var(--shi-border); align-content: center; }
.trap__h { font-size: 0.68rem; font-weight: 700; text-transform: uppercase; letter-spacing: 0.06em; color: var(--shi-fg-muted); border-bottom: 2px solid var(--shi-brand) !important; }
.trap__gate { font-weight: 700; color: var(--shi-accent-green); }
.trap__human { font-weight: 700; color: var(--shi-accent-coral); }
</style>

<!--
⏱ 8 min (live)

**Branch:** demo/4-review (ein Commit, bewusst mit --no-verify an den Hooks vorbei committet)

**Ablauf:**
1. Commit-Nachricht zeigen: `git show --stat`. „Alle Tests grün“ stimmt sogar für die Frontend-Tests.
2. Die Gates laufen lassen, die der Agent umgangen hat:
   - `cd demo/frontend && npm run lint` → prefer-inject, prefer-control-flow
   - `cd demo/backend && ./mvnw test` → Vertragstest: „properties which are not allowed by the schema: position, status“
   - In echt: CI am Pull Request. `--no-verify` umgeht lokale Hooks, aber nicht die CI.
3. Copilot als Reviewer, neue Chat-Sitzung (frischer Kontext):
   „Prüfe den letzten Commit gegen docs/warteliste/intent.md und AGENTS.md. Du bist Reviewer: Nenne Abweichungen mit Datei und Zeile. Ändere nichts.“
   Erwartbar: commons-lang3 (Regel „keine neuen Abhängigkeiten“), OrderByCreatedAtDesc (Intent: „am längsten wartet“). Den indexOf-Fehler übersieht der Reviewer oft. Gut so, er ist Thema der nächsten Folie.
4. Dann Klick: Auflösung.

**Die Fehler im Detail:**
- RegistrationDto: Feld heißt `position`, Vertrag nicht angepasst → Vertragstest rot. Frontend-Tests grün, weil deren Mocks zum Frontend passen, nicht zum Backend.
- registration-form.ts: `*ngIf` + Konstruktor-Injection → Lint rot.
- pom.xml: commons-lang3 für StringUtils.isBlank. Doppelt zu @NotBlank, dazu eine neue Abhängigkeit ohne Rückfrage.
- RegistrationRepository: findByCourseIdAndStatusOrderByCreatedAtDesc. Der Test hat nur eine Person auf der Warteliste, deshalb fällt es nicht auf.
- RegistrationService.waitlistPosition: indexOf(registration) auf einer Entity aus einer anderen Transaktion → -1 + 1 = 0. Im Test läuft alles in einer Transaktion, dort stimmt es.
-->

---

# Grüne Tests, falscher Betrieb

<div class="grid grid-cols-2 gap-8 mt-6">

<div>

Beim Bau dieser Demo ungeplant passiert:

<Steps class="mt-4" :items="[
  { title: 'Alle 8 Tests grün', body: 'Inklusive Vertragstest. Wartelistenplatz 1 und 2 wie erwartet.' },
  { title: 'Server gestartet, Anfrage geschickt', body: 'Antwort: waitlistPosition 0. Für jede Person.' },
  { title: 'Ursache', body: 'Die Tests laufen in einer einzigen Datenbanktransaktion, der Server nicht. Der Vergleich im Code hing genau daran.' },
]" />

</div>

<div>

<Callout type="danger" title="Tests prüfen, was jemand bedacht hat">
Grüne Tests beweisen, dass die bedachten Fälle funktionieren. Wer die Tests vom selben
Agenten schreiben lässt wie den Code, bekommt dessen blinde Flecken doppelt.
</Callout>

<Callout type="tip" title="Was geholfen hat" class="mt-4">
Die Anwendung einmal wirklich laufen lassen. Danach ein Test, der genau diesen Fall festhält –
er ist jetzt Teil des Repositorys.
</Callout>

</div>

</div>

<!--
⏱ 2 min

**Kern:** Eine echte Geschichte aus der Vorbereitung, kein konstruierter Fehler. Genau dieser Fehler steckt als Nummer 5 in Station 4.

**Sagen:**
- Ich habe den Code mit KI-Unterstützung geschrieben, und die Tests waren grün. Erst der manuelle Lauf gegen den echten Server hat es gezeigt.
- Regressionstest: SeminareApiTest.reportsWaitlistPositionOutsideASharedTransaction (Branch demo/3-umsetzung).

**Falls gefragt (technisch):**
- Spring-Tests mit @Transactional teilen einen Persistence Context. indexOf() auf einer Entity-Liste vergleicht Objektidentität; im Test ist es dasselbe Objekt, im Betrieb eine neue Instanz. Fix: Vergleich über die ID.
-->

---

# Was die Demo für Führung heißt

<CardGrid :cols="2" class="mt-6">
  <Card title="Klarheit vor Tempo" icon="i-carbon-idea" variant="accent">
    Ein Intent mit Nicht-Zielen und ein freigegebener Plan sparen mehr Zeit als jedes
    schnellere Modell.
  </Card>
  <Card title="Vorgaben ins Repository" icon="i-carbon-document" variant="accent">
    <code>AGENTS.md</code>, API-Vertrag, Lint-Regeln: einmal geschrieben, für alle Menschen
    und Agenten gültig, im Review änderbar.
  </Card>
  <Card title="Automatische Gates" icon="i-carbon-flash" variant="accent">
    Tests, Vertragstests und Lint laufen lokal vor dem Commit und noch einmal in der CI. Die CI
    kann niemand umgehen.
  </Card>
  <Card title="Menschen prüfen Absicht" icon="i-carbon-user" variant="accent">
    Drei von fünf Fehlern hat kein Werkzeug gefunden. Das Review gegen den Intent machen
    Menschen, und dafür braucht es Zeit im Plan.
  </Card>
</CardGrid>

<!--
⏱ 2 min, Überleitung zu den Risiken.

**Sagen:** „Die Demo war der gute Fall: ein kleines Repository, klare Vorgaben, ein aufmerksamer Mensch. Jetzt zu dem, was im großen Maßstab schiefgeht.“
-->
