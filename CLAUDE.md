# Lagerverwaltung

Terminal-Lagerverwaltung in Java 21 mit SQLite. Starten mit `./lager.sh`, das bei Bedarf vorher
mit Maven baut.

## Dokumentenkette

Jeder Schritt hat ein eigenes Dokument, jedes baut auf dem vorigen auf:

1. [anforderungen.md](anforderungen.md): was das Programm können muss, ohne Technik. Nummeriert,
   je Anforderung ein Satz. Die Nummern bleiben fest, die späteren Glieder verweisen darauf.
2. [datenmodell-und-ablaeufe.md](datenmodell-und-ablaeufe.md): welche Dinge es gibt, welche
   Eigenschaften sie haben, wie sie zusammenhängen, und die Abläufe Einlagern, Auslagern, Auswerten.
3. [spezifikation.md](spezifikation.md): die Bauanleitung. Welche Befehle es gibt, was jeder Befehl
   prüft, was er verändert und was er ausgibt.
4. Implementierung: der Quelltext unter `src/`.
5. Test: JUnit-Tests unter `src/test/`. Jede Anforderung hat mindestens einen Test, dessen Name mit
   ihrer Nummer beginnt, z. B. `@DisplayName("A14 …")`. Kommt eine Anforderung dazu, kommt ihr Test
   dazu.

Änderungen laufen die Kette entlang: erst die Anforderung, dann Datenmodell und Abläufe, dann die
Spezifikation, zuletzt Code und Test. Ein späteres Glied widerspricht nie einem früheren. Eine
zusammenfassende KONZEPT.md gibt es nicht mehr.

## Git ist das Gedächtnis des Projekts

Von einer Sitzung zur nächsten weiß der Agent nichts mehr. Was gemacht wurde und warum, steht
deshalb in der Git-Historie und nirgendwo sonst.

**Zu Beginn jeder Sitzung**

- `git status`: Gibt es angefangene, nicht eingecheckte Änderungen? Dann zuerst klären, was damit
  passieren soll.
- `git log --oneline -15`: Was ist zuletzt passiert? Details mit `git show <commit>`.

**Commits**

- Ein Commit je Zug. Ein Zug ist eine thematisch abgeschlossene Einheit aus mehreren
  Arbeitsschritten, etwa „Anforderungen überarbeitet“. Nach jedem Zug committen, nicht erst am
  Ende, und keine Züge in einem Commit bündeln. Leere Commits gibt es nicht.
- Jede Commit-Nachricht beginnt mit einem Präfix aus diesem Katalog, dann Doppelpunkt und
  Kurzbeschreibung auf Deutsch:

  | Präfix | Wofür |
  |---|---|
  | `Setup:` | Einrichtung und Regeln: `CLAUDE.md`, `.gitignore`, Werkzeuge |
  | `Anforderungen:` | `anforderungen.md` |
  | `Datenmodell:` | `datenmodell-und-ablaeufe.md` |
  | `Spezifikation:` | `spezifikation.md` |
  | `Code:` | Quelltext unter `src/`, `pom.xml`, `lager.sh` |
  | `Test:` | Tests und Testdaten wie `beispieldaten.txt` |
  | `Sonstiges:` | alles andere |

- Unter der ersten Zeile steht, warum: welche Entscheidung getroffen wurde, was verworfen wurde,
  was noch offen ist. Diesen Teil braucht man später.
- Vor jedem `Code:`- und `Test:`-Commit prüfen und das Ergebnis in die Commit-Nachricht schreiben:
  `mvn -q -B test` (alle Tests grün) und `rm -f test.db && ./lager.sh test.db < beispieldaten.txt`
  (läuft ohne Fehler durch). Ein Fehlschlag blockiert den Commit nicht, muss aber in der Nachricht
  stehen.
- Ändert sich das Verhalten, werden die betroffenen Glieder der Kette im selben Zug angepasst.
- Am Ende der Sitzung ein Abschluss-Commit für alles, was noch nicht erfasst ist, damit der Ordner
  sauber ist. Ist schon alles committet, entfällt er.
- Keine Geheimnisse: Zugangsdaten, Passwörter und Schlüssel kommen in keine Datei, die committet
  wird.
- Kein Push ohne Anweisung. Was nach GitHub geht, entscheidet der Mensch.
- Die Geschichte wird nicht umgeschrieben: kein `git reset --hard`, kein `git push --force`, kein
  `git commit --amend` auf Commits, die schon auf GitHub liegen. Falsches nimmt `git revert` zurück.
- Experimente gehören auf einen eigenen Branch, nicht auf `main`.

**Nachschlagen**

- `git diff`: Was ist geändert und noch nicht vorgemerkt?
- `git log -p -- <datei>`: Geschichte einer Datei mit allen Änderungen.
- `git log --grep=<Begriff>`: Wann und warum kam etwas dazu?
- `git blame <datei>`: Aus welchem Commit stammt eine Zeile?
- `git show <commit>:<datei>`: eine alte Fassung ansehen, ohne etwas zu ändern.

**Nicht im Repository:** `target/` (Build) und `*.db` (Laufzeitdaten), siehe `.gitignore`.

GitHub: <https://github.com/Vince4201/lagerlogistik> (öffentlich).
