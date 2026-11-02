import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Organizer {
    public static void main(String[] args) throws IOException {
        List<String> notes = readNotes("inbox.txt");
        for (String note : notes) {
            System.out.println(note);
        }
    }

    static List<String> readNotes(String fileName) throws IOException {
        List<String> notes = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(fileName))) {
            if (!line.isBlank()) {
                notes.add(line.trim());
            }
        }
        return notes;
    }
}