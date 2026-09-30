<script setup lang="ts">
/**
 * SHI-Logo. Zwei offizielle Ausbaustufen von shi-gmbh.com:
 *   color — blaues Parallelogramm mit weißer Wortmarke (helle Hintergründe)
 *   white — komplett weiß, Wortmarke ausgespart (dunkle/blaue Hintergründe)
 */
defineProps<{
  variant?: 'color' | 'white'
  /** CSS-Höhe, z. B. '48px' oder '3rem' */
  height?: string
  alt?: string
}>()
</script>

<template>
  <img
    class="shi-raw"
    :src="variant === 'white' ? '/shi-logo-white.png' : '/shi-logo.png'"
    :alt="alt ?? 'SHI GmbH'"
    :style="{ height: height ?? '48px', width: 'auto' }"
  />
</template>
