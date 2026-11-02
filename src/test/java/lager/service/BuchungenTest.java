package lager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lager.LagerTestBasis;
import lager.model.Bestandsposten;
import lager.model.Bewegung;
import lager.model.Bewegungsart;

/** Lager und Bestand, die vier Buchungsarten und die harten Regeln. */
class BuchungenTest extends LagerTestBasis {

    // --- Lager und Bestand --------------------------------------------------------------------

    @Test
    @DisplayName("A04 Ein Artikel liegt an mehreren Lagerorten, ein Lagerort enthält mehrere Artikel")
    void artikelUndLagerorteSindNzuM() {
        lager.eingang("1001", "A-01", 5, "");
        lager.eingang("1001", "B-01", 7, "");
        lager.eingang("1002", "A-01", 3, "");

        assertEquals(List.of("A-01", "B-01"),
                lager.bestaende("1001", null).stream().map(Bestandsposten::lagerort).toList());
        assertEquals(List.of("1001", "1002"),
                lager.bestaende(null, "A-01").stream().map(p -> p.artikel().nummer()).toList());
        assertEquals(12, lager.gesamtbestand("1001"));
    }

    @Test
    @DisplayName("A05 Jede Bestandsänderung steht im Protokoll, der Bestand ergibt sich daraus")
    void bestandErgibtSichAusDenBewegungen() {
        lager.eingang("1001", "A-01", 10, "");
        lager.ausgang("1001", "A-01", 3, "");
        lager.umlagerung("1001", "A-01", "B-01", 2, "");
        lager.korrektur("1001", "B-01", 1, "Inventur");

        List<Bewegung> protokoll = lager.protokoll("1001", null);
        assertEquals(4, protokoll.size());
        long summe = protokoll.stream().mapToLong(Bewegung::aenderungGesamtbestand).sum();
        assertEquals(summe, lager.gesamtbestand("1001"));
        assertEquals(5, lager.bestand("1001", "A-01"));
        assertEquals(1, lager.bestand("1001", "B-01"));
    }

    @Test
    @DisplayName("A06 Ein Fehler wird mit einer Korrektur ausgeglichen, die falsche Bewegung bleibt stehen")
    void fehlerWerdenPerKorrekturAusgeglichen() {
        Bewegung falsch = lager.eingang("1001", "A-01", 100, "Tippfehler, richtig wären 10").bewegung();
        lager.korrektur("1001", "A-01", 10, "Tippfehler bei Eingang #" + falsch.nr());

        List<Bewegung> protokoll = lager.protokoll("1001", null);
        assertEquals(2, protokoll.size());
        assertEquals(100, protokoll.get(0).menge());
        assertEquals(Bewegungsart.KORREKTUR, protokoll.get(1).art());
        assertEquals(10, lager.gesamtbestand("1001"));
    }

    @Test
    @DisplayName("A07 Die Einheit ist nur ein Anzeigetext und ändert keine Mengen")
    void einheitIstNurAnzeigetext() {
        lager.eingang("1001", "A-01", 12, "");
        lager.artikelAendern("1001", "Schraube M6", "Karton", 10);

        assertEquals(12, lager.gesamtbestand("1001"));
        assertEquals("Karton", lager.findeArtikel("1001").orElseThrow().einheit());
    }

    // --- Buchungen ----------------------------------------------------------------------------

    @Test
    @DisplayName("A08 Ein Wareneingang erhöht den Bestand am Ziel-Lagerort")
    void wareneingangErhoehtBestandAmZiel() {
        Bewegung b = lager.eingang("1001", "A-01", 25, "LS 4711").bewegung();

        assertEquals(Bewegungsart.EINGANG, b.art());
        assertNull(b.vonLagerort());
        assertEquals("A-01", b.nachLagerort());
        assertEquals("LS 4711", b.bemerkung());
        assertEquals(25, lager.bestand("1001", "A-01"));
    }

    @Test
    @DisplayName("A09 Ein Warenausgang senkt den Bestand am Quell-Lagerort")
    void warenausgangSenktBestandAnDerQuelle() {
        lager.eingang("1001", "A-01", 25, "");
        Bewegung b = lager.ausgang("1001", "A-01", 8, "Auftrag 1").bewegung();

        assertEquals(Bewegungsart.AUSGANG, b.art());
        assertEquals("A-01", b.vonLagerort());
        assertNull(b.nachLagerort());
        assertEquals(17, lager.bestand("1001", "A-01"));
    }

    @Test
    @DisplayName("A10 Eine Umlagerung verschiebt die Menge, der Gesamtbestand bleibt gleich")
    void umlagerungAendertGesamtbestandNicht() {
        lager.eingang("1001", "A-01", 25, "");
        Buchungsergebnis ergebnis = lager.umlagerung("1001", "A-01", "B-01", 5, "");

        assertEquals(Bewegungsart.UMLAGERUNG, ergebnis.bewegung().art());
        assertEquals(20, lager.bestand("1001", "A-01"));
        assertEquals(5, lager.bestand("1001", "B-01"));
        assertEquals(25, ergebnis.bestand().gesamtbestand());
    }

    @Test
    @DisplayName("A11 Eine Korrektur übernimmt den gezählten Bestand und bucht die Differenz")
    void korrekturBuchtDieDifferenz() {
        lager.eingang("1001", "A-01", 20, "");

        Bewegung weniger = lager.korrektur("1001", "A-01", 17, "Schwund").bewegung();
        assertEquals("A-01", weniger.vonLagerort());
        assertNull(weniger.nachLagerort());
        assertEquals(3, weniger.menge());
        assertEquals(17, lager.bestand("1001", "A-01"));

        Bewegung mehr = lager.korrektur("1001", "A-01", 19, "Fund").bewegung();
        assertNull(mehr.vonLagerort());
        assertEquals("A-01", mehr.nachLagerort());
        assertEquals(2, mehr.menge());
        assertEquals(19, lager.bestand("1001", "A-01"));

        assertThrows(LagerException.class, () -> lager.korrektur("1001", "A-01", 19, "nichts zu tun"));
    }

    // --- Harte Regeln -------------------------------------------------------------------------

    @Test
    @DisplayName("A12 Eine Menge von 0 oder weniger wird abgelehnt")
    void mengeMussGroesserNullSein() {
        assertThrows(LagerException.class, () -> lager.eingang("1001", "A-01", 0, ""));
        assertThrows(LagerException.class, () -> lager.eingang("1001", "A-01", -5, ""));
        assertThrows(LagerException.class, () -> lager.ausgang("1001", "A-01", 0, ""));
        assertThrows(LagerException.class, () -> lager.umlagerung("1001", "A-01", "B-01", 0, ""));
        assertTrue(lager.protokoll(null, null).isEmpty());
    }

    @Test
    @DisplayName("A13 Unbekannte oder deaktivierte Artikel und Lagerorte lassen sich nicht bebuchen")
    void nurVorhandeneUndAktiveWerdenBebucht() {
        assertThrows(LagerException.class, () -> lager.eingang("9999", "A-01", 1, ""));
        assertThrows(LagerException.class, () -> lager.eingang("1001", "Z-99", 1, ""));

        // Artikel 1002 und Lagerort C-01 haben Bewegungen und keinen Bestand: Sie werden deaktiviert.
        lager.eingang("1002", "C-01", 1, "");
        lager.ausgang("1002", "C-01", 1, "");
        lager.artikelEntfernen("1002");
        lager.lagerortEntfernen("C-01");

        assertThrows(LagerException.class, () -> lager.eingang("1002", "A-01", 1, ""));
        assertThrows(LagerException.class, () -> lager.eingang("1001", "C-01", 1, ""));
        assertEquals(2, lager.protokoll(null, null).size());
    }

    @Test
    @DisplayName("A14 Entnommen wird höchstens, was am Lagerort liegt, kein Lagerort wird negativ")
    void keinLagerortWirdNegativ() {
        lager.eingang("1001", "A-01", 5, "");
        lager.eingang("1001", "B-01", 50, "");

        assertThrows(LagerException.class, () -> lager.ausgang("1001", "A-01", 6, ""));
        assertThrows(LagerException.class, () -> lager.umlagerung("1001", "A-01", "C-01", 6, ""));
        assertThrows(LagerException.class, () -> lager.ausgang("1001", "C-01", 1, ""));
        assertEquals(5, lager.bestand("1001", "A-01"));

        lager.ausgang("1001", "A-01", 5, "");
        assertEquals(0, lager.bestand("1001", "A-01"));
    }

    @Test
    @DisplayName("A15 Bei einer Umlagerung müssen Quelle und Ziel verschieden sein")
    void umlagerungBrauchtVerschiedeneLagerorte() {
        lager.eingang("1001", "A-01", 5, "");

        assertThrows(LagerException.class, () -> lager.umlagerung("1001", "A-01", "A-01", 1, ""));
        assertEquals(1, lager.protokoll(null, null).size());
    }

    @Test
    @DisplayName("A16 Eine Korrektur ohne Begründung wird abgelehnt")
    void korrekturBrauchtBegruendung() {
        lager.eingang("1001", "A-01", 5, "");

        assertThrows(LagerException.class, () -> lager.korrektur("1001", "A-01", 4, ""));
        assertThrows(LagerException.class, () -> lager.korrektur("1001", "A-01", 4, "   "));
        assertThrows(LagerException.class, () -> lager.korrektur("1001", "A-01", 4, null));
        assertEquals(5, lager.bestand("1001", "A-01"));
    }
}
