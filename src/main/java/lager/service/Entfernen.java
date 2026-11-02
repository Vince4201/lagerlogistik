package lager.service;

/** Was beim Entfernen eines Artikels oder Lagerorts passiert. */
public enum Entfernen {
    /** Noch nie gebucht: Der Datensatz wird gelöscht. */
    LOESCHEN,
    /** Steht im Bewegungsprotokoll: Der Datensatz bleibt erhalten und wird nur deaktiviert. */
    DEAKTIVIEREN
}
