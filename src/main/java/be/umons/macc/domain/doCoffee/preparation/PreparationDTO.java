package be.umons.macc.domain.doCoffee.preparation;

import java.io.Serializable;

import static be.umons.macc.Configuration.LIQUID_MEASURE;
import static be.umons.macc.Configuration.SOLID_MEASURE;

public class PreparationDTO implements Serializable {

    private final String NAME;
    private PreparationType preparationType;
    private String waterQuantity;
    private String grainsQuantity;
    private String milkQuantity;
    private String chocolateQuantity;
    private String strength;
    private String cups;

    public PreparationDTO(String name) {
        this.NAME = name;
    }

    public PreparationDTO(String name, PreparationType preparationType) {
        this.NAME = name;
        this.preparationType = preparationType;
    }

    public void setPreparationType(PreparationType preparationType) {
        this.preparationType = preparationType;
    }

    public void setIngredients(String waterQuantity, String grainsQuantity, String milkQuantity, String chocolateQuantity) {
        this.waterQuantity = waterQuantity+ LIQUID_MEASURE;
        this.grainsQuantity = grainsQuantity+ SOLID_MEASURE;
        this.milkQuantity = milkQuantity+ LIQUID_MEASURE;
        this.chocolateQuantity = chocolateQuantity+ SOLID_MEASURE;
    }

    public void setDetails(String strength, String cups) {
        this.strength = strength;
        this.cups = cups;
    }

    public String getNAME() {
        return NAME;
    }

    public PreparationType getPreparationType() {
        return preparationType;
    }

    public String getWaterQuantity() {
        return waterQuantity;
    }

    public String getGrainsQuantity() {
        return grainsQuantity;
    }

    public String getMilkQuantity() {
        return milkQuantity;
    }

    public String getChocolateQuantity() {
        return chocolateQuantity;
    }

    public String getStrength() {
        return strength;
    }

    public String getCups() {
        return cups;
    }

}
