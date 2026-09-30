<script setup lang="ts">
/**
 * Große Kernaussage / Zahl auf Blaufläche. Für den einen Satz, der hängen bleibt.
 *
 * ---
 * layout: statement
 * source: Gartner, 2025
 * ---
 * 70 % aller Suchanfragen bleiben unbeantwortet.
 */
defineProps<{ source?: string }>()
</script>

<template>
  <div class="slidev-layout shi-bleed shi-statement shi-surface-brand h-full">
    <div class="shi-statement__text"><slot /></div>
    <div v-if="source" class="shi-statement__source">{{ source }}</div>
  </div>
</template>
