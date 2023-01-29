package be.umons.macc.domain.doCoffee.preparation;

import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.doCoffee.CupNumber;
import be.umons.macc.domain.doCoffee.CupNumberEnum;
import be.umons.macc.domain.doCoffee.Strongness;
import be.umons.macc.domain.doCoffee.ingredient.Drink;

import java.text.DecimalFormat;

import static be.umons.macc.Configuration.*;
import static be.umons.macc.domain.doCoffee.StrongnessEnum.getEnumFromDescription;
import static be.umons.macc.domain.doCoffee.ingredient.IngredientEnumFactory.prepare;
import static be.umons.macc.domain.doCoffee.preparation.PreparationType.COFFEE;
import static be.umons.macc.domain.doCoffee.preparation.PreparationType.MILK;

public class Preparation extends ObserverStrategy {

    private CoffeeMachine coffeeMachine;

    private Double groundingCoffeeQuantity;
    private Double groundingWaterQuantity;
    private Double effectiveWaterQuantity;
    private Double effectiveMilkQuantity;
    private PreparationDTO preparationDTO;
    private Drink drink;

    private final Strongness strongness;
    private final CupNumber cupNumber;

    private Double progressionLevel;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public Preparation(Strongness strongness, CupNumber cupNumber, CoffeeMachine coffeeMachine) throws UnexpectedBehaviorException {
        progressionLevel = 0.0;
        addMachine(coffeeMachine);
        this.strongness = strongness;
        this.cupNumber = cupNumber;
        this.groundingWaterQuantity = WATER_BASE_QUANTITY * cupNumber.getValue();
        this.effectiveWaterQuantity = WATER_BASE_QUANTITY * cupNumber.getValue();
        actualizeTheGrainsQuantity();
    }

    public Preparation(PreparationDTO currentPreparation, CoffeeMachine coffeeMachine) throws UnexpectedBehaviorException {
        addMachine(coffeeMachine);

        this.strongness = new Strongness(getEnumFromDescription(currentPreparation.getStrength()));
        this.cupNumber = new CupNumber(currentPreparation.getCups());

        updatePreparation(currentPreparation);
    }

    public void updatePreparation(PreparationDTO preparationDTO) {
        if (preparationDTO == null) return;
        if (preparationDTO.getPreparationType() == null) preparationDTO.setPreparationType(COFFEE);

        this.groundingWaterQuantity = WATER_BASE_QUANTITY * cupNumber.getValue();
        drink = prepare(preparationDTO.getPreparationType());

        this.preparationDTO = preparationDTO;

        updatePreparation();
    }

    private void updatePreparation() {

        effectiveMilkQuantity = MILK_MOCK * drink.milkFactor();

        if (MILK.equals(preparationDTO.getPreparationType()))
            effectiveWaterQuantity = 0.0;
        else
            effectiveWaterQuantity = groundingWaterQuantity;

        actualizeTheGrainsQuantity();

        this.preparationDTO.setIngredients(
                decimalFormat.format(effectiveWaterQuantity * drink.liquidFactor()),
                decimalFormat.format(groundingCoffeeQuantity * drink.coffeeFactor()),
                decimalFormat.format(effectiveMilkQuantity),
                decimalFormat.format(CHOCOLATE_MOCK * drink.chocolateFactor())
        );

        this.preparationDTO.setDetails(
                strongness.getLevel(),
                CupNumberEnum.getSValue(cupNumber.getEnum())
        );

        notifierObservers();
    }

    // PREPARATION CONFIGURATION (grounding values)

    public void lessQuantity() {
        if (groundingWaterQuantity > 0) {
            groundingWaterQuantity--;
            updatePreparation();
        }
    }

    public void moreQuantity() {
        if (groundingWaterQuantity < 100) {
            groundingWaterQuantity++;
            updatePreparation();
        }
    }

    public void switchCupNumber() {
        cupNumber.switchCupNumber();
        if (cupNumber.isTwice())
            doubleQuantity();
        else
            divideQuantityBy2();
    }

    public void strongUp() {
        strongness.strongUp();
        updatePreparation();
    }

    public void strongDown() {
        strongness.strongDown();
        updatePreparation();
    }

    // STATES PREOCCUPATION

    public void grainsGrinding() {
        if (drink == null) return;
        coffeeMachine.getConsumableLevel().doCoffeeUsingGrainsFromReserve(groundingCoffeeQuantity * drink.coffeeFactor());
    }

    public void waterPercolating() {
        if (drink == null) return;
        coffeeMachine.getConsumableLevel().doCoffeeUsingWaterFromReserve(effectiveWaterQuantity * drink.liquidFactor());
        coffeeMachine.getConsumableLevel().doCoffeeUseFilter(WATER_FILTER_USAGE_INCREMENT);
    }

    public void overFlow() {
        double overFlowValue = getCupNumber().getValue().doubleValue() + effectiveMilkQuantity;
        coffeeMachine.getConsumableLevel().doCoffeeUsingOverFlowTank(overFlowValue);
    }

    public boolean canBeFiltered() {
        return coffeeMachine.getConsumableLevel().isFilterOkay(this);
    }

    public boolean willSplashNot() {
        return coffeeMachine.getConsumableLevel().isOverFlowTankOkay(this);
    }

    public boolean enoughGrains() {
        return coffeeMachine.getConsumableLevel().isGrainsReserveOkay(this);
    }

    public boolean enoughWater() {
        return coffeeMachine.getConsumableLevel().isWaterReserveOkay(this);
    }

    // GETTERS

    public PreparationDTO getPreparationDTO() {
        return preparationDTO;
    }

    public Double getGroundingCoffeeQuantity() {
        return groundingCoffeeQuantity;
    }

    public Double getEffectiveWaterQuantity() {
        return effectiveWaterQuantity;
    }

    public CupNumber getCupNumber() {
        return cupNumber;
    }

    public double getProgressionLevel() {
        return progressionLevel;
    }

    public void setProgressionLevel(double level) {
        this.progressionLevel = level;
    }

    public Drink getDrink() {
        return drink;
    }

    // LOCALS

    private void actualizeTheGrainsQuantity() {
        groundingCoffeeQuantity = COFFEE_COEFFICIENT * strongness.getIntensity() * groundingWaterQuantity;
    }

    private void addMachine(CoffeeMachine coffeeMachine) throws UnexpectedBehaviorException {
        if (coffeeMachine == null)
            throw new UnexpectedBehaviorException("null machine not allowed here");

        this.coffeeMachine = coffeeMachine;
    }

    private void doubleQuantity() {
        groundingWaterQuantity = groundingWaterQuantity * 2;
        updatePreparation();
    }

    private void divideQuantityBy2() {
        groundingWaterQuantity = groundingWaterQuantity / 2.0;
        updatePreparation();
    }

}
