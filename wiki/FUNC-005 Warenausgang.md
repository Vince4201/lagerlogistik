---
type: function-description
id: FUNC-005
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-009, REQ-012, REQ-013, REQ-014, REQ-017, REQ-018]
---

# FUNC-005 — Warenausgang

Der Warenausgang (Auslagern, Menüpunkt 2) bucht eine Menge eines
Artikels von einem Quell-Lagerort ab und warnt, wenn der Gesamtbestand
danach unter dem Mindestbestand liegt. Es gilt der
[[FUNC-003 Gemeinsamer Buchungsablauf]].

## Verhalten

- **Fragt** nacheinander: Artikel-Nr., Aus Lagerort (die Quelle),
  Menge, Bemerkung (optional, z. B. Auftrag).
- **Prüft:** Artikel und Lagerort existieren und sind aktiv; der Artikel
  hat überhaupt Bestand (sonst Fehlermeldung); am gewählten Lagerort
  liegt etwas von ihm (sonst wird die Frage wiederholt), Quelle ist also
  nur ein Lagerort, an dem der Artikel liegt; 1 ≤ Menge ≤ Bestand dort.
- **Verändert:** neue Bewegung der Art „Ausgang“ mit der Quelle als
  Von-Lagerort; danach wird der Mindestbestand geprüft.
- **Gibt aus:** wie der [[FUNC-004 Wareneingang]], mit
  `Ausgang … aus <Lagerort>`. Liegt der Gesamtbestand danach unter dem
  Mindestbestand, steht statt des Gesamtbestands der Block
  „WARNUNG: Mindestbestand unterschritten“ mit Artikel, Gesamtbestand,
  Mindestbestand und Fehlmenge.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-005)
- Basiert auf: REQ-009, REQ-012, REQ-013, REQ-014, REQ-017, REQ-018
  ([[Anforderungen]])
- Siehe auch: [[FUNC-003 Gemeinsamer Buchungsablauf]] — Regeln jeder
  Buchung; [[FUNC-007 Korrektur und Inventur]] — dieselbe Warnung bei
  einer Korrektur nach unten
- Erwähnt in: [[SPEC-001 Datenbankschema]], [[ARC-001 Datenmodell]] —
  Belegung der Lagerorte je Art
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Warenausgang; [spezifikation.md](../spezifikation.md) §Hauptmenü,
  2 Warenausgang
