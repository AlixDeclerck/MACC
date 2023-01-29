package be.umons.macc.domain.doCoffee.preparation;

import be.umons.macc.Configuration;
import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.doCoffee.CupNumber;
import be.umons.macc.domain.doCoffee.Strongness;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static be.umons.macc.Configuration.MILK_MOCK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PreparationTest {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private final String NULL_SOLID_VALUE = "0"+Configuration.SOLID_MEASURE;
    private final String NULL_LIQUID_VALUE = "0"+Configuration.LIQUID_MEASURE;

    CoffeeMachine coffeeMachine;

    private Preparation preparation;

    @BeforeEach
    void init() {

        try {
            coffeeMachine = new CoffeeMachine();
            preparation = new Preparation(new Strongness(), new CupNumber(), coffeeMachine);
        } catch (UnexpectedBehaviorException e) {
            e.printStackTrace();
        }
    }

    @Test
    void selectPreparation_DEFAULT() {
        PreparationDTO newPreparation = new PreparationDTO("");
        PreparationDTO updatedPreparation = null;

        preparation.updatePreparation(newPreparation);
        Double coffeeFactor = preparation.getDrink().coffeeFactor();
        Double liquidFactor = preparation.getDrink().liquidFactor();
        updatedPreparation = preparation.getPreparationDTO();

        String grainsQuantity = decimalFormat.format(preparation.getGroundingCoffeeQuantity()*coffeeFactor)+ Configuration.SOLID_MEASURE;
        String waterQuantity = decimalFormat.format(preparation.getEffectiveWaterQuantity()*liquidFactor)+ Configuration.LIQUID_MEASURE;

        assertEquals(grainsQuantity, updatedPreparation.getGrainsQuantity());
        assertEquals(waterQuantity, updatedPreparation.getWaterQuantity());
        assertEquals(NULL_LIQUID_VALUE, updatedPreparation.getMilkQuantity());
        assertEquals(NULL_SOLID_VALUE, updatedPreparation.getChocolateQuantity());
    }

    @Test
    void selectPreparation_CAFE() {
        PreparationDTO newPreparation = new PreparationDTO("COFFEE", PreparationType.COFFEE);
        PreparationDTO updatedPreparation = null;

        preparation.updatePreparation(newPreparation);
        Double coffeeFactor = preparation.getDrink().coffeeFactor();
        Double liquidFactor = preparation.getDrink().liquidFactor();
        updatedPreparation = preparation.getPreparationDTO();

        String grainsQuantity = decimalFormat.format(preparation.getGroundingCoffeeQuantity()*coffeeFactor)+ Configuration.SOLID_MEASURE;
        String waterQuantity = decimalFormat.format(preparation.getEffectiveWaterQuantity()*liquidFactor)+ Configuration.LIQUID_MEASURE;

        assertEquals(grainsQuantity, updatedPreparation.getGrainsQuantity());
        assertEquals(waterQuantity, updatedPreparation.getWaterQuantity());
        assertEquals(NULL_LIQUID_VALUE, updatedPreparation.getMilkQuantity());
        assertEquals(NULL_SOLID_VALUE, updatedPreparation.getChocolateQuantity());

    }

    @Test
    void selectPreparation_ESPRESSO() {
        PreparationDTO newPreparation = new PreparationDTO("ESPRESSO", PreparationType.ESPRESSO);
        PreparationDTO updatedPreparation = null;

        preparation.updatePreparation(newPreparation);
        Double coffeeFactor = preparation.getDrink().coffeeFactor();
        Double liquidFactor = preparation.getDrink().liquidFactor();
        updatedPreparation = preparation.getPreparationDTO();

        String grainsQuantity = decimalFormat.format(preparation.getGroundingCoffeeQuantity()*coffeeFactor)+ Configuration.SOLID_MEASURE;
        String waterQuantity = decimalFormat.format(preparation.getEffectiveWaterQuantity()*liquidFactor)+ Configuration.LIQUID_MEASURE;

        assertEquals(grainsQuantity, updatedPreparation.getGrainsQuantity());
        assertEquals(waterQuantity, updatedPreparation.getWaterQuantity());
        assertEquals(NULL_LIQUID_VALUE, updatedPreparation.getMilkQuantity());
        assertEquals(NULL_SOLID_VALUE, updatedPreparation.getChocolateQuantity());
    }

    @Test
    void selectPreparation_MACCHIATO() {
        PreparationDTO newPreparation = new PreparationDTO("MACCHIATO", PreparationType.MACCHIATO);
        PreparationDTO updatedPreparation = null;

        preparation.updatePreparation(newPreparation);
        Double coffeeFactor = preparation.getDrink().coffeeFactor();
        Double liquidFactor = preparation.getDrink().liquidFactor();
        Double milkFactor = preparation.getDrink().milkFactor();
        Double chocolateFactor = preparation.getDrink().chocolateFactor();
        updatedPreparation = preparation.getPreparationDTO();

        String grainsQuantity = decimalFormat.format(preparation.getGroundingCoffeeQuantity()*coffeeFactor)+ Configuration.SOLID_MEASURE;
        String waterQuantity = decimalFormat.format(preparation.getEffectiveWaterQuantity()*liquidFactor)+ Configuration.LIQUID_MEASURE;
        String milkQuantity = decimalFormat.format(MILK_MOCK * milkFactor)+ Configuration.LIQUID_MEASURE;
        String chocolateQuantity = decimalFormat.format(Configuration.CHOCOLATE_MOCK * chocolateFactor)+ Configuration.SOLID_MEASURE;

        assertEquals(grainsQuantity, updatedPreparation.getGrainsQuantity());
        assertEquals(waterQuantity, updatedPreparation.getWaterQuantity());
        assertEquals(milkQuantity, updatedPreparation.getMilkQuantity());
        assertEquals(chocolateQuantity, updatedPreparation.getChocolateQuantity());
    }

}
