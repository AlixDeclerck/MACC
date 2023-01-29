package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.DoneState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckFilterErrorState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckGrainsErrorState;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import be.umons.macc.gui.component.strategy.JavaFxToolkitSessionLocker;
import org.junit.jupiter.api.Test;

import static be.umons.macc.Configuration.MAX_WATER;
import static be.umons.macc.Configuration.STARTUP_GRAINS_LEVEL;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreparationStateTest implements PreparationMockup, JavaFxToolkitSessionLocker {

    public static final int ESPRESSO_MAX_GRAIN_LEVEL = 50;
    public static final int MAX_WATER_IN_A_PREPARATION = 100;

    @Test
    public void happy_preparation() throws Exception {

        countDownLatch();
        Preparation preparation = doMilk();

        PreparationState preparationState = PreparationState.getInstance();
        preparationState.newPreparation(preparation);

        assertTrue(preparationState instanceof CheckTankState);

        CoffeeMachineState step1 = preparationState.next();
        assertTrue(step1 instanceof CheckFilterState);

        CoffeeMachineState step2 = step1.next();
        assertTrue(step2 instanceof GrindGrainsState);

        CoffeeMachineState step3 = step2.next();
        assertTrue(step3 instanceof PercolateWaterState);

        CoffeeMachineState step4 = step3.next();
        assertTrue(step4 instanceof ReadyState);

        CoffeeMachineState step5 = step4.next();
        assertTrue(step5 instanceof DoneState);

    }

    @Test
    public void preparation_grains_needed() throws Exception {

        countDownLatch();
        Preparation preparation = doStrongCoffee();

        for (int i = 0; i < (STARTUP_GRAINS_LEVEL/ ESPRESSO_MAX_GRAIN_LEVEL); i++) {
            preparation.grainsGrinding();
        }

        PreparationState preparationState = PreparationState.getInstance();
        preparationState.newPreparation(preparation);

        assertTrue(preparationState instanceof CheckTankState);

        CoffeeMachineState step1 = preparationState.next();
        assertTrue(step1 instanceof CheckFilterState);

        CoffeeMachineState step2 = step1.next();
        assertTrue(step2 instanceof GrindGrainsState);

        CoffeeMachineState step3 = step2.next();
        assertTrue(step3 instanceof CheckGrainsErrorState);

    }

    @Test
    public void preparation_water_filter_to_change() throws Exception {

        countDownLatch();
        Preparation preparation = doBigCoffee();

        for (int i = 0; i < (MAX_WATER / MAX_WATER_IN_A_PREPARATION); i++) {
            preparation.waterPercolating();
        }

        PreparationState preparationState = PreparationState.getInstance();
        preparationState.newPreparation(preparation);

        assertTrue(preparationState instanceof CheckTankState);

        CoffeeMachineState step1 = preparationState.next();
        assertTrue(step1 instanceof CheckFilterState);

        CoffeeMachineState step2 = step1.next();
        assertTrue(step2 instanceof CheckFilterErrorState);

    }

}