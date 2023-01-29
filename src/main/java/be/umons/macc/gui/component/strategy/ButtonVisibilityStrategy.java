package be.umons.macc.gui.component.strategy;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *  The ButtonVisibilityStrategy is two steps approach,
 *  First an event is registered then the Component Strategy
 *  can process this event which has to be a Child of the Node.
 */
public class ButtonVisibilityStrategy implements ComponentStrategy {

    private static final String OK_BUTTON_ID = "okButton";
    private ActionEvent event = new ActionEvent();

    protected void register(ActionEvent event) {
        this.event = event;
    }

    protected void disableNewPreparationFunctionality(boolean ok) {
        if (this.event == null) return;
        if (!(event.getSource() instanceof Node)) return;

        buttonsDisable(ok);
        if (!ok) disableOkButton();
    }

    protected void waitingToTakingPreparation() {
        if (this.event == null) return;
        if (!(event.getSource() instanceof Node)) return;

        for (Button b : retrieveButtons(event))
            b.setDisable(!b.getId().equals(OK_BUTTON_ID));
    }

    protected void buttonMaintenance(String element) {
        if (this.event == null) return;
        if (!(event.getSource() instanceof Node)) return;

        for (Button b : retrieveButtons(event))
            b.setDisable(!b.getId().equals(element));
    }

    protected void disableButton(String element, boolean status) {
        if (this.event == null) return;
        if (!(event.getSource() instanceof Node)) return;

        Node source = (Node) event.getSource();
        Scene theScene = source.getScene();
        Button b = (Button) theScene.lookup("#" + element);
        b.setDisable(status);
    }

    private void disableOkButton() {
        for (Button b : retrieveButtons(event))
            b.setDisable(b.getId().equals(OK_BUTTON_ID));
    }

    private void buttonsDisable(boolean disable) {
        for (Button b : retrieveButtons(event))
            if (!b.getId().equals(((Button) event.getSource()).getId()))
                b.setDisable(disable);
    }

}
