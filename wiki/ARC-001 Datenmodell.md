---
type: architecture-description
id: ARC-001
status: offen
status_date: 2026-11-09
abgeleitet_von: [DEC-004, DEC-005, DEC-006, DEC-008, FUNC-003, FUNC-004, FUNC-005, FUNC-006, FUNC-007, FUNC-009]
---

# ARC-001 — Datenmodell

Dokumentierter Ist-Stand: Gespeichert werden Artikel, Lagerorte und
Bewegungen. Der Bestand wird nicht gespeichert, sondern aus den
Bewegungen berechnet ([[ARC-002 Bestand aus Bewegungen berechnet]]).

## Festlegung

| Objekt | Attribute | Hinweise |
|---|---|---|
| Artikel | Nummer (Schlüssel), Bezeichnung, Einheit, Mindestbestand, aktiv | Mindestbestand ≥ 0 |
| Lagerort | Code (Schlüssel), Bezeichnung, aktiv | Bezeichnung darf leer sein |
| Bewegung | Nr. (fortlaufend), Zeitpunkt, Art, Artikel, Menge, Von-Lagerort, Nach-Lagerort, Bemerkung | unveränderlich |

Jede Bewegung gehört zu genau einem Artikel und hat einen Von-Lagerort,
einen Nach-Lagerort oder beide. Die n:m-Beziehung zwischen Artikel und
Lagerort entsteht über die Bewegungen, eine eigene Zuordnungstabelle
gibt es nicht.

### Menge und Richtung einer Bewegung

Die Menge ist immer positiv. Die Richtung steckt in den Lagerorten: Am
Von-Lagerort wird abgebucht, am Nach-Lagerort zugebucht.

| Art | Von-Lagerort | Nach-Lagerort |
|---|---|---|
| Eingang | – | Ziel |
| Ausgang | Quelle | – |
| Umlagerung | Quelle | Ziel, verschieden von der Quelle |
| Korrektur | der Lagerort, wenn weniger gezählt wurde | der Lagerort, wenn mehr gezählt wurde |

Bei einer Korrektur ist immer genau einer der beiden Lagerorte belegt.

## Begründung aus DEC und FUNC

Das Datenmodell trägt den [[FUNC-003 Gemeinsamer Buchungsablauf]] mit
den Buchungsarten [[FUNC-004 Wareneingang]], [[FUNC-005 Warenausgang]],
[[FUNC-006 Umlagerung]] und [[FUNC-007 Korrektur und Inventur]] sowie
die [[FUNC-009 Stammdatenpflege]]. Die Herkunfts-Datei ordnet es
REQ-004 bis REQ-007, REQ-021 und REQ-022 zu; eine Abwägung von
Alternativen ist dort nicht dokumentiert.

## Vermerke

_(keine)_

## Bezüge

- Gehört zu: [[Softwaresystemarchitektur]] (Fassaden-Eintrag ARC-001,
  §Festlegungen; Überblick in §Ist-Stand)
- Siehe auch: [[ARC-002 Bestand aus Bewegungen berechnet]]
- Erwähnt in: [[SPEC-001 Datenbankschema]] — Umsetzung als Tabellen mit
  CHECK und Triggern; [[ARC-003 Paketaufbau des Codes]] — Paket
  `lager.model`
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Datenmodell, §Menge und Richtung einer Bewegung
