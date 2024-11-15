package se.cedrik.spel.model;

public abstract class Entity {
    private String role;
    private int health;
    private int damage;

    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    // Getter för health
    public int getHealth() {
        return health;
    }

    // Setter för health
    public void setHealth(int health) {
        this.health = health;
    }

    // Getter för damage
    public int getDamage() {
        return damage;
    }

    // Getter för role
    public String getRole() {
        return role;
    }

    // punch metoden som attackerar en annan entity
    public void punch(Entity toPunch) {
        toPunch.takeHit(this.damage); // Skada tilldelas den attackerade entiteten
    }

    // Metod för att ta emot skada
    public void takeHit(int damage) {
        this.health = Math.max(0, this.health - damage);  // Se till att hälsan inte går under 0
    }

    // Kontrollera om entiteten är medveten (hälsa > 0)
    public boolean isConscious() {
        return this.health > 0;
    }
}
