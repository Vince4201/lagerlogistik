# Lagerverwaltung

Terminal-Lagerverwaltung in Java 21 mit SQLite. Starten mit `./lager.sh`, das bei Bedarf vorher
mit Maven baut.

## Dokumentenkette

Die Dokumentation besteht aus drei Dateien, die aufeinander aufbauen:

1. [anforderungen.md](anforderungen.md): was das Programm leisten soll, ohne Technik.
2. [datenmodell-und-ablaeufe.md](datenmodell-und-ablaeufe.md): wie die Anforderungen in Daten und
   Abläufe übersetzt werden.
3. [spezifikation.md](spezifikation.md): wie das Programm technisch gebaut ist, genau genug zum
   Umsetzen und Prüfen.

Änderungen laufen die Kette entlang: erst die Anforderung, dann Datenmodell und Abläufe, dann die
Spezifikation, zuletzt der Code. Ein späteres Dokument widerspricht nie einem früheren. Eine
zusammenfassende KONZEPT.md gibt es nicht mehr.

## Git ist das Gedächtnis des Projekts

Von einer Sitzung zur nächsten weiß der Agent nichts mehr. Was gemacht wurde und warum, steht
deshalb in der Git-Historie und nirgendwo sonst.

**Zu Beginn jeder Sitzung**

- `git status`: Gibt es angefangene, nicht eingecheckte Änderungen? Dann zuerst klären, was damit
  passieren soll.
- `git log --oneline -15`: Was ist zuletzt passiert? Details mit `git show <commit>`.

**Während der Arbeit**

- Nach jedem abgeschlossenen Schritt committen, der baut und läuft. Ein Commit enthält eine Sache.
- Prüfen vor dem Commit: `rm -f test.db && ./lager.sh test.db < beispieldaten.txt` muss ohne
  Fehler durchlaufen.
- Commit-Nachrichten auf Deutsch. Erste Zeile kurz: was geändert wurde. Darunter: warum, welche
  Entscheidung getroffen wurde, was verworfen wurde, was noch offen ist. Diesen Teil braucht man später.
- Ändert sich das Verhalten, werden die betroffenen Dokumente der Kette im selben Commit angepasst.
- Experimente gehören auf einen eigenen Branch, nicht auf `main`.

**Nachschlagen**

- `git log --grep=<Begriff>`: Wann und warum kam etwas dazu?
- `git log -p -- <datei>`: Geschichte einer Datei.
- `git blame <datei>`: Aus welchem Commit stammt eine Zeile?
- `git revert <commit>`: Einen Commit zurücknehmen, ohne die Historie zu verändern.

**Nicht im Repository:** `target/` (Build) und `*.db` (Laufzeitdaten), siehe `.gitignore`.
