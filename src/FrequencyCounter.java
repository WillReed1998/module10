import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrequencyCounter {
    public static void main(String[] args) {
        String filePath = "src/words.txt";
        Map<String, Integer> wordFrequency = getWordFrequency(filePath);
        printWordFrequency(wordFrequency);
    }

    private static Map<String, Integer> getWordFrequency(String filePath) {
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordFrequency;
    }

    private static void printWordFrequency(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordFrequency.entrySet());

        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}