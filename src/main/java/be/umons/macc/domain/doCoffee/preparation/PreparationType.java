package be.umons.macc.domain.doCoffee.preparation;

import be.umons.macc.domain.doCoffee.ingredient.IngredientType;

import java.util.HashMap;
import java.util.Map;

public enum PreparationType {

    COFFEE("coffeeButton", IngredientType.COFFEE),
    CAPPUCCINO("cappuccinoButton", IngredientType.CAPPUCCINO),
    ESPRESSO("espressoButton", IngredientType.ESPRESSO),
    ESPRESSO_MACCHIATO("espressoMacchiatoButton", IngredientType.ESPRESSO_MACCHIATO),
    MACCHIATO("macchiatoButton", IngredientType.MACCHIATO),
    COFFEE_MILK("milkyCoffeeButton", IngredientType.COFFEE_MILK),
    MILK("milkyMousseButton", IngredientType.MILK);

    private final String buttonId;
    private final IngredientType ingredientType;

    private static final Map<String,PreparationType> preparationTypeLookup = new HashMap<>();

    static {
        for(PreparationType p : PreparationType.values())
            preparationTypeLookup.put(p.getButtonId(),p);
    }

    PreparationType(String buttonId, IngredientType ingredientType) {
        this.buttonId = buttonId;
        this.ingredientType = ingredientType;
    }

    public static PreparationType getPreparationTypeFromButtonValue(String buttonId) {
        return preparationTypeLookup.get(buttonId);
    }

    public  IngredientType getIngredientType() {
        return ingredientType;
    }

    public String getButtonId() {
        return buttonId;
    }

}
