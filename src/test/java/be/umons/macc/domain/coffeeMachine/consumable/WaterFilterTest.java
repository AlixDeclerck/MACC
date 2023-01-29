package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.doCoffee.CupNumber;
import be.umons.macc.domain.doCoffee.Strongness;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.umons.macc.Configuration.*;
import static be.umons.macc.Configuration.WATER_FILTER_CLEAN_LEVEL;
import static org.junit.jupiter.api.Assertions.*;

class WaterFilterTest {

    Double NULL_QUANTITY = 0.0;
    Preparation preparation;

    @BeforeEach
    public void init() throws UnexpectedBehaviorException {
        preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
    }

    @Test
    public void waterFilter_status()  {
        WaterFilter waterFilter = new WaterFilter();
        int limit = FILTER_LIMIT.intValue() - STARTUP_FILTER_START_LEVEL.intValue() - 1;
        int increment = WATER_FILTER_USAGE_INCREMENT.intValue();

        assertEquals(STARTUP_FILTER_START_LEVEL, waterFilter.getStatus());
        assertTrue(waterFilter.isOkay(preparation));

        for (int i=0; i < limit; i = i + increment) {
            waterFilter.doCoffee(NULL_QUANTITY);
            assertTrue(waterFilter.isOkay(preparation));
        }

        waterFilter.doCoffee(NULL_QUANTITY);
        assertEquals(FILTER_LIMIT, waterFilter.getStatus());

        waterFilter.doCoffee(NULL_QUANTITY);
        assertFalse(waterFilter.isOkay(preparation));

        waterFilter.renewItem();

        assertEquals(WATER_FILTER_CLEAN_LEVEL, waterFilter.getStatus());
        assertTrue(waterFilter.isOkay(preparation));

    }

}