import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Organizer {
    public static void main(String[] args) throws IOException {
        List<String> notes = readNotes("inbox.txt");
        for (String note : notes) {
            System.out.println(note);
        }

        Map<String, List<String>> topics = loadTopics("topics.txt");
        System.out.println(topics);

        System.out.println(findTopic("finish cs180 hw 10 by tuesday", topics));
        System.out.println(findTopic("tmrrw gym push day", topics));
        System.out.println(findTopic("listen to more beach weather songs", topics));
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

    static Map<String, List<String>> loadTopics(String fileName) throws IOException {
        Map<String, List<String>> topics = new LinkedHashMap<>();
        for (String line : Files.readAllLines(Paths.get(fileName))) {
            if (line.isBlank()) continue;
            String[] parts = line.split(":", 2);
            String topic = parts[0].trim();
            List<String> keywords = new ArrayList<>();
            for (String word : parts[1].split(",")) {
                keywords.add(word.trim().toLowerCase());
            }
            topics.put(topic, keywords);
        }
        return topics;
    }

    static String findTopic(String note, Map<String, List<String>> topics) {
    String lower = note.toLowerCase();
    for (String topic : topics.keySet()) {
        for (String keyword : topics.get(topic)) {
            if (lower.contains(keyword)) {
                return topic;
            }
        }
    }
    return "Uncategorized";
}
}