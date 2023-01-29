package be.umons.macc.domain.coffeeMachine.state.error;

import javafx.application.Platform;

/**
 * Child of Error child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses;
 * More Specific when Overriding (polymorphism)
 * Specific error during preparation state flow
 */
public class CheckGrainsErrorState extends ErrorState {

    private static CheckGrainsErrorState instance;
    private static final String[] STATUS = {"[ADD COFFEE GRAINS]","[KOFFIEBOON NODIG]","[FAUT DU GRAIN]"};
    private CheckGrainsErrorState() {}

    public static synchronized CheckGrainsErrorState getInstance() {
        if (instance == null) instance = new CheckGrainsErrorState();
        return instance;
    }

    @Override
    protected void entry() {
        register(actualEvent);
        buttonMaintenance(COFFEE_FILL_BUTTON);
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    public String displayStatus() {
        return STATUS[langNumber];
    }

}
