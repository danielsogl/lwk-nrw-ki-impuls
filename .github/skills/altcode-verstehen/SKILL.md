---
name: altcode-verstehen
description: Fasst zusammen, was ein undokumentiertes Bestandsmodul wirklich tut, welche überraschenden Randfälle es hat und was riskant zu ändern ist. Nutzen, bevor Altcode geändert, abgelöst oder in eine neue Anwendung übertragen wird, etwa Swing-Oberflächen, Berichtsvorlagen oder alte Services.
---
# Altcode verstehen

Du prüfst ein Bestandsmodul für jemanden, der es ändern oder ablösen will. Du änderst nichts.

## Schritte

1. Jede öffentliche Methode bzw. Funktion im Zielbereich lesen. Namen und Kommentaren nicht trauen,
   sondern nachvollziehen, was der Code tatsächlich tut.
2. Für jeden Zweig und Randfall (leere Eingabe, null, negative Werte, fehlende Felder, Grenzwerte)
   von Hand ermitteln, was passiert.
3. Fachregeln, die im Code stecken, als Liste herausschreiben: Bedingung, Ergebnis, Fundstelle.

## Ausgabe

- Was das Modul tut, in einfacher Sprache.
- **Fachregeln:** je Regel ein Satz mit Datei und Zeile. Diese Liste prüft die Fachseite.
- **Überraschungen:** alles, was man aus den Namen nicht erraten würde, je mit der Eingabe, die es zeigt.
- **Risiko je öffentlicher Methode:** gefahrlos änderbar / erst mit Characterization Test ändern /
  nicht ohne Fachexpertin oder Fachexperten ändern.

## Regeln

- Keine Datei ändern. Wo Verhalten unklar ist, einen Characterization Test vorschlagen, der das heutige
  Verhalten festhält, statt eine Korrektur.
- Vermutungen als Vermutung kennzeichnen.
