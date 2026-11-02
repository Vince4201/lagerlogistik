package lager.service;

/** Verstoß gegen eine Fachregel. Die Meldung wird so im Terminal angezeigt. */
public class LagerException extends RuntimeException {

    public LagerException(String meldung) {
        super(meldung);
    }
}
