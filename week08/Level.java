package week08;

import java.io.*;

/**
 * Created by Bilal on 8.12.2016 г..
 */
public class Level implements Serializable {
    private static final long serialVersionUID = 8334032830581082987L;
    private Tile[] dungeon;
    private int level;
    private int focusX, focusY;
    private Tile currentTile;

    public Level(Tile[] dungeon, int level) {
        this.dungeon = dungeon;
        this.level = level;
    }

    public Level(int level) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Level readLevel = null;

        try {
            fis = new FileInputStream("levels/level" + level);
            ois = new ObjectInputStream(fis);
            readLevel = (Level) ois.readObject();

        } catch (Exception e) {
            UI.showMessage("Congrats! You finished the game!",false);
            //No more levels
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (readLevel != null) {
                this.dungeon = readLevel.dungeon;
                this.level = readLevel.level;
            }
        }
    }

    public void serialize() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("levels/level" + level);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Tile[] getDungeon() {
        return dungeon;
    }

    public void setDungeon(Tile[] dungeon) {
        this.dungeon = dungeon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setFocus(int x, int y) {
        int position = getPosition(x, y);
        Tile tile = dungeon[position];


        if (tile == Tile.WALL) {
            UI.showMessage("Although he was the greatest kitty ever, " + Game.hero.getName()
                    + " couldn't climb that wall.", false);
            return;
        }


        switch (tile) {
            case ENEMY: {
                try {
                    Game.fight();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }

            case WEAPON: {
                Game.acquireWeapon();
                break;
            }

            case TREASURE: {
                Game.acquireSpell();
                break;
            }

            case GATE: {
                Game.levelUp();
            }
        }

        currentTile = tile;
        dungeon[getPosition(focusX, focusY)] = Tile.PATH;
        dungeon[position] = Tile.HERO;

        focusX = x;
        focusY = y;
    }


    public void moveHero(String direction) {
        int nextFocusX = focusX, nextFocusY = focusY;
        switch (direction) {
            case Game.LEFT: {
                nextFocusX -= 1;
                break;
            }

            case Game.RIGHT: {
                nextFocusX += 1;
                break;
            }

            case Game.UP: {
                nextFocusY -= 1;
                break;
            }

            case Game.DOWN: {
                nextFocusY += 1;
                break;
            }
            default: {
                UI.showMessage("Didn't quite get that. Try up/down/left/right.", false);
                return;
            }
        }

        if (!isValidTile(nextFocusX, nextFocusY)) {
            UI.showMessage("That isn't a valid move.", false);
        } else {
            setFocus(nextFocusX, nextFocusY);
            Game.hero.regen();
        }
    }


    public boolean isValidTile(int nextFocusX, int nextFocusY) {
        return nextFocusX >= 0 && nextFocusX < 8 && nextFocusY >= 0 && nextFocusY < 8;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(dungeon[j + i * 8].toString());
                sb.append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    private int getPosition(int x, int y) {
        return x + y * 8;
    }

    public void castSpell(int range, int damage) {
        if (Game.hero.canCast()) {

        }
    }
}
