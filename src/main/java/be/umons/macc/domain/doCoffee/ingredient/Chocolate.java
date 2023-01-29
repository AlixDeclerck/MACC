package be.umons.macc.domain.doCoffee.ingredient;

public class Chocolate extends IngredientDecorator {

    public Chocolate(Drink drink) {
        super(drink);
    }

    @Override
    public double chocolateFactor() {
        return super.chocolateFactor() + 1.0;
    }

}
