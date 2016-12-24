package com.hack.week9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mestanov on 15.12.16.
 */

public class Client implements Mappable {
    public int id, age;
    public String name;

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Client() {
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("age", Integer.toString(age));
        return map;
    }

    @Override
    public Client fromList(List<String> list) {
        if (list.size() != getClass().getFields().length) {
            throw new IllegalArgumentException("Cannot convert to the Object.");
        }

        this.id = Integer.valueOf(list.get(0));
        this.name = list.get(1);
        this.age = Integer.valueOf(list.get(2));

        return this;
    }
}
