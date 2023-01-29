package be.umons.macc.domain.doCoffee;

import java.util.HashMap;
import java.util.Map;

public enum StrongnessEnum {

    LIGHT(1, "*"),
    SWEET(2, "**"),
    MIDDLE(3, "***"),
    STRONG(4, "****"),
    ULTRA_STRONG(5, "*****");

    private final int intensity;
    private final String keyValue;

    private static final Map<String, StrongnessEnum> StrongnessLookup = new HashMap<>();

    static {
        for(StrongnessEnum s : StrongnessEnum.values())
            StrongnessLookup.put(s.getKeyValue(),s);
    }

    StrongnessEnum(int intensity, String keyValue){
        this.intensity = intensity;
        this.keyValue = keyValue;
    }

    public static StrongnessEnum getEnumFromDescription(String description) {
        return StrongnessLookup.get(description);
    }

    public int getIntensity() {return intensity;}

    public String getKeyValue(){
        return keyValue;
    }
}
