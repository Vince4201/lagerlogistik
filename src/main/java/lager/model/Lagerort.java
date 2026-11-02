package lager.model;

/** Ein Lagerplatz, z. B. "A-01-03". Er kann beliebig viele verschiedene Artikel enthalten. */
public record Lagerort(String code, String bezeichnung, boolean aktiv) {

    public Lagerort mitAktiv(boolean aktiv) {
        return new Lagerort(code, bezeichnung, aktiv);
    }
}
