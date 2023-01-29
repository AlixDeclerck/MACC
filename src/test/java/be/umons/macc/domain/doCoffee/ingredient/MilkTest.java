package be.umons.macc.domain.doCoffee.ingredient;

import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.preparation.PreparationMockup;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MilkTest implements PreparationMockup {

    IngredientDecorator ingredientDecorator;

    public MilkTest() throws UnexpectedBehaviorException {
        Preparation preparation = doMilk();
        Drink drink = preparation.getDrink();
        ingredientDecorator = new Milk(drink);
    }

    @Test
    void liquidFactor() {
        double expected = 1.0;
        double provided = ingredientDecorator.milkFactor();
        assertEquals(expected, provided);
    }

}