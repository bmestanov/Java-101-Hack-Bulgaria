package week10;

import week09.MySQLHelper;

import java.util.List;

/**
 * Created by mestanov on 20.12.16.
 */
public class CinemaHall {
    public static final String TABLE_NAME = "reservations";
    public static final int MAX_SEATS = 100;
    private boolean[][] seats;
    private int availableSeats;

    public CinemaHall() {
        seats = new boolean[10][10];
        availableSeats = MAX_SEATS;
    }

    public boolean put(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10)
            return false;

        if(seats[x][y]){
            System.err.println("The seat is taken.");
            return false;
        }
        seats[x][y] = true;
        availableSeats--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            sb.append((i+1) + " ");
            for (int j = 0; j < 10; j++) {
                sb.append(seats[i][j] ? "X" : ".");
                sb.append(" ");
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public static CinemaHall getHall(Projection projection) {
        MySQLHelper helper = new MySQLHelper(UI.DB_NAME);
        List<List<String>> queryResult = helper.selectFrom(TABLE_NAME, "row, col", "projection_id = " + projection.getId(), null);
        CinemaHall hall = new CinemaHall();
        for (List<String> takenSeats : queryResult) {
            int x = Integer.valueOf(takenSeats.get(0));
            int y = Integer.valueOf(takenSeats.get(1));

            hall.setTaken(x, y);
            hall.availableSeats--;
        }

        helper.closeConnection();
        return hall;
    }

    private void setTaken(int x, int y) {
        seats[x][y] = true;
    }

    private void serialize(){

    }
}
