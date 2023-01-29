package be.umons.macc.domain.coffeeMachine.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoneStateTest {

    CoffeeMachineState doneState = DoneState.getInstance();

    @Test
    void to_idle_state() {
        CoffeeMachineState next = doneState.next();
        assertTrue(next instanceof ChildSafetyState);
    }

    @Test
    void to_childSafety_state() {
        CoffeeMachineState next = doneState.next().next();
        assertTrue(next instanceof IdleState);
    }

}