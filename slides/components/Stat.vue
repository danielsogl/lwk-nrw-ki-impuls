<script setup lang="ts">
/** Kennzahl-Kachel für "Zahlen & Fakten". Funktioniert auch auf Blauflächen. */
defineProps<{
  value: string | number
  label?: string
  hint?: string
}>()
</script>

<template>
  <div class="shi-stat">
    <div class="shi-stat__value">{{ value }}</div>
    <div v-if="label" class="shi-stat__label">{{ label }}</div>
    <div v-if="hint" class="shi-stat__hint">{{ hint }}</div>
    <slot />
  </div>
</template>
