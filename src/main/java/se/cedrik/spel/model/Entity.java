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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public void punch(Entity toPunch) {
        toPunch.takeHit(this.damage);
        System.out.println(this.role + " punches " + toPunch.getRole() + "for " + this.damage + " damage");

    }

    public void takeHit(int takeHit) {
        this.health -= takeHit;
        if (this.health < 0) {
            this.health = 0;
        }

    }

    public boolean isConscious() {
        return this.health > 0;
    }
    public void addDamage (int damage) {
        this.damage += damage;
    }
}
