package be.umons.macc.domain.coffeeMachine.state.error;

import javafx.application.Platform;

/**
 * Child of Error child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses,
 * More Specific when Overriding (polymorphism)
 * Specific error during maintenance
 */
public class CheckMaintenanceErrorState extends ErrorState {

    private static CheckMaintenanceErrorState instance;
    private static final String[] STATUS = {"[MAINTENANCE TIME]","[MAINTENANCE NODIG]","[MAINTENANCE]"};
    private CheckMaintenanceErrorState() {}

    public static synchronized CheckMaintenanceErrorState getInstance() {
        if (instance == null) instance = new CheckMaintenanceErrorState();
        return instance;
    }

    @Override
    protected void entry() {
        register(actualEvent);
        disableButton(START_STOP_BUTTON, true);
        buttonMaintenance(MAINTENANCE);
        Platform.runLater(
                () -> {
                    changeState(actualEvent, STATUS[langNumber]);
                }
        );
    }

    @Override
    protected void exit() {
        register(actualEvent);
        disableButton(START_STOP_BUTTON, false);
        buttonMaintenance(START_STOP);
    }

    public String displayStatus() {
        return STATUS[langNumber];
    }

}
