package be.umons.macc.gui.deviceScreen;

import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import be.umons.macc.domain.doCoffee.preparation.PreparationDTO;
import be.umons.macc.gui.component.strategy.PreparationObserverStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static be.umons.macc.domain.doCoffee.preparation.PreparationType.getPreparationTypeFromButtonValue;
import static be.umons.macc.uc.UseCasesMapper.*;

/**
 * Display preparation information
 */
public class DeviceViewController implements PreparationObserverStrategy {

    @FXML
    Label deviceWaterInfo;

    @FXML
    Label deviceCoffeeInfo;

    @FXML
    Label deviceOverFlowInfo;

    @FXML
    Label deviceFilterInfo;

    @FXML
    Label drinkName;

    @FXML
    Label drinkQuantity;

    @FXML
    Label grainsQuantity;

    @FXML
    Label milkQuantity;

    @FXML
    Label chocolateQuantity;

    @FXML
    Label drinkStrongnessInfo;

    @FXML
    Label drinkCupsInfo;

    @FXML
    Label msgPreparation;

    @FXML
    Label msgState;

    private Preparation preparation;

    public void init(Preparation preparation) {
        this.preparation = preparation;
        preparation.addObserver(this);
    }

    public void viewInitialization(CoffeeMachine coffeeMachine) {
        msgState.setText(coffeeMachine.state().displayStatus());
        actualizePreparation();
        actualizeOverFlowTank(getOverflowTankLevel(coffeeMachine));
        actualizeWaterFilter(getWaterFilterLevel(coffeeMachine));
        actualizeReserveGrains(getCoffeeGrainsLevel(coffeeMachine));
        actualizeReserveWater(getWaterBacLevel(coffeeMachine));
    }

    @Override
    public void update() {
        actualizePreparation();
    }

    public void actualizePreparation() {
        setCurrentPreparation(preparation.getPreparationDTO());
    }

    public void actualizeOverFlowTank(String level) {
        deviceOverFlowInfo.setText(level);
    }

    public void actualizeWaterFilter(String level) {
        deviceFilterInfo.setText(level);
    }

    public void actualizeReserveGrains(String level) {
        deviceCoffeeInfo.setText(level);
    }

    public void actualizeReserveWater(String level) {
        deviceWaterInfo.setText(level);
    }

    public PreparationDTO getCurrentPreparation() {
        PreparationDTO currentPreparation = new PreparationDTO(drinkName.getText(), getPreparationTypeFromButtonValue(drinkName.getId()));
        currentPreparation.setIngredients(
                drinkQuantity.getText(),
                grainsQuantity.getText(),
                milkQuantity.getText(),
                chocolateQuantity.getText()
        );
        currentPreparation.setDetails(
                drinkStrongnessInfo.getText(),
                drinkCupsInfo.getText()
        );

        return currentPreparation;
    }

    public void setCurrentPreparation(PreparationDTO favoritePreparation) {
        drinkName.setText(favoritePreparation.getNAME());
        drinkQuantity.setText(favoritePreparation.getWaterQuantity());
        grainsQuantity.setText(favoritePreparation.getGrainsQuantity());
        milkQuantity.setText(favoritePreparation.getMilkQuantity());
        chocolateQuantity.setText(favoritePreparation.getChocolateQuantity());
        drinkStrongnessInfo.setText(favoritePreparation.getStrength());
        drinkCupsInfo.setText(favoritePreparation.getCups());
    }
}
