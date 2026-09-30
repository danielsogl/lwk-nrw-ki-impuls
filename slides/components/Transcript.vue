<script setup lang="ts">
/**
 * Agent-Sitzung als Transkript – der zentrale Erzählbaustein dieses Decks.
 *
 * <Transcript tool="claude" :lines="[
 *   { kind: 'prompt', text: 'add rate limiting to the upload endpoint' },
 *   { kind: 'tool',   text: 'Read src/routes/upload.ts' },
 *   { kind: 'err',    text: 'FAIL tests/upload.test.ts (1)' },
 *   { kind: 'ok',     text: '12 passed' },
 *   { kind: 'note',   text: 'agent stops and reports' },
 * ]" />
 *
 * Bewusst KEIN Screenshot eines Terminals: Screenshots altern mit jedem
 * UI-Release, sind im PDF unscharf und im Dark Mode falschherum. Ein Transkript
 * aus Text bleibt lesbar, exportiert sauber und lässt sich in beiden
 * Farbschemata kontrastprüfen.
 *
 * `clicks` blendet die Zeilen einzeln ein – für Abläufe, die man Schritt für
 * Schritt erzählt (Loop-Demos in M3, Fehlersuche in M7).
 */
withDefaults(
  defineProps<{
    lines: {
      /**
       * prompt – was die Person eingibt · tool – Werkzeugaufruf des Agenten
       * out – Ausgabe · ok – bestandene Prüfung · err – fehlgeschlagene Prüfung
       * note – Kommentar von außen, kein Teil der Sitzung
       */
      kind: 'prompt' | 'tool' | 'out' | 'ok' | 'err' | 'note'
      text: string
    }[]
    /** Beschriftung der Kopfzeile, z. B. der Werkzeugname. */
    tool?: string
    /** Zeilen nacheinander per Klick einblenden. */
    clicks?: boolean
  }>(),
  { tool: 'session' },
)

const MARKER: Record<string, string> = {
  prompt: '›',
  tool: '⏵',
  out: ' ',
  ok: '✓',
  err: '✗',
  note: ' ',
}
</script>

<template>
  <div class="shi-transcript">
    <div class="shi-transcript__bar">
      <span class="shi-transcript__dots" aria-hidden="true" />
      <span class="shi-transcript__tool">{{ tool }}</span>
    </div>
    <div class="shi-transcript__body">
      <div
        v-for="(line, i) in lines"
        :key="i"
        v-click="clicks ? undefined : false"
        class="shi-transcript__line"
        :class="`shi-transcript__line--${line.kind}`"
      >
        <span class="shi-transcript__marker" aria-hidden="true">{{ MARKER[line.kind] }}</span>
        <span class="shi-transcript__text">{{ line.text }}</span>
      </div>
    </div>
  </div>
</template>
