package lager.model;

/**
 * Stammdaten eines Artikels. Der Mindestbestand bezieht sich auf den Gesamtbestand über
 * alle Lagerorte.
 */
public record Artikel(String nummer, String bezeichnung, String einheit, long mindestbestand, boolean aktiv) {

    /**
     * Fachregel: Gewarnt wird erst, wenn der Gesamtbestand den Mindestbestand unterschreitet.
     * Ein Mindestbestand von 0 kann nie unterschritten werden und bedeutet "keine Überwachung".
     */
    public boolean istUnterMindestbestand(long gesamtbestand) {
        return gesamtbestand < mindestbestand;
    }

    public Artikel mitAktiv(boolean aktiv) {
        return new Artikel(nummer, bezeichnung, einheit, mindestbestand, aktiv);
    }
}
