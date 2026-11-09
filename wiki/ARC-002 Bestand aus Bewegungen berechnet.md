---
type: architecture-description
id: ARC-002
status: offen
status_date: 2026-11-09
abgeleitet_von: [DEC-004, FUNC-003, FUNC-008]
---

# ARC-002 — Bestand aus Bewegungen berechnet

Dokumentierter Ist-Stand: Es gibt keine eigene Bestandstabelle. Jeder
Bestand ergibt sich aus den Bewegungen des [[ARC-001 Datenmodell]].

## Festlegung

- Bestand eines Artikels an einem Lagerort = Summe der Mengen, die
  dorthin gebucht wurden, minus Summe der Mengen, die von dort abgebucht
  wurden.
- Gesamtbestand eines Artikels = Summe seiner Bestände über alle
  Lagerorte.
- Eine Bewegung ändert den Gesamtbestand um +Menge (nur Nach-Lagerort),
  −Menge (nur Von-Lagerort) oder gar nicht (Umlagerung).

## Begründung aus DEC und FUNC

Weil es keine eigene Bestandstabelle gibt, können Bestand und Protokoll
nie voneinander abweichen (Begründung aus der Herkunfts-Datei). Der
[[FUNC-003 Gemeinsamer Buchungsablauf]] berechnet nach jeder Buchung
den Gesamtbestand neu, die [[FUNC-008 Auswertungen]] lesen die
Bestände. Die Festlegung samt verworfener Alternative (eine eigene
Bestandstabelle) steht in [[DEC-004 Bestand nur aus den Bewegungen berechnet]].

## Vermerke

_(keine)_

## Bezüge

- Gehört zu: [[Softwaresystemarchitektur]] (Fassaden-Eintrag ARC-002,
  §Festlegungen; Überblick in §Ist-Stand)
- Siehe auch: [[ARC-001 Datenmodell]]
- Erwähnt in: [[SPEC-001 Datenbankschema]] — View `bestand`
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Datenmodell (Einleitung), §Bestand
