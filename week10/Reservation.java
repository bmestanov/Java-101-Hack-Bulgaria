package week10;

import week09.Mappable;
import week09.MySQLHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mestanov on 20.12.16.
 */
public class Reservation {
    private static final String TABLE_NAME = "reservations";
    private static final int MAX_SEATS = 100;
    private List<Seat> reserved;
    private boolean[][] allSeats;
    private int availableSeats;

    public Reservation() {
        allSeats = new boolean[10][10];
        availableSeats = MAX_SEATS;
        reserved = new ArrayList<>();
    }

    public static Reservation getHall(Projection projection) {
        MySQLHelper helper = new MySQLHelper(UI.DB_NAME);
        List<List<String>> queryResult = helper.selectFrom(TABLE_NAME, "row, col", "projection_id = " + projection.getId(), null);
        Reservation hall = new Reservation();
        for (List<String> takenSeats : queryResult) {
            int x = Integer.valueOf(takenSeats.get(0));
            int y = Integer.valueOf(takenSeats.get(1));

            hall.setTaken(x, y);
            hall.availableSeats--;
        }

        helper.closeConnection();
        return hall;
    }

    public static void cancelReservations(String userName, MySQLHelper helper) {
        helper.deleteFrom(TABLE_NAME, String.format("username = '%s'", userName));
    }

    public boolean put(int x, int y, int projId, String userName) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10)
            return false;

        Seat toReserve = new Seat(x, y, projId, userName);
        if (allSeats[x][y] || reserved.contains(toReserve)) {
            System.err.println("The seat is taken.");
            return false;
        }
        allSeats[x][y] = true;
        reserved.add(toReserve);
        availableSeats--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  01 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            sb.append((num < 10 ? "0" : ""));
            sb.append(num);
            sb.append(" ");
            for (int j = 0; j < 10; j++) {
                sb.append(allSeats[i][j] ? "X" : ".");
                sb.append(" ");
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    private void setTaken(int x, int y) {
        allSeats[x][y] = true;
    }

    public void serialize(int projId, MySQLHelper helper) {
        for (Seat s : reserved) {
            helper.insertInto(TABLE_NAME, s);
        }
    }

    public boolean taken(int x, int y) {
        return allSeats[x - 1][y - 1];
    }

    private class Seat implements Mappable {
        int x, y;
        int projId;
        String userName;

        Seat(int x, int y, int projId, String userName) {
            this.x = x;
            this.y = y;
            this.projId = projId;
            this.userName = userName;
        }

        @Override
        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            map.put("username", userName);
            map.put("projection_id", Integer.toString(projId));
            map.put("row", Integer.toString(x));
            map.put("col", Integer.toString(y));

            return map;
        }

        @Override
        public Mappable fromList(List<String> attributes) {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Seat seat = (Seat) o;

            return x == seat.x && y == seat.y && projId == seat.projId;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + projId;
            return result;
        }

    }
}
