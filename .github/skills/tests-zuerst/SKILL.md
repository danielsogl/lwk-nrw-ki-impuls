---
name: tests-zuerst
description: Setzt einen freigegebenen Plan test-first in zwei Phasen um. Erst rote Tests für die Testfälle aus docs/<feature>/plan.md, dann Halt für die Freigabe durch einen Menschen, danach minimaler Code, bis alles grün ist, ohne die Tests zu ändern. Nutzen, sobald Tests zu einem Plan geschrieben oder ein Plan umgesetzt werden soll.
---
# Tests zuerst

Setzt `docs/<feature>/plan.md` test-first um. Die Tests sind der Prüfstein. Hat ein Mensch sie
freigegeben, werden sie nicht mehr geändert.

## Phase 1: Rot

1. `plan.md` und `AGENTS.md` lesen. Eine bestehende Testdatei neben dem betroffenen Code lesen und
   ihren Stil übernehmen (Backend: `SeminareApiTest`, Frontend: `*.spec.ts`).
2. Je Testfall aus dem Plan genau einen Test schreiben. Der Testname sagt, welcher Fall geprüft wird.
   Jeder Test baut seinen Zustand selbst auf und hängt nicht von der Reihenfolge ab.
3. Nur die betroffene Testsuite ausführen: `cd backend && ./mvnw test` bzw.
   `cd frontend && npm test -- --watch=false`.
4. Jeder neue Test muss **aus dem richtigen Grund** rot sein: fehlendes Verhalten. Ein Tippfehler, ein
   Importfehler oder eine falsche URL zählt nicht und wird korrigiert.
5. **Anhalten**, sobald alle neuen Tests aus dem richtigen Grund rot sind. Nicht weiter nachschärfen.
   Testnamen und Fehlergrund je Test zeigen und um Freigabe bitten. Noch keinen Produktionscode.

## Phase 2: Grün

Erst nach der Freigabe der Tests:

1. Die Schritte aus `plan.md` der Reihe nach umsetzen, je Schritt die kleinste Änderung, die seine Tests
   grün macht. Bestehende Muster nutzen statt neue zu erfinden.
2. Betrifft ein Schritt die Schnittstelle, gilt der Skill `api-vertrag-aendern`.
3. Am Ende alle drei Prüfungen aus `AGENTS.md` ausführen und das Ergebnis zeigen: welcher Testfall durch
   welchen Test abgedeckt ist, und was unsicher blieb.

## Regeln

- **Nie einen bestehenden Test ändern, löschen, überspringen oder abschwächen**, auch nicht die aus
  Phase 1. Wirkt ein Test falsch: anhalten und begründen. Ausnahme: Der Plan verlangt ausdrücklich ein
  geändertes Verhalten (zum Beispiel „Antwort 409 entfällt“); dann den betroffenen alten Test in Phase 1
  ersetzen und das in der Zusammenfassung nennen.
- Einen Schritt erst als erledigt melden, wenn ein grüner Lauf wirklich ausgeführt wurde.
- Nichts über den Plan hinaus bauen. Nicht-Ziele bleiben Nicht-Ziele.
