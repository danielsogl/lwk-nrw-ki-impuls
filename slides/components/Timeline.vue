<script setup lang="ts">
/**
 * Zeitstrahl für Projekt-/Firmenhistorie oder Roadmaps.
 * `tone: 'muted'` nimmt einen Eintrag optisch zurück (z. B. Zukunft) –
 * dieselbe Schreibweise wie bei <Flow> und <Layers>.
 *
 * Die Verbindungslinie gehört zum jeweiligen Eintrag und richtet sich nach dem
 * *folgenden* Punkt: Sie führt zu ihm hin, also ist sie grau, sobald er noch
 * aussteht – erledigte Abschnitte bleiben in Markenfarbe.
 */
const props = defineProps<{
  items: { when: string; title: string; body?: string; tone?: 'muted' }[]
  /** Einträge nacheinander per Klick einblenden. */
  clicks?: boolean
}>()

const line = (i: number) => {
  if (i === props.items.length - 1)
    return 'none'
  return props.items[i + 1].tone === 'muted' ? 'muted' : 'done'
}
</script>

<template>
  <div class="shi-timeline">
    <div
      v-for="(item, i) in items"
      :key="i"
      v-click="clicks ? undefined : false"
      class="shi-timeline__item"
      :data-tone="item.tone"
      :data-line="line(i)"
    >
      <div class="shi-timeline__when">{{ item.when }}</div>
      <div class="shi-timeline__title">{{ item.title }}</div>
      <div v-if="item.body" class="shi-timeline__body">{{ item.body }}</div>
    </div>
  </div>
</template>
