# KI für Entwickler:innen: Produktiver mit Angular und Spring Boot

Material zum Impuls bei der Landwirtschaftskammer NRW am 2. Oktober 2026.

| Ordner | Inhalt |
|---|---|
| [`demo/`](demo/) | Demo-Anwendung „Seminaranmeldung“: Angular 22, Spring Boot 4, OpenAPI-Vertrag. Stationen der Live-Demo als Branches. |
| [`slides/`](slides/) | Foliensatz (Slidev) mit Presenter-Notes und Quellen. |

## Schnellstart

```bash
npm install                                   # installiert die Git-Hooks (lefthook)
cd demo/backend && ./mvnw spring-boot:run     # http://localhost:8080
cd demo/frontend && npm install && npm start  # http://localhost:4200
cd slides && npm install && npm run dev       # http://localhost:3030
```

Für die Arbeit mit einem KI-Agenten den Ordner `demo/` als Workspace öffnen. Dort liegen
`AGENTS.md` und die Intent-Datei für das Warteliste-Feature.

## Stationen der Demo

| Branch | Stand |
|---|---|
| `main` | Ausgangslage: Anmeldung, ausgebuchte Kurse werden abgelehnt |
| `demo/2-plan` | Plan für die Warteliste, zur Freigabe |
| `demo/3-umsetzung` | Warteliste umgesetzt, Vertrag, Backend und Frontend, mit Tests |
| `demo/4-review` | Plausibler Agenten-Commit mit fünf eingebauten Fehlern |
