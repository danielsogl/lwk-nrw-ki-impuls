# Seminaranmeldung – Vorgaben für KI-Agenten

Kursanmeldung für Fortbildungen: Angular-Frontend (`frontend/`), Spring-Boot-Backend
(`backend/`), dazwischen ein REST-Vertrag (`api/openapi.yaml`).

## Befehle

- Backend testen: `cd backend && ./mvnw test`
- Frontend testen: `cd frontend && npm test -- --watch=false`
- Frontend linten: `cd frontend && npm run lint`
- Lokal starten: `cd backend && ./mvnw spring-boot:run` und `cd frontend && npm start` (http://localhost:4200)

Eine Änderung ist fertig, wenn alle drei Prüfungen grün sind. Nicht vorher „fertig“ melden.

## Arbeitsweise

- Features beginnen mit einer Intent-Datei in `docs/<feature>/intent.md`. Sie gibt vor, *was* gebaut
  wird. Nicht-Ziele bleiben draußen.
- Vor dem Code einen Plan in `docs/<feature>/plan.md` schreiben und auf Freigabe warten.
- Kleine Schritte: erst ein fehlschlagender Test, dann der Code, der ihn grün macht.

## API-Vertrag

- `api/openapi.yaml` ist die einzige Quelle der Wahrheit für die Schnittstelle.
- API-Änderungen beginnen im Vertrag. Danach Backend-DTOs **und** `frontend/src/app/courses/course.model.ts`
  im selben Schritt anpassen.
- Die Backend-Tests prüfen jede Antwort gegen den Vertrag (`openApi().isValid(...)`). Neue Endpunkte
  bekommen diese Prüfung ebenfalls.

## Backend (Spring Boot 4, Java 25)

- Pakete nach Fachlichkeit (`course`, `registration`), nicht nach Schicht.
- DTOs als `record`, Konstruktor-Injection, kein Lombok, `jakarta.*` statt `javax.*`.
- Fachlogik gehört in den Service, nicht in den Controller.
- Fehler als `ProblemDetail` (RFC 9457) mit deutschem `title`.
- Tests über HTTP mit MockMvc in `SeminareApiTest`; Testdaten stehen in `data.sql`.

## Frontend (Angular 22)

- Standalone-Komponenten mit `ChangeDetectionStrategy.OnPush`, `inject()` statt Konstruktor-Injection.
- Zustand in Signals; Daten laden mit `httpResource`, Formulare mit Signal Forms (`@angular/forms/signals`).
- Templates mit `@if`/`@for`, nicht `*ngIf`/`*ngFor`. Keine NgModules.
- Texte auf Deutsch, Barrierefreiheit beachten (Labels, `role="alert"` für Fehler).

## Regeln

- Bestehende Tests nie löschen, abschwächen oder überspringen. Wirkt ein Test falsch: anhalten und begründen.
- Keine neuen Abhängigkeiten ohne Rückfrage.
- Git-Hooks nie umgehen (`--no-verify`), Lint-Regeln nie abschalten, um grün zu werden.
- Keine echten personenbezogenen Daten in Code, Tests oder Prompts – nur Beispieldaten (`@example.org`).
