package be.umons.macc.domain.coffeeMachine.state;

/**
 * Child of CoffeeMachineState
 * Functionalities are the same as the motherClasses and
 * More Specific when Overriding (polymorphism)
 * Essentially describe the state evolution flow
 */
public class IdleState extends CoffeeMachineState {

    private static IdleState instance;
    private static final String[] STATUS = {"[IDLE]","[BESCHIKBAAR]","[DISPONIBLE]"};
    private IdleState(){}

    public static IdleState getInstance() {
        if (instance == null) instance = new IdleState();
        return instance;
    }

    @Override
    protected void entry() {
        System.out.println("ENTER IDLE STATE");
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
