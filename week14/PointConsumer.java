package week14;

import week14.PointGenerator.Point;

import java.util.List;

/**
 * Created on 08/02/2017.
 */
public abstract class PointConsumer {
    protected final List<Point> points;

    public PointConsumer(List<Point> points) {
        this.points = points;
    }

    public abstract void consume() throws InterruptedException;
}
