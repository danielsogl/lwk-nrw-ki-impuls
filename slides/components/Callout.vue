<script setup lang="ts">
withDefaults(
  defineProps<{
    type?: 'info' | 'tip' | 'warn' | 'danger'
    title?: string
  }>(),
  { type: 'info' },
)

const META = {
  info: { color: 'var(--shi-info)', icon: 'i-carbon-information' },
  tip: { color: 'var(--shi-success)', icon: 'i-carbon-idea' },
  warn: { color: 'var(--shi-warn)', icon: 'i-carbon-warning' },
  danger: { color: 'var(--shi-danger)', icon: 'i-carbon-warning-alt' },
} as const
</script>

<template>
  <div class="shi-callout" :style="{ '--shi-callout-color': (META[type] ?? META.info).color }">
    <div class="shi-callout__icon" :class="(META[type] ?? META.info).icon" />
    <div>
      <div v-if="title" class="shi-callout__title">{{ title }}</div>
      <div><slot /></div>
    </div>
  </div>
</template>
