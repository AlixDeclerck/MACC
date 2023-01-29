package be.umons.macc.gui.component.translator;

public enum Language {
    ENGLISH(0),
    DUTCH(1),
    FRENCH(2);

    private final int value;

    Language(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
