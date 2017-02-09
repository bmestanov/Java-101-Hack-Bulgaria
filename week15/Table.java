package week15;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 09/02/2017.
 */
public class Table {
    private List<Philosopher> philosophers;
    private Fork fork;

    public Table() {
        fork = new Fork();
        philosophers = Arrays.asList(
                new Philosopher("Aristotle"),
                new Philosopher("Plato"),
                new Philosopher("Carl Marx"),
                new Philosopher("Albert Camus"),
                new Philosopher("Friedrich Nietzche"),
                new Philosopher("Blaise Pascal")
        );

        philosophers.forEach(philosopher -> philosopher.setFork(fork));
    }

    public void dine() {
        philosophers.forEach(Thread::start);
    }

    public static void main(String[] args) {
        new Table().dine();
    }
}
