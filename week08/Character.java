/**
 * Created by Bilal on 8.12.2016 г..
 */
public abstract class Character {
    protected int health;
    protected int mana;
    private int maxHealth;
    private int maxMana;


    public Character(int health, int mana) {
        this.health = health;
        this.mana = mana;
        this.maxHealth = health;
        this.maxMana = mana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isAlive(){
        return  health > 0;
    }

    public boolean canCast(){
        return mana > 0;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public void takeHealing(int healthPoints){
        health = Math.min(maxHealth, health+healthPoints);
    }

    public void takeMana(int manaPoints){
        mana = Math.min(maxMana, mana + manaPoints);
    }

    public abstract int attack(boolean isWeapon);
}
