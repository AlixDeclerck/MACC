package be.umons.macc.domain.doCoffee.ingredient;

import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.preparation.PreparationMockup;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChocolateTest implements PreparationMockup {

    IngredientDecorator ingredientDecorator;

    public ChocolateTest() throws UnexpectedBehaviorException {
        Preparation preparation = doChocolate();
        Drink drink = preparation.getDrink();
        ingredientDecorator = new Chocolate(drink);
    }

    @Test
    void chocolateFactor() {
        double expected = 1.0;
        double provided = ingredientDecorator.chocolateFactor();
        assertEquals(expected, provided);
    }

}