package be.umons.macc.gui.component.translator;

import java.util.*;

import static be.umons.macc.Configuration.COUNTRY;
import static be.umons.macc.Configuration.UI_RESOURCES;
import static java.util.ResourceBundle.getBundle;

public class ResourceLibraryMapper {

    public static Map<String, String> getEnglishText() {
        return getDefinitions(getBundle(UI_RESOURCES, new Locale("EN", COUNTRY)));
    }

    public static Map<String, String> getDutchText() {
        return getDefinitions(getBundle(UI_RESOURCES, new Locale("NL", COUNTRY)));
    }

    public static Map<String, String> getFrenchText() {
        return getDefinitions(getBundle(UI_RESOURCES, new Locale("FR", COUNTRY)));
    }

    private static Map<String, String> getDefinitions(ResourceBundle RESOURCE_BUNDLE_EN) {

        Map<String, String> definitions = new HashMap<>();
        Enumeration<String> bundleKeys = RESOURCE_BUNDLE_EN.getKeys();

        while(bundleKeys.hasMoreElements()) {
            String iKey = bundleKeys.nextElement();
            definitions.put(iKey, RESOURCE_BUNDLE_EN.getString(iKey));
        }

        return definitions;
    }

}
