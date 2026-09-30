---
# ─── Deck-Konfiguration ───────────────────────────────────────────────
theme: default
title: KI für Entwickler:innen
titleTemplate: '%s · SHI GmbH'
author: Daniel Sogl
favicon: /favicon.png
lang: de

# CI-Schriften (wie shi-gmbh.com: Open Sans 400/600/700).
# provider: none – die Fonts liegen als Paket im Repo und werden in
# styles/index.ts geladen. Nichts wird zur Laufzeit von Google geholt.
fonts:
  sans: Open Sans
  serif: Open Sans
  mono: JetBrains Mono
  provider: none

colorSchema: auto
aspectRatio: 16/9
canvasWidth: 980
lineNumbers: false
transition: slide-left
drawings:
  persist: false
download: false
exportFilename: ki-fuer-entwicklerinnen-angular-spring-boot
comark: true

seoMeta:
  ogTitle: KI für Entwickler:innen. Produktiver mit Angular und Spring Boot
  ogDescription: Impuls, Live-Demo und Diskussion für Führungskräfte
  twitterCard: summary_large_image

themeConfig:
  footer: KI für Entwickler:innen · Angular & Spring Boot
  event: Landwirtschaftskammer NRW · 2. Oktober 2026

info: |
  ## KI für Entwickler:innen
  Impuls mit Live-Demo für Führungskräfte. 120 Minuten, remote.

layout: cover
subtitle: Produktiver mit Angular und Spring Boot. Was KI im Entwicklungsalltag leistet und was Führung dafür regeln muss.
speaker: Daniel Sogl
role: im Auftrag von Angular Architects
event: Landwirtschaftskammer NRW
date: 2. Oktober 2026
partner: /aa-logo-white.svg
partnerAlt: Angular Architects
---

# KI für Entwickler:innen

<!--
Ablauf (120 min, Start 11:00, remote):
- 11:00 Einstieg (5)
- 11:05 Impuls: Der Engpass hat sich verschoben (12)
- 11:17 Live-Demo, 4 Stationen (35)
- 11:52 Risiken & Leitplanken (15)
- 12:07 Datenschutz & EU AI Act (10)
- 12:17 Was Führung jetzt entscheidet (8)
- 12:25 Diskussion (30), Feedback-Formular in den letzten 5 Minuten
- 12:55 Puffer bis 13:00

Vor dem Start:
- Backend (`./mvnw spring-boot:run`) und Frontend (`npm start`) laufen, Browser auf localhost:4200
- VS Code auf `demo/` geöffnet, Copilot Chat im Agent Mode, Branch `main`
- Terminal mit großer Schrift; Benachrichtigungen aus
- Fallback: Branches demo/2-plan, demo/3-umsetzung, demo/4-review
-->

---
layout: speaker
name: Daniel Sogl
role: SHI GmbH · Trainer bei Angular Architects
image: /speaker.jpg
linkedin: daniel-sogl
github: danielsogl
---

- Ich baue KI-Systeme und bringe sie in Produktion
- Seit zwei Jahren begleite ich Teams, die KI-Coding-Werkzeuge einführen
- Angular-Trainer bei Angular Architects, Schwerpunkt KI im Entwicklungsprozess
- Das meiste, was Sie heute sehen, habe ich gelernt, weil es erst schiefging

<div class="mt-4 flex gap-2">
  <Tag>AI Engineering</Tag>
  <Tag color="teal">Angular</Tag>
  <Tag color="violet">Developer Experience</Tag>
</div>

<!--
⏱ 1 min

Kurz halten. Zu Fehlergeschichten einladen: „Wenn Sie eigene haben, heben Sie sie für die Diskussion auf.“
-->

---

# Worum es heute geht

<CardGrid :cols="3" class="mt-8">
  <Card title="Was geht" icon="i-carbon-idea" variant="accent">
    Wo KI-Werkzeuge im Alltag eines Angular- und Spring-Boot-Teams heute tragen. Live, an einem
    durchgehenden Beispiel.
  </Card>
  <Card title="Was schiefgeht" icon="i-carbon-warning-alt" variant="accent">
    Plausibler, aber falscher Code und neue Sicherheitsrisiken. Und woran man beides erkennt.
  </Card>
  <Card title="Was Sie entscheiden" icon="i-carbon-decision-tree" variant="accent">
    Welche Leitplanken ein Team braucht, und welche davon nur Führung setzen kann.
  </Card>
</CardGrid>

<Callout type="info" title="Zum Mitnehmen" class="mt-8">
Folien als PDF und das komplette Demo-Repository bekommen Sie im Anschluss.
</Callout>

<!--
⏱ 2 min

**Frage in die Runde (Chat oder Handzeichen):** „Wer in Ihren Teams nutzt heute schon KI beim Programmieren, offiziell oder inoffiziell?“
Antwort merken, beim Thema Datenschutz („inoffiziell“ = private Accounts) darauf zurückkommen.
-->

---
layout: agenda
items:
  - label: Der Engpass hat sich verschoben
    hint: 12 min
  - label: Live-Demo
    hint: ein Feature, vier Stationen
  - label: Risiken & Leitplanken
    hint: 15 min
  - label: Datenschutz & EU AI Act
    hint: 10 min
  - label: Was Führung jetzt entscheidet
    hint: 8 min
  - label: Diskussion
    hint: 30 min
---

---
src: ./pages/01-impuls.md
---

---
src: ./pages/02-demo.md
---

---
src: ./pages/03-risiken.md
---

---
src: ./pages/04-datenschutz.md
---

---
src: ./pages/05-fuehrung.md
---

---
layout: outro
speaker: Daniel Sogl
role: SHI GmbH · Trainer bei Angular Architects
linkedin: daniel-sogl
partner: /aa-logo-white.svg
partnerAlt: Angular Architects
---

# Vielen Dank!

Folien und Demo-Repository kommen im Anschluss.

<!--
Das Feedback-Formular ist auf der Diskussionsfolie verlinkt. Vor dem Schluss sicherstellen, dass es ausgefüllt wurde.
-->
