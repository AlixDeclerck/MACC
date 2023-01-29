package be.umons.macc.domain.doCoffee.ingredient;

/**
 * Decorator pattern that decor drink with ingredients.
 */
public abstract class IngredientDecorator implements Drink {

    protected Drink drink;

    public IngredientDecorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double coffeeFactor() {
        return drink.coffeeFactor();
    }

    @Override
    public double milkFactor() {
        return drink.milkFactor();
    }

    @Override
    public double chocolateFactor() {
        return drink.chocolateFactor();
    }

    @Override
    public double liquidFactor() {
        return drink.liquidFactor();
    }

}
