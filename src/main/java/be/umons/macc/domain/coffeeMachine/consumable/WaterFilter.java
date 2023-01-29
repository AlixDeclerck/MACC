package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.Configuration;
import be.umons.macc.domain.doCoffee.preparation.Preparation;

import static be.umons.macc.Configuration.*;
import static java.lang.Double.compare;

/**
 * The water filter usage occur when system percolate to create drink
 */
public class WaterFilter implements ConsumableItem {

    private Double actualLevel = STARTUP_FILTER_START_LEVEL;

    @Override
    public void doCoffee(Double qt) {
        actualLevel = actualLevel + WATER_FILTER_USAGE_INCREMENT;
    }

    @Override
    public void renewItem() {
        actualLevel = WATER_FILTER_CLEAN_LEVEL;
    }

    @Override
    public Double getStatus() {
        return actualLevel;
    }

    @Override
    public boolean isOkay(Preparation p) {
        return compare(actualLevel, FILTER_LIMIT) == -1;
    }

    @Override
    public String toString() {
        return getStatus().toString();
    }
}
