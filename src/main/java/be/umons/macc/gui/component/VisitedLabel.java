package be.umons.macc.gui.component;

import be.umons.macc.gui.component.strategy.ComponentStrategy;
import be.umons.macc.gui.component.translator.Visitor;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.util.List;

public class VisitedLabel implements Visited, ComponentStrategy {

    private final List<Label> labels;
    public VisitedLabel(ActionEvent actionEvent) {
        labels = retrieveLabels(actionEvent);
    }

    public List<Label> getLabels() {
        return labels;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
