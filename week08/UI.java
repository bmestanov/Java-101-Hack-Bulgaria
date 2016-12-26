package week08;

/**
 * Created by Bilal on 8.12.2016 г..
 */
public class UI {
    public static void main(String[] args) {
        new Game();
    }

    public static void showMessage(final String text, boolean showStatus) {
        String status = "";
        if (showStatus) {
            status = "(" + Game.hero.getHealth() + "HP / " + Game.hero.getMana() + "M) \n";
        }

        final String newText = status + text;
        System.out.println(newText);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
