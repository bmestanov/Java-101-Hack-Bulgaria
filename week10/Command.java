package week10;

/**
 * Created by mestanov on 24.12.16.
 */
public class Command {
    public static final String EXIT = "exit";
    public static final String GIVE_UP = "give up";
    private String command;
    private String description;

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Command command1 = (Command) o;

        return command != null ? command.equals(command1.command) : command1.command == null;
    }

    @Override
    public int hashCode() {
        return command != null ? command.hashCode() : 0;
    }

    public String getDescription() {
        return description;
    }

    public String getCommandName() {
        return command;
    }
}
