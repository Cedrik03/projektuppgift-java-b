package se.cedrik.spel.model;

// Burglar är en typ av Entity
public class Burglar extends Entity {
    // Konstruktor för Burglar
    public Burglar(String role, int health, int damage) {
        super(role, health, damage); // Anropa superklassens konstruktor (Entity)
    }
}
