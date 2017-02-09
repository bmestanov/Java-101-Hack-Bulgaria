package week15;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created on 09/02/2017.
 */
public class ThreadedSudokuValidator {
    public static final byte[][] SAMPLE_BOARD = {
            {4, 5, 2, 3, 9, 1, 8, 7, 6},
            {3, 1, 8, 6, 7, 5, 2, 9, 4},
            {6, 7, 9, 4, 2, 8, 3, 1, 5},
            {8, 3, 1, 5, 6, 4, 7, 2, 9},
            {2, 4, 5, 9, 8, 7, 1, 6, 3},
            {9, 6, 7, 2, 1, 3, 5, 4, 8},
            {7, 9, 6, 8, 5, 2, 4, 3, 1},
            {1, 8, 3, 7, 4, 9, 6, 5, 2},
            {5, 2, 4, 1, 3, 6, 9, 8, 7}};

    private final static Set<Byte> validator = new HashSet<>(SudokuBoard.SIZE);
    public static final int THREADS = 3;


    public static void main(String[] args) throws InterruptedException {
        SudokuBoard board = new SudokuBoard(SAMPLE_BOARD);
        ThreadedSudokuValidator validator = new ThreadedSudokuValidator();

        System.out.println(validator.isValid(board));
    }

    private boolean isValid(final SudokuBoard board) throws InterruptedException {
        List<Callable<Boolean>> predicates = Arrays.asList(
                () -> allValid(board.getRows()),
                () -> allValid(board.getCols()),
                () -> allValid(board.getZones()));

        ExecutorService service = Executors.newFixedThreadPool(THREADS);

        long t1 = System.currentTimeMillis();
        List<Future<Boolean>> results = service.invokeAll(predicates);

        boolean result = results.stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            return false;
        }).allMatch(bool -> bool);

        long t2 = System.currentTimeMillis();
        service.shutdown();

        System.out.printf("Took %d ms \n", t2 - t1);
        return result;
    }

    //Segment = a column, row or zone
    private synchronized boolean isValidSegment(byte[] segment) {
        validator.clear();
        for (byte b : segment)
            validator.add(b);
        return validator.size() == segment.length;
    }

    //A collection of segments
    private boolean allValid(byte[][] segments) {
        return Arrays.stream(segments)
                .map(this::isValidSegment)
                .allMatch(bool -> bool);
    }

    public static class SudokuBoard {
        static final int SIZE = 9;
        static final int SUBSIZE = 3;
        final byte[][] board;

        public SudokuBoard(byte[][] board) {
            this.board = board;
        }

        public byte[][] getRows() {
            return board;
        }

        public byte[][] getCols() {
            byte[][] cols = new byte[SIZE][SIZE];

            for (int i = 0; i < SIZE; i++) {
                byte[] column = new byte[SIZE];
                for (int j = 0; j < SIZE; j++) {
                    column[j] = board[j][i];
                }

                cols[i] = column;
            }

            return cols;
        }

        public byte[][] getZones() {
            byte[][] zones = new byte[SIZE][SIZE];

            int k = 0;
            for (int i = 0; i < SIZE; i += SUBSIZE) {
                for (int j = 0; j < SIZE; j += SUBSIZE) {
                    zones[k++] = getZone(i, j);
                }
            }

            return zones;
        }

        private byte[] getZone(int startX, int startY) {
            byte[] zone = new byte[SIZE];

            int k = 0;
            for (int i = startX; i < startX + SUBSIZE; i++) {
                for (int j = startY; j < startY + SUBSIZE; j++) {
                    zone[k++] = board[i][j];
                }
            }

            return zone;
        }
    }
}
