<script setup lang="ts">
/** Kleines Label für Tech-Stack, Status, Kategorien. */
withDefaults(
  defineProps<{
    color?: 'blue' | 'teal' | 'amber' | 'coral' | 'violet' | 'green' | 'neutral'
    solid?: boolean
    icon?: string
  }>(),
  { color: 'blue' },
)

const COLORS = {
  blue: 'var(--shi-brand-text)',
  teal: 'var(--shi-accent-teal)',
  amber: 'var(--shi-accent-amber)',
  coral: 'var(--shi-accent-coral)',
  violet: 'var(--shi-accent-violet)',
  green: 'var(--shi-accent-green)',
  neutral: 'var(--shi-fg-muted)',
} as const
</script>

<template>
  <span
    class="shi-tag"
    :class="{ 'shi-tag--solid': solid }"
    :style="{ '--shi-tag-color': COLORS[color] ?? COLORS.blue }"
  >
    <span v-if="icon" :class="icon" />
    <slot />
  </span>
</template>
