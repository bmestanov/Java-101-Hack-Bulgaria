package week10;

import week09.MySQLHelper;

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
                System.out.println("Enter a date of projection or leave blank>");
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
                //Step 1
                System.out.println("Enter a name for the reservation> ");

                String name = scanner.nextLine();
                if(name.equals(Command.GIVE_UP)){
                    return;
                }

                System.out.println("Enter quantity of tickets> ");
                int tickets = scanner.nextInt();
                scanner.nextLine();

                //Step 2
                Movie.showMovies(helper);
                System.out.println("Enter the Movie ID> ");
                int movie = scanner.nextInt();
                scanner.nextLine();

                //Step 3
                System.out.println("Enter projection ID> ");
                List<Projection> projections = Projection.showProjections(movie, helper);

                int projection = scanner.nextInt();
                scanner.nextLine();



            }
        });
    }
}
