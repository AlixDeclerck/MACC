package be.umons.macc.domain.doCoffee;

import static be.umons.macc.Configuration.STARTUP_CUP_NUMBER;

public enum CupNumberEnum {

    ONE(STARTUP_CUP_NUMBER),
    TWO(2);

    private final int number;

    CupNumberEnum(int number) {
        this.number = number;
    }

    public int getValue() {
        return number;
    }

    public static CupNumberEnum getEnumFromNumber(int cupNumber) {
        if (STARTUP_CUP_NUMBER == cupNumber)
            return CupNumberEnum.ONE;
        else
            return CupNumberEnum.TWO;
    }

    public static String getSValue(CupNumberEnum cupNumber) {
        if (CupNumberEnum.ONE.equals(cupNumber))
            return "*";
        else
            return "**";
    }

}
