package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckGrainsErrorState;
import javafx.application.Platform;

/**
 * Child of PreparationState child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class GrindGrainsState extends PreparationState {

    private static boolean error;
    private static GrindGrainsState instance;
    private static final String[] STATUS = {"[GRAINS GRINDING]","[MAALGRAAN]","[MOUTURE DU GRAINS]"};
    private GrindGrainsState() {}

    public static synchronized GrindGrainsState getInstance() {
        if (instance == null) instance = new GrindGrainsState();
        return instance;
    }

    /**
     * entry transit to percolate before grains grinding if historyState equals
     * PercolateWaterState because grind grains are already ready.
     */

    @Override
    protected void entry() {
        if (isPercolateWaterHistoryState()) transition(PercolateWaterState.getInstance());
        error = !getPreparation().enoughGrains();
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    @Override
    protected void exit() {
        getPreparation().grainsGrinding();
        setHistoryState(PercolateWaterState.getInstance());
    }

    @Override
    public CoffeeMachineState next() {
        if (error)
            return transition(CheckGrainsErrorState.getInstance());
        return transition(PercolateWaterState.getInstance());
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
