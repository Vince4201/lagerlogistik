---
type: function-description
id: FUNC-004
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-008, REQ-012, REQ-013]
---

# FUNC-004 — Wareneingang

Der Wareneingang (Einlagern, Menüpunkt 1) bucht eine Menge eines
Artikels auf einen Ziel-Lagerort zu. Es gilt der
[[FUNC-003 Gemeinsamer Buchungsablauf]].

## Verhalten

- **Fragt** nacheinander: Artikel-Nr., Nach Lagerort (das Ziel), Menge,
  Bemerkung (optional, z. B. Lieferschein).
- **Prüft:** Artikel und Lagerort existieren und sind aktiv, Menge > 0.
- **Verändert:** neue Bewegung der Art „Eingang“ mit dem Ziel als
  Nach-Lagerort.
- **Gibt aus:** nach der Wahl des Artikels seinen Gesamtbestand,
  Mindestbestand und Bestand je Lagerort; nach der Buchung
  `Gebucht (#<Nr>): Eingang <Menge> <Einheit> <Artikel> nach <Lagerort>`
  und den neuen Gesamtbestand.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-004)
- Basiert auf: REQ-008, REQ-012, REQ-013 ([[Anforderungen]])
- Siehe auch: [[FUNC-003 Gemeinsamer Buchungsablauf]] — Regeln jeder
  Buchung
- Erwähnt in: [[SPEC-001 Datenbankschema]], [[ARC-001 Datenmodell]] —
  Belegung der Lagerorte je Art
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Wareneingang; [spezifikation.md](../spezifikation.md) §Hauptmenü,
  1 Wareneingang
