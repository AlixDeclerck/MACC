package be.umons.macc.domain.coffeeMachine;

import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.IdleState;
import be.umons.macc.domain.coffeeMachine.state.MaintenanceState;
import be.umons.macc.domain.coffeeMachine.state.preparation.PreparationState;
import be.umons.macc.domain.doCoffee.preparation.Preparation;

/**
 * The state pattern context class
 * preparationMode, grainsGrinding, waterPercolating, servePreparation, takingPreparation
 * are separated methods to let other class manage somme functionalities as the
 * time that each action take
 */
public class CoffeeMachineContext  {

    private CoffeeMachineState currentState = MaintenanceState.getInstance();

    public void doMaintenance() {
        nextState();
    }

    public CoffeeMachineState getCoffeeMachineState() {
        return currentState;
    }

    CoffeeMachineState getState() {
        return currentState;
    }

    void nextState() {
        setState(currentState.next());
    }

    private void setState(CoffeeMachineState instance) {
        this.currentState = instance;
    }

    void preparationMode(Preparation preparation) throws UnexpectedBehaviorException {
        if (preparation == null) {
            throw new UnexpectedBehaviorException("null preparation not allowed here");
        }

        // INIT AND CHECK TANK PHASE
        setState(PreparationState.getInstance().newPreparation(preparation));

        // CHECK FILTER PHASE
        nextState();

    }

    void grainsGrinding() {
        nextState();
    }

    void waterPercolating() {
        nextState();
    }

    void servePreparation() {
        nextState();
    }

    void takingPreparation() {
        System.out.println(currentState.displayStatus());
        nextState();
    }

    String status() {
        String state = getState().getClass().getName();
        String[] stateTab = state.split("\\.");
        return stateTab[stateTab.length-1];
    }

    void safeLaunch() {
        System.out.println("[SAFE LAUNCH]");
        currentState = IdleState.getInstance();
    }

}
