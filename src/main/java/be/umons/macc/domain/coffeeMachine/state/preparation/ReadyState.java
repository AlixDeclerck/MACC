package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.DoneState;
import javafx.application.Platform;

/**
 * Child of PreparationState child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class ReadyState extends PreparationState {

    private static ReadyState instance;
    private static final String[] STATUS = {"[YOUR DRINK IS READY]","[JOUW DRINK KLAAR IS]","[VOTRE BOISSON EST PRETE]"};
    private ReadyState() {}

    public static synchronized ReadyState getInstance() {
        if (instance == null) instance = new ReadyState();
        return instance;
    }

    @Override
    protected void entry() {
        register(actualEvent);
        waitingToTakingPreparation();
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    @Override
    public CoffeeMachineState next() {
        return transition(DoneState.getInstance());
    }

    @Override
    protected void exit() {
        getPreparation().overFlow();
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
