---
type: mission
# active: false   # bei Deaktivierung (Default ist active: true)
---

# Mission Statement

## Meta

- **Typ:** `mission` — Projekt-Überblick (Kurzform)
  für Menschen und externe Agenten. Singleton.
- **Nicht Sessionstart-Lektüre:** diese Datei wird beim
  Sessionstart nicht automatisch gelesen — sie ist der
  context-window-schonende Einstieg für neue Leser und
  externe Agenten.
- **Pflege:** über den Setup-Dialog (Wiki.md §3a.2);
  Änderungen mit User-Freigabe.
- **Langform:** für die ausführliche Version siehe
  [[Mission Statement Long]] (falls aktiviert).

---

**Bearbeitungsstand:** 2026-11-09 — Entwurf aus der Migration,
abgeleitet aus den vorgefundenen Dateien; Bestätigung durch den
Anwender im Setup-Dialog steht aus (`../Wiki.md` §3a.2).

## Projekt

**Lagerverwaltung** — eine kleine Einzelplatz-Lagerverwaltung für
das Terminal. Sie weiß jederzeit, welcher Artikel in welcher Menge
an welchem Lagerort liegt, und macht jede Bestandsänderung
nachvollziehbar: Jede Änderung ist eine Bewegung im Protokoll, der
Bestand wird daraus berechnet, gebuchte Bewegungen werden nie
geändert oder gelöscht. Es geht nur um Mengen, nicht um Preise.

Version 1 ist in Java 21 mit SQLite gebaut, wird über Menüs im
Terminal bedient und ist durch JUnit-Tests je Anforderung
abgesichert.

## Beteiligte

Ein Benutzer, der das Lager am eigenen Rechner führt
([[Rollen]]). Weitere Beteiligte sind nicht erfasst.

## Zentrale Themen

- Was das Programm können muss: [[Anforderungen]]
- Was es aus Sicht des Benutzers tut: [[Funktionsbeschreibungen]]
- Wie es gebaut ist: [[Softwaresystemarchitektur]],
  [[Spezifikationen]]
- Wie es geprüft wird: [[Testfaelle]], [[Softwaresystemtest-Setup]]
- Festlegungen: [[Entscheidungen]]

## Weiteres

Das Projekt entstand zunächst nach dem „naiven Ansatz“ mit einer
Dokumentenkette und wurde am 2026-11-09 auf den Referenzprozess
Softwareentwicklung migriert (Marke `Uebernahme-2026-11-09`,
[[Zielmarken]]). Die vorgefundenen Dokumente bleiben als Nachweis
unverändert im Projekt-Root liegen.
