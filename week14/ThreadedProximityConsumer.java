package week14;

import week14.PointGenerator.Point;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 08/02/2017.
 */
public class ThreadedProximityConsumer extends PointConsumer {
    private final int threads;

    public ThreadedProximityConsumer(List<Point> points, int threadCount) {
        super(points);
        this.threads = threadCount;
    }


    @Override
    public void consume() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(threads);
        final int segmentSize = points.size() / threads;

        long t1 = System.currentTimeMillis();

        for (int i = 0; i < points.size(); i += segmentSize) {
            int start = i;
            service.execute(() -> consumeInBounds(start, start + segmentSize));
        }

        service.shutdown();
        service.awaitTermination(60, TimeUnit.SECONDS);
        long t2 = System.currentTimeMillis();

        System.out.printf("Finished in %d ms", t2 - t1);
    }

    private long consumeInBounds(int start, int end) {
        for (int i = start; i < end && i < points.size(); i++) {
            Point point = points.get(i);

            System.out.printf("%s > %s", point,
                    calculateClosest(point));
            System.out.println();
        }

        return System.currentTimeMillis();
    }

    private Point calculateClosest(final Point target) {
        return points.stream().min((o1, o2) -> {
            if (o1 == target || o2 == target) {
                return Integer.MAX_VALUE;
            }
            return Point.distance(target, o1) - Point.distance(target, o2);
        }).get();
    }

    public static void main(String[] args) throws InterruptedException {
        PointGenerator pointGenerator = new PointGenerator();
        ThreadedProximityConsumer consumer = new ThreadedProximityConsumer(
                pointGenerator.generate(10_000), 32);

        consumer.consume();

        // Consumption time (5 tests each)
        // 1  thread  -> 8.3s
        // 4  threads -> 4.3s
        // 8  threads -> 4.7s
        // 16 threads -> 3.6s
        // 32 threads -> 3.8s
    }
}
