<script setup lang="ts">
/**
 * Ablaufdiagramm als Kette aus Knoten – für Pipelines, Prozesse, Agenten-Loops.
 * Bewusst kein Mermaid: rendert in CI-Farben, skaliert mit der Folie und lässt
 * sich Schritt für Schritt einblenden.
 *
 * <Flow :items="[
 *   { label: 'Frage', icon: 'i-carbon-chat' },
 *   { label: 'Retrieval', icon: 'i-carbon-search', hint: 'Top-k' },
 * ]" clicks />
 *
 * `direction="column"` stapelt untereinander, `loop="Beschriftung"` zeichnet
 * die Rückführung vom letzten zum ersten Knoten (Agenten-Schleife).
 */
withDefaults(
  defineProps<{
    items: { label: string; icon?: string; hint?: string; tone?: 'brand' | 'muted' }[]
    direction?: 'row' | 'column'
    /** Rückführung vom letzten zum ersten Knoten, Text steht an der Linie. */
    loop?: string
    /** Knoten nacheinander per Klick einblenden. */
    clicks?: boolean
  }>(),
  { direction: 'row' },
)
</script>

<template>
  <div class="shi-flow-wrap">
    <div class="shi-flow" :class="`shi-flow--${direction}`">
      <div
        v-for="(item, i) in items"
        :key="i"
        v-click="clicks ? undefined : false"
        class="shi-flow__node"
        :class="item.tone ? `shi-flow__node--${item.tone}` : ''"
      >
        <div v-if="item.icon" class="shi-flow__icon" :class="item.icon" />
        <div class="shi-flow__label">{{ item.label }}</div>
        <div v-if="item.hint" class="shi-flow__hint">{{ item.hint }}</div>
      </div>
    </div>
    <div v-if="loop" class="shi-flow__loop"><span>{{ loop }}</span></div>
  </div>
</template>
