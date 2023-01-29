package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.doCoffee.preparation.Preparation;

import static be.umons.macc.domain.coffeeMachine.consumable.ConsumableIngredientType.GRAINS;
import static java.lang.Double.compare;

/**
 * They are two consumable ingredients : Coffee, Water
 */
public class Ingredient implements ConsumableItem {

    private final ConsumableIngredientType type;
    private final Double max;
    private Double level;

    public Ingredient(ConsumableIngredientType type, Double max, Double level) {
        this.type = type;
        this.max = max;
        this.level = level;
    }

    @Override
    public void doCoffee(Double qt) {
        level = compare(level, qt) > 0 ? level - qt : (double) 0;
    }

    @Override
    public void renewItem() {
        this.level = max;
    }

    @Override
    public Double getStatus() {
        return level;
    }

    @Override
    public String toString() {
        return Double.toString(getStatus());
    }

    @Override
    public boolean isOkay(Preparation preparation) {
        if (GRAINS.equals(type))
            return preparation.getGroundingCoffeeQuantity() < level;
        return preparation.getEffectiveWaterQuantity() < level;
    }

}
