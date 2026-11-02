package lager.model;

/** Ein Artikel zusammen mit seinem Gesamtbestand über alle Lagerorte. */
public record Artikelbestand(Artikel artikel, long gesamtbestand) {

    public boolean unterMindestbestand() {
        return artikel.istUnterMindestbestand(gesamtbestand);
    }

    /** Wie viel bis zum Mindestbestand fehlt (0, wenn nichts fehlt). */
    public long fehlmenge() {
        return Math.max(0, artikel.mindestbestand() - gesamtbestand);
    }
}
