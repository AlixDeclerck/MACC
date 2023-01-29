package be.umons.macc.domain.doCoffee.ingredient;

import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.preparation.PreparationMockup;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest implements PreparationMockup {

    IngredientDecorator ingredientDecorator;

    public CoffeeTest() throws UnexpectedBehaviorException {
        Preparation preparation = doCoffee();
        Drink drink = preparation.getDrink();
        ingredientDecorator = new Coffee(drink);
    }

    @Test
    void coffeeFactor() {
        double expected = 4.0;
        double provided = ingredientDecorator.coffeeFactor();
        assertEquals(expected, provided);
    }

}