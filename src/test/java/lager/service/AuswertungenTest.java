package lager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lager.LagerTestBasis;
import lager.model.Artikelbestand;
import lager.model.Bestandsposten;
import lager.model.Bewegung;

/** Die vier Auswertungen. Jeder Test startet mit denselben vier Buchungen. */
class AuswertungenTest extends LagerTestBasis {

    @BeforeEach
    void buchen() {
        lager.eingang("1001", "A-01", 8, "");             // #1
        lager.eingang("1002", "A-01", 4, "");             // #2
        lager.umlagerung("1001", "A-01", "B-01", 3, "");  // #3
        lager.ausgang("1002", "A-01", 4, "");             // #4, danach liegt 1002 nirgends mehr
    }

    @Test
    @DisplayName("A27 Je Artikel: Gesamtbestand und Verteilung auf die Lagerorte")
    void bestandJeArtikel() {
        List<Bestandsposten> verteilung = lager.bestaende("1001", null);
        assertEquals(List.of("A-01:5", "B-01:3"),
                verteilung.stream().map(p -> p.lagerort() + ":" + p.menge()).toList());

        List<Artikelbestand> uebersicht = lager.artikelUebersicht();
        assertEquals(List.of("1001:8", "1002:0"),
                uebersicht.stream().map(ab -> ab.artikel().nummer() + ":" + ab.gesamtbestand()).toList());
    }

    @Test
    @DisplayName("A28 Je Lagerort: welche Artikel in welcher Menge dort liegen, ohne Bestände von 0")
    void bestandJeLagerort() {
        assertEquals(List.of("1001:5"),
                lager.bestaende(null, "A-01").stream().map(p -> p.artikel().nummer() + ":" + p.menge()).toList());
        assertEquals(List.of("A-01/1001:5", "B-01/1001:3"),
                lager.bestaende(null, null).stream()
                        .map(p -> p.lagerort() + "/" + p.artikel().nummer() + ":" + p.menge()).toList());
        assertTrue(lager.bestaende(null, "C-01").isEmpty());
    }

    @Test
    @DisplayName("A29 Liste der Artikel unter dem Mindestbestand mit Fehlmenge")
    void listeUnterMindestbestand() {
        List<Artikelbestand> liste = lager.unterMindestbestand();

        assertEquals(1, liste.size(), "1002 hat keinen Mindestbestand und fehlt deshalb");
        assertEquals("1001", liste.get(0).artikel().nummer());
        assertEquals(2, liste.get(0).fehlmenge());
    }

    @Test
    @DisplayName("A30 Bewegungsprotokoll in Buchungsreihenfolge, filterbar nach Artikel und Lagerort")
    void bewegungsprotokoll() {
        assertEquals(List.of(1L, 2L, 3L, 4L), nummern(lager.protokoll(null, null)));
        assertEquals(List.of(1L, 3L), nummern(lager.protokoll("1001", null)));
        assertEquals(List.of(3L), nummern(lager.protokoll(null, "B-01")), "Lagerort als Ziel einer Umlagerung");
        assertEquals(List.of(2L, 4L), nummern(lager.protokoll("1002", "A-01")));
        assertTrue(lager.protokoll("1002", "B-01").isEmpty());
    }

    private static List<Long> nummern(List<Bewegung> bewegungen) {
        return bewegungen.stream().map(Bewegung::nr).toList();
    }
}
