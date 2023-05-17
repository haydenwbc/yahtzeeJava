
import java.util.Scanner;

public class RuleSet {
    public static void showRuleSet() {
        clearConsole();
        System.out.println("Welcome to the Rules of Yahtzee!\n");

        System.out.println("1. The game consists of 13 rounds. In each round, a player gets three rolls of the dice.");
        System.out.println("2. After the first and second roll, the player can save any dice they want and re-roll the other dice.");
        System.out.println("3. The player must choose one of 13 categories to score after the third roll.");
        System.out.println("4. The score entered in the category box depends on how well the five dice match the scoring rule for the category.\n");

        System.out.println("The Yahtzee scorecard contains 13 scoring boxes divided into upper and lower sections.\n");
        System.out.println("Upper section:");
        System.out.println(" - In the upper section there are six boxes: Aces, Twos, Threes, Fours, Fives, and Sixes.");
        System.out.println(" - The score in each of these boxes is determined by adding the total number of dice matching that box.");
        System.out.println(" - If a player scores a total of 63 or more points in these six boxes, a bonus of 35 is added to the upper section score.\n");

        System.out.println("Lower section:");
        System.out.println(" - The lower section contains a number of poker-themed categories with specific point values:");
        System.out.println("   * Three Of A Kind: At least three dice the same");
        System.out.println("   * Four Of A Kind: At least four dice the same");
        System.out.println("   * Full House: Three of one number and two of another");
        System.out.println("   * Small Straight: Four sequential dice");
        System.out.println("   * Large Straight: Five sequential dice");
        System.out.println("   * Yahtzee: All five dice the same");
        System.out.println("   * Chance: Any combination\n");

        System.out.println("Yahtzee bonuses and Joker rules:");
        System.out.println(" - A Yahtzee occurs when all five dice are the same.");
        System.out.println(" - If the player throws a Yahtzee and has already filled the Yahtzee box with a score of 50, they score a Yahtzee bonus and get an extra 100 points.");
        System.out.println(" - If the player throws a Yahtzee and has filled the Yahtzee category with a score of 0, they do not get a Yahtzee bonus.");
        System.out.println(" - In either case, they then select a category, as usual. Scoring is the same as normal except that, if the Upper Section box corresponding to the Yahtzee has been used, the Full House, Small Straight, and Large Straight categories can be used to score 25, 30, or 40 (respectively) even though the dice do not meet the normal requirement for those categories. In this case, the Yahtzee is said to act as a \"Joker\".\n");

        System.out.println("The winner is the player with the highest total. The rules do not specify what happens in the event of a tie.");
        System.out.println("\nPress 'q' to return to the game.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            char input = scanner.next().charAt(0);
            if (input == 'q') {
                break;
            }
        }
        clearConsole();
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}

