package be.umons.macc.domain.coffeeMachine;

import be.umons.macc.domain.coffeeMachine.consumable.ConsumableLevelFacade;
import be.umons.macc.domain.coffeeMachine.exception.TechnicalIssueException;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.CoffeeMachineState;
import be.umons.macc.domain.coffeeMachine.state.DoneState;
import be.umons.macc.domain.coffeeMachine.state.IdleState;
import be.umons.macc.domain.coffeeMachine.state.preparation.ReadyState;
import be.umons.macc.domain.doCoffee.CupNumber;
import be.umons.macc.domain.doCoffee.Strongness;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import be.umons.macc.domain.doCoffee.preparation.PreparationDTO;
import be.umons.macc.domain.doCoffee.preparation.PreparationType;

import java.util.concurrent.TimeUnit;

import static be.umons.macc.Configuration.COFFEE_MACHINE_LIMIT_OF_UTILISATION;
import static be.umons.macc.Configuration.PREPARATION_WAITING_TIME;

public class CoffeeMachine {

    private final CoffeeMachineContext context = new CoffeeMachineContext();
    private final Preparation preparation;
    private Preparation savedPreparation;
    private final ConsumableLevelFacade consumableLevelFacade = new ConsumableLevelFacade();

    private static int obsolescence;

    static {
        obsolescence = 104201;
    }

    public CoffeeMachine() throws UnexpectedBehaviorException {
        this.preparation = new Preparation(new Strongness(), new CupNumber(), this);
    }

    public void doMaintenance() {
        context.doMaintenance();
    }

    public void selectPreparation(PreparationDTO preparationQuantity, PreparationType preparationType) {
        preparationQuantity.setPreparationType(preparationType);
        preparation.updatePreparation(preparationQuantity);
    }

    // PREPARATION PHASES

    public void startPreparation() throws UnexpectedBehaviorException, TechnicalIssueException {
        if (obsolescence >= COFFEE_MACHINE_LIMIT_OF_UTILISATION)
            throw new TechnicalIssueException("white smoke, disturbing noise and strange smell. It's time to buy a new coffee machine..");
        context.preparationMode(preparation);
    }

    public void preparationGrains() throws InterruptedException {
        waiting();
        context.grainsGrinding();
    }

    public void waterPercolating() throws InterruptedException {
        waiting();
        context.waterPercolating();
    }

    public void finalisePreparation() throws InterruptedException {
        waiting();
        context.servePreparation();
    }

    public void takingPreparation() {
        context.takingPreparation();
    }


    // STATES

    public CoffeeMachineState state() {
        return context.getState();
    }

    public void childSafeOn() {
        context.nextState();
    }

    public void makeAvailable() {
        context.nextState();
    }

    public boolean isAvailable() {
        return (context.getState() instanceof IdleState || context.getState() instanceof DoneState);
    }

    public boolean isReady() {
        return (context.getState() instanceof ReadyState);
    }


    // GETTERS & INTERN

    public Preparation getPreparation() {
        return preparation;
    }

    public ConsumableLevelFacade getConsumableLevel() {
        return consumableLevelFacade;
    }

    private void waiting() throws InterruptedException {
        TimeUnit.SECONDS.sleep(PREPARATION_WAITING_TIME);
    }

    public PreparationDTO getFavoritePreparation() {
        return savedPreparation.getPreparationDTO();
    }

    public void saveFavoritePreparation(PreparationDTO currentPreparation) throws UnexpectedBehaviorException {
        savedPreparation = new Preparation(currentPreparation, this);
    }

    static void setObsolescence(int obsolescence) {
        CoffeeMachine.obsolescence = obsolescence;
    }

    public void safeLaunch() {
        context.safeLaunch();
    }
}
