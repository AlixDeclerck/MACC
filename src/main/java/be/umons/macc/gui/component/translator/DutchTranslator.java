package be.umons.macc.gui.component.translator;

import be.umons.macc.gui.component.VisitedButtons;
import be.umons.macc.gui.component.VisitedLabel;

public class DutchTranslator extends Translator implements Visitor {

    @Override
    public void visit(VisitedButtons buttonManagement) {
        translateButtons(ResourceLibraryMapper.getDutchText(), buttonManagement.getButtons());
    }

    @Override
    public void visit(VisitedLabel visitedLabel) {
        translateLabels(ResourceLibraryMapper.getDutchText(), visitedLabel.getLabels());
    }

}
