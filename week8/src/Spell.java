/**
 * Created by Bilal on 8.12.2016 г..
 */
public class Spell {
    private static String[] names = {"The Claw of Hell", "Super-meow", "FlashBang"};
    private final String name;
    private int damage;
    private int points;
    private int range;

    public Spell(String name, int damage, int points, int range) {
        this.damage = damage;
        this.points = points;
        this.range = range;
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public static Spell generate() {
        String name = names[(int)(names.length * Math.random())];
        int points = (int)(10*Math.random()) + 10;
        int damage = (int)(20*Math.random()) + 20;
        int range = (int)(3*Math.random()) + 1;
        
        return new Spell(name,damage,points,range);
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public int getPoints() {
        return points;
    }
}
