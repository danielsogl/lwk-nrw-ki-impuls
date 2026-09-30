import { defineShikiSetup } from '@slidev/types'

/**
 * Slidevs Default-Themes (`min-light` / `min-dark`) setzen Interpunktion auf
 * #999 — nur 2.6:1 auf dem Codeblock-Hintergrund und auf einem Beamer nicht
 * mehr lesbar. Die High-Contrast-Varianten von GitHub sind auf WCAG AA ausgelegt.
 */
export default defineShikiSetup(() => ({
  themes: {
    light: 'github-light-high-contrast',
    dark: 'github-dark-high-contrast',
  },
}))
