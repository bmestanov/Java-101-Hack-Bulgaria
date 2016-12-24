package com.hack.week10;

import com.hack.week9.MySQLHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mestanov on 24.12.16.
 */
public class UI {
    private static Scanner scanner;
    public static MySQLHelper helper;
    public static final String DB_NAME = "mySqlTest";
    public static Map<Command, Runnable> commands;

    static {
        helper = new MySQLHelper(DB_NAME);
        scanner = new Scanner(System.in);
        commands = new HashMap<>();
        commands.put(new Command("exit", "terminates the application"), () -> System.out.println("Goodbye!"));
        commands.put(new Command("help", "show help for all commands"), new Runnable() {
            @Override
            public void run() {
                for (Command cmd : commands.keySet()){
                    System.out.println(cmd.getCommandName() + "> " +cmd.getDescription());
                }
            }
        });
        commands.put(new Command("show movies", "shows all movies in the database"), new Runnable() {
            @Override
            public void run() {
                Movie.showMovies(helper);
            }
        });
        commands.put(new Command("show projections", "show all projections of a movie"), new Runnable() {
            @Override
            public void run() {
                System.out.println("Enter Movie ID: ");
                int movieId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter a date of projection or leave blank:");
                String date;
                if (!(date = scanner.nextLine()).isEmpty()) {
                    try {
                        Projection.showProjections(movieId, helper, new SimpleDateFormat("dd-MM-yy").parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Projection.showProjections(movieId, helper);
                }
            }
        });
        commands.put(new Command("make reservation", "go to reservation menu"), new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }

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
