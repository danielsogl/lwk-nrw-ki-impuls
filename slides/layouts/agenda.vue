<script setup lang="ts">
import { computed } from 'vue'

/**
 * Agenda-Folie. Kann am Anfang einmal komplett und später vor jedem Kapitel
 * mit `current:` wiederholt werden – erledigte Punkte werden durchgestrichen,
 * der aktuelle hervorgehoben, der Rest zurückgenommen.
 *
 * ---
 * layout: agenda
 * current: 2
 * items:
 *   - Ausgangslage
 *   - label: Architektur
 *     hint: 15 min
 *   - Live-Demo
 * ---
 *
 * Ohne `items` wird der Standard-Slot gerendert (freie Markdown-Agenda).
 */
const props = withDefaults(
  defineProps<{
    items?: (string | { label: string; hint?: string })[]
    /** 1-basierter Index des aktuellen Punkts */
    current?: number
    title?: string
    note?: string
  }>(),
  { title: 'Agenda' },
)

const normalized = computed(() =>
  (props.items ?? []).map((item) =>
    typeof item === 'string' ? { label: item, hint: undefined } : item,
  ),
)
</script>

<template>
  <div class="slidev-layout shi-agenda-layout">
    <div class="shi-agenda">
      <aside class="shi-agenda__aside">
        <h1>{{ title }}</h1>
        <p v-if="note">{{ note }}</p>
      </aside>

      <ol
        v-if="normalized.length"
        class="shi-agenda__list"
        :class="{
          'shi-agenda--focused': current != null,
          // Ab sieben Punkten läuft die Liste sonst unten aus der Folie. Die
          // Enge hängt an der Anzahl und gehört deshalb ins Layout – sonst
          // müsste jede Agenda-Folie eines langen Decks einzeln nachjustiert
          // werden.
          'shi-agenda--dense': normalized.length > 6,
        }"
      >
        <li
          v-for="(item, i) in normalized"
          :key="i"
          class="shi-agenda__item"
          :class="{
            'shi-agenda__item--current': current === i + 1,
            'shi-agenda__item--done': current != null && i + 1 < current,
          }"
        >
          <span class="shi-agenda__label">{{ item.label }}</span>
          <span v-if="item.hint" class="shi-agenda__hint">{{ item.hint }}</span>
        </li>
      </ol>

      <div v-else><slot /></div>
    </div>
  </div>
</template>
