package week10;

import org.jetbrains.annotations.Nullable;
import week09.Mappable;
import week09.MySQLHelper;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mestanov on 20.12.16.
 */
public class Projection implements Mappable {
    public static final String TABLE_NAME = "projections";
    private int id, movieId;
    private String type;
    private Date date;
    private Time time;
    private Reservation hall;

    public Projection() {
        hall = new Reservation();
    }

    public static List<Projection> showProjections(int movieId, MySQLHelper helper) {
        String whereClause = String.format("movie_id = %d", movieId);
        String orderBy = "date";
        List<List<String>> queryResult = helper.selectFrom(TABLE_NAME, null, whereClause, orderBy);
        List<Projection> projections = new ArrayList<>();
        for (List<String> projection : queryResult) {
            Projection p = new Projection().fromList(projection);
            projections.add(p);
            System.out.println(p);
        }

        return projections;
    }

    public static List<Projection> showProjections(int movieId, MySQLHelper helper, Date date) {
        String query = String.format("movie_id = %d and date = \'%s\'", movieId, new SimpleDateFormat("YY-MM-dd").format(date));
        List<List<String>> queryResult = helper.selectFrom(TABLE_NAME, null, query, null);
        List<Projection> projections = new ArrayList<>();
        for (List<String> projection : queryResult) {
            Projection p = new Projection().fromList(projection);
            projections.add(p);
            System.out.println(p);
        }

        return projections;
    }

    @Nullable
    public static Projection pickProjection(List<Projection> projections, int projId) {
        for (Projection projection : projections) {
            if (projection.getId() == projId)
                return projection;
        }
        return null;
    }

    @Override
    public Map<String, String> toMap() {
        return null;
    }

    @Override
    public Projection fromList(List<String> attributes) {
        this.id = Integer.valueOf(attributes.get(0));
        this.movieId = Integer.valueOf(attributes.get(1));
        this.type = attributes.get(2);
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(attributes.get(3));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.time = new Time(new SimpleDateFormat("HH:mm:ss").parse(attributes.get(4)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.hall = Reservation.getHall(this);

        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nMovie ID: %d\nType: %s\nDate: %s\nTime: %s\nAvailable spots: %d\n%s\n", id, movieId, type,
                new SimpleDateFormat("dd-MM-yy").format(date),
                new SimpleDateFormat("HH:mm").format(time), hall.getAvailableSeats(), hall.toString());
    }

    public boolean isSeatTaken(int x, int y) {
        return hall.taken(x, y);
    }

    public void put(int x, int y, String userName) {
        hall.put(x - 1, y - 1, id, userName);
    }

    public void serialize(MySQLHelper helper) {
        hall.serialize(id, helper);
    }
}
