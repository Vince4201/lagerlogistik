package lager.model;

import java.time.LocalDateTime;

/**
 * Ein Eintrag im Bewegungsprotokoll. Die Menge ist immer positiv; die Richtung ergibt sich aus
 * den Lagerorten: Am Von-Lagerort wird abgebucht, am Nach-Lagerort zugebucht.
 *
 * <pre>
 * Eingang     von = null     nach = Ziel
 * Ausgang     von = Quelle   nach = null
 * Umlagerung  von = Quelle   nach = Ziel
 * Korrektur   nur von (weniger gezählt) oder nur nach (mehr gezählt)
 * </pre>
 */
public record Bewegung(long nr, LocalDateTime zeitpunkt, Bewegungsart art, String artikel, long menge,
                       String vonLagerort, String nachLagerort, String bemerkung) {

    /** Veränderung des Gesamtbestands: positiv bei Zugang, negativ bei Abgang, 0 bei Umlagerung. */
    public long aenderungGesamtbestand() {
        long aenderung = 0;
        if (nachLagerort != null) {
            aenderung += menge;
        }
        if (vonLagerort != null) {
            aenderung -= menge;
        }
        return aenderung;
    }
}
