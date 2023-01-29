package be.umons.macc.domain.coffeeMachine.state.error;

import javafx.application.Platform;

/**
 * Child of Error child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses;
 * More Specific when Overriding (polymorphism)
 * Specific error during preparation state flow
 */
public class CheckTankErrorState extends ErrorState {

    private static CheckTankErrorState instance;
    private static final String[] STATUS = {"[PLEASE CLEAN THE TANK]","[DE WATERTANK IS VOLE]","[LE BAC DE DEBORDEMENT EST PLEIN]"};
    private CheckTankErrorState() {}

    public static synchronized CheckTankErrorState getInstance() {
        if (instance == null) instance = new CheckTankErrorState();
        return instance;
    }

    @Override
    protected void entry() {
        register(actualEvent);
        buttonMaintenance(OVER_FLOW_TANK_BUTTON);
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
