# Plan: Warteliste für ausgebuchte Kurse

Grundlage: [`intent.md`](./intent.md). Status: **zur Freigabe**

## Annahmen – bitte bestätigen

1. Ein ausgebuchter Kurs lehnt Anmeldungen nicht mehr mit `409` ab, sondern setzt sie auf die Warteliste.
2. Die Reihenfolge der Warteliste ergibt sich aus dem Anmeldezeitpunkt (bei Gleichstand: Anmelde-ID).
3. Nachrücken passiert nur, wenn eine **feste** Anmeldung storniert wird – nicht beim Stornieren eines
   Wartelistenplatzes.
4. Doppelte Anmeldungen derselben E-Mail sind nicht Teil dieses Features (heute ebenfalls möglich).

## Schritte

Jeder Schritt ist ein Commit mit grünen Tests.

### 1 · Vertrag (`api/openapi.yaml`)

- `Registration` bekommt `status` (`CONFIRMED` | `WAITLISTED`) und `waitlistPosition`
  (Ganzzahl ab 1, `null` bei fester Anmeldung).
- `POST /api/courses/{courseId}/registrations`: Antwort `409` entfällt.

### 2 · Backend

- Test zuerst (`SeminareApiTest`):
  - Anmeldung auf ausgebuchten Kurs → `201`, `status = WAITLISTED`, `waitlistPosition = 1`, zweite Person Position 2
  - `freePlaces` zählt nur feste Anmeldungen
  - Storno einer festen Anmeldung → die älteste Wartelisten-Anmeldung wird `CONFIRMED`
  - Storno eines Wartelistenplatzes → niemand rückt nach
- `Registration`: neues Feld `status` (Enum `RegistrationStatus`)
- `RegistrationRepository`: Zählen nach Status, Warteliste sortiert lesen
- `RegistrationService.register`: Status nach freien Plätzen setzen; `CourseFullException` entfällt
- `RegistrationService.cancel`: nach Storno einer festen Anmeldung nachrücken lassen – in derselben Transaktion
  und mit gesperrtem Kurs (`findForUpdateById`, wie bei der Anmeldung), damit nichts überbucht
- `CourseController`: `freePlaces` aus festen Anmeldungen berechnen

### 3 · Frontend

- `course.model.ts`: `Registration` um `status` und `waitlistPosition` erweitern
- Test zuerst (`course-list.spec.ts`): ausgebuchter Kurs zeigt „Auf die Warteliste“; Bestätigung nennt den Platz
- `course-list.html`: Bei `freePlaces === 0` statt Anmelde-Button „Auf die Warteliste“
- `registration-form.ts`: Button-Text je nach Lage; Fehlertext für `409` entfällt
- Bestätigung: „Sie stehen auf Platz N der Warteliste.“

## Nicht Teil dieses Plans

E-Mail-Benachrichtigung, Längenbegrenzung, Austragen über die Oberfläche, Verwaltungsoberfläche
(siehe Nicht-Ziele im Intent).

## Risiken

- **Reihenfolge bei gleichem Zeitstempel:** Die Warteliste sortiert nach `createdAt`, bei Gleichstand
  nach ID. Sonst wäre die Reihenfolge nicht eindeutig.
