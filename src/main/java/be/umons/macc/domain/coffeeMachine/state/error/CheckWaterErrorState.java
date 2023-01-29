package be.umons.macc.domain.coffeeMachine.state.error;

import javafx.application.Platform;

/**
 * Child of Error child of CoffeeMachineState
 * Functionalities are the same idea as the motherClasses;
 * More Specific when Overriding (polymorphism)
 * Specific error during preparation state flow
 */
public class CheckWaterErrorState extends ErrorState {

    private static CheckWaterErrorState instance;
    private static final String[] STATUS = {"[NEED TO ADD WATER]","[DE WATERBAC IS LEEG]","[MET DE L'EAU]"};
    private CheckWaterErrorState() {}

    public static synchronized CheckWaterErrorState getInstance() {
        if (instance == null) instance = new CheckWaterErrorState();
        return instance;
    }

    @Override
    protected void entry() {
        register(actualEvent);
        buttonMaintenance(WATER_FILL_BUTTON);
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
