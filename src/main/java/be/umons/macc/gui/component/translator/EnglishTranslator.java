package be.umons.macc.gui.component.translator;

import be.umons.macc.gui.component.VisitedButtons;
import be.umons.macc.gui.component.VisitedLabel;

public class EnglishTranslator extends Translator implements Visitor {

    @Override
    public void visit(VisitedButtons buttonManagement) {
        translateButtons(ResourceLibraryMapper.getEnglishText(), buttonManagement.getButtons());
    }

    @Override
    public void visit(VisitedLabel visitedLabel) {
        translateLabels(ResourceLibraryMapper.getEnglishText(), visitedLabel.getLabels());
    }


}
