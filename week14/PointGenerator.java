package week14;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 08/02/2017.
 */
public class PointGenerator {
    public List<Point> generate(int sampleSize) {
        List<Point> points = new ArrayList<>(sampleSize);

        for (int i = 0; i < sampleSize; i++) {
            points.add(new Point((int) (Math.random() * 10_000),
                    (int) (Math.random() * 10_000)));
        }

        return points;
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static int distance(Point o1, Point o2) {
            return (int) Math.sqrt(Math.pow(o1.x - o2.x, 2) +
                    Math.pow(o1.y - o2.y, 2));
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            return this.x == point.x &&
                    this.y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
