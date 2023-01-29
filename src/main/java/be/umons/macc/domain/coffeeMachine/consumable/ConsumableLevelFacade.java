package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.doCoffee.preparation.Preparation;

import static be.umons.macc.Configuration.*;
import static be.umons.macc.domain.coffeeMachine.consumable.ConsumableIngredientType.GRAINS;
import static be.umons.macc.domain.coffeeMachine.consumable.ConsumableIngredientType.WATER;

/**
 * Facade pattern that expose de consumption features.
 */
public class ConsumableLevelFacade {

    private final Ingredient grains = new Ingredient(GRAINS, MAX_GRAINS, STARTUP_GRAINS_LEVEL);
    private final Ingredient water = new Ingredient(WATER, MAX_WATER, STARTUP_WATER_LEVEL);
    private final OverFlowTank overFlowTank = new OverFlowTank();
    private final WaterFilter waterFilter = new WaterFilter();

    /**
     * 4 methods implemented by Filter :
     *
     */

    public void doCoffeeUseFilter(Double qt) {
        waterFilter.doCoffee(qt);
    }

    public void renewFilter() {
        waterFilter.renewItem();
    }

    public boolean isFilterOkay(Preparation p) {
        return waterFilter.isOkay(p);
    }

    public Double getStatusOfFilter() {
        return waterFilter.getStatus();
    }

    /**
     * 4 methods implemented by Overflow Tank
     *
     */

    public void doCoffeeUsingOverFlowTank(Double qt) {
        overFlowTank.doCoffee(qt);
    }

    public void renewOverFlowTank() {
        overFlowTank.renewItem();
    }

    public Double getOverFlowTankStatus() {
        return overFlowTank.getStatus();
    }

    public boolean isOverFlowTankOkay(Preparation p) {
        return overFlowTank.isOkay(p);
    }

    /**
     * 4 methods implemented by Coffee Reserve
     *
     */

    public void doCoffeeUsingGrainsFromReserve(Double qt) {
        grains.doCoffee(qt);
    }

    public void renewGrainsReserve() {
        grains.renewItem();
    }

    public Double getStatusOfGrains() {
        return grains.getStatus();
    }

    public boolean isGrainsReserveOkay(Preparation p) {
        return grains.isOkay(p);
    }

    /**
     * 4 methods implemented by Water Reserve
     *
     */

    public void doCoffeeUsingWaterFromReserve(Double qt) {
        water.doCoffee(qt);
    }

    public void renewWaterReserve() {
        water.renewItem();
    }

    public Double getStatusOfWater() {
        return water.getStatus();
    }

    public boolean isWaterReserveOkay(Preparation p) {
        return water.isOkay(p);
    }

}
