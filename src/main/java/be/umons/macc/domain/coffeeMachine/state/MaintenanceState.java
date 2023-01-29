package be.umons.macc.domain.coffeeMachine.state;

import be.umons.macc.domain.coffeeMachine.state.error.CheckMaintenanceErrorState;

import java.util.Random;

import static be.umons.macc.Configuration.MAINTENANCE_DO_MAINTENANCE_NUMBER;
import static be.umons.macc.Configuration.MAINTENANCE_NUM_OF_CHANCES;

/**
 * Child of CoffeeMachineState
 * Functionalities are the same as the motherClasses and
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class MaintenanceState extends CoffeeMachineState {

    private boolean error;
    private static MaintenanceState instance;
    private MaintenanceState() {}

    public static synchronized MaintenanceState getInstance() {
        if (instance == null)
            instance = new MaintenanceState();
        return instance;
    }

    @Override
    protected void entry() {
        Random random = new Random();
        int randomResult = random.nextInt(MAINTENANCE_NUM_OF_CHANCES + 1) + 1;
        System.out.println("[" + randomResult + "]");
        this.error = isMaintenanceNeeded(randomResult);
    }

    boolean isMaintenanceNeeded(int randomValue) {
        return MAINTENANCE_DO_MAINTENANCE_NUMBER == randomValue;
    }

    CoffeeMachineState path(boolean error) {
        if(error)
            return CheckMaintenanceErrorState.getInstance();

        System.out.println("[MAINTENANCE OK]");
        return IdleState.getInstance();
    }

    @Override
    public CoffeeMachineState next() {
        entry();
        return path(error);
    }

    @Override
    public String displayStatus() {
        return "[MAINTENANCE]";
    }
}
