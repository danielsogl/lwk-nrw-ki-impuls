<script setup lang="ts">
/** Responsives Raster für <Card>. `cols` = Spaltenzahl (Default 3). */
const props = withDefaults(defineProps<{ cols?: number | string; gap?: string }>(), {
  cols: 3,
})
</script>

<template>
  <div
    class="shi-grid"
    :style="{
      gridTemplateColumns: `repeat(${props.cols}, minmax(0, 1fr))`,
      gap: props.gap ?? '1rem',
    }"
  >
    <slot />
  </div>
</template>
