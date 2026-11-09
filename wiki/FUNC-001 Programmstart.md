---
type: function-description
id: FUNC-001
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-020]
---

# FUNC-001 — Programmstart

Beim Start öffnet die Lagerverwaltung ihre Datenbankdatei und legt sie
an, wenn sie fehlt. Bevor das Hauptmenü erscheint, weist sie auf
fehlende Stammdaten und auf Artikel unter dem Mindestbestand hin.

## Ablauf

1. Die Datenbankdatei wird geöffnet. Fehlt sie, wird sie angelegt,
   fehlende Tabellen ebenso.
2. Gibt es noch keine Artikel, kommt der Hinweis, zuerst Stammdaten
   anzulegen („Noch keine Artikel vorhanden …“).
3. Liegen Artikel unter dem Mindestbestand, kommt ein Hinweis mit ihrer
   Anzahl: „Hinweis: N Artikel liegen unter dem Mindestbestand
   (Menüpunkt 7).“
4. Das Hauptmenü erscheint ([[FUNC-002 Bedienung im Terminal]]).

## Ausgabe

- den Pfad der Datenbank, mit „(neu angelegt)“, wenn sie neu ist,
- einen Tipp zur Bedienung,
- gegebenenfalls die Hinweise aus Schritt 2 und 3,
- dann das Hauptmenü.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-001)
- Basiert auf: REQ-020 ([[Anforderungen]])
- Siehe auch: [[FUNC-008 Auswertungen]] — Menüpunkt 7 „Artikel unter
  Mindestbestand“
- Erwähnt in: [[SPEC-001 Datenbankschema]] — Anlage des Schemas beim
  Öffnen; [[ARC-003 Paketaufbau des Codes]] — Startpunkt `lager.Main`
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Programmstart; [spezifikation.md](../spezifikation.md) §Programmstart
