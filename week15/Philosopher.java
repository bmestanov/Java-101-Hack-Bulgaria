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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getName() + ": Nom nom nom!");
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fork.release();
        }
    }

    public void setFork(Fork fork) {
        this.fork = fork;
    }
}
