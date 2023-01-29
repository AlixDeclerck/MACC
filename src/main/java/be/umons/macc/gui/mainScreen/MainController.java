package be.umons.macc.gui.mainScreen;

import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.TechnicalIssueException;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.coffeeMachine.state.ChildSafetyState;
import be.umons.macc.domain.coffeeMachine.state.error.CheckMaintenanceErrorState;
import be.umons.macc.domain.coffeeMachine.state.error.ErrorState;
import be.umons.macc.gui.component.Visited;
import be.umons.macc.gui.component.VisitedButtons;
import be.umons.macc.gui.component.VisitedLabel;
import be.umons.macc.gui.component.translator.*;
import be.umons.macc.gui.deviceScreen.DeviceViewController;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.util.ResourceBundle;

import static be.umons.macc.Configuration.PROGRESS_BAR_MAX;
import static be.umons.macc.Configuration.UI_RESOURCES;
import static be.umons.macc.gui.component.translator.Language.*;
import static be.umons.macc.uc.UseCasesMapper.*;
import static java.util.Locale.getDefault;
import static javafx.concurrent.Worker.State.*;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.ButtonType.CLOSE;

/**
 * Handle the buttons Events From the view
 * Call uses cases functionalities
 */
public class MainController {

    private CoffeeMachine coffeeMachine;
    private Service<Void> preparationService;
    private Language selectedLanguage;

    @FXML
    DeviceViewController deviceViewController;

    @FXML
    Button childrenSecurityButton;

    @FXML
    Button okButton;

    @FXML
    Button startStopButton;

    @FXML
    ProgressBar preparationBar;

    @FXML
    public void initialize() {

        try {
            coffeeMachine = new CoffeeMachine();
            initializeCoffeeMachine(coffeeMachine);
            languageInitialization();
            deviceViewController.init(coffeeMachine.getPreparation());
            deviceViewController.viewInitialization(coffeeMachine);

            initButtons();

            initObservers();

        } catch (UnexpectedBehaviorException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                Alert dialog = new Alert(ERROR, e.getMessage(), CLOSE);
                dialog.show();
            });
        }
    }



    // BUTTON EVENTS

    public void changeDrink(ActionEvent actionEvent) {
        Button bt = (Button)actionEvent.getSource();
        selectPreparationType(coffeeMachine, bt.getId(), bt.getText());
    }

    public void moreStrong() {
        increasePreparationStrongness(coffeeMachine.getPreparation());
    }

    public void lessStrong() {
        decreasePreparationStrongness(coffeeMachine.getPreparation());
    }

    public void doubleCupsNumber() {
        twicingCups(coffeeMachine.getPreparation());
    }

    public void lessQuantity() {
        decreasePreparationQuantity(coffeeMachine.getPreparation());
    }

    public void moreQuantity() {
        increasePreparationQuantity(coffeeMachine.getPreparation());
    }

    public void humanActionWaterFill() {
        deviceViewController.actualizeReserveWater(fillWaterBac(coffeeMachine));
        makeAvailable();
    }

    public void humanActionCoffeeFill() {
        deviceViewController.actualizeReserveGrains(fillGrainsReserve(coffeeMachine));
        makeAvailable();
    }

    public void humanActionFilterChange() {
        deviceViewController.actualizeWaterFilter(changeCoffeeFilter(coffeeMachine));
        makeAvailable();
    }

    public void humanActionOverflowTank() {
        deviceViewController.actualizeOverFlowTank(cleanOverflowTank(coffeeMachine));
        makeAvailable();
    }

    public void humanActionMaintenance() {
        doMaintenance(startStopButton);
    }

    public void preparation(ActionEvent event) {
        if (RUNNING == preparationService.getState()) {
            preparationService.cancel();
        } else if (CANCELLED == preparationService.getState()) {
            coffeeMachine.state().registerActualEventToDescendants(event);
            preparationService.reset();
            preparationService.start();
        } else if (READY == preparationService.getState()) {
            coffeeMachine.state().registerActualEventToDescendants(event);
            preparationService.start();
        }
    }

    public void takePreparation(ActionEvent event) {
        if (coffeeMachine.isReady()) {
            preparationService.reset();
            preparationBar = new ProgressBar();
            preparationBar.progressProperty().bind(preparationService.progressProperty());
            coffeeMachine.state().registerActualEventToDescendants(event);
            takePreparationFromMachine(coffeeMachine);
        }
    }

    public void langToEN(ActionEvent actionEvent) {
        selectedLanguage = ENGLISH;
        coffeeMachine.state().registerLanguageToDescendants(selectedLanguage);
        Visitor translator = new EnglishTranslator();
        Visited buttonsManagement = new VisitedButtons(actionEvent);
        Visited labelManagement = new VisitedLabel(actionEvent);

        buttonsManagement.accept(translator);
        labelManagement.accept(translator);
    }

    public void langToNL(ActionEvent actionEvent) {
        selectedLanguage = DUTCH;
        coffeeMachine.state().registerLanguageToDescendants(selectedLanguage);
        Visitor translator = new DutchTranslator();
        Visited buttonsManagement = new VisitedButtons(actionEvent);
        Visited labelManagement = new VisitedLabel(actionEvent);

        buttonsManagement.accept(translator);
        labelManagement.accept(translator);
    }

    public void langToFR(ActionEvent actionEvent) {
        selectedLanguage = FRENCH;
        coffeeMachine.state().registerLanguageToDescendants(selectedLanguage);
        Visitor translator = new FrenchTranslator();
        Visited buttonsManagement = new VisitedButtons(actionEvent);
        Visited labelManagement = new VisitedLabel(actionEvent);

        buttonsManagement.accept(translator);
        labelManagement.accept(translator);
    }

    private void initButtons() {
        okButton.setDisable(true);
        if (coffeeMachine.state() instanceof CheckMaintenanceErrorState) {
            startStopButton.setDisable(true);
            startStopButton.setText("[MAINTENANCE]");
        }
    }

    private void initObservers() {

        preparationService = new Service<Void>(){

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>(){

                    @Override
                    protected Void call() throws InterruptedException, UnexpectedBehaviorException, TechnicalIssueException {
                        startStop(coffeeMachine); levelUp();
                        if (isErrorState()) return null;
                        preparationGrains(coffeeMachine); levelUp();
                        if (isErrorState()) return null;
                        preparationWater(coffeeMachine); levelUp();
                        if (isErrorState()) return null;
                        finalisePreparation(coffeeMachine); levelUp();
                        return null;
                    }

                    private boolean isErrorState() {
                        return coffeeMachine.state() instanceof ErrorState;
                    }

                    private void levelUp() {
                        updateProgress(
                                setProgressionLevelUp(coffeeMachine.getPreparation()),
                                PROGRESS_BAR_MAX
                        );
                    }
                };
            }
        };

        preparationService.setOnSucceeded((WorkerStateEvent event) -> {
            deviceViewController.actualizeReserveGrains(getCoffeeGrainsLevel(coffeeMachine));
            deviceViewController.actualizeReserveWater(getWaterBacLevel(coffeeMachine));
            deviceViewController.actualizeOverFlowTank(getOverflowTankLevel(coffeeMachine));
            deviceViewController.actualizeWaterFilter(getWaterFilterLevel(coffeeMachine));
        });

        preparationBar.progressProperty().bind(preparationService.progressProperty());
        childrenSecurityButton.setOnAction((event) -> {
            coffeeMachine.state().registerActualEventToDescendants(event);
            if (coffeeMachine.state() instanceof ChildSafetyState) {
                waitingPreparationOn(coffeeMachine);
            } else {
                childSafetyOn(coffeeMachine);
            }
        });
    }

    public void saveFavorite() throws UnexpectedBehaviorException {
        saveFavoritePreparation(
                coffeeMachine,
                deviceViewController.getCurrentPreparation());
    }

    public void readFavorite() {
        deviceViewController.setCurrentPreparation(coffeeMachine.getFavoritePreparation());
    }

    private void makeAvailable() {
        if (coffeeMachine.isAvailable()) return;
        preparationService.reset();
        preparationBar = new ProgressBar();
        preparationBar.progressProperty().bind(preparationService.progressProperty());
        waitingPreparationOn(coffeeMachine);
        resetLevel(coffeeMachine.getPreparation());
    }

    private void languageInitialization() {
        ResourceBundle bundle = ResourceBundle.getBundle(UI_RESOURCES, getDefault());
        if (bundle.getLocale().toString().equals("fr_BE")) selectedLanguage = FRENCH;
        else if (bundle.getLocale().toString().equals("nl_BE")) selectedLanguage = DUTCH;
        coffeeMachine.state().registerLanguageToDescendants(selectedLanguage);
    }

}
