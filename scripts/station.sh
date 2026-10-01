#!/usr/bin/env bash
# Lädt den vorbereiteten Stand einer Demo-Station und startet das Backend neu.
#   scripts/station.sh 1|2|3|4
# Offene Änderungen aus dem Live-Teil landen vorher im Stash (git stash list),
# die Stations-Branches selbst bleiben unverändert.
set -euo pipefail
cd "$(git rev-parse --show-toplevel)"

case "${1:-}" in
  1) branch=main ;;
  2) branch=demo/2-plan ;;
  3) branch=demo/3-umsetzung ;;
  4) branch=demo/4-review ;;
  *) echo "Aufruf: scripts/station.sh 1|2|3|4" >&2; exit 1 ;;
esac

if [ -n "$(git status --porcelain)" ]; then
  git stash push --include-untracked --quiet --message "Live-Stand vor Station $1"
  echo "Live-Stand gesichert: git stash list"
fi
git switch --quiet --force-create "station-$1" "$branch"
echo "Station $1 geladen ($branch)"

# Backend neu starten, damit es den Code dieser Station ausliefert.
pids=$(lsof -ti tcp:8080 || true)
[ -n "$pids" ] && kill $pids && sleep 2

# Baseline für den Hook vorab berechnen, solange der Stand sauber ist und kein Backend baut.
printf "Baseline-Tests laufen …"
node scripts/hooks/baseline.mjs start < /dev/null > /dev/null && echo " fertig."

(cd backend && nohup ./mvnw -q spring-boot:run > ../backend.log 2>&1 &)
printf "Backend startet neu"
for _ in $(seq 1 60); do
  curl -s -o /dev/null http://localhost:8080/api/courses && { echo " – bereit."; exit 0; }
  printf "."; sleep 1
done
echo " – noch nicht bereit, siehe backend.log" >&2
exit 1
