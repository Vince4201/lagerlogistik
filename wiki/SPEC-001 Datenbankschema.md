---
type: specification
id: SPEC-001
status: offen
status_date: 2026-11-09
abgeleitet_von: [FUNC-001, FUNC-003, FUNC-004, FUNC-005, FUNC-006, FUNC-007, FUNC-008, FUNC-009]
---

# SPEC-001 — Datenbankschema

Bauanleitung der Datenhaltung: Die Lagerverwaltung speichert ihre Daten
in einer SQLite-Datenbank mit den Tabellen `artikel`, `lagerort` und
`bewegung`. Fremdschlüssel, CHECK und Trigger sichern das
[[ARC-001 Datenmodell]], die View `bestand` berechnet die Bestände, und
jeder ändernde Befehl läuft in einer Transaktion.

## Schema

Das Schema wird beim Öffnen angelegt, soweit es fehlt
(`lager.db.Datenbank`). Fremdschlüssel sind eingeschaltet
(`PRAGMA foreign_keys = ON`).

| Tabelle | Spalten |
|---|---|
| `artikel` | `nummer` TEXT PK, `bezeichnung` TEXT, `einheit` TEXT, `mindestbestand` INTEGER ≥ 0 (Standard 0), `aktiv` 0/1 (Standard 1) |
| `lagerort` | `code` TEXT PK, `bezeichnung` TEXT, `aktiv` 0/1 (Standard 1) |
| `bewegung` | `nr` INTEGER PK AUTOINCREMENT, `zeitpunkt` TEXT, `art` TEXT, `artikel` → `artikel`, `menge` INTEGER > 0, `von_lagerort` → `lagerort`, `nach_lagerort` → `lagerort`, `bemerkung` TEXT (Standard leer) |

## Integritätsregeln

- `art` ist `EINGANG`, `AUSGANG`, `UMLAGERUNG` oder `KORREKTUR`. Ein
  CHECK erzwingt die Belegung von `von_lagerort` und `nach_lagerort`
  nach der Tabelle
  [[ARC-001 Datenmodell#Menge und Richtung einer Bewegung]].
- Die Trigger `bewegung_nicht_aendern` und `bewegung_nicht_loeschen`
  lehnen UPDATE und DELETE auf `bewegung` ab (REQ-006).
- Die View `bestand (artikel, lagerort, menge)` berechnet Zugänge minus
  Abgänge je Artikel und Lagerort (REQ-005;
  [[ARC-002 Bestand aus Bewegungen berechnet]]).
- Jeder Befehl, der etwas verändert, läuft in einer Transaktion: ganz
  oder gar nicht.

## Bezüge

- Gehört zu: [[Spezifikationen]] (Fassaden-Eintrag SPEC-001)
- Setzt um: [[FUNC-001 Programmstart]] (Anlage beim Öffnen),
  [[FUNC-003 Gemeinsamer Buchungsablauf]] (Transaktion, Menge > 0),
  [[FUNC-004 Wareneingang]], [[FUNC-005 Warenausgang]],
  [[FUNC-006 Umlagerung]], [[FUNC-007 Korrektur und Inventur]]
  (Belegung der Lagerorte je Art), [[FUNC-008 Auswertungen]] (View
  `bestand`), [[FUNC-009 Stammdatenpflege]] (Tabellen `artikel`,
  `lagerort`)
- Basiert auf: [[ARC-001 Datenmodell]],
  [[ARC-002 Bestand aus Bewegungen berechnet]]; REQ-005, REQ-006
  ([[Anforderungen]])
- Siehe auch: [[SPEC-002 Sortierung und Zeitpunkte]]
- Herkunft (Bestand): [spezifikation.md](../spezifikation.md)
  §Datenbankschema
