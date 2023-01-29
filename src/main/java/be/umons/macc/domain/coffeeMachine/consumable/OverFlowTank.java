package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.doCoffee.preparation.Preparation;

import static be.umons.macc.Configuration.*;

/**
 * The tank overflow usage occur when user take the drink (simulate by system)
 */
public class OverFlowTank implements ConsumableItem {

    private Double level = STARTUP_OVERFLOW_START_LEVEL;

    @Override
    public void doCoffee(Double qt) {
        Double rand = (Math.random() * qt * OVERFLOW_TANK_FACTOR);
        level = level + rand.intValue();
    }

    @Override
    public void renewItem() {
        level = OVERFLOW_EMPTY_LEVEL;
    }

    @Override
    public Double getStatus() {
        return level;
    }

    @Override
    public boolean isOkay(Preparation p) {
        return level.intValue() < OVERFLOW_LIMIT;
    }

    @Override
    public String toString() {
        return getStatus().toString();
    }

}
