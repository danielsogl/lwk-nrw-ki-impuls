<script setup lang="ts">
/**
 * Inhaltskachel. `icon` ist ein UnoCSS-Icon-Name, z. B. "i-carbon-search".
 * Inhalt kommt als Slot (Markdown erlaubt).
 */
defineProps<{
  title?: string
  icon?: string
  variant?: 'plain' | 'accent' | 'filled'
}>()
</script>

<template>
  <div
    class="shi-card"
    :class="{
      'shi-card--accent': variant === 'accent',
      'shi-card--filled': variant === 'filled',
    }"
  >
    <div v-if="icon" class="shi-card__icon" :class="icon" />
    <div v-if="title" class="shi-card__title">{{ title }}</div>
    <div class="shi-card__body"><slot /></div>
  </div>
</template>
