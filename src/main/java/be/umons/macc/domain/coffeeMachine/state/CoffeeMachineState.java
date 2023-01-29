package be.umons.macc.domain.coffeeMachine.state;

import be.umons.macc.gui.component.strategy.ButtonVisibilityStrategy;
import be.umons.macc.gui.component.strategy.LabelVisibilityStrategy;
import be.umons.macc.gui.component.translator.Language;
import javafx.event.ActionEvent;

/**
 * CoffeeMachine Mother class
 */
public abstract class CoffeeMachineState extends ButtonVisibilityStrategy implements LabelVisibilityStrategy {

    protected static ActionEvent actualEvent;
    protected static int langNumber;
    public abstract CoffeeMachineState next();
    public abstract String displayStatus();

    /**
     * Propagate the event to all CoffeeMachineState descendants so they will
     * be able to use the event content during state process.
     * @param event
     */

    public void registerActualEventToDescendants(ActionEvent event) {
        actualEvent = event;
    }
    public void registerLanguageToDescendants(Language language) {langNumber = language.getValue();}

    /**
     * This method was initially taken from the stopwatch pattern which was designed for. That give a canvas
     * of what is wished as implementation. The behavior is a classic state structure. entry() is what that do
     * entering in a new state and exit() is what that do after the processing of that state. Has we process
     * with the current and next state the exit (of the previous state) come first.
     * @param nextState
     * @return CoffeeMachineState
     */

    protected final CoffeeMachineState transition(CoffeeMachineState nextState) {
        exit();
        nextState.entry();
        nextState.updateStatus(nextState.displayStatus());
        return nextState;
    }

    protected void updateStatus(String status) {
        System.out.println("UPDATE STATUS : "+status);
    }

    protected void exit() {
        defaultOutput("Exit");
    }

    protected void entry() {
        defaultOutput("Enter");
    }

    private void defaultOutput(String action) {
        String[] stateTab = this.getClass().getName().split("\\.");
        System.out.println(action+" from "+stateTab[stateTab.length-1]+" with nothing to do.");
    }

}
