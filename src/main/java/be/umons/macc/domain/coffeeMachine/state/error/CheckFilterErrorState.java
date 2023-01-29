package be.umons.macc.domain.coffeeMachine.state.error;

import javafx.application.Platform;

/**
 * Child of Error child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses;
 * More Specific when Overriding (polymorphism)
 * Specific error during preparation state flow
 */
public class CheckFilterErrorState extends ErrorState {

    private static CheckFilterErrorState instance;
    private static final String[] STATUS = {"[CHANGE THE WATER FILTER]","[NIEW WATERFILTER NODIG]","[VEUILLEZ CHANGER LE FILTRE A EAU]"};
    private CheckFilterErrorState() {}

    public static synchronized CheckFilterErrorState getInstance() {
        if (instance == null) instance = new CheckFilterErrorState();
        return instance;
    }

    @Override
    protected void entry() {
        register(actualEvent);
        buttonMaintenance(FILTER_BUTTON);
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
