package week10;

import week09.Mappable;
import week09.MySQLHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mestanov on 20.12.16.
 */
public class Movie implements Mappable {
    public static final String TABLE_NAME = "movies";
    private int id;
    private String name;
    private float rating;

    public Movie(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    public Movie() {}

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("rating", Float.toString(rating));
        return map;
    }

    @Override
    public Movie fromList(List<String> map) {
        this.id = Integer.valueOf(map.get(0));
        this.name = map.get(1);
        this.rating = Float.valueOf(map.get(2));
        return this;
    }

    public static void showMovies(MySQLHelper helper) {
        String orderBy = "rating";
        List<List<String>> queryResult = helper.selectFrom(TABLE_NAME, null, null, orderBy);
        for(List<String> movie : queryResult){
            System.out.println(new Movie().fromList(movie));
        }
    }

    @Override
    public String toString() {
        return String.format("Movie ID: %d\nTitle: %s\nRating: %s\n",id,name,Float.toString(rating));
    }
}
