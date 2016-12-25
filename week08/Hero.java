/**
 * Created by Bilal on 8.12.2016 г..
 */
public class Hero extends Character {
    private String name, title;
    private Spell spell;
    private Weapon weapon;
    private int regenRate, maxHealth, maxMana;

    public Hero(String name, String title, int health, int mana, int regenRate) {
        super(health, mana);
        this.name = name;
        this.title = title;
        this.maxHealth = health;
        this.maxMana = mana;
        this.regenRate = regenRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getRegenRate() {
        return regenRate;
    }

    public void setRegenRate(int regenRate) {
        this.regenRate = regenRate;
    }

    public String knownAs() {
        return name + " the " + title;
    }

    public void learnSpell(Spell spell) {
        this.spell = spell;
    }

    public void takeWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public boolean canCast() {
        return getSpell() != null && getMana() > getSpell().getPoints();
    }

    public int attack(boolean isWeapon) {
        if (isWeapon && weapon != null) {
            return weapon.getDamage();
        } else if (spell != null) {
            return spell.getDamage();
        }

        return 0;
    }

    public boolean hasSpell() {
        return spell != null;
    }

    public boolean hasWeapon() {
        return weapon != null;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Spell getSpell() {
        return spell;
    }

    public void regen() {
        health += regenRate;
        if (health > maxHealth)
            health = maxHealth;
    }
}
