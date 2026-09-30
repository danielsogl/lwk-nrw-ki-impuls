# Demo: Seminaranmeldung

Beispielanwendung für den Impuls „KI für Entwickler:innen: Produktiver mit Angular und Spring Boot“.
Angular 22 im Frontend, Spring Boot 4 (Java 25) im Backend, dazwischen ein OpenAPI-Vertrag.

## Starten

Voraussetzungen: JDK 25, Node 24.

```bash
cd backend && ./mvnw spring-boot:run      # http://localhost:8080
cd frontend && npm install && npm start   # http://localhost:4200
```

Die Datenbank ist eine H2-In-Memory-Datenbank; `backend/src/main/resources/data.sql` legt drei Kurse an,
einer davon ist ausgebucht.

## Der Aufbau der Demo

| Station | Was passiert | Stand |
|---|---|---|
| 1 · Verstehen | Der Agent erklärt die bestehende Anwendung | `main` |
| 2 · Planen | Aus `docs/warteliste/intent.md` entsteht ein Plan zur Freigabe | `demo/2-plan` |
| 3 · Umsetzen | Warteliste über Vertrag, Backend und Frontend, testgetrieben | `demo/3-umsetzung` |
| 4 · Prüfen | Ein plausibler, aber fehlerhafter Stand – was fangen die Gates, was das Review? | `demo/4-review` |

Jede Station hat einen eigenen Branch. Schlägt ein Live-Schritt fehl, geht es mit
`git switch <branch>` am vorbereiteten Stand weiter.

## Leitplanken im Repository

- **`AGENTS.md`** – Architektur- und Coding-Vorgaben für KI-Agenten (GitHub Copilot, Claude Code u. a.).
- **`api/openapi.yaml`** – der Vertrag. Backend-Tests prüfen jede Antwort dagegen.
- **`lefthook.yml`** (im Repo-Root) – Lint und Tests vor jedem Commit.
- **`.github/workflows/ci.yml`** – dieselben Prüfungen noch einmal in der CI.
