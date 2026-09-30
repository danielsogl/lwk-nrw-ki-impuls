/**
 * Schriften kommen aus dem Repo, nicht von fonts.googleapis.com.
 *
 * Zwei Gründe: Ein Vortrag muss auch ohne Netz aussehen wie geplant – und ein
 * gehostetes Deck würde sonst die IP jedes Betrachters an Google schicken.
 * shi-gmbh.com hält es genauso (Astra "Load Google Fonts Locally").
 *
 * Gewichte wie auf der Website: 400 Fließtext, 600 Zwischenüberschriften,
 * 700 Überschriften. Nur der Latin-Subset – alles andere wäre Ballast.
 */
import '@fontsource/open-sans/latin-400.css'
import '@fontsource/open-sans/latin-600.css'
import '@fontsource/open-sans/latin-700.css'
import '@fontsource/jetbrains-mono/latin-400.css'

import './_tokens.css'
import './_base.css'
import './_layouts.css'
import './_components.css'
