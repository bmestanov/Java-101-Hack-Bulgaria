package week11;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import week09.MySQLHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bilal on 12/01/2017.
 */
public class ObjectModel<T> {
    private MySQLHelper helper;
    private Class callerClass;

    public ObjectModel(String dbName, Class callerClass) {
        this.helper = new MySQLHelper(dbName);
        this.callerClass = callerClass;
    }

    @NotNull
    private String capitalize(String s) {
        Character c = s.charAt(0);
        return s.replace(c, Character.toUpperCase(c));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private T toObject(List<String> dbRow) {
        try {
            Field[] fields = callerClass.getFields();
            Object instance = callerClass.newInstance();

            for (int i = 0; i < dbRow.size(); i++) {
                String entry = dbRow.get(i);
                Field field = fields[i];
                Method setter = callerClass.getMethod("set" + capitalize(field.getName()),
                        field.getType());

                if (field.getType() == Integer.class) {
                    setter.invoke(field, Integer.valueOf(entry));
                } else {
                    setter.invoke(field, entry);
                }
            }

            return (T) instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getAll() {
        List<T> entries = new ArrayList<>();
        List<List<String>> queryResult =
                helper.selectFrom(callerClass.getSimpleName() + "s", null, null, null);

        entries.addAll(queryResult.stream().map(this::toObject)
                .collect(Collectors.toList()));

        return entries;
    }

    public T getById(int id) {
        String tableName = callerClass.getSimpleName() + "s";
        return toObject(helper.selectFrom(tableName, null, "id = " + id, null)
                .get(0));
    }
}
