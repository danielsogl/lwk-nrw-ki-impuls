---
layout: section
number: 5
subtitle: Werkzeuge kauft man ein. Die Leitplanken darum herum setzt die Führung.
---

# Was Führung jetzt entscheidet

---

# Was die Forschung zeigt

<div class="grid grid-cols-2 gap-8 mt-6">

<div>

Große Branchenstudien aus 2025 und 2026 kommen zu drei Ergebnissen:

- Fast alle nutzen KI, im DORA-Report 2025 sind es **90 %**
- KI ist ein **Verstärker**: Gute Prozesse werden besser, schwache schlechter
- Durchsatz **und** Instabilität steigen, gemeinsam

<div class="mt-3 text-sm text-[var(--shi-fg-dim)]">Umfragen, Selbstauskunft. Zusammenhänge, keine Kausalität.</div>

</div>

<div>

<Callout type="tip" title="Die Erkenntnis für die Planung">
Eine <strong>J-Kurve</strong>: Erst sinkt die Leistung, dann steigt sie. Dazu kommt eine
<strong>Prüfsteuer</strong>, also der Aufwand, KI-Ergebnisse zu verifizieren. Sie gehört ins
Budget.
</Callout>

<Callout type="warn" title="Und im selben Report" class="mt-4">
Nur <strong>24 %</strong> vertrauen den Ergebnissen viel oder sehr viel. Über 80 % fühlen
sich trotzdem produktiver.
</Callout>

</div>

</div>

<!--
⏱ 2 min

**Kern:** Wer Ihnen ab Monat eins eine gerade Linie nach oben verspricht, verspricht zu viel.

VOLATIL: DORA, „State of AI-assisted Software Development 2025“ (≈5.000 Befragte, Juni/Juli 2025): 90 % nutzen KI; Vertrauen 24 % viel/sehr viel vs. 30 % wenig/gar nicht; >80 % fühlen sich produktiver. DORA „ROI of AI-assisted Software Development“ (v2026.1, 22.04.2026): J-Kurve und „verification tax“. dora.dev/ai/roi/report/

Methodisch ehrlich, einmal sagen: Umfragen über die eigene Erfahrung. Gute Teams führen vielleicht einfach früher ein.
-->

---

# Welche Kennzahlen taugen

<div class="grid grid-cols-2 gap-8 mt-6">

<div>

<div class="text-sm font-bold uppercase tracking-wide text-[var(--shi-danger)] mb-2">Täuscht</div>

<Callout type="danger" title="Zeilen, Commits, Pull Requests">
Messen das Erzeugen, also genau den Teil, der billig geworden ist.
</Callout>

<Callout type="danger" title="Rankings einzelner Personen" class="mt-3">
Aus der Messung wird ein Ziel, und Ziele werden erreicht. Dazu kommt die Mitbestimmung.
</Callout>

<Callout type="danger" title="Annahmequote von Vorschlägen" class="mt-3">
Sagt nichts darüber, ob der Code einen Monat später noch da ist.
</Callout>

</div>

<div>

<div class="text-sm font-bold uppercase tracking-wide text-[var(--shi-success)] mb-2">Taugt, auf Teamebene gemessen</div>

<Callout type="tip" title="Wartezeit bis zum Review">
Frühindikator für den Engpass aus Kapitel 3.
</Callout>

<Callout type="tip" title="Fehlerrate nach Auslieferung" class="mt-3">
Instabilität steigt mit dem Durchsatz.
</Callout>

<Callout type="tip" title="Nacharbeit" class="mt-3">
Was wird in den Wochen nach der Auslieferung umgeschrieben?
</Callout>

</div>

</div>

<!--
⏱ 2 min

**Kern:** Ob KI funktioniert, ist eine zu große und zu politische Frage. Messen Sie, ob die Auslieferung besser wird. Das hätten Sie ohnehin messen sollen.

**Sagen:**
- Teamebene ist fachlich richtig und passt zur Dienstvereinbarung aus Kapitel 4. Beides zeigt in dieselbe Richtung.
- Wenn nur eine Kennzahl: Wartezeit bis zum Review.

VOLATIL: METR-Umfrage 2026 (n = 349): selbst berichteter Geschwindigkeitsgewinn (Median 3×) liegt weit über dem berichteten Nutzen (1,3×); Selbstauskünfte überschätzen gemessene Effekte deutlich. Selbst berichtete Zahlen als Obergrenze lesen.
-->

---

# Wo kann Ihr Team hinkommen?

<div class="grid grid-cols-[1fr_1.15fr] gap-8 mt-4">

<div>

<div class="lvs">
  <div class="lvs__row"><span>0</span>Autovervollständigung</div>
  <div class="lvs__row"><span>1</span>Praktikant</div>
  <div class="lvs__row lvs__row--most"><span>2</span>Junior · jede Zeile lesen</div>
  <div class="lvs__row lvs__row--day"><span>3</span>Entwickler:in · Änderungen prüfen</div>
  <div class="lvs__row lvs__row--day"><span>4</span>Team · Vorgabe schreiben, Ergebnis prüfen</div>
  <div class="lvs__row"><span>5</span>Dunkle Fabrik · niemand liest den Code</div>
</div>

<div class="mt-3 text-xs text-[var(--shi-fg-dim)]">Stufen nach Dan Shapiro, <em>The Five Levels</em> (Jan. 2026)</div>

</div>

<div>

<Steps :items="[
  { title: 'Wo stehen Sie heute?', body: 'Ehrlich eingeschätzt. Welche Stufe darüber wäre in drei Monaten erreichbar?' },
  { title: 'Was muss es vorher geben?', body: 'Intent, Vorgaben im Repository, Tests, Gates in der CI, Zeit für Review. Was fehlt Ihrem Team?' },
  { title: 'Welche Freigaben bleiben menschlich?', body: 'Anforderung, Merge, Release, Störung. Für jede eine benannte Person, auch auf Stufe 4.' },
]" />

</div>

</div>

<style>
.lvs { display: flex; flex-direction: column-reverse; gap: 0.3rem; }
.lvs__row { display: flex; align-items: center; gap: 0.6rem; padding: 0.4rem 0.7rem; border: 1px solid var(--shi-border); border-radius: var(--shi-radius); background: var(--shi-card); font-size: 0.85rem; font-weight: 600; }
.lvs__row span { font-weight: 800; color: var(--shi-brand); width: 1rem; text-align: center; }
.lvs__row--most { border-color: var(--shi-accent-amber); background: color-mix(in srgb, var(--shi-accent-amber) 12%, var(--shi-card)); }
.lvs__row--day { border-color: var(--shi-brand); background: color-mix(in srgb, var(--shi-brand) 12%, var(--shi-card)); }
</style>

<!--
⏱ 2 min

**Kern:** Ziel ist die nächste Stufe, mit einer benannten Person an jeder Freigabe.

**Sagen:** Rückbezug auf die Frage vom Anfang: „Wo stehen Ihre Teams? Hat sich die Einschätzung nach der Demo verändert?“
-->

---

# Fünf Entscheidungen für die nächsten 90 Tage

<Steps class="mt-4" :items="[
  { title: 'Werkzeug offiziell machen', body: 'Organisationslizenz statt Schatten-Nutzung. Policies zentral: Training aus, zugelassene Modelle, Agent-Funktionen, MCP-Server.' },
  { title: 'Rahmen klären, bevor es skaliert', body: 'Personalrat und Datenschutz einbinden, Dienstvereinbarung, interne Richtlinie. Eine verantwortliche Person für KI-Kompetenz.' },
  { title: 'Vorgaben ins Repository', body: 'AGENTS.md mit Architektur- und Coding-Regeln, API-Vertrag, Lint-Regeln. Gepflegt vom Team, geändert per Review.' },
  { title: 'Gates, die niemand umgehen kann', body: 'Pflicht-Prüfungen in der CI, Branch-Schutz, menschliche Freigabe. Review-Zeit einplanen, Juniors und Seniors pairen.' },
  { title: 'Klein anfangen, ehrlich messen', body: 'Ein Pilotteam, ein echtes Feature, drei Kennzahlen auf Teamebene. Nach 90 Tagen entscheiden, was bleibt.' },
]" />

<!--
⏱ 2 min

**Kern:** Nichts davon ist eine Werkzeugfrage. Alles davon ist eine Entscheidung, die das Team nicht allein treffen kann.

**Sagen:**
- Reihenfolge ist Absicht: 1 und 2 zuerst, sonst fängt jemand trotzdem an, dann eben inoffiziell.
- Überleitung: „Welche dieser fünf ist bei Ihnen die schwierigste? Damit steigen wir in die Diskussion ein.“
-->

---

# Diskussion

<div class="grid grid-cols-[1.4fr_1fr] gap-8 mt-4">

<div>

<Steps :items="[
  { title: 'Einstieg', body: 'Welche der fünf Entscheidungen ist bei Ihnen die schwierigste, und warum?' },
  { title: 'Daten', body: 'Welche Nutzungsdaten wollen Sie als Führung überhaupt sehen, und was schließen Sie ausdrücklich aus?' },
  { title: 'Verantwortung', body: 'Wer gibt Modelle und Agent-Funktionen frei, und wer verantwortet die KI-Kompetenz?' },
  { title: 'Einstiegspunkt', body: 'Welches Team, welches Feature eignet sich für einen ersten Piloten?' },
]" />

</div>

<div>

<Callout type="info" title="Ihr Feedback">
Bitte nehmen Sie sich zum Schluss zwei Minuten:<br />
<a href="https://admin.angulararchitects.io/feedback/96c4a97c-9a87-4e77-9b3f-9b7206335ed7" target="_blank">admin.angulararchitects.io/feedback</a>
</Callout>

<Callout type="tip" title="Zum Mitnehmen" class="mt-4">
Folien als PDF und das Demo-Repository mit allen vier Stationen.
</Callout>

</div>

</div>

<!--
⏱ 30 min (12:25–12:55)

**Moderation:**
- Mit der Einstiegsfrage beginnen, reihum 1 Satz pro Person (12 Personen ≈ 6 min). Danach die häufigste Antwort vertiefen.
- Leitfragen nur als Anker, wenn es stockt.
- Weitere Fragen, falls nötig:
  - „Brauchen wir EU-Datenresidenz, oder tragen wir DPF und Standardvertragsklauseln bewusst mit?“
  - „Wie verändert sich die Ausbildung, wenn Juniors weniger selbst schreiben?“
  - „Wer schreibt bei Ihnen den Intent, die Fachseite oder die Entwicklung?“

**Feedback-Formular um 12:50 aktiv ansprechen** und Link zusätzlich in den Chat posten:
https://admin.angulararchitects.io/feedback/96c4a97c-9a87-4e77-9b3f-9b7206335ed7
-->
