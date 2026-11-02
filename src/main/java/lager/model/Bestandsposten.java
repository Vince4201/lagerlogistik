package lager.model;

/** Menge eines Artikels an einem Lagerort. */
public record Bestandsposten(Artikel artikel, String lagerort, long menge) {
}
