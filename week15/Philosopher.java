package week15;

/**
 * Created on 09/02/2017.
 */
public class Philosopher extends Thread {
    Fork fork;

    public Philosopher(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                fork.acquire();
                System.out.println(getName() + ": Nom nom nom!");
                fork.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void setFork(Fork fork) {
        this.fork = fork;
    }
}
