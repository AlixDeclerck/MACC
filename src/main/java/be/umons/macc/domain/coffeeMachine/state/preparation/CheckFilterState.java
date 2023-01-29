package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckFilterErrorState;
import javafx.application.Platform;

/**
 * Child of PreparationState child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class CheckFilterState extends PreparationState {

    private static boolean error;
    private static CheckFilterState instance;
    private static final String[] STATUS = {"[TECHNICAL CHECK]","[TECHNISCHE CONTROLE]","[VERIFICATION TECHNIQUE]"};
    // private static final String[] STATUS = {"[CHECK FILTER]","[CHECK FILTER]","[CHECK FILTER]"}; // because several states in verification fx
    private CheckFilterState() {}

    public static synchronized CheckFilterState getInstance() {
        if (instance == null) instance = new CheckFilterState();
        return instance;
    }

    @Override
    protected void entry() {
        error = !getPreparation().canBeFiltered();
        if (isPercolateWaterHistoryState()) transition(PercolateWaterState.getInstance());
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    @Override
    public CoffeeMachineState next() {
        if (error)
            return transition(CheckFilterErrorState.getInstance());
        return transition(GrindGrainsState.getInstance());
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
