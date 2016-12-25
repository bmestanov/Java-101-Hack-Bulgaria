import week10.Command;

import java.util.Scanner;

import static week10.UI.commands;

/**
 * Created by Bilal at 13:41 on 25/12/2016.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String command;
        do {
            System.out.println("Enter your command> ");
            command = scanner.nextLine();
            try {
                Command givenCommand = new Command(command,null);
                commands.get(givenCommand).run();
            } catch (NullPointerException e){
                System.err.println("The command you entered doesn't exist");
            }
        } while (!command.equals(Command.EXIT));
    }
}
