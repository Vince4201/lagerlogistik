---
type: decision
id: DEC-004
status: umgesetzt
status_date: 2026-11-09
---

# DEC-004 Bestand nur aus den Bewegungen berechnet

Bestand aus der Dokumentenkette (Migration, Phase 3) —
ohne Zielmarke und Anlage-Datum.

## Kontext und Problem

Jede Bestandsänderung wird als Bewegung protokolliert, und
der Bestand ergibt sich allein aus den Bewegungen (REQ-005).
Das Datenmodell legt fest, wie der Bestand zu den Bewegungen
steht.

## Betrachtete Optionen

- Bestand aus den Bewegungen berechnen (gewählt)
- eigene Bestandstabelle neben den Bewegungen — genannt in
  `datenmodell-und-ablaeufe.md` §Bestand

## Entscheidung

Gespeichert werden Artikel, Lagerorte und Bewegungen. Der
Bestand wird nicht gespeichert, sondern aus den Bewegungen
berechnet: Bestand eines Artikels an einem Lagerort ist die
Summe der Mengen, die dorthin gebucht wurden, minus die Summe
der Mengen, die von dort abgebucht wurden; der Gesamtbestand
ist die Summe über alle Lagerorte.

Begründung der Quelle: „Weil es keine eigene Bestandstabelle
gibt, können Bestand und Protokoll nie voneinander abweichen.“
(`datenmodell-und-ablaeufe.md` §Bestand)

## Folgen

- Die n:m-Beziehung zwischen Artikel und Lagerort (REQ-004)
  entsteht über die Bewegungen; eine eigene Zuordnungstabelle
  gibt es nicht (`datenmodell-und-ablaeufe.md` §Datenmodell).
- Umsetzung: Die View `bestand (artikel, lagerort, menge)`
  berechnet Zugänge minus Abgänge je Artikel und Lagerort
  (`spezifikation.md` §Datenbankschema); so seit dem ersten
  Commit `3b6f6b4` („Bestand als View über dem
  Bewegungsprotokoll“).
- `lager.db.DatenbankTest` prüft die View `bestand`
  (`spezifikation.md` §Prüfen).

## Verworfen und warum

- **Eigene Bestandstabelle:** Bestand und Protokoll könnten
  voneinander abweichen (`datenmodell-und-ablaeufe.md`
  §Bestand).

## Verweise

- Herkunft: `anforderungen.md` A05 und A04 (REQ-005,
  REQ-004); `datenmodell-und-ablaeufe.md` §Datenmodell,
  §Bestand; `spezifikation.md` §Datenbankschema, §Prüfen;
  Commit `3b6f6b4`
- Nachbarn: DEC-005 (Bewegungen unveränderlich), DEC-006
  (deaktivieren statt löschen), [[DEC-008 SQLite als Datenbank ueber sqlite-jdbc]]

## Vermerke

_(append-only)_

## Bezüge

- Fassaden-Eintrag: [[Entscheidungen]]
- Datenbank ansehen: [[Datenbank-mit-sqlite3-ansehen]]
