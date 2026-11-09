---
type: internal-pattern
---

# Testen mit Maven

**Zweck:** im Arbeitsablauf alle automatischen Tests
ausführen, ohne das Programm zu starten.

**Schritte:** im Projektordner `mvn -q -B test` aufrufen;
erwartet: alle Tests grün. Befehle, Werkzeuge und Erwartung
führt [[Softwaresystemtest-Setup]] §Ausführung (führender
Ort), den Aufbau der Tests dieselbe Fassade.

**Wahl-Optionen:**

- `mvn -q -B test` — nur die Tests.
- `./lager.sh` — baut mit Tests und startet nur, wenn alle
  grün sind (DEC-012, [[Bauen-und-Starten-mit-lager-sh]]).

**Konvention:** Der Name jedes Tests beginnt mit der Nummer
der Anforderung, die er prüft
([[Testname-mit-Anforderungsnummer]]).

**Grenzen:** Wann der Testlauf vor einem Commit fällig ist
und wie sein Ergebnis in die Commit-Nachricht kommt, regelt
die Werkzeug-Kadenz (MasterPrompt §6.1; das Kommando steht in
`Tools.md` §Kadenz-Werkzeuge); ein rotes Ergebnis blockiert
nicht ([[Berichten-statt-Blockieren]]).

## Herkunft

Bestand aus `spezifikation.md` §Prüfen,
`CLAUDE-vorgefunden.md` §Commits
(Prüfung vor Code- und Test-Commits) und Commit `2eb6d29`.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Entscheidung: [[DEC-011 Tests mit JUnit Jupiter ueber Surefire]]
- Kompletter Durchlauf über die Bedienung:
  [[Prueflauf-mit-Beispieldaten]]
