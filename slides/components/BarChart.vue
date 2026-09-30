<script setup lang="ts">
import { computed } from 'vue'

/**
 * Balkendiagramm für Studienzahlen – waagerecht, weil die Beschriftungen der
 * eigentliche Inhalt sind und senkrecht gekippte Achsentexte auf einer Folie
 * niemand liest.
 *
 * Die Achse enthält immer die Null. Bei negativen Werten wird eine Nulllinie
 * gezeichnet und die Balken wachsen nach beiden Seiten – genau das ist die
 * Aussage der METR-Folie: vier Prognosen nach rechts, die Messung nach links.
 *
 * <BarChart :items="[
 *   { label: 'Prognose', value: 24 },
 *   { label: 'Messung', value: -19, tone: 'danger' },
 * ]" caption="Quelle …" />
 *
 * `variant="point"` zeichnet statt der Balken Punktschätzer mit
 * Konfidenzintervall (Items brauchen dann `lo`/`hi`) – für Ergebnisse, deren
 * Unsicherheit die halbe Aussage ist.
 */
type Tone = 'brand' | 'navy' | 'danger' | 'green' | 'amber' | 'teal' | 'violet' | 'muted'

interface Item {
  label: string
  value: number
  /** Untere/obere Grenze des Konfidenzintervalls. */
  lo?: number
  hi?: number
  tone?: Tone
  /** Zweite Zeile unter der Beschriftung, z. B. Stichprobengröße. */
  hint?: string
}

const props = withDefaults(
  defineProps<{
    items: Item[]
    /** Einheit hinter dem Zahlenwert. */
    unit?: string
    /** Vorzeichen auch bei positiven Werten zeigen. */
    signed?: boolean
    /** Nachkommastellen erzwingen – sonst so viele, wie im Datenwert stehen. */
    decimals?: number
    /** Achsengrenzen erzwingen; sonst aus den Daten inklusive Null. */
    min?: number
    max?: number
    variant?: 'bar' | 'point'
    /** Quellenangabe unter dem Diagramm. Gehört dazu, nicht dekorativ. */
    caption?: string
    /** Balken nacheinander einblenden. */
    clicks?: boolean
  }>(),
  { unit: '%', variant: 'bar' },
)

const TONES: Record<Tone, string> = {
  brand: 'var(--shi-brand)',
  navy: 'var(--shi-navy)',
  danger: 'var(--shi-danger)',
  green: 'var(--shi-accent-green)',
  amber: 'var(--shi-accent-amber)',
  teal: 'var(--shi-accent-teal)',
  violet: 'var(--shi-accent-violet)',
  muted: 'var(--shi-fg-dim)',
}

const bounds = computed(() => {
  const values = props.items.flatMap((i) => [i.value, i.lo, i.hi].filter((v): v is number => v != null))
  // Die Null muss in der Achse liegen, sonst lügt die Balkenlänge über das
  // Verhältnis zweier Werte.
  const lo = props.min ?? Math.min(0, ...values)
  const hi = props.max ?? Math.max(0, ...values)
  return { lo, hi, span: hi - lo || 1 }
})

/** Wert → Position in Prozent der Achsenbreite. */
function pos(value: number) {
  const { lo, span } = bounds.value
  return ((value - lo) / span) * 100
}

const zero = computed(() => pos(0))
const hasNegative = computed(() => bounds.value.lo < 0)

function barStyle(item: Item) {
  const at = pos(item.value)
  return {
    left: `${Math.min(at, zero.value)}%`,
    width: `${Math.abs(at - zero.value)}%`,
    background: TONES[item.tone ?? 'brand'],
  }
}

function rangeStyle(item: Item) {
  const from = pos(item.lo ?? item.value)
  const to = pos(item.hi ?? item.value)
  return { left: `${from}%`, width: `${to - from}%` }
}

function format(value: number) {
  const sign = value > 0 && props.signed ? '+' : ''
  // Echtes Minuszeichen statt Bindestrich, und geschütztes Leerzeichen vor der
  // Einheit – sonst rutscht das Prozentzeichen in die nächste Zeile.
  const minus = value < 0 ? '−' : ''
  const body = Math.abs(value)
  // Nachkommastelle nur, wo sie in den Daten steht – 43,13 % ist eine
  // Messung, 24 % ist eine Prognose, und der Unterschied soll sichtbar sein.
  const digits =
    props.decimals ??
    (Number.isInteger(value) ? 0 : Math.min(2, String(value).split('.')[1]?.length ?? 0))
  return `${sign}${minus}${body.toFixed(digits)} ${props.unit}`
}
</script>

<template>
  <div class="shi-bars" :class="{ 'shi-bars--signed': hasNegative }">
    <div
      v-for="(item, i) in items"
      :key="i"
      v-click="clicks ? undefined : false"
      class="shi-bars__row"
    >
      <div class="shi-bars__label">
        {{ item.label }}
        <span v-if="item.hint" class="shi-bars__hint">{{ item.hint }}</span>
      </div>

      <div class="shi-bars__track">
        <div v-if="hasNegative" class="shi-bars__zero" :style="{ left: `${zero}%` }" />

        <template v-if="variant === 'bar'">
          <div class="shi-bars__bar" :style="barStyle(item)" />
        </template>

        <template v-else>
          <div v-if="item.lo != null" class="shi-bars__range" :style="rangeStyle(item)" />
          <div
            class="shi-bars__dot"
            :style="{ left: `${pos(item.value)}%`, background: TONES[item.tone ?? 'brand'] }"
          />
        </template>
      </div>

      <div class="shi-bars__value">
        {{ format(item.value) }}
        <span v-if="item.lo != null && item.hi != null" class="shi-bars__ci">
          {{ format(item.lo) }} … {{ format(item.hi) }}
        </span>
      </div>
    </div>

    <div v-if="caption" class="shi-bars__caption">{{ caption }}</div>
  </div>
</template>
