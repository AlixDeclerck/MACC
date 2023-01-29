package be.umons.macc.domain.doCoffee.preparation;

import be.umons.macc.gui.component.strategy.PreparationObserverStrategy;

import java.util.ArrayList;
import java.util.List;

public class ObserverStrategy {

    private final List<PreparationObserverStrategy> observerStrategies = new ArrayList<>();

    public synchronized void addObserver(PreparationObserverStrategy preparationObserverStrategy) {
        if (!observerStrategies.contains(preparationObserverStrategy)) observerStrategies.add(preparationObserverStrategy);
    }

    public synchronized void removeObserver(PreparationObserverStrategy preparationObserverStrategy) {
        observerStrategies.remove(preparationObserverStrategy);
    }

    protected synchronized void notifierObservers() {
        for (PreparationObserverStrategy preparationObserverStrategy : observerStrategies) preparationObserverStrategy.update();
    }

}
