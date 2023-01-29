package be.umons.macc;

import java.util.Locale;

/**
 * Technical configuration file
 */
public class Configuration {

    public static final String APPLICATION_TITLE = "MACC";
    public static final String APPLICATION_MAIN_PAGE = "gui/mainScreen/main.fxml";
    public static final String COUNTRY = "BE";
    public static final String UI_RESOURCES = "UIResources";
    public static Locale LOC = Locale.getDefault();
    public static final Integer APPLICATION_SCENE_WIDTH = 1500;
    public static final Integer APPLICATION_SCENE_HEIGHT = 650;

    // Config
    public static final Integer PREPARATION_WAITING_TIME = 1;
    public static final Double LEVEL_INCREMENT = 0.25;
    public static final Integer PROGRESS_BAR_MAX = 1;
    public static final Integer MAINTENANCE_NUM_OF_CHANCES = 42;
    public static final Integer MAINTENANCE_DO_MAINTENANCE_NUMBER = 42;
    public static final Integer COFFEE_MACHINE_LIMIT_OF_UTILISATION = 1000000;
    public static final String[] DYNAMIC_COMPONENT_LABEL = {"ingredientLabelBox","preparationLabelBox"};
    public static final String[] DYNAMIC_COMPONENT_BUTTON = {"memoryBox", "selectionBox", "preparationBox", "quantityBox", "strongnessBox", "menuBox", "humanActionBox"};

    // Default values
    public static final String NAME = ":-)";
    public static final Double WATER_BASE_QUANTITY = 10.0;
    public static final Double COFFEE_COEFFICIENT = 0.1;
    public static final Double MILK_MOCK = 5.0;
    public static final Double CHOCOLATE_MOCK = 1.0;
    public static final Integer STARTUP_CUP_NUMBER = 1;
    public static final Double STARTUP_WATER_LEVEL = 200.0;
    public static final Double STARTUP_GRAINS_LEVEL = 350.0;
    public static final Double STARTUP_OVERFLOW_START_LEVEL = 15.0;
    public static final Double STARTUP_FILTER_START_LEVEL = 44.0;

    // Measures
    public static final String LIQUID_MEASURE = " cl";
    public static final String SOLID_MEASURE = " gr";
    public static final String QUANTITY_SEPARATOR = " / ";

    // Generics
    public static final Double LEVEL_NULL = 0.0;
    public static final Double MAX_GRAINS = 500.0;
    public static final Double MAX_WATER = 2000.0;
    public static final Double OVERFLOW_LIMIT = 20.0;
    public static final Double OVERFLOW_TANK_FACTOR = 1.1;;
    public static final Double OVERFLOW_EMPTY_LEVEL = 0.0;
    public static final Double WATER_FILTER_USAGE_INCREMENT = 1.0;
    public static final Double FILTER_LIMIT = 50.0;
    public static final Double WATER_FILTER_CLEAN_LEVEL = 0.0;

}
