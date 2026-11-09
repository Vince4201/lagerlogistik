---
type: specification
id: SPEC-002
status: offen
status_date: 2026-11-09
abgeleitet_von: [FUNC-002]
---

# SPEC-002 — Sortierung und Zeitpunkte

Bauanleitung für die Anzeigeregeln aus
[[FUNC-002 Bedienung im Terminal#Sortierung und Anzeige]]: wie die
Sortierung nach dem Zahlenwert entsteht und wie genau Zeitpunkte
gespeichert werden.

## Umsetzung

- Artikel und Lagerorte werden zuerst nach dem Zahlenwert von Nummer
  bzw. Code sortiert (`CAST(... AS INTEGER)`), dann alphabetisch.
- Zeitpunkte werden sekundengenau gespeichert; das Anzeigeformat steht
  in FUNC-002.

## Bezüge

- Gehört zu: [[Spezifikationen]] (Fassaden-Eintrag SPEC-002)
- Setzt um: [[FUNC-002 Bedienung im Terminal]] (§Sortierung und Anzeige)
- Siehe auch: [[SPEC-001 Datenbankschema]] — Spalte `zeitpunkt` der
  Tabelle `bewegung`
- Herkunft (Bestand): [spezifikation.md](../spezifikation.md)
  §Sortierung und Anzeige (Umsetzungs-Teil)
