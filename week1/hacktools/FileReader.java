package hacktools;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String[] readToArray(String filename) {
        List<String> contents = readToList(filename);
        return contents.toArray(new String[contents.size()]);
    }

    public static List<String> readToList(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}