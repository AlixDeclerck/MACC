package be.umons.macc.domain.coffeeMachine.state.error;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.IdleState;

/**
 * Child of CoffeeMachineState
 * Essentially expose properties to Child classes
 */
public abstract class ErrorState extends CoffeeMachineState {

    protected static final String OVER_FLOW_TANK_BUTTON = "overFlowTankButton";
    protected static final String FILTER_BUTTON = "filterButton";
    protected static final String COFFEE_FILL_BUTTON = "coffeeFillButton";
    protected static final String WATER_FILL_BUTTON = "waterFillButton";
    protected static final String START_STOP_BUTTON = "startStopButton";
    protected static final String MAINTENANCE = "!! maintenance !!";
    protected static final String START_STOP = "Start / Stop";

    public CoffeeMachineState next() {
        return transition(IdleState.getInstance());
    }

    @Override
    public String displayStatus() {
        return "[ERROR]";
    }

}
