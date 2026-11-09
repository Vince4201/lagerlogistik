---
type: internal-rule
---

# Build und Laufzeitdaten nicht im Repository

**Regel:** Die Build-Ausgabe (`target/`) und die
Laufzeitdaten der SQLite-Datenbank (`*.db` samt
Begleitdateien) werden nicht eingecheckt. Die vollständige
Liste führt `.gitignore` (führender Ort; dort auch Dateien
von Editoren und IDEs).

**Begründung der Herkunft:** `target/` entsteht mit
`./lager.sh` neu; `lager.db` lässt sich aus
`beispieldaten.txt` neu erzeugen (Commit `3b6f6b4`), und
eine Testdatenbank entsteht aus `beispieldaten.txt`
(Kommentare in `.gitignore`).

**Abgrenzung:** konkretisiert MasterPrompt §6.3 (Nicht
committen) für dieses Projekt.

## Herkunft

Bestand aus `CLAUDE-vorgefunden.md` („Nicht im Repository:
`target/` (Build) und `*.db` (Laufzeitdaten), siehe
`.gitignore`.“), `.gitignore` und Commit `3b6f6b4`.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Bauen: [[Bauen-und-Starten-mit-lager-sh]]; Testdatenbank:
  [[Prueflauf-mit-Beispieldaten]]
