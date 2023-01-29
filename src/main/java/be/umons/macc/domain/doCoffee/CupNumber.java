package be.umons.macc.domain.doCoffee;

import static be.umons.macc.domain.doCoffee.CupNumberEnum.ONE;
import static be.umons.macc.domain.doCoffee.CupNumberEnum.TWO;

public class CupNumber {
    CupNumberEnum value;

    public CupNumber() {
        value = ONE;
    }

    public CupNumber(String val) {
        if ("*".equals(val)) value = ONE;
        else value = TWO;
    }

    public void switchCupNumber(){
        if (ONE.equals(value))
            value = TWO;
        else
            value = ONE;
    }

    public CupNumberEnum getEnum() {
        return value;
    }

    public Integer getValue() {
        return value.getValue();
    }

    public boolean isTwice() {
        return TWO.equals(value);
    }
}
