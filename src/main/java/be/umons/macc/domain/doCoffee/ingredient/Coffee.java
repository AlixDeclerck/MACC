package be.umons.macc.domain.doCoffee.ingredient;

public class Coffee extends IngredientDecorator {

    public Coffee(Drink drink) {
        super(drink);
    }

    @Override
    public double coffeeFactor() {
        return super.coffeeFactor() + 1.0;
    }

}
