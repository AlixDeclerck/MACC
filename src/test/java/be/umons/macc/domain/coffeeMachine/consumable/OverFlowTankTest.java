package be.umons.macc.domain.coffeeMachine.consumable;

import be.umons.macc.domain.coffeeMachine.CoffeeMachine;
import be.umons.macc.domain.coffeeMachine.exception.UnexpectedBehaviorException;
import be.umons.macc.domain.doCoffee.CupNumber;
import be.umons.macc.domain.doCoffee.Strongness;
import be.umons.macc.domain.doCoffee.preparation.Preparation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.umons.macc.Configuration.OVERFLOW_EMPTY_LEVEL;
import static be.umons.macc.Configuration.STARTUP_OVERFLOW_START_LEVEL;
import static org.junit.jupiter.api.Assertions.*;

class OverFlowTankTest {

    Preparation preparation;

    @BeforeEach
    public void init() throws UnexpectedBehaviorException {
        preparation = new Preparation(new Strongness(), new CupNumber(), new CoffeeMachine());
    }

    @Test
    public void overFlowTank_status()  {
        OverFlowTank overFlowTank = new OverFlowTank();
        assertEquals(STARTUP_OVERFLOW_START_LEVEL, overFlowTank.getStatus());
        assertTrue(overFlowTank.isOkay(preparation));

        overFlowTank.renewItem();

        assertEquals(OVERFLOW_EMPTY_LEVEL, overFlowTank.getStatus());
        assertTrue(overFlowTank.isOkay(preparation));

    }

}