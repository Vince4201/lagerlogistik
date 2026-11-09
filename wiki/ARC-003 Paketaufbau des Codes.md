---
type: architecture-description
id: ARC-003
status: offen
status_date: 2026-11-09
abgeleitet_von: [DEC-007, DEC-009, DEC-010, FUNC-001, FUNC-002, FUNC-003]
---

# ARC-003 — Paketaufbau des Codes

Dokumentierter Ist-Stand: Der Quelltext ist nach Zuständigkeit in
Pakete geteilt — Startpunkt, Datenmodell, Datenbankzugriff, Fachlogik
und Terminal-Oberfläche.

## Festlegung

| Paket | Inhalt |
|---|---|
| `lager` | `Main`: Startpunkt, öffnet die Datenbank und startet die Konsole |
| `lager.model` | Artikel, Lagerort, Bewegung und weitere Records |
| `lager.db` | SQLite-Zugriff: Schema mit View `bestand`, Repositories |
| `lager.service` | Fachlogik: Buchungen, Regeln, Mindestbestand |
| `lager.ui` | Menüs und Dialoge im Terminal |

## Begründung aus DEC und FUNC

Die Pakete bilden die Funktionen ab: den Startpunkt
([[FUNC-001 Programmstart]]), die Menüs und Dialoge
([[FUNC-002 Bedienung im Terminal]]) und die Fachlogik der Buchungen
und Regeln ([[FUNC-003 Gemeinsamer Buchungsablauf]]); `lager.model`
hält die Objekte aus [[ARC-001 Datenmodell]], das Schema in `lager.db`
beschreibt [[SPEC-001 Datenbankschema]]. Eine Begründung des Zuschnitts
ist in der Herkunfts-Datei nicht dokumentiert; Technologie-Festlegungen
(Java, SQLite) gehören nach [[Entscheidungen]].

## Vermerke

_(keine)_

## Bezüge

- Gehört zu: [[Softwaresystemarchitektur]] (Fassaden-Eintrag ARC-003,
  §Festlegungen; Überblick in §Ist-Stand)
- Siehe auch: [[ARC-001 Datenmodell]] — die Objekte in `lager.model`
- Herkunft (Bestand): [spezifikation.md](../spezifikation.md)
  §Aufbau des Codes
