<script setup lang="ts">
/**
 * Kundenzitat / Testimonial (überschreibt das Built-in-Layout im SHI-Look).
 *
 * ---
 * layout: quote
 * author: Dr. Maxine Muster
 * position: Leiterin Digitale Produkte, Beispiel-Verlag
 * logo: /logos/kunde.svg
 * ---
 * Die Trefferqualität ist heute ein Verkaufsargument.
 */
defineProps<{
  author?: string
  position?: string
  logo?: string
}>()
</script>

<template>
  <div class="slidev-layout shi-quote">
    <div class="shi-quote__mark" aria-hidden="true">„</div>
    <div class="shi-quote__text"><slot /></div>
    <div v-if="author || logo" class="shi-quote__author">
      <img v-if="logo" :src="logo" alt="" class="shi-raw mb-3 h-8 w-auto" />
      <strong v-if="author">{{ author }}</strong>
      <span v-if="position">{{ position }}</span>
    </div>
  </div>
</template>
