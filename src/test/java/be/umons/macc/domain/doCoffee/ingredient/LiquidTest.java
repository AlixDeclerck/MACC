package be.umons.macc.domain.doCoffee.ingredient;

import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.preparation.PreparationMockup;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiquidTest implements PreparationMockup {

    IngredientDecorator ingredientDecorator;

    public LiquidTest() throws UnexpectedBehaviorException {
        Preparation preparation = doCoffee();
        Drink drink = preparation.getDrink();
        ingredientDecorator = new Liquid(drink);
    }

    @Test
    void liquidFactor() {
        double expected = 3.0;
        double provided = ingredientDecorator.liquidFactor();
        assertEquals(expected, provided);
    }

}