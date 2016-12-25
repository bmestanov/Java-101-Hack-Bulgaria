package week01.listify;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Listify {
    public static String[] readToArray(String filename) {
        List<String> contents = new LinkedList<>();
        try {
            contents = read(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return contents.toArray(new String[contents.size()]);
    }

    private static List<String> read(String filename) throws java.io.IOException {
        List<String> content = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        List<String> result = new LinkedList<>();
        result.add("{");

        for (int i = 0; i < content.size(); i++) {
            String line = content.get(i);

            if (line.contains(";")) {
                line = line.substring(0, line.indexOf(";"));
            }

            if (line.contains("[")) {
                if (!line.contains("]")) {
                    throw new IOException("Invalid .INI file.");
                }
                result.add(line.replace("[", "\"").replace("]", "\"") + ": {");
            } else if (line.contains("=")) {

                if (line.contains("[") || line.contains("]")) {
                    throw new IOException("Invalid .INI file or improper layering at line " + (i + 1));
                }

                int index_of_equals = line.indexOf("=");
                int index_of_key = 0;
                int index_of_value = index_of_equals + 1;
                while (line.charAt(index_of_key) == ' ') {
                    index_of_key++;
                }

                while (line.charAt(index_of_value) == ' ') {
                    index_of_value++;
                }

                boolean hasNext = (i + 1) < content.size() && content.get(i + 1).contains("=");

                String key = line.substring(index_of_key, index_of_equals);
                String value = line.substring(index_of_value);

                if (key.isEmpty() || value.isEmpty()) {
                    throw new IOException("Empty key or value at line " + (i + 1));
                }

                result.add("\"" + key + "\": \"" + value + (hasNext ? "\"," : "\""));

                if (!hasNext) {
                    result.add((i + 2 >= content.size()) ? "}" : "},");
                }
            }

        }

        result.add("}");
        return result;
    }
}