package se.cedrik.spel.model;

// Resident är en typ av Entity
public class Resident extends Entity {
    // Konstruktor för Resident
    public Resident(String role, int health, int damage) {
        super(role, health, damage); // Anropa superklassens konstruktor (Entity)
    }
}
