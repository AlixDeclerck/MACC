package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckTankErrorState;
import javafx.application.Platform;

/**
 * Child of PreparationState child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class CheckTankState extends PreparationState {

    private static boolean error;
    private static CheckTankState instance;
    private static final String[] STATUS = {"[CHECK FLOW TANK]","[CONTROLE VAN HET  FLOW TANK]","[VERIFICATION FLOW TANK]"};
    private CheckTankState() {}

    public static synchronized CheckTankState getInstance() {
        if (instance == null) instance = new CheckTankState();
        return instance;
    }

    @Override
    protected void entry() {
        error = !getPreparation().willSplashNot();
        if (isPercolateWaterHistoryState()) transition(PercolateWaterState.getInstance());
    }

    @Override
    public CoffeeMachineState next() {
        entry();
        if (error)
            return transition(CheckTankErrorState.getInstance());
        return transition(CheckFilterState.getInstance());
    }

    @Override
    protected void exit() {
        register(actualEvent);
        disableNewPreparationFunctionality(true);
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
