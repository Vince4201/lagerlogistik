package lager.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lager.LagerTestBasis;
import lager.model.Artikel;

/**
 * Anforderungen an die Bedienung. Jeder Test spielt Eingaben zeilenweise ein, wie jemand am
 * Terminal, und prüft die Ausgabe. Eine leere Zeile ist ein Enter.
 */
class LagerKonsoleTest extends LagerTestBasis {

    /** Startet das Programm mit den Eingaben und liefert alles, was es ausgegeben hat. */
    private String lauf(String... eingaben) {
        var ein = new BufferedReader(new StringReader(String.join("\n", eingaben) + "\n"));
        var puffer = new ByteArrayOutputStream();
        var aus = new PrintStream(puffer, true, StandardCharsets.UTF_8);
        new LagerKonsole(lager, ein, aus, false).starte("test.db");
        return puffer.toString(StandardCharsets.UTF_8);
    }

    @Test
    @DisplayName("A02 Bedienung über ein Menü im Terminal")
    void hauptmenue() {
        String ausgabe = lauf("0");

        assertTrue(ausgabe.contains("=== LAGERVERWALTUNG ==="));
        for (String punkt : new String[] {"1  Wareneingang", "2  Warenausgang", "3  Umlagerung",
                "4  Korrektur / Inventur", "5  Bestand je Artikel", "6  Bestand je Lagerort",
                "7  Artikel unter Mindestbestand", "8  Bewegungsprotokoll", "9  Stammdaten", "0  Beenden"}) {
            assertTrue(ausgabe.contains(punkt), punkt);
        }
        assertTrue(ausgabe.contains("Auf Wiedersehen."));
    }

    @Test
    @DisplayName("A07 Mengen sind ganze Zahlen, eine Kommazahl wird erneut abgefragt")
    void mengeIstEineGanzeZahl() {
        String ausgabe = lauf("1", "1001", "A-01", "2,5", "5", "", "", "0");

        assertTrue(ausgabe.contains("Fehler: Bitte eine ganze Zahl eingeben."));
        assertTrue(ausgabe.contains("Gebucht (#1): Eingang 5 Stk 1001 nach A-01"));
        assertEquals(5, lager.bestand("1001", "A-01"));
    }

    @Test
    @DisplayName("A18 Nach einem Warenausgang unter den Mindestbestand erscheint eine Warnung")
    void warnungNachWarenausgang() {
        lager.eingang("1001", "A-01", 12, "");

        String ausgabe = lauf("2", "1001", "A-01", "5", "", "", "0");

        assertTrue(ausgabe.contains("!! WARNUNG: Mindestbestand unterschritten"));
        assertTrue(ausgabe.contains("!!   Es fehlen 3 Stk."));
    }

    @Test
    @DisplayName("A20 Beim Programmstart kommt ein Hinweis auf Artikel unter dem Mindestbestand")
    void hinweisBeimStart() {
        // Artikel 1001 hat Mindestbestand 10 und noch keinen Bestand.
        assertTrue(lauf("0").contains("!! Hinweis: 1 Artikel liegt unter dem Mindestbestand (Menüpunkt 7)."));

        lager.eingang("1001", "A-01", 10, "");
        assertFalse(lauf("0").contains("unter dem Mindestbestand (Menüpunkt 7)"));
    }

    @Test
    @DisplayName("A21 Die Einheit eines neuen Artikels ist ohne Eingabe Stk")
    void einheitStandardStk() {
        lauf("9", "2", "2001", "Kabelbinder", "", "", "", "0", "0");

        assertEquals(new Artikel("2001", "Kabelbinder", "Stk", 0, true), lager.findeArtikel("2001").orElseThrow());
    }

    @Test
    @DisplayName("A23 Nummern und Codes werden in Großbuchstaben gespeichert")
    void grossbuchstaben() {
        lauf("9", "2", "ab-7", "Testartikel", "", "", "",
                "6", "x-01", "", "",
                "0", "0");

        assertTrue(lager.findeArtikel("AB-7").isPresent());
        assertTrue(lager.findeArtikel("ab-7").isEmpty());
        assertTrue(lager.findeLagerort("X-01").isPresent());
    }

    @Test
    @DisplayName("A31 Falsche Eingaben werden erneut abgefragt")
    void falscheEingabenWerdenErneutAbgefragt() {
        String ausgabe = lauf("x", "1", "9999", "1001", "Z-99", "A-01", "-3", "3", "", "", "0");

        assertTrue(ausgabe.contains("Fehler: Bitte eine Zahl von 0 bis 9 eingeben."));
        assertTrue(ausgabe.contains("Fehler: Artikel 9999 gibt es nicht."));
        assertTrue(ausgabe.contains("Fehler: Lagerort Z-99 gibt es nicht."));
        assertTrue(ausgabe.contains("Fehler: Die Zahl muss mindestens 1 sein."));
        assertEquals(3, lager.bestand("1001", "A-01"));
    }

    @Test
    @DisplayName("A32 Eine leere Eingabe bricht den laufenden Vorgang ab")
    void leereEingabeBrichtAb() {
        String ausgabe = lauf("1", "1001", "A-01", "", "0");

        assertTrue(ausgabe.contains("Abgebrochen."));
        assertTrue(lager.protokoll(null, null).isEmpty());
    }

    @Test
    @DisplayName("A33 Mit ? erscheint eine Auswahlliste")
    void fragezeichenZeigtAuswahlliste() {
        String ausgabe = lauf("1", "?", "", "0");

        assertTrue(ausgabe.contains("Bezeichnung"));
        assertTrue(ausgabe.contains("Schraube M6"));
        assertTrue(ausgabe.contains("Mutter M6"));
    }
}
