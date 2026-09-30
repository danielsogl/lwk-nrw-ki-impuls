<script setup lang="ts">
/**
 * Nummerierter Ablauf (Vorgehen, Projektphasen, Architekturschritte).
 *
 * <Steps :items="[
 *   { title: 'Analyse', body: 'Daten & Anforderungen' },
 *   { title: 'Prototyp' },
 * ]" />
 *
 * `direction="row"` stellt die Schritte nebeneinander, `clicks` blendet sie
 * einzeln ein.
 */
withDefaults(
  defineProps<{
    items: { title: string; body?: string }[]
    direction?: 'column' | 'row'
    start?: number
    /** Schritte nacheinander per Klick einblenden. */
    clicks?: boolean
  }>(),
  { direction: 'column', start: 1 },
)
</script>

<template>
  <div class="shi-steps" :class="{ 'shi-steps--row': direction === 'row' }">
    <div
      v-for="(step, i) in items"
      :key="i"
      v-click="clicks ? undefined : false"
      class="shi-step"
    >
      <div class="shi-step__num"><span>{{ start + i }}</span></div>
      <div>
        <div class="shi-step__title">{{ step.title }}</div>
        <div v-if="step.body" class="shi-step__body">{{ step.body }}</div>
      </div>
    </div>
  </div>
</template>
