import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements IPlayer {

    private ScoreSheet scoreSheet;
    private static final String[] CATEGORIES = {
            "ones", "twos", "threes", "fours", "fives", "sixes",
            "threeofakind", "fourofakind", "fullhouse", "smallstraight", "largestraight", "yahtzee", "chance"
    };

    public HumanPlayer() {
    }

    @Override
    public void setScoreSheet(ScoreSheet scoreSheet) {
        this.scoreSheet = scoreSheet;
    }

    @Override
    public ScoreSheet getScoreSheet() {
        return this.scoreSheet;
    }

    @Override
    public void takeTurn(Roll roll, ScoreSheet scoreSheet) {
        Turn turn = new Turn();

        System.out.println("Starting turn...");

        turn.startTurn(scoreSheet);

        List<Integer> keepers = turn.getKeepers();

        System.out.println("Keepers: " + keepers);

        String category = chooseCategory(scoreSheet, keepers);

        int score = scoreSheet.calculateScore(category, keepers);

        scoreSheet.updateScore(category, score);

        System.out.println("Scored " + score + " points in the " + category + " category.");
    }

    private List<Integer> printScoringOptions(ScoreSheet scoreSheet, List<Integer> dice, boolean showAllCategories) {
        System.out.println("Scoring options:");
        List<Integer> availableOptions = new ArrayList<>();
        int optionNumber = 1;
        for (int i = 0; i < CATEGORIES.length; i++) {
            String category = CATEGORIES[i];
            if (!scoreSheet.isCategoryFilled(category)) {
                int score = scoreSheet.calculateScore(category, dice);
                if (score > 0 || showAllCategories) {
                    System.out.println(optionNumber + ") " + category + ": " + (showAllCategories ? 0 : score));
                    availableOptions.add(i);
                    optionNumber++;
                }
            }
        }
        return availableOptions;
    }

    @Override
    public String chooseCategory(ScoreSheet scoreSheet, Roll roll) {
        return chooseCategory(scoreSheet, roll.getDiceValues());
    }

    public String chooseCategory(ScoreSheet scoreSheet, List<Integer> diceValues) {
        Scanner scanner = new Scanner(System.in);
        String chosenCategory;

        while (true) {
            List<Integer> availableOptions = printScoringOptions(scoreSheet, diceValues, false);
            if (availableOptions.isEmpty()) {
                System.out.println("No valid scores in keepers, please choose a category to fill a 0 in:");
                availableOptions = printScoringOptions(scoreSheet, diceValues, true);
            }
            System.out.println("Enter the number of the category you'd like to score in:");
            String input = scanner.nextLine();

            try {
                int chosenOptionNumber = Integer.parseInt(input);
                if (chosenOptionNumber >= 1 && chosenOptionNumber <= availableOptions.size()) {
                    int chosenCategoryIndex = availableOptions.get(chosenOptionNumber - 1);
                    chosenCategory = CATEGORIES[chosenCategoryIndex];
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid category number between 1 and " + availableOptions.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid category number between 1 and " + availableOptions.size() + ".");
            }
        }

        return chosenCategory;
    }

}
