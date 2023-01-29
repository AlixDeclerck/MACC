package be.umons.macc.gui.component.strategy;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

import static be.umons.macc.Configuration.DYNAMIC_COMPONENT_BUTTON;
import static be.umons.macc.Configuration.DYNAMIC_COMPONENT_LABEL;

public interface ComponentStrategy {

    default List<Button> retrieveButtons(ActionEvent actionEvent) {
        List<Button> buttons = new LinkedList<>();
        if (actionEvent == null) return buttons;

        Node source = (Node) actionEvent.getSource();
        Scene theScene = source.getScene();

        for (String s : DYNAMIC_COMPONENT_BUTTON) {
            Pane h = (Pane) theScene.lookup("#" + s);
            for (Object o : h.getChildren())
                if (o instanceof Button) buttons.add((Button)o);
        }

        return buttons;

    }

    default List<Label> retrieveLabels(ActionEvent actionEvent) {
        List<Label> labels = new LinkedList<>();
        if (actionEvent == null) return labels;

        Node source = (Node) actionEvent.getSource();
        Scene theScene = source.getScene();

        for (String s : DYNAMIC_COMPONENT_LABEL) {
            Pane h = (Pane) theScene.lookup("#" + s);
            for (Object o : h.getChildren())
                if (o instanceof Label) labels.add((Label)o);
        }

        return labels;

    }

}
