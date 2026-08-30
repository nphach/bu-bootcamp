import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        String inputFile = "scores.txt";
        String outputFile = "report.txt";

        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores(inputFile);

        // Step 2: calculate statistics
        double AVERAGE = calculateAverage(scores);
        int MIN_VALUE = 100;
        int MAX_VALUE = 0;

        for (int i = 0; i < scores.size(); i++) {
            int score = scores.get(i);

            if (score < MIN_VALUE) {
                MIN_VALUE = score;
            }

            if (score > MAX_VALUE) {
                MAX_VALUE = score;
            }
        }

        // Step 3: write and print report
        writeReport(scores, AVERAGE, MAX_VALUE, MIN_VALUE, outputFile);
    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                try {
                    int score = Integer.parseInt(line);
                    result.add(score);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading line: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return result;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        double sum = 0.0;

        for (int i = 0; i < scores.size(); i++) {
            sum += (double) scores.get(i);
        }

        if (scores.size() == 0) {
            return sum;
        } else {
            return sum / scores.size();
        }
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
            double avg, int high, int low,
            String outputFile) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int i = 0; i < scores.size(); i++) {
            int score = scores.get(i);

            if (score >= 90) {
                countA += 1;
            } else if ((score >= 80) && (score <= 89)) {
                countB += 1;
            } else if ((score >= 70) && (score <= 79)) {
                countC += 1;
            } else if ((score >= 60) && (score <= 69)) {
                countD += 1;
            } else {
                countF += 1;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(String.format("=== Grade Analyis Report ===%n"));
            writer.write(String.format("%-25s %d%n", "Total scores processed:", scores.size()));
            writer.write(String.format("%-25s %.2f%n", "Average score:", avg));
            writer.write(String.format("%-25s %d%n", "Highest score:", high));
            writer.write(String.format("%-25s %d%n", "Lowest score:", low));

            writer.write(String.format("Grade Distribution:%n"));
            writer.write(String.format("%-15s %d%n", "A (90-100):", countA));
            writer.write(String.format("%-15s %d%n", "B (80-89):", countB));
            writer.write(String.format("%-15s %d%n", "C (70-79):", countC));
            writer.write(String.format("%-15s %d%n", "D (60-69):", countD));
            writer.write(String.format("%-15s %d%n", "F (below 60):", countF));

            // print in console
            System.out.print(String.format("=== Grade Analyis Report ===%n"));
            System.out.print(String.format("%-25s %d%n", "Total scores processed:", scores.size()));
            System.out.print(String.format("%-25s %.2f%n", "Average score:", avg));
            System.out.print(String.format("%-25s %d%n", "Highest score:", high));
            System.out.print(String.format("%-25s %d%n", "Lowest score:", low));

            System.out.print(String.format("Grade Distribution:%n"));
            System.out.print(String.format("%-15s %d%n", "A (90-100):", countA));
            System.out.print(String.format("%-15s %d%n", "B (80-89):", countB));
            System.out.print(String.format("%-15s %d%n", "C (70-79):", countC));
            System.out.print(String.format("%-15s %d%n", "D (60-69):", countD));
            System.out.print(String.format("%-15s %d%n", "F (below 60):", countF));
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }
}