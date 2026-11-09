---
type: internal-pattern
---

# Prüflauf mit Beispieldaten

**Zweck:** im Arbeitsablauf das Programm einmal komplett über
die Bedienung durchlaufen, mit den Eingaben aus
`beispieldaten.txt`. Inhalt der Datei, Befehle und Erwartung
führt [[Softwaresystemtest-Setup]] §Ausführung (führender
Ort).

**Schritte:**

1. `rm -f test.db && ./lager.sh test.db < beispieldaten.txt`
   — frische Testdatenbank; `lager.sh` baut vorher bei Bedarf
   samt Tests ([[Bauen-und-Starten-mit-lager-sh]]).
2. Ergebnis ansehen: `sqlite3 test.db "SELECT * FROM bestand"`
   ([[Datenbank-mit-sqlite3-ansehen]]).

Erwartet: Der Lauf geht ohne Fehler durch.

**Wahl-Optionen:**

- `test.db` als eigene Datei für den Prüflauf (so in der
  Herkunft).
- `lager.db` lässt sich ebenso aus `beispieldaten.txt` neu
  erzeugen (Commit `3b6f6b4`).

**Grenzen:** Wann der Prüflauf vor einem Commit fällig ist
und wie sein Ergebnis in die Commit-Nachricht kommt, regelt
die Werkzeug-Kadenz (MasterPrompt §6.1; das Kommando steht in
`Tools.md` §Kadenz-Werkzeuge); ein rotes Ergebnis blockiert
nicht ([[Berichten-statt-Blockieren]]).

## Herkunft

Bestand aus `spezifikation.md` §Prüfen,
`CLAUDE-vorgefunden.md` §Commits („läuft ohne Fehler
durch“), `.gitignore` (Kommentar zur Testdatenbank) und
Commit `3b6f6b4`.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Nur die automatischen Tests: [[Testen-mit-Maven]]
- Laufzeitdaten nicht im Repository:
  [[Build-und-Laufzeitdaten-nicht-im-Repository]]
