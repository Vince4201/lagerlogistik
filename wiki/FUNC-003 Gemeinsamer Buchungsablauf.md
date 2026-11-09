---
type: function-description
id: FUNC-003
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-005, REQ-012, REQ-013, REQ-014, REQ-017, REQ-018]
---

# FUNC-003 — Gemeinsamer Buchungsablauf

Wareneingang, Warenausgang, Umlagerung und Korrektur buchen nach
demselben Ablauf. Eine Buchung wird ganz oder gar nicht ausgeführt: Sie
prüft Menge, Artikel, Lagerorte und den Bestand an der Quelle, speichert
die Bewegung und warnt, wenn der Gesamtbestand dadurch unter den
Mindestbestand fällt.

## Ablauf

1. Die Menge muss größer 0 sein.
2. Der Artikel muss existieren und aktiv sein.
3. Jeder beteiligte Lagerort muss existieren und aktiv sein.
4. Gibt es einen Von-Lagerort, darf die Menge den Bestand dort nicht
   übersteigen.
5. Die Bewegung wird mit dem aktuellen Zeitpunkt gespeichert.
6. Der Gesamtbestand des Artikels wird neu berechnet.
7. Hat die Buchung den Gesamtbestand gesenkt und liegt er jetzt unter
   dem Mindestbestand, kommt eine Warnung.

Scheitert einer der Schritte 1 bis 4, wird nichts gespeichert und der
Grund gemeldet.

## Buchungsarten

Einlagern heißt hier Wareneingang, Auslagern Warenausgang. Eingaben,
Prüfungen und Ausgabe je Art: [[FUNC-004 Wareneingang]],
[[FUNC-005 Warenausgang]], [[FUNC-006 Umlagerung]],
[[FUNC-007 Korrektur und Inventur]].

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-003)
- Basiert auf: REQ-005, REQ-012, REQ-013, REQ-014, REQ-017, REQ-018
  ([[Anforderungen]])
- Siehe auch: [[FUNC-002 Bedienung im Terminal]] — Eingaberegeln, die
  auch für Buchungen gelten
- Erwähnt in: [[SPEC-001 Datenbankschema]] — Transaktion, CHECK und
  Trigger; [[ARC-001 Datenmodell]],
  [[ARC-002 Bestand aus Bewegungen berechnet]],
  [[ARC-003 Paketaufbau des Codes]]
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Abläufe (Einleitung), §Buchung, gilt für alle vier Arten
