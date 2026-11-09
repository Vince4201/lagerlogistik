---
type: internal-rule
---

# Keine Umschreibung der Git-Historie

**Regel** (Wortlaut der Herkunft): „Die Geschichte wird nicht
umgeschrieben: kein `git reset --hard`, kein
`git push --force`, kein `git commit --amend` auf Commits,
die schon auf GitHub liegen. Falsches nimmt `git revert`
zurück.“

**Angewandt im Bestand:** Die frühen Commits ohne Präfix sind
so geblieben, weil sie schon öffentlich auf GitHub lagen
(Commit `1013075`); eine falsche Festlegung hat Commit
`e11a977` per `git revert` zurückgenommen (er nimmt
`f3b2bd4` zurück).

**Abgrenzung:** MasterPrompt §6 regelt das Umschreiben der
Historie nicht; diese Regel ergänzt ihn für dieses Projekt.
Pushen bleibt Sache einer Anweisung (MasterPrompt §6.4).

## Herkunft

Bestand aus `CLAUDE-vorgefunden.md` §Git ist das Gedächtnis
des Projekts, Abschnitt Commits; aufgenommen mit Commit
`1013075`. Schon seit dem ersten Commit `3b6f6b4` nannte die
Instruktionsdatei `git revert` als Weg, einen Commit
zurückzunehmen, ohne die Historie zu verändern.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Verwandte Regel: [[Experimente-auf-eigenem-Branch]]
- Nachschlagen: [[Nachschlagen-in-der-Git-Historie]]
