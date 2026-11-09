---
type: function-description
id: FUNC-006
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-010, REQ-012, REQ-013, REQ-014, REQ-015, REQ-019]
---

# FUNC-006 — Umlagerung

Die Umlagerung (Menüpunkt 3) bucht eine Menge eines Artikels von einem
Quell-Lagerort auf einen anderen Ziel-Lagerort um. Der Gesamtbestand
bleibt gleich, deshalb gibt es keine Mindestbestandsprüfung. Es gilt der
[[FUNC-003 Gemeinsamer Buchungsablauf]].

## Verhalten

- **Voraussetzung:** mindestens zwei aktive Lagerorte (sonst
  Fehlermeldung).
- **Fragt** nacheinander: Artikel-Nr., Von Lagerort (die Quelle),
  Nach Lagerort (das Ziel), Menge, Bemerkung (optional).
- **Prüft:** der Artikel hat Bestand; an der Quelle liegt etwas von ihm;
  Ziel ≠ Quelle (sonst wird das Ziel neu abgefragt);
  1 ≤ Menge ≤ Bestand an der Quelle.
- **Verändert:** neue Bewegung der Art „Umlagerung“ mit der Quelle als
  Von-Lagerort und dem Ziel als Nach-Lagerort.
- **Gibt aus:** `Gebucht (#<Nr>): Umlagerung … von <Quelle> nach <Ziel>`
  und den unveränderten Gesamtbestand. Nie eine Mindestbestandswarnung.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-006)
- Basiert auf: REQ-010, REQ-012, REQ-013, REQ-014, REQ-015, REQ-019
  ([[Anforderungen]])
- Siehe auch: [[FUNC-003 Gemeinsamer Buchungsablauf]] — Regeln jeder
  Buchung
- Erwähnt in: [[SPEC-001 Datenbankschema]], [[ARC-001 Datenmodell]] —
  Belegung der Lagerorte je Art
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Umlagerung; [spezifikation.md](../spezifikation.md) §Hauptmenü,
  3 Umlagerung
