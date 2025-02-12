package week1.ex9;

import java.io.*;
import java.util.*;

public class LogFileAnalyzer {

    // Method to analyze the log file and output results to another file
    public void analyzeLogFile(String inputFile, String outputFile, List<String> keywords) {
        Map<String, Integer> keywordCounts = new HashMap<>();

        for (String keyword : keywords) {
            keywordCounts.put(keyword, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the log file: " + e.getMessage());
        }

        // Ensure output file exists; create it if necessary
        File outputFileObj = new File(outputFile);
        if (!outputFileObj.exists()) {
            try {
                outputFileObj.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the output file: " + e.getMessage());
                return;
            }
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("Log File Analysis Results:\n");
            for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to the output file: " + e.getMessage());
        }

        System.out.println("Analysis complete. Results written to: " + outputFile);
    }

    public static void main(String[] args) {
        LogFileAnalyzer analyzer = new LogFileAnalyzer();

        List<String> keywords = Arrays.asList("ERROR", "WARNING", "INFO");

        String inputFile = "C://Users/medin/OneDrive/Desktop/JavaFSD/src/week1/ex9/input.log";
        String outputFile = "C://Users/medin/OneDrive/Desktop/JavaFSD/src/week1/ex9/output.txt";

        analyzer.analyzeLogFile(inputFile, outputFile, keywords);
    }
}
