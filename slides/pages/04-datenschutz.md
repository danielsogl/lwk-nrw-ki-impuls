---
layout: section
number: 4
subtitle: Orientierung für Führung, keine Rechtsberatung. Stand 30. September 2026.
---

# Datenschutz & EU AI Act

---

# Was der EU AI Act heute verlangt

<div class="grid grid-cols-[1.1fr_1fr] gap-8 mt-4">

<Timeline :items="[
  { when: 'Feb. 2025', title: 'Verbote und KI-Kompetenz', body: 'Art. 5 und Art. 4 gelten, auch für Behörden und Körperschaften.' },
  { when: 'Aug. 2025', title: 'Pflichten für Modellanbieter', body: 'Treffen OpenAI, Anthropic und Google. Die Kammer ist nicht betroffen.' },
  { when: 'Juli 2026', title: 'Digital Omnibus in Kraft', body: 'Art. 4 abgeschwächt, Hochrisiko-Fristen verschoben.' },
  { when: 'Dez. 2027', title: 'Hochrisiko-Systeme (Anhang III)', body: 'Relevant, wenn Sie selbst KI-Fachverfahren bauen.', tone: 'muted' },
]" />

<div>

<Callout type="tip" title="Ein Coding-Assistent ist kein Hochrisiko-System">
Die Kammer ist <strong>Betreiberin</strong>. Die strengen Betreiberpflichten (Art. 26) gelten
nur für Hochrisiko-Systeme.
</Callout>

<Callout type="warn" title="Aber: Was Sie damit bauen, kann es sein" class="mt-4">
Ein KI-System, das Förderanträge vorprüft oder Beschäftigte bewertet, fällt unter
Anhang III. Mit welchem Werkzeug es entwickelt wurde, spielt keine Rolle.
</Callout>

</div>

</div>

<!--
⏱ 2 min

**Kern:** Für den Coding-Assistenten selbst bleibt aus dem AI Act vor allem Art. 4. Interessant wird der AI Act, sobald die Kammer KI in Fachverfahren einsetzt.

**Fakten (Recherche 30.09.2026):**
- Digital Omnibus on AI = VO (EU) 2026/1744, ABl. 24.07.2026. Inkrafttreten laut konsolidierter EUR-Lex-Fassung 27.07.2026.
- Hochrisiko Anhang III → 02.12.2027, Anhang I → 02.08.2028. Art. 50 (Transparenz) unverändert ab 02.08.2026.
- Betreiber: Art. 3 Nr. 4 schließt Behörden ausdrücklich ein.
- Anhang III Nr. 5a (Zugang zu öffentlichen Leistungen) und Nr. 4 (Beschäftigung) sind die Beispiele.

Quellen: eur-lex.europa.eu/eli/reg/2026/1744/oj · fpf.org (AI Act timeline, 28.07.2026) · Freshfields „EU AI Act unpacked #34“ (10.07.2026)
-->

---

# KI-Kompetenz ist eine Führungsaufgabe

<div class="grid grid-cols-2 gap-8 mt-6">

<div>

<Steps :items="[
  { title: 'Was Art. 4 verlangt', body: 'Seit dem Omnibus: Maßnahmen ergreifen, die KI-Kompetenz der Beschäftigten unterstützen. Kein Zertifikat, kein Pflichtniveau.' },
  { title: 'Was praktisch hilft', body: 'Schulungen und eine interne Richtlinie mit erlaubten und verbotenen Szenarien. Beides dokumentiert.' },
  { title: 'Wer zuständig ist', body: 'Eine benannte Person für Kompetenz, Werkzeugfreigabe und Modellauswahl.' },
]" />

</div>

<div>

<Callout type="info" title="Aufsicht in Deutschland">
Das KI-Marktüberwachungsgesetz ist seit Juli 2026 in Kraft. Gegen Behörden und öffentliche
Stellen sieht es keine Bußgelder vor.
</Callout>

<Callout type="warn" title="Trotzdem kein Freifahrtschein" class="mt-4">
Ohne Nachweis fehlt Ihnen im Ernstfall die Antwort auf die Frage, wer was wusste und wer es
freigegeben hat.
</Callout>

</div>

</div>

<!--
⏱ 2 min

**Kern:** Art. 4 ist die eine AI-Act-Pflicht, die heute direkt bei der Kammer liegt. Sie ist weich formuliert, aber sie ist eine Führungsaufgabe.

**Fakten:**
- Omnibus: statt „sicherstellen“ jetzt „Maßnahmen ergreifen, die die Entwicklung von KI-Kompetenz unterstützen“; Pflicht bleibt bei Anbieter und Betreiber. EU-Kommission, AI Literacy Q&A (akt. 27.07.2026): kein Zertifikat, interne Nachweise sinnvoll.
- KI-MIG, ausgefertigt 22.07.2026, in Kraft 29.07.2026, BNetzA zuständig; Landesbehörden überwachen öffentliche Stellen der Länder.
- PRÜFEN vor dem Termin: § 17 Abs. 2 KI-MIG (keine Geldbußen gegen Behörden) und § 2 Abs. 6. Die Paragraphen stammen aus einer Tool-Zusammenfassung, nicht aus dem gelesenen Gesetzestext. Auf der Folie steht deshalb nur die Aussage, nicht der Paragraph.

Quellen: digital-strategy.ec.europa.eu/en/faqs/ai-literacy-questions-answers · gesetze-im-internet.de/ki-mig/
-->

---

# Was Copilot mit Ihren Daten macht

<div class="grid grid-cols-2 gap-6 mt-4 text-sm">

<Card title="Nur mit Organisationslizenz" icon="i-carbon-enterprise" variant="accent">
Business und Enterprise: <strong>kein Training</strong> auf Ihren Daten. Bei privaten
Free-/Pro-Accounts trainiert GitHub seit April 2026 standardmäßig mit.
</Card>

<Card title="Aufbewahrung" icon="i-carbon-time" variant="accent">
In der IDE werden Eingaben und Vorschläge nach der Antwort verworfen. Außerhalb der IDE
(Web, CLI) 28 Tage. Nutzungsdaten bis zu 2 Jahre.
</Card>

<Card title="Ausschlüsse sind kein Geheimnisschutz" icon="i-carbon-view-off" variant="accent">
Content Exclusion greift <strong>nicht</strong> im Agent Mode. Zugangsdaten und personenbezogene
Daten gehören nicht ins Repository, mit oder ohne KI.
</Card>

<Card title="Wo die Modelle rechnen" icon="i-carbon-earth-europe-africa" variant="accent">
EU-Datenresidenz nur mit GitHub Enterprise Cloud mit Data Residency (GHE.com). Sonst: USA,
abgesichert über EU-US Data Privacy Framework und Standardvertragsklauseln.
</Card>

</div>

<!--
⏱ 3 min

**Kern:** Mit Organisationslizenz und klaren Regeln ist Copilot datenschutzrechtlich handhabbar. Das Risiko liegt in der Schatten-Nutzung.

**Sagen:** Die häufigste Lücke ist der private Account, mit dem jemand „nur mal kurz“ dienstlichen Code verarbeitet.

**Rückbezug Einstieg:** „Offiziell oder inoffiziell?“ Inoffiziell heißt oft: privater Account, dort wird trainiert.

**Fakten:**
- Trainingsrichtlinie: github.blog, Update März 2026, gilt ab 24.04.2026 für Free/Pro/Pro+ (Opt-out möglich).
- Aufbewahrung laut Copilot Trust Center; Trust Center selbst war nicht direkt lesbar. PRÜFEN: Fristen für Agent Mode und Cloud Agent sind nicht eindeutig dokumentiert.
- Content Exclusion: docs.github.com … /copilot/concepts/context/content-exclusion. Greift nicht in Edit/Agent Mode, nicht bei Symlinks/Remote-Dateisystemen.
- Data Residency: GA seit 13.04.2026 (GitHub Changelog), Inferenz in EU Data Boundary, eingeschränkte Modellauswahl, 10 % Aufschlag auf AI-Credits.
- DPF: EuG hat es am 03.09.2025 bestätigt (T-553/23); Rechtsmittel C-703/25 P beim EuGH anhängig. Restrisiko „Schrems III“.
- IP-Freistellung (Customer Copyright Commitment) gilt für Business/Enterprise; seit April 2026 ohne Pflicht zum Duplicate-Filter.
-->

---

# Personalrat und Nutzungsdaten

<div class="grid grid-cols-[1fr_1.1fr] gap-8 mt-4">

<div>

<Stat value="pro Person" label="liefert die Copilot-Metrics-API Nutzungsdaten" hint="Aktive Tage, Sitzungen, Tokens, seit 2026 auch eine „Adoptionsphase“" />

<Callout type="warn" title="Mitbestimmung" class="mt-4">
Nach LPVG NRW reicht es, dass eine technische Einrichtung zur Leistungs- oder
Verhaltenskontrolle <strong>geeignet</strong> ist. Ob Sie das vorhaben, spielt keine Rolle.
</Callout>

</div>

<div>

<Steps :items="[
  { title: 'Früh einbinden', body: 'Personalrat und Datenschutzbeauftragte vor dem Rollout beteiligen.' },
  { title: 'Dienstvereinbarung', body: 'Zweck, Zugriff und Aggregation regeln. Keine Auswertung einzelner Personen.' },
  { title: 'Vorabprüfung dokumentieren', body: 'Ohne personenbezogene Daten im Code reicht meist eine Schwellwertanalyse statt einer vollen DSFA.' },
]" />

</div>

</div>

<!--
⏱ 2 min

**Kern:** Die Nutzungsmetriken, die Führung gern sehen würde, sind genau das, was mitbestimmungspflichtig ist. Wer das früh klärt, spart Monate.

**Fakten:**
- § 72 Abs. 3 Nr. 2 LPVG NRW: Mitbestimmung bei Einführung, Anwendung und Erweiterung technischer Einrichtungen, „es sei denn, dass deren Eignung zur Überwachung … ausgeschlossen ist“. Daneben Nr. 1 (automatisierte Verarbeitung personenbezogener Daten) und Nr. 3 (grundlegend neue Arbeitsmethoden).
  PRÜFEN: Wortlaut stammt aus einer Sekundärquelle (Stand 2019), vor dem Termin auf recht.nrw.de gegenlesen. Deshalb auf der Folie ohne Paragraphennummer.
- DSK-Orientierungshilfe „KI und Datenschutz“, 06.05.2024: Dienstvereinbarung empfohlen, unabhängig davon, ob personenbezogene Daten (inkl. Nutzungsdaten) verarbeitet werden; Training ausschließen; Beschäftigte schulen.
- Metrics-API: docs.github.com/en/copilot/reference/copilot-usage-metrics; Kohorten „ai_adoption_phase“ laut Changelog 29.05.2026.
- DSFA: Vorabprüfung immer; DSFA wahrscheinlich, sobald Nutzungsdaten zur Leistungsbewertung dienen (Einordnung, keine Behördenaussage).

Zur Kennzahlenfrage siehe Kapitel 5: Teamebene statt Personenebene. Das ist auch fachlich die bessere Messung.
-->

---

# Was die Aufsicht empfiehlt

<CardGrid :cols="2" class="mt-6">
  <Card title="BSI und ANSSI, Okt. 2024" icon="i-carbon-security" variant="accent">
    Risikoanalyse vor der Einführung. Richtlinien durch das Management. Code Review bleibt
    Pflicht. Produktivitätsgewinne nicht überschätzen.
  </Card>
  <Card title="Datenschutzkonferenz, Mai 2024" icon="i-carbon-policy" variant="accent">
    Training ausschließen, dienstliche Accounts, klare Weisungen mit erlaubten und verbotenen
    Szenarien, Beschäftigte schulen, Personalrat einbinden.
  </Card>
</CardGrid>

<Callout type="tip" title="Ein Fahrplan in fünf Schritten" class="mt-6">
Vorabprüfung dokumentieren · Auftragsverarbeitung (GitHub DPA) prüfen · Organisations-Policies
setzen (Training aus, Modelle, Agent-Funktionen) · interne Richtlinie · Review und
Sicherheitsscans bleiben Pflicht.
</Callout>

<!--
⏱ 1 min

**Kern:** Die Aufsicht sagt nichts Überraschendes, und fast alles davon ist Führungsarbeit.

**Quellen:**
- BSI/ANSSI „AI Coding Assistants“, 04.10.2024: bsi.bund.de → ANSSI_BSI_AI_Coding_Assistants.pdf. Nennt ausdrücklich Prompt Injection und erfundene Pakete (Package Hallucination). Rückbezug auf Kapitel 3.
- DSK-Orientierungshilfe KI und Datenschutz, 06.05.2024: datenschutzkonferenz-online.de.
- LDI NRW: kein eigenes Papier zu Coding-Assistenten gefunden, verweist auf DSK (ldi.nrw.de/ki-programm).
-->
