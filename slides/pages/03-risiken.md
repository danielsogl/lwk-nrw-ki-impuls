---
layout: section
number: 3
subtitle: Mehr Tempo verstärkt, was an einem Prozess stimmt. Und alles, was nicht stimmt.
---

# Risiken & Leitplanken

---

# Wo KI-Code wartet

<div class="prw mt-6">
  <div class="prw__metric">
    <div class="prw__value">4,6×</div>
    <div class="prw__label">länger bis zum ersten Review</div>
    <div class="prw__bar"><span class="prw__tag">übrige PRs</span><i style="width: 21.7%" /></div>
    <div class="prw__bar prw__bar--ai"><span class="prw__tag">KI-generiert</span><i style="width: 100%" /></div>
  </div>
  <div class="prw__metric">
    <div class="prw__value">2,5×</div>
    <div class="prw__label">größere Pull Requests</div>
    <div class="prw__bar"><span class="prw__tag">übrige PRs</span><i style="width: 40%" /></div>
    <div class="prw__bar prw__bar--ai"><span class="prw__tag">KI-unterstützt</span><i style="width: 100%" /></div>
  </div>
  <div class="prw__metric">
    <div class="prw__value">32,7 %</div>
    <div class="prw__label">werden übernommen</div>
    <div class="prw__bar"><span class="prw__tag">übrige PRs · 84,4 %</span><i style="width: 84.4%" /></div>
    <div class="prw__bar prw__bar--ai"><span class="prw__tag">KI-generiert</span><i style="width: 32.7%" /></div>
  </div>
</div>

<div class="text-xs mt-2 text-[var(--shi-fg-dim)]">LinearB 2026, Herstellerdaten</div>

<style>
.prw { display: grid; grid-template-columns: repeat(3, 1fr); gap: 1.75rem; }
.prw__metric { display: flex; flex-direction: column; gap: 0.35rem; }
.prw__value { font-size: 2.6rem; font-weight: 700; line-height: 1; color: var(--shi-brand); font-variant-numeric: tabular-nums; }
.prw__label { font-weight: 600; font-size: 0.95rem; margin-bottom: 0.3rem; }
.prw__bar { position: relative; height: 1.5rem; background: var(--shi-surface); border-radius: 4px; overflow: hidden; }
.prw__bar i { position: absolute; inset: 0 auto 0 0; background: var(--shi-fg-dim); opacity: 0.35; }
.prw__bar--ai i { background: var(--shi-accent-coral); opacity: 1; }
.prw__tag { position: relative; z-index: 1; font-size: 0.72rem; font-weight: 600; line-height: 1.5rem; padding-left: 0.5rem; color: var(--shi-fg); }
.prw__bar--ai .prw__tag { color: var(--shi-bg); }
</style>

<div class="grid grid-cols-2 gap-6 mt-6">

<Callout type="danger" title="Die Asymmetrie">
Plausiblen Code zu erzeugen kostet fast nichts. Ihn zu widerlegen kostet die Zeit einer
Kollegin, die ihn nicht geschrieben hat.
</Callout>

<Callout type="tip" title="Was daraus folgt">
Reviewer:innen <strong>weniger</strong> zu lesen geben: kleine Änderungen, ein Intent zum
Abgleich, ein Agent für den ersten Durchgang.
</Callout>

</div>

<!--
⏱ 3 min

**Kern:** KI-Änderungen sind billig erzeugt und teuer geprüft. Das Review ist der Engpass, und das Review machen Ihre erfahrensten Leute.

**Sagen:**
- VOLATIL: LinearB 2026, 8,1 Mio. Pull Requests, Herstellerdaten, KI-Erkennung nicht offengelegt. Einmal angefasst, werden sie schneller reviewt. Das Problem ist das Warten.
- Frage: „Wer trägt bei Ihnen die Review-Last? Und steht die in irgendeiner Planung?“
-->

---

# Warum Schrott teuer ist

curl, eines der meistgenutzten Open-Source-Werkzeuge der Welt, hat im Januar 2026 sein
Bug-Bounty-Programm beendet: Echte Meldungen waren von plausiblen nicht mehr zu unterscheiden.

<BarChart
  class="mt-6"
  unit="%"
  style="--shi-bars-label: 17rem"
  :items="[
    { label: 'Bestätigte Schwachstellen, vor 2025', value: 15, tone: 'muted' },
    { label: 'Bestätigte Schwachstellen, ab 2025', value: 5, tone: 'danger' },
  ]"
  caption="curl bei HackerOne, April 2019 bis Januar 2026: 87 bestätigte Schwachstellen, über 100.000 $ ausgezahlt. Daniel Stenberg, daniel.haxx.se, 26.01.2026."
/>

<Callout type="tip" title="Übertragen auf Ihre Teams" class="mt-6">
Jede ungeprüfte KI-Ausgabe ist eine Behauptung, die jemand anderes prüfen muss. Die Kosten
landen später bei einem Kollegen, und sie übersteigen die gesparte Zeit.
</Callout>

<!--
⏱ 2 min

VOLATIL: daniel.haxx.se/blog/2026/01/26/the-end-of-the-curl-bug-bounty/. Quote bestätigter Meldungen fiel von „deutlich über 15 %“ auf „unter 5 %“, also nicht auf null. Stenberg: „explosion in AI slop reports“, „serious mental toll“. Seit 1. März 2026 wieder bei HackerOne, ohne Prämien.

**Frage stehen lassen:** „Aus wessen Zeit kommt der Geschwindigkeitsgewinn eigentlich?“
-->

---

# Pakete, die es nicht gibt

Modelle erfinden Namen von Bibliotheken, und oft immer wieder **dieselben**. Angreifer
registrieren diese Namen und warten.

<div class="grid grid-cols-[1.3fr_1fr] gap-9 mt-3">

<div>

<BarChart
  style="--shi-bars-label: 12rem"
  :decimals="1"
  :items="[
    { label: 'Offene Modelle', value: 21.7, tone: 'danger' },
    { label: 'Kommerzielle Modelle', value: 5.2, tone: 'amber' },
    { label: 'Alle 16 Modelle', value: 19.7, tone: 'muted' },
  ]"
  caption="Anteil empfohlener Paketnamen, die in npm oder PyPI nicht existieren. 576.000 Codebeispiele, 16 Modelle. Spracklen et al., USENIX Security 2025."
/>

</div>

<div>

<Stat value="43 %" label="kamen jedes Mal wieder" hint="der erfundenen Namen erschienen in allen zehn Wiederholungen derselben Anfrage" />

<Callout type="danger" title="Kein Angriff nötig" class="mt-3">
Der Agent installiert das Paket selbst, weil er glaubt, dass es existiert.
</Callout>

</div>

</div>

<Callout type="tip" title="Günstige Gegenmittel" class="mt-1">
Lockfiles. Kein unbeaufsichtigtes Installieren durch Agenten. Jede neue Abhängigkeit ins Review, wie in Station 4.
</Callout>

<!--
⏱ 2 min

**Kern:** Ein Lieferkettenrisiko, das über den eigenen Agenten ins Haus kommt. Es läuft an allem vorbei, was Sie gegen fremde Abhängigkeiten aufgebaut haben.

VOLATIL: usenix.org/system/files/usenixsecurity25-spracklen.pdf. 205.474 verschiedene erfundene Namen; 43 % erschienen in allen zehn Wiederholungen, 58 % in mehr als einer. Replikation 2026 (Preprint, arxiv.org/abs/2605.17062): fünf neuere Modelle, 4,6–6,1 % erfundene Namen, 127 Namen bei allen fünf identisch, 53 davon nach Veröffentlichung noch registrierbar.

Für Java gilt dasselbe wie für npm: Maven Central ist genauso betroffen, sobald jemand den Namen registriert.
-->

---

# Die „Lethal Trifecta“

<div class="tri mt-2">

<figure class="tri__venn">
<svg viewBox="0 0 300 270" role="img" aria-label="Venn-Diagramm: private Daten, fremde Inhalte und ein Weg nach draußen überschneiden sich im Angriff">
  <circle cx="150" cy="95" r="80" class="tri__c tri__c--a" />
  <circle cx="100" cy="180" r="80" class="tri__c tri__c--b" />
  <circle cx="200" cy="180" r="80" class="tri__c tri__c--c" />
  <text x="150" y="62" class="tri__l">Private</text><text x="150" y="80" class="tri__l">Daten</text>
  <text x="68" y="205" class="tri__l">Fremde</text><text x="68" y="223" class="tri__l">Inhalte</text>
  <text x="232" y="205" class="tri__l">Weg nach</text><text x="232" y="223" class="tri__l">draußen</text>
  <circle cx="150" cy="152" r="17" class="tri__hit" />
  <text x="150" y="157" class="tri__x">!</text>
</svg>
<figcaption>Nach Simon Willison, „The lethal trifecta“, Juni 2025</figcaption>
</figure>

<div class="tri__list">
  <div class="tri__row tri__row--a">
    <div class="tri__h"><span class="i-carbon-data-base" /> Private Daten</div>
    <div class="tri__what">Quellcode, Zugangsdaten, <code>.env</code>, interne Systeme.</div>
    <div class="tri__cut"><span class="i-carbon-cut inline-block align-[-2px]" /> Kappen: <code>.env*</code> sperren, ein Token pro Aufgabe mit minimalen Rechten</div>
  </div>
  <div class="tri__row tri__row--b">
    <div class="tri__h"><span class="i-carbon-warning-alt" /> Fremde Inhalte</div>
    <div class="tri__what">Ein Ticket, eine Webseite, <strong>ein Pull Request</strong>, eine Erweiterung.</div>
    <div class="tri__cut"><span class="i-carbon-cut inline-block align-[-2px]" /> Kappen: Erweiterungen und MCP-Server vor der Freigabe prüfen</div>
  </div>
  <div class="tri__row tri__row--c">
    <div class="tri__h"><span class="i-carbon-send-alt" /> Weg nach draußen</div>
    <div class="tri__what">Ein Netzwerkaufruf, ein Push, ein Kommentar.</div>
    <div class="tri__cut"><span class="i-carbon-cut inline-block align-[-2px]" /> Kappen: Sandbox mit Freigabeliste für das Netzwerk</div>
  </div>
</div>

</div>

<style>
.tri { display: grid; grid-template-columns: 17rem 1fr; gap: 2rem; align-items: center; }
.tri__venn { margin: 0; }
.tri__venn svg { width: 100%; }
.tri__venn figcaption { font-size: 0.68rem; font-style: italic; color: var(--shi-fg-dim); text-align: center; }
.tri__c { fill-opacity: 0.22; stroke-width: 2; }
.tri__c--a { fill: var(--shi-brand); stroke: var(--shi-brand); }
.tri__c--b { fill: var(--shi-accent-amber); stroke: var(--shi-accent-amber); }
.tri__c--c { fill: var(--shi-accent-violet); stroke: var(--shi-accent-violet); }
.tri__l { text-anchor: middle; font-size: 14px; font-weight: 700; fill: var(--shi-fg); }
.tri__hit { fill: var(--shi-accent-coral); }
.tri__x { text-anchor: middle; font-size: 16px; font-weight: 800; fill: var(--shi-bg); }
.tri__list { display: flex; flex-direction: column; gap: 0.6rem; }
.tri__row { padding: 0.5rem 0.8rem; border-left: 4px solid var(--c); background: color-mix(in srgb, var(--c) 8%, transparent); border-radius: var(--shi-radius); font-size: 0.85rem; }
.tri__row--a { --c: var(--shi-brand); }
.tri__row--b { --c: var(--shi-accent-amber); }
.tri__row--c { --c: var(--shi-accent-violet); }
.tri__h { display: flex; align-items: center; gap: 0.4rem; font-weight: 700; font-size: 0.95rem; }
.tri__what { color: var(--shi-fg-muted); }
.tri__cut { margin-top: 0.15rem; font-weight: 600; color: var(--shi-accent-green); }
</style>

<Callout type="danger" title="Der Angriff braucht alle drei. Eins zu entfernen reicht." class="mt-4">
Kein Update macht ein Modell immun gegen Überredung. Der Agent, der fremde Inhalte liest,
darf nichts zu stehlen haben oder keinen Weg, es zu verschicken.
</Callout>

<!--
⏱ 3 min

**Kern:** Das ist eine Architekturfrage, keine Modellfrage. Und damit eine Frage der Berechtigungen, die jemand vergibt.

**Sagen (zwei echte Fälle):**
- VOLATIL: CVE-2025-53773. Versteckte Anweisungen in öffentlichem Code brachten GitHub Copilot in VS Code dazu, in die eigene .vscode/settings.json „chat.tools.autoApprove: true“ zu schreiben und danach Befehle ohne Rückfrage auszuführen. Gepatcht im August 2025; das Muster gilt für jede Konfiguration, die der Agent selbst schreiben kann.
- VOLATIL: Invariant Labs, Mai 2025. Ein präpariertes GitHub-Issue brachte einen Agenten mit zu weit gefasstem Token dazu, Inhalte eines privaten Repositorys in einen öffentlichen Pull Request zu kopieren. Kein CVE: Jeder einzelne Aufruf war erlaubt. Abhilfe: Token nur für das eine Repository.
-->

---

# Gates, an die niemand denken muss

<div class="gates mt-4">

<svg class="gates__rings" viewBox="0 0 240 240" role="img" aria-label="Drei Ringe um den Agenten: Berechtigungen, Hooks, Branch-Schutz">
  <circle cx="120" cy="120" r="116" class="gates__r3" />
  <circle cx="120" cy="120" r="84" class="gates__r2" />
  <circle cx="120" cy="120" r="52" class="gates__r1" />
  <circle cx="120" cy="120" r="24" class="gates__core" />
  <text x="120" y="124" class="gates__t gates__t--core">Agent</text>
  <text x="120" y="84" class="gates__t">1</text>
  <text x="120" y="52" class="gates__t">2</text>
  <text x="120" y="20" class="gates__t">3</text>
</svg>

<div class="gates__list">
  <div class="gates__item">
    <div class="gates__num">1</div>
    <div><div class="gates__title"><span class="i-carbon-locked" /> Berechtigungen <em>im Werkzeug</em></div>
    Erlauben, nachfragen oder verbieten, je Werkzeug und Pfad. Zentral per Organisations-Policy
    gesetzt, lokal nicht lockerbar.</div>
  </div>
  <div class="gates__item">
    <div class="gates__num">2</div>
    <div><div class="gates__title"><span class="i-carbon-flash" /> Hooks <em>bei jedem Commit</em></div>
    Laufen immer gleich und lassen nicht mit sich reden. In der Demo: Lint und Tests vor jedem Commit, für
    Menschen und Agenten gleich.</div>
  </div>
  <div class="gates__item">
    <div class="gates__num">3</div>
    <div><div class="gates__title"><span class="i-carbon-branch" /> Branch-Schutz <em>auf dem Server</em></div>
    Pflicht-Prüfungen in der CI, Pflicht-Freigabe durch einen Menschen, Code Owners für Tests
    und CI-Konfiguration. Kein lokaler Agent kommt daran vorbei.</div>
  </div>
</div>

</div>

<style>
.gates { display: grid; grid-template-columns: 13rem 1fr; gap: 2rem; align-items: center; }
.gates__rings { width: 100%; }
.gates__r3 { fill: var(--shi-card-alt); stroke: var(--shi-brand); stroke-width: 1.5; stroke-dasharray: 4 3; }
.gates__r2 { fill: color-mix(in srgb, var(--shi-brand) 14%, var(--shi-bg)); stroke: var(--shi-brand); stroke-width: 1.5; }
.gates__r1 { fill: color-mix(in srgb, var(--shi-brand) 28%, var(--shi-bg)); stroke: var(--shi-brand); stroke-width: 1.5; }
.gates__core { fill: var(--shi-navy); }
.gates__t { text-anchor: middle; font-size: 15px; font-weight: 700; fill: var(--shi-brand-text); }
.gates__t--core { fill: #fff; font-size: 11px; }
.gates__list { display: flex; flex-direction: column; gap: 0.8rem; font-size: 0.85rem; color: var(--shi-fg-muted); }
.gates__item { display: flex; gap: 0.8rem; }
.gates__num { flex-shrink: 0; width: 1.6rem; height: 1.6rem; border-radius: 50%; display: grid; place-items: center; font-weight: 700; font-size: 0.8rem; background: var(--shi-brand); color: var(--shi-on-brand); }
.gates__title { display: flex; align-items: center; gap: 0.4rem; font-weight: 700; font-size: 0.95rem; color: var(--shi-fg); }
.gates__title em { font-weight: 400; font-size: 0.78rem; color: var(--shi-fg-dim); }
</style>

<Callout type="danger" title="Regeln außer Reichweite des Agenten" class="mt-5">
Einstellungen, Hooks und CI-Konfiguration gehören dorthin, wo der Agent nicht schreiben kann –
oder hinter eine menschliche Freigabe. In Station 4 hat der Agent die Hooks umgangen; die CI nicht.
</Callout>

<!--
⏱ 3 min

**Kern:** Gates, an die niemand denken muss, sortiert nach Abstand zum Agenten. Je weiter außen, desto weniger kann er daran ändern.

**Sagen:**
- Ring 1 ist eine Einkaufs- und Richtlinienfrage: Copilot Business/Enterprise erlaubt Policies auf Organisationsebene (Modelle, Agent-Funktionen, MCP-Server).
- Ring 3 ist eine Einstellung im Git-Server, die heute schon existiert und oft nicht genutzt wird.
-->

---

# Juniors und Seniors scheitern unterschiedlich

<div class="grid grid-cols-2 gap-6 mt-5">
  <Card title="Juniors" icon="i-carbon-user" variant="accent">
    Übernehmen eine plausible Änderung, weil sie plausibel und richtig in dieser Codebasis
    noch nicht unterscheiden können. Die Sicherheit des Agenten wirkt wie Kompetenz.
  </Card>
  <Card title="Seniors" icon="i-carbon-user-multiple" variant="accent">
    Überfliegen das Review, weil die Änderung aussieht wie etwas, das sie selbst geschrieben
    hätten. Das Problem ist Vertrautheit.
  </Card>
</div>

<Callout type="tip" title="Das Gegenmittel ist alt: Pairing" class="mt-3">
Junior und Senior besprechen, <strong>warum</strong> der Agent einen Weg gewählt hat, und
schauen erst danach auf den Diff. So entsteht das Urteilsvermögen, das früher beim Selberschreiben nebenbei entstand.
</Callout>

<Callout type="danger" title="Eine kontrollierte Studie, keine Umfrage" class="mt-2">
52 überwiegend junge Entwickler:innen: Wer eine Aufgabe mit KI gelöst hatte, erreichte danach
im Wissenstest ohne KI 50 %, die Gruppe ohne KI 67 %. Am größten war die Lücke beim Debugging.
</Callout>

<!--
⏱ 2 min

**Kern:** Beide Fehlerbilder gleich gewichten. Das Übervertrauen der Seniors ist mindestens so teuer, weil niemand einen Senior gegenprüft.

VOLATIL: Anthropic, kontrollierte Studie, n = 52, 29.01.2026, anthropic.com/research/AI-assistance-coding-skills. 50 % vs. 67 % (d = 0,738, p = 0,01), Lücke am größten bei Debugging-Fragen. KI-Gruppe etwa 2 Minuten schneller, nicht signifikant. Grenzen: kleine Stichprobe, misst direkte Erinnerung, nicht dauerhafte Fähigkeit.

Für Führung: Ausbildung und Nachwuchsentwicklung brauchen einen bewussten Plan. Sonst wächst eine Generation, die Code abnicken, aber nicht debuggen kann.
-->

---
layout: statement
---

KI ist ein Verstärker. Sie macht ein gutes System besser und ein schwaches schlechter.

<!--
⏱ 30 s. Überleitung: „Jetzt zu den Leitplanken, die das Haus setzen muss.“
-->
