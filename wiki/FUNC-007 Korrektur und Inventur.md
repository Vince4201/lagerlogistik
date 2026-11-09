---
type: function-description
id: FUNC-007
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-011, REQ-016, REQ-017, REQ-018]
---

# FUNC-007 — Korrektur und Inventur

Korrektur / Inventur (Menüpunkt 4) gleicht den gebuchten Bestand eines
Artikels an einem Lagerort an den gezählten Bestand an. Gebucht wird
nur die Differenz, mit einer Begründung als Pflicht. Es gilt der
[[FUNC-003 Gemeinsamer Buchungsablauf]].

## Ablauf

1. Artikel-Nr. und Lagerort wählen; beide existieren und sind aktiv.
   Angezeigt wird wie beim [[FUNC-004 Wareneingang]] der Bestand des
   Artikels und vor der Zählung der gebuchte Bestand am Lagerort.
2. Gezählten Bestand eingeben (0 oder mehr).
3. Differenz = gezählt − gebucht. Ist sie 0, endet der Befehl mit
   „Kein Unterschied, es wird nichts gebucht.“, bevor die Begründung
   abgefragt wird.
4. Begründung eingeben (Pflicht).
5. Differenz > 0 (mehr gezählt): neue Bewegung der Art „Korrektur“ über
   die Differenz mit dem Lagerort als Nach-Lagerort.
   Differenz < 0 (weniger gezählt): neue Bewegung „Korrektur“ mit dem
   Lagerort als Von-Lagerort, danach wird der Mindestbestand geprüft.

## Ausgabe

Nach der Buchung
`Gebucht (#<Nr>): Korrektur ±<Differenz> <Einheit> <Artikel> an <Lagerort>`.
Bei einer Korrektur nach unten kommt wie beim
[[FUNC-005 Warenausgang]] gegebenenfalls die Warnung.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-007)
- Basiert auf: REQ-011, REQ-016, REQ-017, REQ-018 ([[Anforderungen]])
- Siehe auch: [[FUNC-003 Gemeinsamer Buchungsablauf]] — Regeln jeder
  Buchung
- Erwähnt in: [[SPEC-001 Datenbankschema]], [[ARC-001 Datenmodell]] —
  Belegung der Lagerorte je Art
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Korrektur / Inventur; [spezifikation.md](../spezifikation.md)
  §Hauptmenü, 4 Korrektur / Inventur
