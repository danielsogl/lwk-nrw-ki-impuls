<script setup lang="ts">
/**
 * Speaker-Vorstellung ("Wer spricht hier?").
 *
 * ---
 * layout: speaker
 * name: Daniel Sogl
 * role: Senior Software Engineer · SHI GmbH
 * image: /speaker/daniel.jpg   # quadratisch zuschneiden; ohne echtes Foto
 *                              # tut es /speaker-placeholder.svg
 * mail: daniel.sogl@shi-gmbh.com
 * linkedin: daniel-sogl
 * github: danielsogl
 * dark: true          # optional: auf Blaufläche statt Weiß
 * ---
 *
 * - Suche & Retrieval seit 2019
 * - Angular, TypeScript, RAG
 */
defineProps<{
  name: string
  role?: string
  image?: string
  mail?: string
  linkedin?: string
  github?: string
  bluesky?: string
  mastodon?: string
  web?: string
  dark?: boolean
}>()
</script>

<template>
  <div
    class="slidev-layout shi-speaker-layout"
    :class="dark ? 'shi-bleed shi-surface-brand px-14! py-12!' : ''"
  >
    <SpeakerCard v-bind="$props" size="220px">
      <slot />
    </SpeakerCard>
  </div>
</template>
