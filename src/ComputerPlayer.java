import java.util.List;
import java.util.Random;

public class ComputerPlayer implements IPlayer {
    private final Random random;
    private ScoreSheet scoreSheet;

    public ComputerPlayer() {
        this.random = new Random();
    }

    @Override
    public ScoreSheet getScoreSheet() {
        return this.scoreSheet;
    }
    @Override
    public void setScoreSheet(ScoreSheet scoreSheet) {
        this.scoreSheet = scoreSheet;
    }
    @Override
    public void takeTurn(Roll roll, ScoreSheet scoreSheet) {
        System.out.println("Computer player is taking a turn...");

        List<String> categories = scoreSheet.getUnfilledCategories();
        String category = chooseCategory(scoreSheet, roll);

        int score = scoreSheet.calculateScore(category, roll.getDiceValues());

        scoreSheet.updateScore(category, score);

        System.out.println("Computer player scored " + score + " points in the " + category + " category.");
    }

    @Override
    public String chooseCategory(ScoreSheet scoreSheet, Roll roll) {
        List<String> categories = scoreSheet.getUnfilledCategories();

        int maxScore = 0;
        String chosenCategory = categories.get(0);

        for (String category : categories) {
            int score = scoreSheet.calculateScore(category, roll.getDiceValues());

            if (score > maxScore) {
                maxScore = score;
                chosenCategory = category;
            }
        }

        return chosenCategory;
    }

}

