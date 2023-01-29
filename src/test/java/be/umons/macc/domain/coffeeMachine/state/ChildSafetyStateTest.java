package be.umons.macc.domain.coffeeMachine.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChildSafetyStateTest {

    CoffeeMachineState childSafetyState = ChildSafetyState.getInstance();

    @Test
    void to_idle_state() {
        CoffeeMachineState next = childSafetyState.next();
        assertTrue(next instanceof IdleState);
    }

    @Test
    void to_childSafety_state() {
        CoffeeMachineState next = childSafetyState.next().next();
        assertTrue(next instanceof ChildSafetyState);
    }

}