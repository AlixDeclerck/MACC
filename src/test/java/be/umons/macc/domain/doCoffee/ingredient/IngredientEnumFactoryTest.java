package be.umons.macc.domain.doCoffee.ingredient;

import org.junit.jupiter.api.Test;

import static be.umons.macc.domain.doCoffee.ingredient.IngredientEnumFactory.prepare;
import static be.umons.macc.domain.doCoffee.preparation.PreparationType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientEnumFactoryTest {

    @Test
    void prepare_COFFEE() {
        Drink drink = prepare(COFFEE);
        assertEquals(3.0, drink.coffeeFactor());
        assertEquals(0.0, drink.milkFactor());
        assertEquals(0.0, drink.chocolateFactor());
        assertEquals(2.0, drink.liquidFactor());
    }

    @Test
    void prepare_CAPPUCCINO() {
        Drink drink = prepare(CAPPUCCINO);
        assertEquals(2.0, drink.coffeeFactor());
        assertEquals(1.0, drink.milkFactor());
        assertEquals(2.0, drink.chocolateFactor());
        assertEquals(2.0, drink.liquidFactor());
    }

    @Test
    void prepare_ESPRESSO() {
        Drink drink = prepare(ESPRESSO);
        assertEquals(5.0, drink.coffeeFactor());
        assertEquals(0.0, drink.milkFactor());
        assertEquals(0.0, drink.chocolateFactor());
        assertEquals(1.0, drink.liquidFactor());
    }

    @Test
    void prepare_ESPRESSO_MACCHIATO() {
        Drink drink = prepare(ESPRESSO_MACCHIATO);
        assertEquals(4.0, drink.coffeeFactor());
        assertEquals(1.0, drink.milkFactor());
        assertEquals(1.0, drink.chocolateFactor());
        assertEquals(1.0, drink.liquidFactor());
    }

    @Test
    void prepare_MACCHIATO() {
        Drink drink = prepare(MACCHIATO);
        assertEquals(3.0, drink.coffeeFactor());
        assertEquals(1.0, drink.milkFactor());
        assertEquals(1.0, drink.chocolateFactor());
        assertEquals(2.0, drink.liquidFactor());
    }

    @Test
    void prepare_COFFEE_MILK() {
        Drink drink = prepare(COFFEE_MILK);
        assertEquals(4.0, drink.coffeeFactor());
        assertEquals(2.0, drink.milkFactor());
        assertEquals(0.0, drink.chocolateFactor());
        assertEquals(2.0, drink.liquidFactor());
    }

    @Test
    void prepare_MILK() {
        Drink drink = prepare(MILK);
        assertEquals(0.0, drink.coffeeFactor());
        assertEquals(4.0, drink.milkFactor());
        assertEquals(0.0, drink.chocolateFactor());
        assertEquals(2.0, drink.liquidFactor());
    }

}