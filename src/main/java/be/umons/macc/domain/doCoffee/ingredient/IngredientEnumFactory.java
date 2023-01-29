package be.umons.macc.domain.doCoffee.ingredient;

import be.umons.macc.domain.doCoffee.preparation.PreparationType;

/**
 * This factory will provide Drink based on Enum Intern functionality
 */
public class IngredientEnumFactory {

    public static Drink prepare(PreparationType preparationType) {
        return preparationType.getIngredientType().preparation();
    }

}
