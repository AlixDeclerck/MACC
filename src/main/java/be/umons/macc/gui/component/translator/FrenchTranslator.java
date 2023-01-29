package be.umons.macc.gui.component.translator;

import be.umons.macc.gui.component.VisitedButtons;
import be.umons.macc.gui.component.VisitedLabel;

public class FrenchTranslator extends Translator implements Visitor {

    @Override
    public void visit(VisitedButtons buttonManagement) {
        translateButtons(ResourceLibraryMapper.getFrenchText(), buttonManagement.getButtons());
    }

    @Override
    public void visit(VisitedLabel visitedLabel) {
        translateLabels(ResourceLibraryMapper.getFrenchText(), visitedLabel.getLabels());
    }


}
