package week08;

/**
 * Created by Bilal on 8.12.2016 г..
 */
public enum Tile {
    HERO("H"),
    ENEMY("E"),
    TREASURE("S"),
    WEAPON("W"),
    PATH(" "),
    WALL("#"),
    GATE("G");

    private String value;
    Tile(String path){
        this.value = path;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }
}
