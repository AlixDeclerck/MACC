package be.umons.macc.gui.component.strategy;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public interface LabelVisibilityStrategy {

    String STATE_LABEL_ID = "msgState";

    default void changeState(ActionEvent event, String text) {
        if (event == null) return;
        if (!(event.getSource() instanceof Node)) return;

        Node source = (Node) event.getSource();
        Scene theScene = source.getScene();
        Label l = (Label) theScene.lookup("#" + STATE_LABEL_ID);
        l.setText(text);
    }

}
