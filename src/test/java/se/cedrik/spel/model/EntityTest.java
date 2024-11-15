package se.cedrik.spel.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EntityTest {

    @Test
    public void testPunchDecreasesHealth() {
        Resident resident = new Resident("Resident", 20, 5);
        Burglar burglar = new Burglar("Burglar", 15, 4);


        burglar.punch(resident);


        assertEquals(16, resident.getHealth(), "Resident ska ha 16 i hälsa efter ett slag.");
    }

    @Test
    public void testPunchBelowZero() {
        Resident resident = new Resident("Resident", 5, 5);
        Burglar burglar = new Burglar("Burglar", 15, 4);


        burglar.punch(resident);
        burglar.punch(resident);


        assertEquals(0, resident.getHealth(), "Resident's .");
    }
}
