package be.umons.macc.domain.doCoffee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CupNumberTest {

    private CupNumber cupNumber = new CupNumber();

    @Test
    void switchCupNumber() {
        assertFalse(cupNumber.isTwice());
        cupNumber.switchCupNumber();
        assertTrue(cupNumber.isTwice());
        cupNumber.switchCupNumber();
        assertFalse(cupNumber.isTwice());
    }

}