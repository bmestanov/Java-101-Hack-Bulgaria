package week15;

import java.util.concurrent.Semaphore;

/**
 * Created on 09/02/2017.
 */
public class Fork extends Semaphore {
    public Fork() {
        super(1, true);
    }
}
