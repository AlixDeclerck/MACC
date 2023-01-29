package be.umons.macc.domain.doCoffee.ingredient;

public class Milk extends IngredientDecorator {

    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public double milkFactor() {
        return super.milkFactor() + 1.0;
    }

}
