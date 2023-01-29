package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.doCoffee.preparation.Preparation;

public interface ConsumableItem {

    void doCoffee(Double qt);
    void renewItem();
    Double getStatus();
    boolean isOkay(Preparation p);

}
