package week10;

import week09.MySQLHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by mestanov on 24.12.16.
 */
public class UI {
    static final String DB_NAME = "mySqlTest";
    public static Map<Command, Runnable> commands;
    private static Scanner scanner;
    private static MySQLHelper helper;

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


                //Step 3
                List<Projection> projections;
                do {
                    Movie.showMovies(helper);
                    System.out.println("Enter the Movie ID> ");
                    int movieId = scanner.nextInt();
                    scanner.nextLine();
                    projections = Projection.showProjections(movieId, helper);
                } while (projections == null || projections.isEmpty());

                Projection selected;
                do {
                    System.out.println("Enter projection ID> ");
                    int projId = scanner.nextInt();
                    scanner.nextLine();
                    selected = Projection.pickProjection(projections, projId);
                } while (selected == null);


                System.out.println(selected);
                for (int i = 0; i < tickets; i++) {
                    System.out.println("Enter seat " + (i + 1) + "> ");
                    int x = scanner.nextInt(), y = scanner.nextInt();
                    scanner.nextLine();
                    if (selected.isSeatTaken(x, y)) {
                        System.err.println("That seat is taken.");
                        i--;
                    } else {
                        selected.put(x, y, name);
                    }
                }

                System.out.println("All set, type finalize to finish> ");
                String command = scanner.nextLine();
                if (command.equals(Command.FINISH)) {
                    selected.serialize(helper);
                }
            }
        });
        commands.put(new Command("cancel reservation", "cancel a person's reservation"), new Runnable() {
            @Override
            public void run() {
                System.out.println("Enter the username of the reservation to be cancelled> ");
                String name = scanner.nextLine();

                Reservation.cancelReservations(name, helper);
            }
        });
    }
}
