#!/usr/bin/env bash
# Läuft vor jedem Werkzeugaufruf eines Copilot-Agenten (VS Code und Copilot CLI).
# Anders als eine Regel in AGENTS.md lässt sich dieser Hook nicht wegargumentieren.
input=$(cat)
case "$input" in
  *--no-verify*|*"lefthook uninstall"*)
    reason="Git-Hooks umgehen ist nicht erlaubt (AGENTS.md). Fehler beheben statt Prüfung abschalten."
    printf '{"permissionDecision":"deny","permissionDecisionReason":"%s","hookSpecificOutput":{"hookEventName":"PreToolUse","permissionDecision":"deny","permissionDecisionReason":"%s"}}\n' "$reason" "$reason"
    ;;
esac
exit 0
