---
type: function-description
id: FUNC-009
status: offen
status_date: 2026-11-09
abgeleitet_von: [REQ-021, REQ-022, REQ-023, REQ-024, REQ-025, REQ-026]
---

# FUNC-009 — Stammdatenpflege

Über das Stammdatenmenü (Menüpunkt 9) werden Artikel und Lagerorte
angezeigt, angelegt, bearbeitet, reaktiviert, gelöscht oder
deaktiviert. Nummer bzw. Code werden beim Anlegen vergeben und lassen
sich danach nicht mehr ändern. Gibt es zu einem Datensatz schon
Bewegungen, wird er nicht gelöscht, sondern nur deaktiviert.

## Stammdatenmenü

```
--- Stammdaten ---
  1  Artikel anzeigen
  2  Artikel anlegen
  3  Artikel bearbeiten / reaktivieren
  4  Artikel löschen / deaktivieren
  5  Lagerorte anzeigen
  6  Lagerort anlegen
  7  Lagerort bearbeiten / reaktivieren
  8  Lagerort löschen / deaktivieren
  0  Zurück
```

0 oder eine leere Eingabe führt zurück ins Hauptmenü, eine andere
Eingabe ergibt „Bitte eine Zahl von 0 bis 8 eingeben.“

## Anzeigen (Punkte 1 und 5)

- **Artikel anzeigen:** Tabelle aller Artikel, auch der deaktivierten:
  Artikel, Bezeichnung, Einheit, Mindestbestand, Status
  (aktiv/deaktiviert).
- **Lagerorte anzeigen:** Tabelle aller Lagerorte, auch der
  deaktivierten: Lagerort, Bezeichnung, Status.

## Anlegen und Bearbeiten (Punkte 2, 3, 6, 7)

Für Artikel und Lagerorte gilt:

- Nummer bzw. Code werden beim Anlegen vergeben und lassen sich danach
  nicht mehr ändern.
- Bearbeiten ändert die Bezeichnung, beim Artikel auch Einheit und
  Mindestbestand.
- Ist der Datensatz deaktiviert, wird beim Bearbeiten angeboten, ihn
  wieder zu aktivieren.
- Liegt ein Artikel nach dem Anlegen oder Bearbeiten unter dem
  Mindestbestand, kommt ein Hinweis.

Die Befehle im Einzelnen:

- **Artikel anlegen** (REQ-021, REQ-023): fragt Artikel-Nr.,
  Bezeichnung, Einheit [Stk], Mindestbestand [0]; prüft die erlaubten
  Zeichen der Nummer und dass es sie noch nicht gibt (sonst wird die
  Frage wiederholt); legt einen neuen aktiven Artikel an; gibt
  „Angelegt: …“ aus und, wenn der Bestand schon unter dem
  Mindestbestand liegt, einen Hinweis.
- **Artikel bearbeiten / reaktivieren** (REQ-023, REQ-026): fragt die
  Artikel-Nr. (auch deaktivierte), bei einem deaktivierten
  „Wieder aktivieren? (j/n)“, dann Bezeichnung, Einheit und
  Mindestbestand mit dem bisherigen Wert als Vorgabe; setzt den Artikel
  gegebenenfalls wieder aktiv und speichert Bezeichnung, Einheit,
  Mindestbestand; gibt „Reaktiviert.“, „Gespeichert.“ und bei einem
  aktiven Artikel unter dem Mindestbestand einen Hinweis aus.
- **Lagerort anlegen** (REQ-022, REQ-023): fragt Lagerort-Code und
  Bezeichnung (optional); prüft die erlaubten Zeichen des Codes und dass
  es ihn noch nicht gibt (sonst wird die Frage wiederholt); legt einen
  neuen aktiven Lagerort an; gibt „Angelegt: Lagerort …“ aus.
- **Lagerort bearbeiten / reaktivieren** (REQ-023, REQ-026): fragt den
  Lagerort (auch deaktivierte), bei einem deaktivierten
  „Wieder aktivieren? (j/n)“, dann die Bezeichnung mit dem bisherigen
  Wert als Vorgabe; setzt den Lagerort gegebenenfalls wieder aktiv und
  speichert die Bezeichnung; gibt „Reaktiviert.“, „Gespeichert.“ aus.

## Löschen oder Deaktivieren (Punkte 4 und 8)

Anforderungen: REQ-024, REQ-025.

1. Der Datensatz muss aktiv sein; zur Wahl stehen nur aktive.
2. Artikel: Der Gesamtbestand muss 0 sein (sonst Fehlermeldung
   „… hat noch N … Bestand“). Lagerort: Es darf kein Artikel mehr dort
   liegen (sonst Fehlermeldung „… liegen noch N Artikel“).
3. Gibt es noch keine Bewegungen dazu, wird er endgültig gelöscht. Sonst
   wird er nur deaktiviert.

Vorher wird angezeigt, ob gelöscht oder deaktiviert wird, und eine
Bestätigung (j/n) erfragt; bei „n“ ändert sich nichts. Ausgabe:
„Gelöscht.“, „Deaktiviert.“ oder „Nichts geändert.“

## Bezüge

- Gehört zu: [[Funktionsbeschreibungen]] (Fassaden-Eintrag FUNC-009)
- Basiert auf: REQ-021, REQ-022, REQ-023, REQ-024, REQ-025, REQ-026
  ([[Anforderungen]])
- Siehe auch: [[FUNC-002 Bedienung im Terminal]] — Feldregeln für
  Nummer, Code, Bezeichnung, Einheit und Mindestbestand
- Erwähnt in: [[SPEC-001 Datenbankschema]] — Tabellen `artikel` und
  `lagerort`; [[ARC-001 Datenmodell]]
- Herkunft (Bestand):
  [datenmodell-und-ablaeufe.md](../datenmodell-und-ablaeufe.md)
  §Artikel oder Lagerort anlegen und bearbeiten, §Artikel oder Lagerort
  entfernen; [spezifikation.md](../spezifikation.md) §Stammdatenmenü
  mit den Punkten 1 bis 8
