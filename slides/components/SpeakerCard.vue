<script setup lang="ts">
import { computed } from 'vue'

/**
 * Speaker-Block. Wird vom `speaker`-Layout genutzt, funktioniert aber auch
 * eigenständig (z. B. Team-Folie mit mehreren Karten).
 */
const props = defineProps<{
  name: string
  role?: string
  image?: string
  size?: string
  mail?: string
  linkedin?: string
  github?: string
  bluesky?: string
  mastodon?: string
  web?: string
}>()

const links = computed(() =>
  [
    { key: 'mail', icon: 'i-carbon-email', label: props.mail, href: `mailto:${props.mail}` },
    { key: 'linkedin', icon: 'i-carbon-logo-linkedin', label: props.linkedin, href: toUrl(props.linkedin, 'https://linkedin.com/in/') },
    { key: 'github', icon: 'i-carbon-logo-github', label: props.github, href: toUrl(props.github, 'https://github.com/') },
    { key: 'bluesky', icon: 'i-carbon-cloud', label: props.bluesky, href: toUrl(props.bluesky, 'https://bsky.app/profile/') },
    { key: 'mastodon', icon: 'i-carbon-logo-mastodon', label: props.mastodon, href: props.mastodon },
    { key: 'web', icon: 'i-carbon-link', label: props.web, href: toUrl(props.web, 'https://') },
  ].filter((l) => !!l.label),
)

function toUrl(value: string | undefined, prefix: string) {
  if (!value) return undefined
  return /^https?:\/\//.test(value) ? value : prefix + value.replace(/^@/, '')
}
</script>

<template>
  <div class="shi-speaker" :style="{ '--shi-speaker-size': size }">
    <div v-if="image" class="shi-speaker__photo">
      <img :src="image" :alt="name" class="shi-raw" />
    </div>
    <div class="min-w-0">
      <div class="shi-speaker__name">{{ name }}</div>
      <div v-if="role" class="shi-speaker__role">{{ role }}</div>
      <div class="shi-speaker__bio"><slot /></div>
      <div v-if="links.length" class="shi-speaker__links">
        <a v-for="link in links" :key="link.key" :href="link.href" target="_blank">
          <span :class="link.icon" />
          <span>{{ link.label }}</span>
        </a>
      </div>
    </div>
  </div>
</template>
