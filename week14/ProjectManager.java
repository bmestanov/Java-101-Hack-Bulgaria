package week14;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 08/02/2017.
 */
public class ProjectManager {
    private List<Developer> devs;
    private List<Task> tasks;
    private double patience;

    public ProjectManager(double patience) {
        this.patience = patience;
    }


    public void setDevs(List<Developer> devs) {
        this.devs = devs;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private void assign() throws InterruptedException {
        //Assigning logic could be anything
        //This manager is a crappy one so he gives all tasks
        //to one poor developer

        if (devs.isEmpty()) {
            System.err.println("God damn it! Where are all the developers?!");
        } else {

            Developer developer = devs.get(0);
            developer.setTasks(tasks);

            long waitingTime = (long) (patience *
                    tasks.stream().mapToLong(developer::timeTookforTaskInMillis).sum());

            Thread work = new Thread(developer);
            long startWork = System.currentTimeMillis();
            work.start();

            System.out.println("Manager: Do the tasks for " + waitingTime + " millis!");
            work.join(waitingTime);

            long now = System.currentTimeMillis();
            if (now - startWork <= waitingTime) {
                System.out.println("Manager: It took you " + (now - startWork));
                System.out.println("Manager: Good job, " + developer.getName());
            } else {
                System.err.println("Manager: This is unacceptable! I waited you for like "
                        + (now - startWork)
                        + " millis! You're fired, "
                        + developer.getName());
                work.interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            throw new IllegalArgumentException("No patience provided");
        }

        double patience = Double.valueOf(args[0]);
        ProjectManager manager = new ProjectManager(patience);

        List<Task> tasks = Arrays.asList(new Task("Python task", 3),
                new Task("Java task", 1),
                new Task("Haskell task", 10));
        manager.setTasks(tasks);

        List<Developer> devs = Arrays.asList(new Developer("Mark Zuckerberg", Skill.YODA));
        manager.setDevs(devs);

        manager.assign();
    }
}
