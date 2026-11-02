package lager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lager.LagerTestBasis;
import lager.model.Artikel;
import lager.model.Lagerort;

/** Anlegen, Bearbeiten, Löschen, Deaktivieren und Reaktivieren von Artikeln und Lagerorten. */
class StammdatenTest extends LagerTestBasis {

    @Test
    @DisplayName("A21 Ein Artikel hat Nummer, Bezeichnung, Einheit und Mindestbestand")
    void artikelHatSeineEigenschaften() {
        lager.artikelAnlegen("2001", "Kabelbinder 200 mm", "Beutel", 20);

        assertEquals(new Artikel("2001", "Kabelbinder 200 mm", "Beutel", 20, true),
                lager.findeArtikel("2001").orElseThrow());
    }

    @Test
    @DisplayName("A22 Ein Lagerort hat einen Code und eine optionale Bezeichnung")
    void lagerortHatCodeUndOptionaleBezeichnung() {
        assertEquals(new Lagerort("C-01", "", true), lager.findeLagerort("C-01").orElseThrow());
        assertEquals("Halle A, Regal 1", lager.findeLagerort("A-01").orElseThrow().bezeichnung());
    }

    @Test
    @DisplayName("A23 Nummern und Codes sind eindeutig")
    void nummernUndCodesSindEindeutig() {
        assertThrows(LagerException.class, () -> lager.artikelAnlegen("1001", "Doppelt", "Stk", 0));
        assertThrows(LagerException.class, () -> lager.lagerortAnlegen("A-01", "Doppelt"));
        assertEquals("Schraube M6", lager.findeArtikel("1001").orElseThrow().bezeichnung());
    }

    @Test
    @DisplayName("A23 Beim Bearbeiten bleibt die Nummer unverändert")
    void nummerBleibtBeimBearbeiten() {
        lager.artikelAendern("1001", "Schraube M6x20", "Stk", 15);
        lager.lagerortAendern("A-01", "Halle A, Regal 1, Fach 1");

        assertEquals(List.of("1001", "1002"), lager.artikelListe(true).stream().map(Artikel::nummer).toList());
        assertEquals("Schraube M6x20", lager.findeArtikel("1001").orElseThrow().bezeichnung());
        assertEquals("Halle A, Regal 1, Fach 1", lager.findeLagerort("A-01").orElseThrow().bezeichnung());
    }

    @Test
    @DisplayName("A24 Ohne Bewegungen werden Artikel und Lagerorte gelöscht")
    void ohneBewegungenWirdGeloescht() {
        assertEquals(Entfernen.LOESCHEN, lager.artikelEntfernen("1002"));
        assertEquals(Entfernen.LOESCHEN, lager.lagerortEntfernen("C-01"));

        assertTrue(lager.findeArtikel("1002").isEmpty());
        assertTrue(lager.findeLagerort("C-01").isEmpty());
    }

    @Test
    @DisplayName("A25 Mit Bewegungen wird nur deaktiviert, und das nur ohne Bestand")
    void mitBewegungenWirdNurDeaktiviert() {
        lager.eingang("1001", "A-01", 5, "");

        assertThrows(LagerException.class, () -> lager.artikelEntfernen("1001"), "Artikel hat Bestand");
        assertThrows(LagerException.class, () -> lager.lagerortEntfernen("A-01"), "Lagerort ist belegt");

        lager.ausgang("1001", "A-01", 5, "");
        assertEquals(Entfernen.DEAKTIVIEREN, lager.artikelEntfernen("1001"));
        assertEquals(Entfernen.DEAKTIVIEREN, lager.lagerortEntfernen("A-01"));

        assertFalse(lager.findeArtikel("1001").orElseThrow().aktiv());
        assertFalse(lager.findeLagerort("A-01").orElseThrow().aktiv());
        assertEquals(2, lager.protokoll("1001", null).size(), "das Protokoll bleibt vollständig");
    }

    @Test
    @DisplayName("A26 Deaktivierte lassen sich nicht bebuchen, nach dem Reaktivieren wieder")
    void deaktivierteLassenSichReaktivieren() {
        lager.eingang("1001", "A-01", 1, "");
        lager.ausgang("1001", "A-01", 1, "");
        lager.artikelEntfernen("1001");
        lager.lagerortEntfernen("A-01");
        assertThrows(LagerException.class, () -> lager.eingang("1001", "B-01", 1, ""));
        assertThrows(LagerException.class, () -> lager.eingang("1002", "A-01", 1, ""));

        lager.artikelReaktivieren("1001");
        lager.lagerortReaktivieren("A-01");

        lager.eingang("1001", "A-01", 3, "");
        assertEquals(3, lager.bestand("1001", "A-01"));
    }
}
