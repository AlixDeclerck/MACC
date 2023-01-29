package be.umons.macc.gui.component.translator;

import be.umons.macc.gui.component.VisitedButtons;
import be.umons.macc.gui.component.VisitedLabel;

public interface Visitor {
    void visit(VisitedButtons buttonManagement);
    void visit(VisitedLabel visitedLabel);
}
