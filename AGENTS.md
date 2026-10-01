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

- Features beginnen mit einer Intent-Datei in `docs/<feature>/intent.md` (Skill `intent-erfassen`). Sie gibt
  vor, *was* gebaut wird. Nicht-Ziele bleiben draußen.
- Vor dem Code einen Plan in `docs/<feature>/plan.md` schreiben und auf Freigabe warten. Unklares zuerst
  als Rückfrage stellen, nicht als Annahme in den Plan schreiben.
- Der Plan enthält nur Schritte, die der Intent verlangt. Nicht-Ziele stehen höchstens als kurzer Verweis darin,
  nie als Schritt.
- Bestehende Strukturen erweitern statt parallele neu bauen (neue Tabellen, Endpunkte oder Services nur,
  wenn der Intent es erfordert).
- Tests zuerst (Skill `tests-zuerst`): neue Tests schreiben, ausführen und zeigen, dass sie aus dem erwarteten
  Grund rot sind. Dann anhalten bis zur Freigabe. Erst danach den Code, der sie grün macht.
- Bevor Bestandscode geändert oder abgelöst wird: Skill `altcode-verstehen`.
- Reviews folgen `REVIEW.md`.
- Macht ein Agent denselben Fehler zweimal, kommt die Korrektur als Regel in diese Datei.

## API-Vertrag

- `api/openapi.yaml` ist die einzige Quelle der Wahrheit für die Schnittstelle.
- Jede Änderung an der Schnittstelle folgt dem Skill `api-vertrag-aendern` (`.github/skills/`).
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
- Mindestens ein Test je Feature läuft ohne gemeinsame Transaktion
  (`@Transactional(propagation = Propagation.NOT_SUPPORTED)`), so wie der Server im Betrieb. Tests mit
  gemeinsamer Transaktion verdecken Fehler, die erst im Betrieb auftreten.
- Das Datenbankschema entsteht aus den JPA-Entities (H2, `ddl-auto`). Keine Migrationswerkzeuge einführen.
- `ArchitekturTest` (ArchUnit) prüft die Regeln oben bei jedem `./mvnw test`: nur freigegebene Bibliotheken,
  keine Feldinjektion. Schlägt er fehl, den Code anpassen, nicht die Regel.

## Frontend (Angular 22)

- Standalone-Komponenten, `inject()` statt Konstruktor-Injection. OnPush ist seit v22 Standard, nicht
  explizit setzen. Neue Singleton-Services mit `@Service()`.
- Zustand in Signals; Daten laden mit `httpResource`, Formulare mit Signal Forms (`@angular/forms/signals`).
- Templates mit `@if`/`@for`, nicht `*ngIf`/`*ngFor`. Keine NgModules.
- `npm run lint` (angular-eslint) prüft diese Muster. Lint-Fehler beheben, nicht die Regel abschalten.
- Texte auf Deutsch, Barrierefreiheit beachten (Labels, `role="alert"` für Fehler).
- Für Angular-Code gilt zusätzlich der offizielle Skill `angular-developer` (`.github/skills/`). Weicht er von
  dieser Datei ab, gilt diese Datei.

## Regeln

- Bestehende Tests nie löschen, abschwächen oder überspringen. Wirkt ein Test falsch: anhalten und begründen.
- Keine neuen Abhängigkeiten ohne Rückfrage.
- Git-Hooks nie umgehen (`--no-verify`), Lint-Regeln nie abschalten, um grün zu werden.
- Keine echten personenbezogenen Daten in Code, Tests oder Prompts – nur Beispieldaten (`@example.org`).
