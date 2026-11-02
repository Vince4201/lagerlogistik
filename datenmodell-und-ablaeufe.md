# Lagerverwaltung – Datenmodell und Abläufe

Zweites Dokument der Kette [anforderungen.md](anforderungen.md) → **datenmodell-und-ablaeufe.md**
→ [spezifikation.md](spezifikation.md). Hier steht, wie die Anforderungen in Daten und Abläufe
übersetzt werden.

## Datenmodell

Gespeichert werden Artikel, Lagerorte und Bewegungen. Der Bestand wird nicht gespeichert, sondern
aus den Bewegungen berechnet.

| Objekt | Attribute | Hinweise |
|---|---|---|
| Artikel | Nummer (Schlüssel), Bezeichnung, Einheit, Mindestbestand, aktiv | Mindestbestand ≥ 0 |
| Lagerort | Code (Schlüssel), Bezeichnung, aktiv | Bezeichnung darf leer sein |
| Bewegung | Nr. (fortlaufend), Zeitpunkt, Art, Artikel, Menge, Von-Lagerort, Nach-Lagerort, Bemerkung | unveränderlich |

Jede Bewegung gehört zu genau einem Artikel und hat einen Von-Lagerort, einen Nach-Lagerort oder
beide. Die n:m-Beziehung zwischen Artikel und Lagerort entsteht über die Bewegungen, eine eigene
Zuordnungstabelle gibt es nicht.

### Menge und Richtung einer Bewegung

Die Menge ist immer positiv. Die Richtung steckt in den Lagerorten: Am Von-Lagerort wird abgebucht,
am Nach-Lagerort zugebucht.

| Art | Von-Lagerort | Nach-Lagerort |
|---|---|---|
| Eingang | – | Ziel |
| Ausgang | Quelle | – |
| Umlagerung | Quelle | Ziel, verschieden von der Quelle |
| Korrektur | der Lagerort, wenn weniger gezählt wurde | der Lagerort, wenn mehr gezählt wurde |

Bei einer Korrektur ist immer genau einer der beiden Lagerorte belegt.

### Bestand

- Bestand eines Artikels an einem Lagerort = Summe der Mengen, die dorthin gebucht wurden, minus
  Summe der Mengen, die von dort abgebucht wurden.
- Gesamtbestand eines Artikels = Summe seiner Bestände über alle Lagerorte.
- Eine Bewegung ändert den Gesamtbestand um +Menge (nur Nach-Lagerort), −Menge (nur Von-Lagerort)
  oder gar nicht (Umlagerung).

Weil es keine eigene Bestandstabelle gibt, können Bestand und Protokoll nie voneinander abweichen.

## Abläufe

### Buchung (gilt für alle vier Arten)

Eine Buchung wird ganz oder gar nicht ausgeführt:

1. Die Menge muss größer 0 sein.
2. Der Artikel muss existieren und aktiv sein.
3. Jeder beteiligte Lagerort muss existieren und aktiv sein.
4. Gibt es einen Von-Lagerort, darf die Menge den Bestand dort nicht übersteigen.
5. Die Bewegung wird mit dem aktuellen Zeitpunkt gespeichert.
6. Der Gesamtbestand des Artikels wird neu berechnet.
7. Hat die Buchung den Gesamtbestand gesenkt und liegt er jetzt unter dem Mindestbestand, kommt
   eine Warnung.

Scheitert einer der Schritte 1 bis 4, wird nichts gespeichert und der Grund gemeldet.

### Wareneingang

Artikel → Ziel-Lagerort → Menge → Bemerkung (optional, z. B. Lieferschein) → Bewegung „Eingang“.

### Warenausgang

Artikel (muss Bestand haben) → Quell-Lagerort (nur einer, an dem der Artikel liegt) → Menge
(höchstens der Bestand an der Quelle) → Bemerkung (optional, z. B. Auftrag) → Bewegung „Ausgang“
→ Mindestbestand prüfen.

### Umlagerung

Voraussetzung: mindestens zwei aktive Lagerorte. Artikel (muss Bestand haben) → Quell-Lagerort →
Ziel-Lagerort (nicht die Quelle) → Menge (höchstens der Bestand an der Quelle) → Bemerkung
(optional) → Bewegung „Umlagerung“. Der Gesamtbestand bleibt gleich, deshalb gibt es keine
Mindestbestandsprüfung.

### Korrektur / Inventur

1. Artikel und Lagerort wählen. Der gebuchte Bestand dort wird angezeigt.
2. Gezählten Bestand eingeben (0 oder mehr).
3. Differenz = gezählt − gebucht. Ist sie 0, wird nichts gebucht.
4. Begründung eingeben (Pflicht).
5. Differenz > 0: Bewegung „Korrektur“ mit Nach-Lagerort.
   Differenz < 0: Bewegung „Korrektur“ mit Von-Lagerort, danach Mindestbestand prüfen.

### Artikel oder Lagerort anlegen und bearbeiten

- Nummer bzw. Code werden beim Anlegen vergeben und lassen sich danach nicht mehr ändern.
- Bearbeiten ändert Bezeichnung, beim Artikel auch Einheit und Mindestbestand.
- Ist der Datensatz deaktiviert, wird beim Bearbeiten angeboten, ihn wieder zu aktivieren.
- Liegt ein Artikel nach dem Anlegen oder Bearbeiten unter dem Mindestbestand, kommt ein Hinweis.

### Artikel oder Lagerort entfernen

1. Der Datensatz muss aktiv sein.
2. Artikel: Der Gesamtbestand muss 0 sein. Lagerort: Es darf kein Artikel mehr dort liegen.
3. Gibt es noch keine Bewegungen dazu, wird er gelöscht. Sonst wird er nur deaktiviert.

Vorher wird angezeigt, ob gelöscht oder deaktiviert wird, und nachgefragt.

### Programmstart

1. Die Datenbankdatei wird geöffnet. Fehlt sie, wird sie angelegt.
2. Gibt es noch keine Artikel, kommt der Hinweis, zuerst Stammdaten anzulegen.
3. Liegen Artikel unter dem Mindestbestand, kommt ein Hinweis mit ihrer Anzahl.
4. Das Hauptmenü erscheint.
