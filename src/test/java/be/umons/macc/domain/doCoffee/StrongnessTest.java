package be.umons.macc.domain.doCoffee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrongnessTest {

    Strongness strongness;

    @Test
    public void strongUp() {
        strongness = new Strongness();
        strongness.strongUp();
        assertEquals(strongness.getLevel(), "****");
    }

    @Test
    public void strongDown() {
        strongness = new Strongness();
        strongness.strongDown();
        assertEquals(strongness.getLevel(), "**");
    }

}
