import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Organizer {
    public static void main(String[] args) throws IOException {
        List<String> notes = readNotes("inbox.txt");
        Map<String, List<String>> topics = loadTopics("topics.txt");
        Map<String, List<String>> sorted = sortNotes(notes, topics);

        writeOutput(sorted, "organized.txt");
        System.out.println("Organized " + notes.size() + " notes into organized.txt");
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
            if (line.isBlank())
                continue;
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

    static Map<String, List<String>> sortNotes(List<String> notes, Map<String, List<String>> topics) {
        Map<String, List<String>> sorted = new LinkedHashMap<>();
        for (String topic : topics.keySet()) {
            sorted.put(topic, new ArrayList<>());
        }
        sorted.put("Uncategorized", new ArrayList<>());

        for (String note : notes) {
            String topic = findTopic(note, topics);
            sorted.get(topic).add(note);
        }
        return sorted;
    }

    static void writeOutput(Map<String, List<String>> sorted, String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String topic : sorted.keySet()) {
            lines.add("## " + topic);
            for (String note : sorted.get(topic)) {
                lines.add("- " + note);
            }
            lines.add("");
        }
        Files.write(Paths.get(fileName), lines);
    }
}