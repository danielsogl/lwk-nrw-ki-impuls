---
name: api-vertrag-aendern
description: Ablauf für jede Änderung an der REST-Schnittstelle (neue Felder, Endpunkte, Statuscodes). Nutzen, sobald api/openapi.yaml, ein Backend-DTO oder course.model.ts betroffen ist.
---
# API-Vertrag ändern

Reihenfolge einhalten, jeder Schritt im selben Commit:

1. **Vertrag zuerst:** `api/openapi.yaml` anpassen. Schemas behalten `additionalProperties: false`.
   Neue Felder mit Typ, Beschreibung und, wo sinnvoll, `nullable`.
2. **Backend:** DTO-`record` in `backend/src/main/java/.../<fachlichkeit>/` anpassen. Feldnamen exakt
   wie im Vertrag.
3. **Frontend:** `frontend/src/app/courses/course.model.ts` im selben Schritt anpassen. Feldnamen
   exakt wie im Vertrag.
4. **Test:** In `SeminareApiTest` prüft `openApi().isValid("../api/openapi.yaml")` jede Antwort gegen
   den Vertrag. Neue Endpunkte bekommen diese Prüfung ebenfalls.
5. **Prüfen:** `cd backend && ./mvnw test` und `cd frontend && npm test -- --watch=false`.

Typische Fehler, die der Vertragstest meldet:

- `properties which are not allowed by the schema: <feld>`: Das Backend liefert ein Feld, das im
  Vertrag fehlt. Vertrag ergänzen oder Feld umbenennen, nicht `additionalProperties` lockern.
- Frontend-Tests grün, Vertragstest rot: Die Frontend-Mocks folgen dem Frontend, nicht dem Vertrag.
  `course.model.ts` gegen `api/openapi.yaml` abgleichen.
