---
type: internal-pattern
---

# Datenbank mit sqlite3 ansehen

**Zweck:** in die SQLite-Datenbank schauen, vor allem auf den
berechneten Bestand.

**Voraussetzung:** das Kommandozeilenwerkzeug `sqlite3`.

**Schritte:** `sqlite3 lager.db "SELECT * FROM bestand"` —
die View `bestand (artikel, lagerort, menge)` berechnet je
Artikel und Lagerort Zugänge minus Abgänge
([[DEC-004 Bestand nur aus den Bewegungen berechnet]]).

**Wahl-Optionen:**

- **Datei:** `lager.db` (Laufzeitdaten) oder `test.db` nach
  dem [[Prueflauf-mit-Beispieldaten]].
- **Abfrage:** die View `bestand` oder die Tabellen
  `artikel`, `lagerort` und `bewegung` (Schema:
  `spezifikation.md` §Datenbankschema).

**Grenzen:** Trigger lehnen UPDATE und DELETE auf `bewegung`
ab (DEC-005).

## Herkunft

Bestand aus `spezifikation.md` §Technik und Start („In die
Datenbank schauen: `sqlite3 lager.db "SELECT * FROM
bestand"`“), §Datenbankschema und §Prüfen.

## Bezüge

- Fassaden-Eintrag: [[InterneMechanik]]
- Entscheidung: [[DEC-008 SQLite als Datenbank ueber sqlite-jdbc]]
