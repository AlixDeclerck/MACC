package be.umons.macc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static be.umons.macc.Configuration.*;


/**
 * A classic JavaFX launcher
 */
public class Launcher extends Application {

    private static Stage stage;

    @Override
    public void start(Stage s) {
        stage = s;
        VBox scene = new VBox(new VBox(new Separator(Orientation.VERTICAL)), new StackPane());
        s.setTitle(Configuration.APPLICATION_TITLE);
        s.setScene(new Scene(scene, APPLICATION_SCENE_WIDTH, APPLICATION_SCENE_HEIGHT));
        s.show();

        if (!COUNTRY.equals(LOC.getCountry()))
            LOC = new Locale("en", COUNTRY);
        else if (!"fr".equals(LOC.getLanguage()) & !"nl".equals(LOC.getLanguage()))
            LOC = new Locale("en", COUNTRY);

        panelLauncher(LOC);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void panelLauncher(Locale locale) {
        try {

            ResourceBundle bundle = ResourceBundle.getBundle(UI_RESOURCES, locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(APPLICATION_MAIN_PAGE), bundle);

            Pane pane = loader.load();
            StackPane content = (StackPane) ((VBox) stage.getScene().getRoot()).getChildren().get(1);
            content.getChildren().clear();
            content.getChildren().add(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
