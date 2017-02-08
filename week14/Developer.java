package week14;

import java.util.List;

/**
 * Created on 08/02/2017.
 */
public class Developer implements Runnable {
    private final String name;
    private final Skill level;
    private List<Task> tasks;

    public Developer(String name, Skill level) {
        this.name = name;
        this.level = level;
    }

    public long timeTookforTaskInMillis(Task task) {
        double distractionCoefficient = Math.random() + 1;
        return (long) (task.getTimeRequired() * 1_000 *
                distractionCoefficient *
                level.getMultiplier());
    }

    @Override
    public void run() {
        long totalTime = tasks.stream()
                .mapToLong(this::timeTookforTaskInMillis)
                .sum();

        try {
            Thread.sleep(totalTime);
        } catch (InterruptedException e) {
            System.err.println(name + ": Hey, I was still working!");
        }
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }
}
