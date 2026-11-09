# devstate_active

## Aktueller Stand

Zielmarke: `Uebernahme-2026-11-09` (in-progress, [[Zielmarken]])

Die Wissens-Migration nach `_template/migration/MIGRATION.md` steht am
Ende von Phase 4 Stufe 1: Beide Prüfer sind nach Runde 2 grün
(REP-003, REP-004). Das Wissen liegt in den Registern (REQ-001–033,
FUNC-001–009, SPEC-001–002, ARC-001–003, TEST-001–042, DEC-001–017,
REC-001) und in [[Migrations-Grundlagen]], samt den Entscheiden des
Anwenders zur Prüfrunde 1 (§Festlegungen Nr. 5). Der Lint meldet keine
Fehler; die Hinweise (202 × Bestand ohne Zielmarke und Anlage-Datum,
1 × DEC-018 ohne Register-Zeile) sind erwartet. Bis Phase 5 gibt es
keine `CLAUDE.md`; die alte liegt als `CLAUDE-vorgefunden.md`.

## Nächste Schritte

- Erledigt: Runde 1 (REP-001, REP-002, beide gelb), Nacharbeit
  (Commits `cca6191..857e33b`), Runde 2 (REP-003, REP-004, beide grün).
- Gesamt-Annahme durch den Anwender: Bericht über die unsicheren
  Stellen vorlegen (Ungeklärt in [[Migrations-Grundlagen]], Status-Wahl,
  keine gebrochenen Links durch die Umbenennung); danach Register-Zeile
  DEC-018 buchen, Abschluss-Commit der Phase 4.
- Dann Phase 5: Glossar-Freigabe, Commit-Sprache, WorkspaceManager,
  ein Repo oder zwei, neue `CLAUDE.md`, Abschluss.

## Blockaden/Risiken

- Codex läuft im Container nur ohne Sandbox; Prüfläufe deshalb nur in
  einem Klon des Repos (`Tools.md` §Prüf-Werkzeuge).
