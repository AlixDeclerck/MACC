package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckWaterErrorState;
import javafx.application.Platform;

/**
 * Child of PreparationState child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class PercolateWaterState extends PreparationState {

    private static boolean error;
    private static PercolateWaterState instance;
    private static final String[] STATUS = {"[WATER PERCOLATING]","[WATER DOORSIJPELEN]","[PERCOLLATION]"};
    private PercolateWaterState() {}

    public static synchronized PercolateWaterState getInstance() {
        if (instance == null) instance = new PercolateWaterState();
        return instance;
    }

    @Override
    protected void entry() {
        error = !getPreparation().enoughWater();
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    /**
     * after percolating water history state is first step preparation again.
     */

    @Override
    protected void exit() {
        getPreparation().waterPercolating();
        setHistoryState(CheckTankState.getInstance());
    }

    @Override
    public CoffeeMachineState next() {
        if (error)
            return transition(CheckWaterErrorState.getInstance());
        return transition(ReadyState.getInstance());
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
