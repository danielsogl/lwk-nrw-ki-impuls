<script setup lang="ts">
/**
 * Abschluss-/Kontaktfolie.
 *
 * ---
 * layout: outro
 * speaker: Daniel Sogl
 * role: Senior Software Engineer
 * mail: daniel.sogl@shi-gmbh.com
 * linkedin: daniel-sogl
 * qr: /qr-slides.png     # optional: QR-Code zu den Slides
 * qrLabel: Slides & Code
 * ---
 * # Vielen Dank!
 *
 * Fragen? Immer her damit.
 */
withDefaults(
  defineProps<{
    speaker?: string
    role?: string
    mail?: string
    phone?: string
    linkedin?: string
    web?: string
    qr?: string
    qrLabel?: string
    /** optional: Partnerlogo (weiß) neben dem SHI-Logo */
    partner?: string
    partnerAlt?: string
    /** Firmenblock ausblenden (z. B. bei internen Decks) */
    hideCompany?: boolean
  }>(),
  { web: 'shi-gmbh.com' },
)
</script>

<template>
  <div class="slidev-layout shi-bleed shi-outro shi-surface-brand">
    <div>
      <slot />
      <div class="shi-outro__contact">
        <div v-if="speaker" class="shi-outro__name">{{ speaker }}</div>
        <div v-if="role">{{ role }}</div>
        <a v-if="mail" :href="`mailto:${mail}`" class="mt-2">{{ mail }}</a>
        <a v-if="phone" :href="`tel:${phone.replace(/\s/g, '')}`">{{ phone }}</a>
        <a
          v-if="linkedin"
          :href="/^https?:/.test(linkedin) ? linkedin : `https://linkedin.com/in/${linkedin}`"
          target="_blank"
        >linkedin.com/in/{{ linkedin }}</a>
      </div>
    </div>

    <div class="shi-outro__aside">
      <div class="shi-logos">
        <ShiLogo variant="white" height="46px" class="shi-outro__logo" />
        <img v-if="partner" :src="partner" :alt="partnerAlt ?? 'Partnerlogo'" class="shi-raw shi-logos__partner" />
      </div>
      <div v-if="qr">
        <img :src="qr" alt="QR-Code" class="shi-outro__qr shi-raw" />
        <div v-if="qrLabel" class="mt-1 text-xs opacity-80">{{ qrLabel }}</div>
      </div>
      <div v-if="!hideCompany" class="shi-outro__company">
        SHI GmbH<br />
        Konrad-Adenauer-Allee 15<br />
        86150 Augsburg<br />
        <a :href="`https://${web}`" target="_blank">{{ web }}</a>
      </div>
    </div>
  </div>
</template>
