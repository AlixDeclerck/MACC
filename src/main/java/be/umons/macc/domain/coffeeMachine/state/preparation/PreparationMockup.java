package be.umons.macc.domain.coffeeMachine.state.preparation;

import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.doCoffee.CupNumber;
import be.umons.macc.domain.doCoffee.Strongness;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import be.umons.macc.domain.doCoffee.preparation.PreparationDTO;
import be.umons.macc.domain.doCoffee.preparation.PreparationType;

/**
 * Provide Preparations Mocks with several values
 */
public interface PreparationMockup {

    default Preparation doCoffee() throws UnexpectedBehaviorException {
        PreparationDTO prep = new PreparationDTO(PreparationType.COFFEE.getButtonId());
        prep.setIngredients("0 cl", "0 gr", "0 cl", "0 gr");
        Preparation preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
        preparation.updatePreparation(prep);

        return preparation;
    }

    default Preparation doStrongCoffee() throws UnexpectedBehaviorException {
        PreparationDTO prep = new PreparationDTO(PreparationType.ESPRESSO.getButtonId(), PreparationType.ESPRESSO);
        prep.setIngredients("0 cl", "0 gr", "0 cl", "0 gr");
        Preparation preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
        preparation.updatePreparation(prep);
        preparation.strongUp(); preparation.strongUp();
        preparation.switchCupNumber();

        return preparation;
    }

    default Preparation doBigCoffee() throws UnexpectedBehaviorException {
        PreparationDTO prep = new PreparationDTO(PreparationType.COFFEE.getButtonId());
        prep.setIngredients("0 cl", "0 gr", "0 cl", "0 gr");
        Preparation preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
        preparation.updatePreparation(prep);
        preparation.switchCupNumber();
        for (int i =0; i < 100; i++)
            preparation.moreQuantity();

        return preparation;
    }

    default Preparation doMilk() throws UnexpectedBehaviorException {
        PreparationDTO prep = new PreparationDTO(PreparationType.MILK.getButtonId());
        prep.setIngredients("0 cl", "0 gr", "10 cl", "0 gr");
        Preparation preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
        preparation.updatePreparation(prep);

        return preparation;
    }

    default Preparation doChocolate() throws UnexpectedBehaviorException {
        PreparationDTO prep = new PreparationDTO(PreparationType.MILK.getButtonId());
        prep.setIngredients("0 cl", "0 gr", "0 cl", "5 gr");
        Preparation preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
        preparation.updatePreparation(prep);

        return preparation;
    }

}
