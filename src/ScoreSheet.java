import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ScoreSheet {
    private static final String[] UPPER_CATEGORIES = {"ones", "twos", "threes", "fours", "fives", "sixes"};
    private Map<String, Integer> scores;

    public ScoreSheet() {
        scores = new HashMap<>();
        String[] categories = {"ones", "twos", "threes", "fours", "fives", "sixes", "threeofakind", "fourofakind",
                "fullhouse", "smallstraight", "largestraight", "yahtzee", "chance"};
        for (String category : categories) {
            scores.put(category, null);
        }
    }

    public boolean isCategoryFilled(String category) {
        return scores.containsKey(category) && scores.get(category) != null;
    }

    public int calculateScore(String category, List<Integer> diceValues) {
        if (diceValues == null || diceValues.size() != 5) {
            throw new IllegalArgumentException("Invalid number of dice values");
        }

        int[] counts = new int[6];
        diceValues.forEach(dieValue -> {
            if (dieValue < 1 || dieValue > 6) {
                throw new IllegalArgumentException("Invalid die value: " + dieValue);
            }
            counts[dieValue - 1]++;
        });

        if (!scores.containsKey(category)) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }

        if (isCategoryFilled(category)) {
            throw new IllegalArgumentException("Category is already filled: " + category);
        }

        switch (category) {
            case "ones" -> {
                return counts[0];
            }
            case "twos" -> {
                return 2 * counts[1];
            }
            case "threes" -> {
                return 3 * counts[2];
            }
            case "fours" -> {
                return 4 * counts[3];
            }
            case "fives" -> {
                return 5 * counts[4];
            }
            case "sixes" -> {
                return 6 * counts[5];
            }
            case "threeofakind" -> {
                for (int i = 0; i < 6; i++) {
                    if (counts[i] >= 3) {
                        return diceValues.stream().mapToInt(Integer::intValue).sum();
                    }
                }
                return 0;
            }
            case "fourofakind" -> {
                for (int i = 0; i < 6; i++) {
                    if (counts[i] >= 4) {
                        return diceValues.stream().mapToInt(Integer::intValue).sum();
                    }
                }
                return 0;
            }
            case "smallstraight" -> {
                if (IntStream.range(0, 3).anyMatch(i -> counts[i] >= 1 && counts[i + 1] >= 1 && counts[i + 2] >= 1 && counts[i + 3] >= 1)) {
                    return 30;
                }
                return 0;
            }
            case "largestraight" -> {
                if (IntStream.range(0, 5).allMatch(i -> counts[i] == 1) || IntStream.range(1, 6).allMatch(i -> counts[i] == 1)) {
                    return 40;
                }
                return 0;
            }
            case "fullhouse" -> {
                boolean hasThreeOfAKind = IntStream.range(0, 6).anyMatch(i -> counts[i] == 3);
                boolean hasPair = IntStream.range(0, 6).anyMatch(i -> counts[i] == 2);
                if (hasThreeOfAKind && hasPair) {
                    return 25;
                }
                return 0;
            }
            case "yahtzee" -> {
                if (IntStream.range(0, 6).anyMatch(i -> counts[i] == 5)) {
                    return 50;
                }
                return 0;
            }
            case "chance" -> {
                return diceValues.stream().mapToInt(Integer::intValue).sum();
            }
            default -> throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    public int getUpperBonus() {
        int upperTotal = 0;
        for (int i = 0; i < 6; i++) {
            upperTotal += scores.get(UPPER_CATEGORIES[i]);
        }
        return upperTotal >= 63 ? 35 : 0;
    }


    public void updateScore(String category, int score) {
        String lowercaseCategory = category.toLowerCase();
        if (!scores.containsKey(lowercaseCategory)) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }
        if (isCategoryFilled(category)) {
            throw new IllegalArgumentException("Category is already filled: " + category);
        }
        scores.put(lowercaseCategory, score);
    }

    public int getTotalScore() {
        int totalScore = 0;
        for (Integer score : scores.values()) {
            if (score != null) {
                totalScore += score;
            }
        }
        return totalScore;
    }

    public List<String> getUnfilledCategories() {
        List<String> unfilledCategories = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() == null) {
                unfilledCategories.add(entry.getKey());
            }
        }
        return unfilledCategories;
    }

    public void displayFinalScores() {
        System.out.println("Final scores:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() != null ? entry.getValue() : "Not filled"));
        }
        System.out.println("Total score: " + getTotalScore());
    }

    public void printScoreSheet() {
        System.out.println("Current scores:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() != null ? entry.getValue() : "Not filled"));
        }
    }
}
