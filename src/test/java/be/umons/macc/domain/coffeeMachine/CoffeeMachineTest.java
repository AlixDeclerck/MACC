package be.umons.macc.domain.coffeeMachine;

import be.umons.macc.domain.coffeeMachine.exception.TechnicalIssueException;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.IdleState;
import be.umons.macc.domain.coffeeMachine.state.MaintenanceState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckMaintenanceErrorState;
import be.umons.macc.domain.coffeeMachine.state.preparation.CheckFilterState;
import be.umons.macc.domain.doCoffee.preparation.PreparationDTO;
import be.umons.macc.domain.doCoffee.preparation.PreparationType;
import be.umons.macc.gui.component.strategy.JavaFxToolkitSessionLocker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.umons.macc.Configuration.COFFEE_MACHINE_LIMIT_OF_UTILISATION;
import static org.junit.jupiter.api.Assertions.*;

class CoffeeMachineTest implements JavaFxToolkitSessionLocker {

    CoffeeMachine coffeeMachine;

    CoffeeMachineTest() throws UnexpectedBehaviorException {
        coffeeMachine = new CoffeeMachine();
    }

    @BeforeEach
    void safeLaunch() {
        if (coffeeMachine.state() instanceof CheckMaintenanceErrorState)
            coffeeMachine.safeLaunch();
    }

    @Test
    void doMaintenance() {
        assertTrue(coffeeMachine.state() instanceof MaintenanceState);
        coffeeMachine.doMaintenance();
        boolean result = (coffeeMachine.state() instanceof IdleState || coffeeMachine.state() instanceof CheckMaintenanceErrorState);
        assertTrue(result);
    }

    @Test
    void happy_Preparation() throws TechnicalIssueException, UnexpectedBehaviorException, InterruptedException {
        boolean result = (coffeeMachine.state() instanceof MaintenanceState);
        CoffeeMachine.setObsolescence(12345);
        assertTrue(result);
        countDownLatch();
        coffeeMachine.startPreparation();
        assertTrue(coffeeMachine.state() instanceof CheckFilterState);
    }

    @Test
    void not_happy_Preparation() throws InterruptedException {
        boolean result = (coffeeMachine.state() instanceof MaintenanceState);
        CoffeeMachine.setObsolescence(COFFEE_MACHINE_LIMIT_OF_UTILISATION);
        assertTrue(result);
        countDownLatch();
        assertThrows(TechnicalIssueException.class, () -> {
            coffeeMachine.startPreparation();
        });
    }

    @Test
    void selectPreparation() {
        PreparationDTO preparationQuantity = new PreparationDTO(PreparationType.COFFEE.getButtonId(), PreparationType.COFFEE);
        coffeeMachine.selectPreparation(preparationQuantity, PreparationType.COFFEE);
        PreparationDTO effectivePreparationQuantity = coffeeMachine.getPreparation().getPreparationDTO();
        assertEquals(preparationQuantity.getPreparationType(), effectivePreparationQuantity.getPreparationType());
    }

}