package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.doCoffee.preparation.Preparation;

/**
 * Child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public abstract class PreparationState extends CoffeeMachineState {

    private static PreparationState instance;
    private static PreparationState historyState;

    private static Preparation onGoingPreparation;
    public static synchronized PreparationState getInstance() {
        if (instance == null) {
            historyState = CheckTankState.getInstance();
            instance = CheckTankState.getInstance();
        }
        return instance;
    }

    public PreparationState newPreparation(Preparation preparation) {
        onGoingPreparation = preparation;
        return getInstance();
    }

    public CoffeeMachineState next() {
        return transition(CheckTankState.getInstance());
    }

    public static void setHistoryState(PreparationState savingState) {
        historyState = savingState;
    }

    public static boolean isPercolateWaterHistoryState() {
        return PercolateWaterState.getInstance().equals(historyState);
    }

    protected Preparation getPreparation() {
        return onGoingPreparation;
    }

    @Override
    public String displayStatus() {
        return "[PREPARATION]";
    }

}
