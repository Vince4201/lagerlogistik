---
type: function-description
id: FUNC-008
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-027, REQ-028, REQ-029, REQ-030]
---

# FUNC-008 — Auswertungen

Die Auswertungen (Menüpunkte 5 bis 8) zeigen Bestände, Fehlmengen und
Bewegungen. Sie lesen nur und ändern nichts. Artikel und Lagerort lassen
sich als Filter angeben, ohne Filter gilt die Auswertung für alle; eine
leere Filter-Eingabe heißt „alle“.

## Bestand je Artikel (Menüpunkt 5)

Anforderung: REQ-027.

- **Fragt:** Artikel-Nr. als Filter (leer = alle).
- **Gibt aus:** mit Filter Gesamtbestand, Mindestbestand und Bestand je
  Lagerort dieses Artikels. Ohne Filter eine Tabelle aller aktiven
  Artikel: Artikel, Bezeichnung, Bestand, Mindestbestand, Einheit,
  Status; Artikel unter dem Mindestbestand sind mit
  „!! unter Mindestbestand“ markiert.

## Bestand je Lagerort (Menüpunkt 6)

Anforderung: REQ-028.

- **Fragt:** Lagerort als Filter (leer = alle).
- **Gibt aus:** Tabelle Lagerort, Artikel, Bezeichnung, Menge, Einheit
  mit allen Beständen ungleich 0, für einen Lagerort oder für alle. Ohne
  Filter danach „Leere Lagerorte: …“ mit den aktiven Lagerorten, an
  denen nichts liegt.

## Artikel unter Mindestbestand (Menüpunkt 7)

Anforderung: REQ-029.

- **Gibt aus:** Tabelle Artikel, Bezeichnung, Bestand, Mindestbestand,
  Fehlmenge, Einheit für alle aktiven Artikel, deren Gesamtbestand
  kleiner als der Mindestbestand ist; Fehlmenge = Mindestbestand −
  Gesamtbestand. Liegt kein Artikel darunter:
  „Kein Artikel liegt unter dem Mindestbestand.“

## Bewegungsprotokoll (Menüpunkt 8)

Anforderung: REQ-030.

- **Fragt:** Artikel-Nr. und Lagerort als Filter (leer = alle), einzeln
  oder beide. Der Lagerort trifft Bewegungen, bei denen er
  Von- oder Nach-Lagerort ist.
- **Gibt aus:** Tabelle Nr, Zeitpunkt, Art, Artikel, Menge, Von, Nach,
  Bemerkung in Buchungsreihenfolge, danach die Anzahl. Die Menge steht
  mit Vorzeichen (+ Zugang, − Abgang), bei Umlagerungen ohne.

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-008)
- Basiert auf: REQ-027, REQ-028, REQ-029, REQ-030 ([[Anforderungen]])
- Siehe auch: [[FUNC-002 Bedienung im Terminal]] — Filter-Eingabe,
  Sortierung und Zeitformat
- Erwähnt in: [[SPEC-001 Datenbankschema]] — View `bestand`;
  [[ARC-002 Bestand aus Bewegungen berechnet]]
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Auswerten; [spezifikation.md](../spezifikation.md) §Hauptmenü,
  5 Bestand je Artikel, 6 Bestand je Lagerort, 7 Artikel unter
  Mindestbestand, 8 Bewegungsprotokoll
