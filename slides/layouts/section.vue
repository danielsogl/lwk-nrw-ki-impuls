<script setup lang="ts">
/**
 * Kapiteltrenner.
 *
 * ---
 * layout: section
 * number: 2
 * subtitle: Wie aus Dokumenten Antworten werden
 * ---
 * # Architektur
 */
defineProps<{
  number?: string | number
  subtitle?: string
}>()
</script>

<template>
  <div class="slidev-layout shi-bleed shi-section shi-surface-brand h-full">
    <div v-if="number != null" class="shi-section__num">
      {{ String(number).padStart(2, '0') }}
    </div>
    <slot />
    <p v-if="subtitle" class="shi-section__sub">{{ subtitle }}</p>
  </div>
</template>
