<script setup lang="ts">
import { computed } from 'vue'
import { useNav, useSlideContext } from '@slidev/client'

/**
 * Fußzeile auf jeder Inhaltsfolie. Gesteuert über `themeConfig` im Headmatter:
 *
 * themeConfig:
 *   footer: 'Semantische Suche im Verlagswesen'   # Default: deck title
 *   event: 'SHI Tech Talk · 12.09.2026'
 *   confidential: 'Intern – vertraulich'          # weglassen für externe Decks
 *
 * Pro Folie abschaltbar mit `footer: false` im Frontmatter.
 *
 * Bewusst ein Per-Slide-Layer (`slide-bottom.vue`), kein `global-bottom.vue`:
 * globale Layer werden auf der /print-Route – und damit im PDF-Export – gar
 * nicht gerendert, die Fußzeile fehlte dort komplett. Deshalb auch `$page`
 * statt `$nav.currentPage`, sonst zeigen im Export alle Folien dieselbe Zahl.
 */
const { total } = useNav()
const { $slidev, $frontmatter, $page } = useSlideContext()

/** Layouts mit vollflächigem Blau bringen ihre eigene Typo mit. */
const BLEED = ['cover', 'section', 'outro', 'statement', 'end', 'intro']

const cfg = computed(() => ($slidev.configs.themeConfig ?? {}) as Record<string, string>)
const layout = computed(() => String($frontmatter?.layout ?? 'default'))
const visible = computed(
  () =>
    $frontmatter?.footer !== false
    && !BLEED.includes(layout.value)
    // `layout: speaker` schaltet die Blaufläche nicht über den Layoutnamen,
    // sondern über `dark: true` zu – sonst stünde die helle Fußzeile darauf.
    && $frontmatter?.dark !== true,
)
const label = computed(() => cfg.value.footer ?? $slidev.configs.title)
</script>

<template>
  <footer v-if="visible" class="shi-footer" :class="`shi-footer--${layout}`">
    <img src="/shi-logo.png" alt="SHI GmbH" class="shi-footer__logo" />
    <span v-if="label">{{ label }}</span>
    <!-- Trenner als CSS-Linie, nicht als "|"-Glyph: ein dekoratives Zeichen
         wäre bei dieser Deckkraft nur 2:1 und würde jede A11y-Prüfung auslösen. -->
    <span v-if="cfg.event" class="shi-footer__event">{{ cfg.event }}</span>
    <span v-if="cfg.confidential" class="shi-footer__badge">{{ cfg.confidential }}</span>
    <span class="shi-footer__spacer" />
    <span class="shi-footer__page">{{ $page }} / {{ total }}</span>
  </footer>
</template>
