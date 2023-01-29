package be.umons.macc.gui.component;

import be.umons.macc.gui.component.translator.Visitor;

public interface Visited {
    void accept(Visitor visitor);
}
