# devstate_active

## Aktueller Stand

Zielmarke: `Uebernahme-2026-11-09` (in-progress, [[Zielmarken]])

Die Wissens-Migration nach `_template/migration/MIGRATION.md` steht am
Ende von Phase 3: Das Wissen aus `anforderungen.md`,
`datenmodell-und-ablaeufe.md`, `spezifikation.md`, den JUnit-Tests,
`CLAUDE-vorgefunden.md` und der Git-Historie liegt jetzt in den
Registern (REQ-001–033, FUNC-001–009, SPEC-001–002, ARC-001–003,
TEST-001–042, DEC-001–017) und in [[Migrations-Grundlagen]]. Der Lint
meldet keine Fehler; die Hinweise betreffen den Bestand ohne
Zielmarke und Anlage-Datum und sind erwartet. Bis Phase 5 gibt es
keine `CLAUDE.md`; die alte liegt als `CLAUDE-vorgefunden.md`.

## Nächste Schritte

- Erledigt: Phase 4 Stufe 1, Runde 1 — REP-001 (Claude Sonnet 5.5, gelb,
  15 Befunde) und REP-002 (Codex gpt-6-luna, gelb, 1 Befund) in
  [[Agent-Berichte]].
- Entscheidungen des Anwenders zu den entscheidungsbedürftigen Befunden
  einholen (Testart, DEC-Dopplung mit REQ, Zeile in §Komponenten mit
  Repo-Name, Zielumgebung, Nachweis der alten Prüfergebnisse).
- Danach alle Befunde nacharbeiten, Commit, Kurz-Re-Check beider Prüfer
  (höchstens drei Runden), Gesamt-Annahme (DEC-018), Phase 5.

## Blockaden/Risiken

- Codex läuft im Container nur ohne Sandbox; Prüfläufe deshalb nur in
  einem Klon des Repos (`Tools.md` §Prüf-Werkzeuge).
