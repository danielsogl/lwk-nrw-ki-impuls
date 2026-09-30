<script setup lang="ts">
/**
 * Titelfolie (Intro).
 *
 * ---
 * layout: cover
 * subtitle: Von der Suchleiste zum Antwortsystem
 * speaker: Daniel Sogl
 * role: Senior Software Engineer
 * event: SHI Tech Talk
 * date: 12. September 2026
 * image: /cover.jpg      # optional: Bildhintergrund statt Verlauf
 * partner: /partner.svg  # optional: Partnerlogo (weiß) neben dem SHI-Logo
 * ---
 * # Semantische Suche im Verlagswesen
 */
defineProps<{
  subtitle?: string
  speaker?: string
  role?: string
  event?: string
  date?: string | number
  image?: string
  partner?: string
  partnerAlt?: string
}>()
</script>

<template>
  <div
    class="slidev-layout shi-bleed shi-cover shi-surface-brand h-full"
    :class="{ 'shi-cover--image': image }"
  >
    <template v-if="image">
      <div class="shi-cover__bg" :style="{ backgroundImage: `url(${image})` }" />
      <div class="shi-cover__scrim" />
    </template>

    <div class="shi-logos">
      <ShiLogo variant="white" height="54px" class="shi-cover__logo" />
      <img v-if="partner" :src="partner" :alt="partnerAlt ?? 'Partnerlogo'" class="shi-raw shi-logos__partner" />
    </div>

    <div class="shi-cover__title">
      <slot />
      <p v-if="subtitle">{{ subtitle }}</p>
    </div>

    <div class="shi-cover__meta">
      <div>
        <div v-if="speaker" class="shi-cover__speaker">{{ speaker }}</div>
        <div v-if="role">{{ role }}</div>
      </div>
      <div class="text-right">
        <div v-if="event">{{ event }}</div>
        <div v-if="date">{{ date }}</div>
      </div>
    </div>
  </div>
</template>
