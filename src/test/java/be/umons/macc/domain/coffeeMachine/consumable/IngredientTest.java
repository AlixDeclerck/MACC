package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.preparation.PreparationMockup;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.umons.macc.Configuration.*;
import static be.umons.macc.domain.coffeeMachine.consumable.ConsumableIngredientType.GRAINS;
import static be.umons.macc.domain.coffeeMachine.consumable.ConsumableIngredientType.WATER;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest implements PreparationMockup {

    Preparation preparation;
    Ingredient ingredient;

    @BeforeEach
    public void init() throws UnexpectedBehaviorException {
        preparation = doCoffee();
    }

    @Test
    public void grains_status()  {
        ingredient = new Ingredient(GRAINS, MAX_GRAINS, STARTUP_GRAINS_LEVEL);
        Double first_coffee = 150.0;
        Double first_result = STARTUP_GRAINS_LEVEL - 150.0;
        Double second_coffee = first_result + 42.0;

        assertTrue(ingredient.isOkay(preparation));
        assertEquals(STARTUP_GRAINS_LEVEL, ingredient.getStatus());

        ingredient.doCoffee(first_coffee);

        assertTrue(ingredient.isOkay(preparation));
        assertEquals(first_result, ingredient.getStatus());

        ingredient.doCoffee(second_coffee);

        assertFalse(ingredient.isOkay(preparation));
        assertEquals(LEVEL_NULL, ingredient.getStatus());

    }

    @Test
    public void water_status()  {
        ingredient = new Ingredient(WATER, MAX_WATER, STARTUP_WATER_LEVEL);
        Double first_coffee = 150.0;
        Double first_result = STARTUP_WATER_LEVEL - 150.0;
        Double second_coffee = first_result + 84.0;

        assertTrue(ingredient.isOkay(preparation));
        assertEquals(STARTUP_WATER_LEVEL, ingredient.getStatus());

        ingredient.doCoffee(first_coffee);

        assertTrue(ingredient.isOkay(preparation));
        assertEquals(first_result, ingredient.getStatus());

        ingredient.doCoffee(second_coffee);

        assertFalse(ingredient.isOkay(preparation));
        assertEquals(LEVEL_NULL, ingredient.getStatus());

    }

}