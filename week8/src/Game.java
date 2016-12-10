import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Bilal on 9.12.2016 г..
 */
public class Game {
    static final String LEFT = "left", RIGHT = "right", UP = "up", DOWN = "down";
    private static Level level;
    static Hero hero;
    private static Scanner sc;

    public Game() {
        prelude();
        do {
            UI.showMessage(level.toString(), false);
            UI.showMessage("Type the direction you want to move.", false);
            String command = sc.next();
            processCommand(command);
        } while (hero.isAlive());
    }

    private void prelude() {
        level = new Level(2);
        UI.showMessage("Welcome to the game!", false);
        UI.showMessage("What is your name?", false);
        String heroName;
        sc = new Scanner(System.in);
        do {
            heroName = sc.next();
        } while (heroName.isEmpty());
        hero = new Hero(heroName, "Almighty", 100, 100, 5);

        UI.showMessage(hero.knownAs() + " founds himself in a dungeon filled with angry dogs " +
                "and only one way out! \nOn the way there are spells and weapons to help him reach the end!", true);

        level.setFocus(0, 0);
    }

    private void processCommand(String command) {
        level.moveHero(command);
    }

    static void fight() throws InterruptedException {
        Enemy enemy = Enemy.generate();
        UI.showMessage("Oh no! " + hero.getName() + " must now fight the evil doge!", false);
        Thread.sleep(1000);
        UI.showMessage("The doge has " + enemy.getHealth() + "HP and " + enemy.getDamage() + " damage points.", false);
        Thread.sleep(1000);
        int turn = 0;
        while (hero.isAlive() && enemy.isAlive()) {
            turn++;
            if (turn % 2 != 0) {
                UI.showMessage(hero.getName() + "\'s turn to attack!", true);
                String attackMethod;
                if (!hero.hasSpell() && !hero.hasWeapon()) {
                    UI.showMessage(hero.getName() + " doesn't have any weapons or spells!", true);
                    continue;
                }
                if (hero.hasSpell() && hero.hasWeapon()) {
                    do {
                        UI.showMessage(hero.getName() + " has both a spell and weapon. Choose with which one to attack!", false);
                        attackMethod = sc.next();
                    } while (!(attackMethod.equals("w") || attackMethod.equals("s")));
                } else {
                    if (hero.hasSpell()) {
                        attackMethod = "s";
                    } else {
                        attackMethod = "w";
                    }
                }

                boolean attackType = attackMethod.equals("w"); //True = weapon, false = spell
                int damage = hero.attack(attackType);
                enemy.takeDamage(damage);
                UI.showMessage("Our hero attacked the doge with " + ((attackType) ? hero.getWeapon().getName() : hero.getSpell().getName()) + ".", true);
                UI.showMessage(hero.knownAs() + " took " + damage + "HP!", true);
            } else {
                int damage = enemy.attack(true);
                hero.takeDamage(damage);
                UI.showMessage("His enemy attacked and " + " took " + damage + "HP!", true);
            }
        }

        if (hero.isAlive()) {
            UI.showMessage("And finally " + hero.knownAs() + " defeats his enemy and now can move on.", true);
        } else {
            UI.showMessage("He had a lot of good days, but today isn't one of them. It was game over for " + hero.getName(), true);
        }
    }

    static void acquireWeapon() {
        hero.takeWeapon(Weapon.generate());
        UI.showMessage(String.format("Great! %s acquired a new weapon - %s (%d damage)",
                hero.getName(), hero.getWeapon().getName(), hero.getWeapon().getDamage()), false);
    }

    static void acquireSpell() {
        hero.learnSpell(Spell.generate());
        UI.showMessage(String.format("Super! %s learned a new spell - %s (%d damage, %d points, %d range)",
                hero.getName(), hero.getSpell().getName(), hero.getSpell().getDamage(), hero.getSpell().getPoints(), hero.getSpell().getRange()), false);
    }

    static void levelUp() {
        Level next = new Level(level.getLevel() + 1);
        level.setDungeon(next.getDungeon());
        level.setLevel(next.getLevel());
        UI.showMessage("Great! You reached the next level! Level" + level.getLevel(),false);
    }


}
