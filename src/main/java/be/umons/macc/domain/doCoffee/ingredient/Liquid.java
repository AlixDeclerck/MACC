package be.umons.macc.domain.doCoffee.ingredient;

public class Liquid extends IngredientDecorator {

    public Liquid(Drink drink) {
        super(drink);
    }

    @Override
    public double liquidFactor() {
        return super.liquidFactor() + 1.0;
    }

}
