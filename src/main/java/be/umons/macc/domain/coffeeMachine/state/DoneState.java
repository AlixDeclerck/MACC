package be.umons.macc.domain.coffeeMachine.state;

/**
 * Child of CoffeeMachineState
 * Functionalities are the same as the motherClasses and
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class DoneState extends CoffeeMachineState {

    private static DoneState instance;
    private static final String[] STATUS = {"[DONE]","[KLAAR]","[BOISSON PRETE]"};
    private DoneState(){}

    public static DoneState getInstance() {
        if (instance == null) instance = new DoneState();
        return instance;
    }

    @Override
    protected void entry() {
        System.out.println("THANKS !!");
        register(actualEvent);
        disableNewPreparationFunctionality(false);
        changeState(actualEvent, STATUS[langNumber]);
    }

    public CoffeeMachineState next() {
        return transition(ChildSafetyState.getInstance());
    }

    @Override
    public String displayStatus() {
        return STATUS[langNumber];
    }

}
