package be.umons.macc.domain.doCoffee;

import static be.umons.macc.domain.doCoffee.StrongnessEnum.*;

public class Strongness {

    private StrongnessEnum strongnessEnum;

    public Strongness() {
        strongnessEnum = MIDDLE;
    }
    public Strongness(StrongnessEnum strongnessEnum) {
        this.strongnessEnum = strongnessEnum;
    }

    public void strongUp() {
        if (ULTRA_STRONG.equals(strongnessEnum))
            return;

        for (StrongnessEnum e : values()) {
            if (e.getIntensity() == (strongnessEnum.getIntensity()+1)) {
                strongnessEnum = e;
                return;
            }
        }
    }

    public void strongDown() {
        if (LIGHT.equals(strongnessEnum))
            return;

        for (StrongnessEnum e : values()) {
            if (e.getIntensity() == (strongnessEnum.getIntensity()-1)) {
                strongnessEnum = e;
                return;
            }
        }
    }

    public String getLevel() {
        return strongnessEnum.getKeyValue();
    }

    public Integer getIntensity() {
        return strongnessEnum.getIntensity();
    }

}
