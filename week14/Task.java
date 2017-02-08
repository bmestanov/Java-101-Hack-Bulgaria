package week14;

/**
 * Created on 08/02/2017.
 */
public class Task {
    private final String description;
    private final int timeRequired;

    public Task(String description, int timeRequired) {
        this.description = description;
        this.timeRequired = timeRequired;
    }

    public String getDescription() {
        return description;
    }

    public int getTimeRequired() {
        return timeRequired;
    }
}
