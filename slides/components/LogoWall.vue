<script setup lang="ts">
/**
 * Logo-Wand für Kundenreferenzen oder Tech-Stack.
 * Fremdlogos standardmäßig entsättigt (`original` schaltet auf die Originalfarben).
 *
 * <LogoWall :logos="[{ src: '/logos/solr.svg', alt: 'Apache Solr' }]" />
 *
 * Statt `src` geht auch `icon` – dann kommt das Logo aus einer Iconify-Sammlung
 * (`@iconify-json/logos`, `@iconify-json/simple-icons`) und muss nicht als Datei
 * im Repo liegen:
 *
 * <LogoWall :logos="[{ icon: 'i-logos-openai', alt: 'OpenAI' }]" original />
 */
withDefaults(
  defineProps<{
    logos: { src?: string; icon?: string; alt: string }[]
    height?: string
    original?: boolean
  }>(),
  { height: '46px' },
)
</script>

<template>
  <div
    class="shi-logowall"
    :class="{ 'shi-logowall--original': original }"
    :style="{ '--shi-logowall-h': height }"
  >
    <!-- Key über den Index: dieselbe Datei darf mehrfach in der Wand stehen
         (Platzhalter), und ein Eintrag ohne src/icon würde sonst `undefined`
         als Key liefern. -->
    <template v-for="(logo, i) in logos" :key="i">
      <div
        v-if="logo.icon"
        class="shi-logowall__icon"
        :class="logo.icon"
        role="img"
        :aria-label="logo.alt"
        :title="logo.alt"
      />
      <img v-else-if="logo.src" :src="logo.src" :alt="logo.alt" class="shi-raw" />
    </template>
  </div>
</template>
