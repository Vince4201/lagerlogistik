---
type: internal-pattern
---

# Nachschlagen in der Git-Historie

**Zweck:** nachschlagen, was wann und warum geändert wurde.
Bis zur Migration standen die Gründe in den
Commit-Nachrichten (bis Commit `2eb6d29`); für den
übernommenen Bestand ist die Historie darum der Nachweis
seiner Herkunft.

**Befehle** (nach `CLAUDE-vorgefunden.md` §Nachschlagen und
§Zu Beginn jeder Sitzung):

- `git log --oneline -15` — was zuletzt passiert ist;
  Einzelheiten eines Commits mit `git show <commit>`.
- `git log --grep=<Begriff>` — wann und warum etwas dazukam.
- `git log -p -- <datei>` — Geschichte einer Datei mit allen
  Änderungen.
- `git blame <datei>` — aus welchem Commit eine Zeile stammt.
- `git show <commit>:<datei>` — den Inhalt einer Datei in
  einem früheren Commit ansehen, ohne etwas zu ändern.
- `git diff` — was geändert und noch nicht vorgemerkt ist.

**Grenzen:** Git hält den technischen Verlauf (MasterPrompt
§6.5); was festgelegt ist, steht im `wiki/` (MasterPrompt §9).
Zurückgenommen wird nur mit `git revert`
([[Keine-Umschreibung-der-Git-Historie]]).

## Herkunft

Bestand aus `CLAUDE-vorgefunden.md` §Nachschlagen (seit
Commit `3b6f6b4`, erweitert mit `1013075`) und §Zu Beginn
jeder Sitzung.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Zustand des Repos aufnehmen: [[Git-Bestandsaufnahme]]
- Zeitachse: [[Chronologie]]
