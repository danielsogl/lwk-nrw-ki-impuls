---
name: intent-erfassen
description: Macht aus einer vagen Feature-Idee ein kurzes, versioniertes docs/<feature>/intent.md, indem es Fachseite oder Product Owner Frage für Frage interviewt. Nutzen ganz am Anfang eines Features, bevor es Plan oder Code gibt, oder wenn jemand sagt „Intent erfassen“ oder „frag mich ab“.
---
# Intent erfassen

Herausfinden, was gebraucht wird und warum. Planung und Umsetzung kommen später (Agent `planer`,
Skill `tests-zuerst`): hier kein Plan, keine Tests, kein Code, keine Endpunkte.

## Schritte

1. Code nur so weit lesen, wie es für gute Fragen nötig ist (was gibt es heute neben dem Feature?).
   Keine Änderungen vorschlagen.
2. **Eine Frage pro Nachricht** stellen und auf die Antwort warten. Zu jeder Frage die eigene beste
   Vermutung anbieten, damit ein „ja“ genügt. In dieser Reihenfolge:
   - **Was:** das Ergebnis aus Sicht der Nutzerinnen und Nutzer
   - **Warum:** wer das Problem hat und was es heute kostet
   - **Rahmenbedingungen:** was gleich bleiben muss (Verhalten, Daten, Datenschutz, Fristen)
   - **Nicht-Ziele:** was ausdrücklich nicht gebaut wird
3. Aufhören, sobald sich das Feature einer neuen Kollegin ohne Raten erklären lässt. Meist 4 bis 8
   Fragen, nie mehr als 10.
4. `docs/<feature>/intent.md` nach der Vorlage schreiben, unter 40 Zeilen, ohne Technik.
5. Die Datei zeigen und fragen: „Fehlt etwas oder stimmt etwas nicht?“ Korrekturen einarbeiten.

## Vorlage

```markdown
# <Feature>

## Was
<2–4 Sätze: das Ergebnis, wie Nutzende es beschreiben würden>

## Warum
<wer das Problem hat, was es heute kostet>

## Rahmenbedingungen
- <muss gleich bleiben>

## Nicht-Ziele
- <ausdrücklich nicht jetzt>

## Offene Fragen
- <was noch niemand beantworten konnte>
```

## Regeln

- Antwortet jemand mit einer Lösung („neuer Endpunkt“), nach dem Problem dahinter fragen.
- Widersprüche und Unbekanntes unter „Offene Fragen“ festhalten, nicht selbst entscheiden.
