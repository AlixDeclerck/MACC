package be.umons.macc.domain.coffeeMachine.state;

import be.umons.macc.domain.coffeeMachine.state.error.CheckMaintenanceErrorState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MaintenanceStateTest {

    MaintenanceState maintenanceState = MaintenanceState.getInstance();

    @Test
    public void nextState_classic() {
        CoffeeMachineState next = maintenanceState.next();
        assertTrue(next instanceof IdleState);
    }

    @Test
    public void nextState_maintenance() {
        assertTrue(maintenanceState.path(maintenanceState.isMaintenanceNeeded(13)) instanceof CheckMaintenanceErrorState);
    }

    @Test
    public void nextState_idle() {
        assertTrue(maintenanceState.path(maintenanceState.isMaintenanceNeeded(42)) instanceof IdleState);
    }

}
