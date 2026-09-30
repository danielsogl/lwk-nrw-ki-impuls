import { defineMermaidRendererSetup } from '@slidev/types'
import mermaid from 'mermaid/dist/mermaid.esm.mjs'
import { nextTick } from 'vue'

/**
 * Mermaid im SHI-Design – Light und Dark.
 *
 * Warum ein eigener Renderer statt `defineMermaidSetup`: Slidev sammelt die
 * Setup-Rückgaben mit `Object.assign` ein und cacht das Ergebnis in einem
 * `createSingletonPromise`. Beides wertet Getter genau einmal aus, die Palette
 * fröre also beim ersten gerenderten Diagramm ein – wer danach umschaltet,
 * bekäme ein dunkles Diagramm auf heller Folie. Dieser Hook läuft dagegen bei
 * jedem Rendern, und Slidev cacht das Ergebnis pro Farbschema (der Cache-Key
 * enthält `options.theme`).
 *
 * `look: 'neo'` ist der Hebel gegen den "sieht nach Wiki aus"-Eindruck: weichere
 * Kanten, ruhigere Linien, größere Innenabstände. Dazu mehr Luft zwischen den
 * Knoten – auf einer Folie ist Weißraum wichtiger als Kompaktheit.
 *
 * ponytail: bewusst `layout: 'dagre'` statt ELK. Seit Mermaid 12 ist ELK
 * gebündelt und Standard; es bringt erst bei großen Graphen etwas – die gehören
 * ohnehin nicht auf eine Folie – und verändert das Bild bestehender Decks.
 * Kleine Abläufe sehen mit <Flow> oder <Layers> besser aus; Mermaid ist für
 * Sequenzen, Zustände und Gantt da.
 */

/**
 * Mermaid-Variable → Token aus styles/_tokens.css. Hell/Dunkel entscheidet das
 * Stylesheet (`html.dark`), hier stehen keine Farbwerte.
 */
const PALETTE = {
  primaryColor: '--shi-card-alt',
  primaryTextColor: '--shi-fg',
  primaryBorderColor: '--shi-brand',
  secondaryColor: '--shi-diagram-fill',
  secondaryBorderColor: '--shi-diagram-stroke',
  tertiaryColor: '--shi-surface',
  tertiaryBorderColor: '--shi-border',
  textColor: '--shi-fg',
  lineColor: '--shi-fg-muted',
  noteBkgColor: '--shi-diagram-note',
  noteTextColor: '--shi-diagram-note-text',
  noteBorderColor: '--shi-diagram-note-border',
  actorBkg: '--shi-diagram-fill',
  actorBorder: '--shi-diagram-stroke',
  actorTextColor: '--shi-fg',
  actorLineColor: '--shi-diagram-lifeline',
  signalColor: '--shi-fg-muted',
  signalTextColor: '--shi-fg',
  labelBoxBkgColor: '--shi-diagram-label',
  labelBoxBorderColor: '--shi-brand',
  labelTextColor: '--shi-fg',
  loopTextColor: '--shi-diagram-loop-text',
  activationBkgColor: '--shi-diagram-activation',
  activationBorderColor: '--shi-diagram-stroke',
  // Ziffer in den autonumber-Kreisen. Ohne das leitet Mermaid sie aus
  // `invert(lineColor)` ab – ein Beige, das auf dem schwarzen Kreis kaum
  // lesbar ist. Der Kreis selbst hat keine Klasse, den holt das themeCSS.
  sequenceNumberColor: '--shi-on-brand',
}

export default defineMermaidRendererSetup(() => {
  let counter = 0

  return async (code, options) => {
    // `theme` aus den Options verwerfen: Slidev setzt darin nur 'dark' bzw.
    // undefined, das würde unsere Palette überschreiben. Alles andere
    // (z. B. `scale` am Codeblock) bleibt.
    const { theme: _slidevTheme, ...rest } = options

    // Slidev rendert aus einem watchEffect heraus, VueUse setzt `html.dark`
    // aber erst mit flush: 'post'. Ohne diesen Tick läse ein Umschalten noch
    // die Tokens des alten Modus.
    await nextTick()
    const css = getComputedStyle(document.documentElement)
    const token = (name: string) => css.getPropertyValue(name).trim()

    mermaid.initialize({
      startOnLoad: false,
      ...rest,
      theme: 'base',
      look: 'neo',
      layout: 'dagre',
      themeVariables: {
        fontFamily: '"Open Sans", sans-serif',
        fontSize: '15px',
        ...Object.fromEntries(Object.entries(PALETTE).map(([key, name]) => [key, token(name)])),
      },
      // Zwei Stellen, an die keine Theme-Variable heranreicht: der Kreis hinter
      // der autonumber-Ziffer (ein <marker><circle> ohne Klasse, deshalb per
      // Default schwarz) und die dort fest verdrahtete sans-serif.
      themeCSS: `
        marker[id$='-sequencenumber'] circle { fill: ${token('--shi-diagram-stroke')}; }
        text.sequenceNumber { font-family: "Open Sans", sans-serif; font-weight: 600; }
      `,
      flowchart: {
        curve: 'basis',
        nodeSpacing: 45,
        rankSpacing: 55,
        padding: 12,
      },
      sequence: {
        actorMargin: 60,
        boxMargin: 12,
        mirrorActors: false,
      },
    })

    const container = document.getElementById('mermaid-rendering-container') ?? undefined
    const { svg } = await mermaid.render(`shi-mermaid-${counter++}`, code, container)
    return svg
  }
})
