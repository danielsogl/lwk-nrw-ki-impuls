---
layout: section
number: 1
subtitle: Code schreiben ist billig geworden. Alles um den Code herum nicht.
---

# Der Engpass hat sich verschoben

---

# Der Engpass hat sich verschoben

<div class="mt-6 text-sm font-semibold text-[var(--shi-fg-muted)]">Ohne KI: jede Phase läuft im Tempo der Menschen</div>

<div class="flex gap-1 mt-2 h-12 text-sm font-semibold text-white">
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 9%">Planen</div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 11%">Entwurf</div>
  <div class="bg-[var(--shi-accent-amber)] text-[var(--shi-bg)] rounded flex items-center justify-center" style="width: 41%">Umsetzen</div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 11%">Testen</div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 10%">Review</div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 18%">Betrieb</div>
</div>

<div class="mt-6 text-sm font-semibold text-[var(--shi-fg-muted)]">Mit KI: Umsetzen läuft in Maschinentempo, der Rest nicht</div>

<div class="flex gap-1 mt-2 h-12 text-sm font-semibold text-white">
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 9%">Planen</div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 11%">Entwurf</div>
  <div class="bg-[var(--shi-accent-amber)] rounded" style="width: 1.5%" title="Umsetzen"></div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 11%">Testen</div>
  <div class="bg-[var(--shi-accent-coral)] text-[var(--shi-bg)] rounded flex items-center justify-center" style="width: 16%">Review ↑</div>
  <div class="bg-[var(--shi-navy)] rounded flex items-center justify-center" style="width: 18%">Betrieb</div>
  <div class="rounded flex items-center justify-center border-2 border-dashed border-[var(--shi-border)] text-[var(--shi-fg-dim)] font-normal" style="width: 33.5%">möglicher Gewinn</div>
</div>

<Callout type="warn" title="Schneller tippen, gleicher Prozess" class="mt-8">
Bleibt alles um den Code herum, wie es ist, füllt KI nur die Warteschlange vor dem
langsamsten menschlichen Schritt: dem Review.
</Callout>

<!--
⏱ 3 min

**Kern:** Die Umsetzung läuft in Maschinentempo, alles drumherum nicht. Der Gewinn entsteht erst, wenn sich die Phasen um den Code ändern.

**Sagen:**
- Proportionen sind illustrativ. Die gestrichelte Box gibt es nur, wenn Planung, Tests und Review mitwachsen.
- Frage: „Wo wartet Code bei Ihnen heute am längsten?“ Meist: Review, Abnahme, Freigabe. Darauf kommen wir im Risikoteil zurück.

**Falls gefragt:**
- Grundlage: Anthropics „AI-native SDLC Playbook“; den Review-Balken habe ich vergrößert, weil er in der Praxis das Problem ist.
-->

---

# Wie viel Verantwortung geben Sie ab?

<div class="lv mt-4">
  <div class="lv__h">Stufe</div><div class="lv__h">Die KI …</div><div class="lv__h">Das Team …</div><div class="lv__h"></div>

  <div class="lv__n">0</div><div><b>Autovervollständigung</b> · schlägt die nächste Zeile vor</div><div>tippt und nimmt jedes Zeichen an</div><div></div>
  <div class="lv__n">1</div><div><b>Praktikant</b> · erledigt klar umrissene Kleinaufgaben</div><div>prüft alles</div><div></div>
  <div class="lv__n lv--most">2</div><div class="lv--most"><b>Junior</b> · arbeitet im Editor mit</div><div class="lv--most">liest jede Zeile</div><div class="lv__tag lv__tag--most">hier stehen die meisten</div>
  <div class="lv__n lv--day">3</div><div class="lv--day"><b>Entwickler:in</b> · schreibt den Code</div><div class="lv--day">prüft Änderungen wie ein Reviewer</div><div class="lv__tag lv__tag--day" style="grid-row: span 2">heute in der Demo</div>
  <div class="lv__n lv--day">4</div><div class="lv--day"><b>Team</b> · baut nach Vorgabe</div><div class="lv--day">schreibt die Vorgabe, prüft Ergebnisse</div>
  <div class="lv__n">5</div><div><b>Dunkle Fabrik</b> · macht aus Vorgaben Software</div><div>niemand liest den Code</div><div></div>
</div>

<div class="mt-3 text-xs text-[var(--shi-fg-dim)]">
Nach Dan Shapiro, <em>The Five Levels: from Spicy Autocomplete to the Dark Factory</em> (Jan. 2026), angelehnt an die SAE-Stufen des autonomen Fahrens.
</div>

<style>
.lv { display: grid; grid-template-columns: 3.2rem 1.5fr 1.2fr 9rem; font-size: 0.86rem; align-items: stretch; }
.lv > div { padding: 0.36rem 0.6rem; border-bottom: 1px solid var(--shi-border); display: flex; align-items: center; gap: 0.3rem; }
.lv__h { font-size: 0.68rem; font-weight: 700; text-transform: uppercase; letter-spacing: 0.06em; color: var(--shi-fg-muted); border-bottom: 2px solid var(--shi-brand) !important; }
.lv__n { font-weight: 800; font-size: 1.1rem; color: var(--shi-brand); justify-content: center; }
.lv .lv--most { background: color-mix(in srgb, var(--shi-accent-amber) 12%, transparent); }
.lv .lv--day { background: color-mix(in srgb, var(--shi-brand) 12%, transparent); }
.lv__tag { font-size: 0.72rem; font-weight: 700; text-transform: uppercase; letter-spacing: 0.04em; justify-content: center; text-align: center; border-bottom: none !important; }
.lv__tag--most { color: var(--shi-accent-amber); }
.lv__tag--day { color: var(--shi-brand); border-left: 3px solid var(--shi-brand); }
</style>

<!--
⏱ 3 min

**Kern:** Autonomie lässt sich stufenweise hochregeln. Stufe 3 und 4 brauchen Vorgaben, Tests und Prüfpunkte. Das ist Organisationsarbeit.

**Sagen:**
- „Wo steht Ihr Team heute ehrlich?“ Antworten merken, am Ende kommen wir darauf zurück.
- Shapiro schätzt, dass rund 90 % der Teams, die sich „KI-nativ“ nennen, auf Stufe 2 stehen.
- Stufe 5 ist heute keine Empfehlung, sondern die Grenze des Denkbaren.

**Falls gefragt:**
- Die Idee des „Autonomiereglers“ stammt von Andrej Karpathy (Software 3.0, Juni 2025); die fünf Stufen von Dan Shapiro.
-->

---

# Der Entwicklungsprozess mit KI

<Flow class="mt-8" :items="[
  { label: 'Planen', icon: 'i-carbon-idea', hint: 'Intent: was und warum' },
  { label: 'Entwurf', icon: 'i-carbon-document', hint: 'Plan, Vorgaben' },
  { label: 'Umsetzen', icon: 'i-carbon-code', hint: 'Test zuerst' },
  { label: 'Testen', icon: 'i-carbon-test-tool', hint: 'automatische Prüfungen' },
  { label: 'Review', icon: 'i-carbon-view', hint: 'Mensch entscheidet' },
  { label: 'Betrieb', icon: 'i-carbon-chart-line', hint: 'ehrliche Kennzahlen' },
]" />

<div class="grid grid-cols-3 gap-4 mt-10 text-sm">
  <Card title="Planen & Entwurf" icon="i-carbon-user">Menschen legen in Dateien fest, was gebaut wird und warum.</Card>
  <Card title="Umsetzen & Testen" icon="i-carbon-bot">Der Agent arbeitet gegen Prüfungen, mit denen er nicht verhandeln kann.</Card>
  <Card title="Review & Betrieb" icon="i-carbon-checkmark-outline">Menschen geben Absicht und Risiko frei. Jede Zeile lesen sie nicht mehr.</Card>
</div>

<!--
⏱ 2 min

**Kern:** Jede Phase endet in einem Artefakt im Repository. Die Demo geht genau diese Kette entlang.

**Sagen:**
- Feature der Demo: eine Warteliste für ausgebuchte Seminare, quer durch Angular, REST-Schnittstelle und Spring Boot.
-->

---
layout: statement
---

Ein KI-Agent ist ein Modell plus alles drumherum. Das Drumherum gestalten Sie.

<!--
⏱ 1 min. Sagen, Pause, weiter.
-->

---

# Das Drumherum entscheidet

<Layers class="mt-2" :items="[
  { title: 'Berechtigungen', hint: 'was er ungefragt darf', items: [
    { label: 'Freigabemodi', icon: 'i-carbon-security' },
    { label: 'Erlauben / Verbieten', icon: 'i-carbon-rule' },
    { label: 'Sandbox', icon: 'i-carbon-container-software' },
  ] },
  { title: 'Rückkopplung', hint: 'wie er Fehler findet', tone: 'brand', items: [
    { label: 'Tests', icon: 'i-carbon-rule-test' },
    { label: 'Git-Hooks & CI', icon: 'i-carbon-flash' },
    { label: 'Plan-Freigabe', icon: 'i-carbon-decision-tree' },
  ] },
  { title: 'Werkzeuge', hint: 'was er erreichen kann', items: [
    { label: 'Dateien & Terminal', icon: 'i-carbon-terminal' },
    { label: 'Erweiterungen', icon: 'i-carbon-plug' },
    { label: 'MCP-Server', icon: 'i-carbon-api' },
  ] },
  { title: 'Kontext', hint: 'was er zuerst weiß', items: [
    { label: 'AGENTS.md', icon: 'i-carbon-document' },
    { label: 'Intent & Plan', icon: 'i-carbon-document-tasks' },
    { label: 'API-Vertrag', icon: 'i-carbon-data-base' },
  ] },
  { title: 'Modell', hint: 'wird eingekauft', tone: 'muted', items: ['Anbieter', 'Version', 'Preis'] },
]" />

<!--
⏱ 2 min

**Kern:** Vier von fünf Schichten sind Konfiguration im Repository, versioniert und für alle gleich. Das Modell ist die einzige Schicht, die Sie nur einkaufen.

**Sagen:**
- Die Werkzeugfrage (Copilot, Claude Code, Cursor …) ist die unwichtigste. Die Werkzeuge haben sich 2026 auf dieselben Bausteine geeinigt: Instruktionsdateien, Hooks, MCP, Freigabemodi.
- Berechtigungen: Entweder bestätigt man alles, bis keiner mehr liest. Oder man erlaubt alles und merkt es später.
-->

---

# Gleiches Modell, anderes Umfeld

<div class="grid grid-cols-2 gap-6 mt-6">

<div>

### Ohne Vorgaben

<Transcript tool="kein Kontext, keine Prüfung" :lines="[
  { kind: 'prompt', text: 'Baue eine Warteliste für volle Kurse' },
  { kind: 'tool', text: 'edit  backend/…/RegistrationDto.java' },
  { kind: 'tool', text: 'edit  frontend/…/course-list.html' },
  { kind: 'out', text: 'Fertig! Die Warteliste ist implementiert.' },
  { kind: 'note', text: 'Vertrag nicht angepasst. Kein Test gelaufen.' },
]" />

</div>

<div>

### Mit Vorgaben im Repository

<Transcript tool="AGENTS.md + Tests" :lines="[
  { kind: 'tool', text: 'read  AGENTS.md  (→ API-Änderungen beginnen im Vertrag)' },
  { kind: 'tool', text: 'edit  api/openapi.yaml' },
  { kind: 'tool', text: 'edit  backend/…  ·  frontend/…' },
  { kind: 'tool', text: 'run   ./mvnw test' },
  { kind: 'err', text: 'FAIL  Antwort enthält Feld, das der Vertrag nicht kennt' },
  { kind: 'tool', text: 'edit  backend/…/RegistrationDto.java' },
  { kind: 'ok', text: '9 Tests grün' },
]" />

</div>

</div>

<Callout type="tip" class="mt-5">
Gleiches Modell, gleiche Aufgabe. Rechts kommen eine Datei mit Vorgaben und ein Pflicht-Befehl dazu.
</Callout>

<!--
⏱ 2 min

**Kern:** Den Unterschied machen zwei Stücke Konfiguration.

**Sagen:**
- Links ist das „KI hat es probiert, war nutzlos“, das viele Teams erlebt haben.
- Rechts findet der Agent seinen eigenen Fehler, bevor ein Mensch ihn sieht. Genau das zeigt gleich die Demo.

**Falls gefragt:**
- Kosten: Rechts verbraucht pro Lauf mehr, ist aber billiger pro korrekt übernommener Änderung. Das ist die Kennzahl, die zählt.
-->
