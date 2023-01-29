package be.umons.macc.gui.component;

import be.umons.macc.gui.component.strategy.ComponentStrategy;
import be.umons.macc.gui.component.translator.Visitor;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.List;

public class VisitedButtons implements Visited, ComponentStrategy {

    private final List<Button> buttons;
    public VisitedButtons(ActionEvent actionEvent) {
        buttons = retrieveButtons(actionEvent);
    }

    public List<Button> getButtons() {
        return buttons;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
