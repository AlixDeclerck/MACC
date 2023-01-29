package be.umons.macc.domain.coffeeMachine.state;

/**
 * Child of CoffeeMachineState
 * Functionalities are the same as the motherClasses and
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class ChildSafetyState extends CoffeeMachineState {

    private static ChildSafetyState instance;
    private static final String[] STATUS = {"[CHILD SAFETY]","[SECURITEIT VAN DE KINDEREN]","[SECURITE ENFANTS]"};
    private ChildSafetyState(){}

    public static ChildSafetyState getInstance() {
        if (instance == null) instance = new ChildSafetyState();
        return instance;
    }

    @Override
    protected void entry() {
        System.out.println("ENTER CHILD SAFETY");
        register(actualEvent);
        disableNewPreparationFunctionality(true);
        changeState(actualEvent, STATUS[langNumber]);
    }

    public CoffeeMachineState next() {
        return transition(IdleState.getInstance());
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
