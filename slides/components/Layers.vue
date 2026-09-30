<script setup lang="ts">
/**
 * Schichten-Diagramm für Architekturen (oben = nah am Nutzer).
 *
 * <Layers :items="[
 *   { title: 'Erlebnis', items: ['Chat-UI', 'Suchportal'] },
 *   { title: 'Modell', tone: 'brand', items: [{ label: 'LLM', icon: 'i-carbon-machine-learning-model' }] },
 * ]" />
 */
defineProps<{
  items: {
    title: string
    hint?: string
    tone?: 'brand' | 'muted'
    items: (string | { label: string; icon?: string })[]
  }[]
  /** Schichten nacheinander per Klick einblenden. */
  clicks?: boolean
}>()

const normalize = (item: string | { label: string; icon?: string }) =>
  typeof item === 'string' ? { label: item } : item
</script>

<template>
  <div class="shi-layers">
    <div
      v-for="(layer, i) in items"
      :key="i"
      v-click="clicks ? undefined : false"
      class="shi-layer"
      :class="layer.tone ? `shi-layer--${layer.tone}` : ''"
    >
      <div class="shi-layer__head">
        <div class="shi-layer__title">{{ layer.title }}</div>
        <div v-if="layer.hint" class="shi-layer__hint">{{ layer.hint }}</div>
      </div>
      <div class="shi-layer__blocks">
        <div v-for="(item, j) in layer.items" :key="j" class="shi-layer__block">
          <div v-if="normalize(item).icon" class="shi-layer__icon" :class="normalize(item).icon" />
          {{ normalize(item).label }}
        </div>
      </div>
    </div>
  </div>
</template>
