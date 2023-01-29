package be.umons.macc.uc;

import be.umons.macc.Configuration;
import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.TechnicalIssueException;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.error.CheckMaintenanceErrorState;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import be.umons.macc.domain.doCoffee.preparation.PreparationDTO;
import be.umons.macc.domain.doCoffee.preparation.PreparationType;
import javafx.scene.control.Button;

import static be.umons.macc.Configuration.*;
import static java.lang.Math.floor;

/**
 * Uses cases after analysis and some output mappings.
 */
public class UseCasesMapper {

    public static void initializeCoffeeMachine(CoffeeMachine coffeeMachine) {
        coffeeMachine.doMaintenance();
        coffeeMachine.selectPreparation(new PreparationDTO(NAME), PreparationType.COFFEE);
    }


    // OPTIONS

    public static void increasePreparationStrongness(Preparation preparation) {
        preparation.strongUp();
    }

    public static void decreasePreparationStrongness(Preparation preparation) {
        preparation.strongDown();
    }

    public static void twicingCups(Preparation preparation) {
        preparation.switchCupNumber();
    }

    public static void increasePreparationQuantity(Preparation preparation) {
        preparation.moreQuantity();
    }

    public static void decreasePreparationQuantity(Preparation preparation) {
        preparation.lessQuantity();
    }

    public static void childSafetyOn(CoffeeMachine coffeeMachine) {
        coffeeMachine.childSafeOn();
    }

    public static void waitingPreparationOn(CoffeeMachine coffeeMachine) {
        coffeeMachine.makeAvailable();
    }

    public static void saveFavoritePreparation(CoffeeMachine coffeeMachine, PreparationDTO currentPreparation) throws UnexpectedBehaviorException {
        coffeeMachine.saveFavoritePreparation(currentPreparation);
    }

    // DO PREPARATION
    public static void selectPreparationType(CoffeeMachine coffeeMachine, String id, String name) {
        coffeeMachine.selectPreparation(new PreparationDTO(name), PreparationType.getPreparationTypeFromButtonValue(id));
    }

    public static void startStop(CoffeeMachine coffeeMachine) throws UnexpectedBehaviorException, TechnicalIssueException {
        coffeeMachine.startPreparation();
    }

    public static void preparationGrains(CoffeeMachine coffeeMachine) throws InterruptedException {
        coffeeMachine.preparationGrains();
    }

    public static void preparationWater(CoffeeMachine coffeeMachine) throws InterruptedException {
        coffeeMachine.waterPercolating();
    }

    public static void finalisePreparation(CoffeeMachine coffeeMachine) throws InterruptedException {
        coffeeMachine.finalisePreparation();
    }

    public static void takePreparationFromMachine(CoffeeMachine coffeeMachine) {
        resetLevel(coffeeMachine.getPreparation());
        coffeeMachine.takingPreparation();
    }

    public static double setProgressionLevelUp(Preparation preparation) {
        double level = preparation.getProgressionLevel();
        level += LEVEL_INCREMENT;
        preparation.setProgressionLevel(level);
        return preparation.getProgressionLevel();
    }

    public static void resetLevel(Preparation preparation) {
        preparation.setProgressionLevel(LEVEL_NULL);
    }


    // CONSUMABLE & MAINTENANCES ACTIONS

    public static String cleanOverflowTank(CoffeeMachine coffeeMachine) {
        coffeeMachine.getConsumableLevel().renewOverFlowTank();
        return getOverflowTankLevel(coffeeMachine);
    }

    public static String changeCoffeeFilter(CoffeeMachine coffeeMachine) {
        coffeeMachine.getConsumableLevel().renewFilter();
        return getWaterFilterLevel(coffeeMachine);
    }

    public static String fillGrainsReserve(CoffeeMachine coffeeMachine) {
        coffeeMachine.getConsumableLevel().renewGrainsReserve();
        return getCoffeeGrainsLevel(coffeeMachine);
    }

    public static String fillWaterBac(CoffeeMachine coffeeMachine) {
        coffeeMachine.getConsumableLevel().renewWaterReserve();
        return getWaterBacLevel(coffeeMachine);
    }

    public static void doMaintenance(Button startstop) {
        startstop.setDisable(false);
        startstop.setText("Start / Stop!");
    }


    // CONSUMABLE MAPPERS

    public static String getOverflowTankLevel(CoffeeMachine coffeeMachine) {
        return coffeeMachine.getConsumableLevel().getOverFlowTankStatus().toString()+QUANTITY_SEPARATOR+OVERFLOW_LIMIT+LIQUID_MEASURE;
    }

    public static String getWaterFilterLevel(CoffeeMachine coffeeMachine) {
        return coffeeMachine.getConsumableLevel().getStatusOfFilter()+ QUANTITY_SEPARATOR+ (int) floor(Configuration.FILTER_LIMIT);
    }

    public static String getCoffeeGrainsLevel(CoffeeMachine coffeeMachine) {
        return coffeeMachine.getConsumableLevel().getStatusOfGrains()+ SOLID_MEASURE;
    }

    public static String getWaterBacLevel(CoffeeMachine coffeeMachine) {
        return coffeeMachine.getConsumableLevel().getStatusOfWater()+ LIQUID_MEASURE;
    }
}
