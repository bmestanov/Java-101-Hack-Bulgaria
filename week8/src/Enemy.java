/**
 * Created by Bilal on 8.12.2016 г..
 */
public class Enemy extends Character {
    int damage;

    public Enemy(int health, int mana, int damage) {
        super(health, mana);
        this.damage = damage;
    }

    @Override
    public int attack(boolean isWeapon) {
        /**
         * Damage decreases overtime.
         */
        damage = Math.max(0, damage-1);
        return damage;
    }

    public static Enemy generate() {
        int health = (int)(Math.random()*50) + 20;
        int damage = (int)(Math.random()*20) + 20;
        return new Enemy(health,0,damage);
    }

    public int getDamage() {
        return damage;
    }
}
