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

- Phase 4 Stufe 1: Prüfer 1 (Claude Sonnet 5.5, Subagent mit frischem
  Kontext) prüft die Migration; Bericht als REP in [[Agent-Berichte]].
- Phase 4 Stufe 1: Prüfer 2 (Codex, zweites Modell) prüft unabhängig —
  braucht `codex login` durch den Anwender.
- Danach Befunde nacharbeiten, Gesamt-Annahme durch den Anwender
  (DEC-018), Phase 5 (Restfragen, neue Instruktionsdatei).

## Blockaden/Risiken

- Codex ist nicht angemeldet (Stand 2026-11-09); ohne zweites Modell
  bleibt nur der Ersatzweg Ein-Modell-Weg (`Tools.md`).
