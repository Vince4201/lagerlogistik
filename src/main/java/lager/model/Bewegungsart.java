package lager.model;

public enum Bewegungsart {
    EINGANG("Eingang"),
    AUSGANG("Ausgang"),
    UMLAGERUNG("Umlagerung"),
    KORREKTUR("Korrektur");

    private final String anzeigename;

    Bewegungsart(String anzeigename) {
        this.anzeigename = anzeigename;
    }

    public String anzeigename() {
        return anzeigename;
    }
}
