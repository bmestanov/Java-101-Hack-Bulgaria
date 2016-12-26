package week08;

/**
 * Created by Bilal on 8.12.2016 г..
 */
public class Weapon {
    private static String[] names =  {"The Sword of Azimuth", "The Axe of Destruction", "The Spear of Justice"};
    private int damage;
    private String name;

    public Weapon(String name, int damage) {
        this.damage = damage;
        this.name = name;
    }

    public static Weapon generate() {
        String name = names[(int)(names.length * Math.random())];
        int damage = (int)(20*Math.random()) + 20;


        return new Weapon(name,damage);
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
