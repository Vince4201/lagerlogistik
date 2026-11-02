package lager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lager.LagerTestBasis;

/** Die weiche Regel Mindestbestand. Artikel 1001 hat Mindestbestand 10, Artikel 1002 keinen. */
class MindestbestandTest extends LagerTestBasis {

    @Test
    @DisplayName("A17 Der Mindestbestand gilt für den Gesamtbestand über alle Lagerorte")
    void mindestbestandGiltFuerDenGesamtbestand() {
        lager.eingang("1001", "A-01", 6, "");
        lager.eingang("1001", "B-01", 6, "");

        // An A-01 liegen danach nur 5, insgesamt aber 11: keine Warnung.
        assertFalse(lager.ausgang("1001", "A-01", 1, "").mindestbestandWarnung());
        assertTrue(lager.ausgang("1001", "B-01", 2, "").mindestbestandWarnung());
    }

    @Test
    @DisplayName("A17 Mindestbestand 0 heißt: keine Überwachung")
    void mindestbestandNullWirdNichtUeberwacht() {
        lager.eingang("1002", "A-01", 3, "");

        assertFalse(lager.ausgang("1002", "A-01", 3, "").mindestbestandWarnung());
        assertTrue(lager.unterMindestbestand().stream().noneMatch(ab -> ab.artikel().nummer().equals("1002")));
    }

    @Test
    @DisplayName("A18 Nach jeder Buchung, die den Gesamtbestand unter den Mindestbestand senkt, wird gewarnt")
    void warnungBeiJederSenkendenBuchung() {
        lager.eingang("1001", "A-01", 12, "");

        assertFalse(lager.ausgang("1001", "A-01", 2, "").mindestbestandWarnung(), "genau 10 ist nicht darunter");
        Buchungsergebnis darunter = lager.ausgang("1001", "A-01", 1, "");
        assertTrue(darunter.mindestbestandWarnung());
        assertEquals(1, darunter.bestand().fehlmenge());
        assertTrue(lager.ausgang("1001", "A-01", 1, "").mindestbestandWarnung(), "auch wenn er schon darunter lag");
        assertTrue(lager.korrektur("1001", "A-01", 5, "Inventur").mindestbestandWarnung(), "Korrektur nach unten");
    }

    @Test
    @DisplayName("A18 Buchungen, die den Gesamtbestand erhöhen, warnen nicht")
    void keineWarnungBeiSteigendemBestand() {
        lager.eingang("1001", "A-01", 3, "");

        assertFalse(lager.eingang("1001", "A-01", 2, "").mindestbestandWarnung());
        assertFalse(lager.korrektur("1001", "A-01", 7, "Inventur").mindestbestandWarnung());
    }

    @Test
    @DisplayName("A19 Eine Umlagerung warnt nie, auch unter dem Mindestbestand")
    void umlagerungWarntNie() {
        lager.eingang("1001", "A-01", 4, "");

        assertFalse(lager.umlagerung("1001", "A-01", "B-01", 2, "").mindestbestandWarnung());
        assertEquals(1, lager.unterMindestbestand().size());
    }
}
