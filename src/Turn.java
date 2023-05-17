import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Turn {
    private static final int MAX_ROLLS = 3;
    private int rollCount;
    private Roll roll;
    private final List<Integer> keepers;

    public Turn() {
        rollCount = 0;
        roll = new Roll(5);
        keepers = new ArrayList<>();
    }

    public void startTurn(ScoreSheet scoreSheet) {
        Scanner scanner = new Scanner(System.in);

        while (rollCount < MAX_ROLLS) {
            System.out.println("Current keepers: " + keepers);
            List<Integer> rolledDice = roll.rollAll();
            rollCount++;
            System.out.println("Roll #" + rollCount + ": " + rolledDice);

            if (rollCount < MAX_ROLLS) {
                handleKeepers(scanner, rolledDice);
                boolean continueToNextRoll = betweenRollsOptions(scanner, scoreSheet);
                if (!continueToNextRoll) {
                    break;
                }
            }
        }
    }

    private void handleKeepers(Scanner scanner, List<Integer> rolledDice) {
        boolean validInput = false;

        do {
            System.out.println("Enter the dice to keep (separated by spaces) or press enter to continue:");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }

            String[] tokens = input.split(" ");
            List<Integer> newKeepers = new ArrayList<>();

            for (String token : tokens) {
                int dieValue = Integer.parseInt(token);
                if (rolledDice.contains(dieValue)) {
                    newKeepers.add(dieValue);
                    rolledDice.remove((Integer) dieValue);
                } else {
                    System.out.println("Invalid input! Please try again.");
                    break;
                }
            }

            if (newKeepers.size() == tokens.length) {
                keepers.addAll(newKeepers);
                roll = new Roll(5 - keepers.size());
                validInput = true;
            }
        } while (!validInput);
    }

    private void handleDiceRemoval(Scanner scanner) {
        System.out.println("Keepers: " + keepers);
        System.out.println("Do you want to remove any dice from the keepers? (y/n):");
        String removeDice = scanner.nextLine().trim().toLowerCase();
        if (removeDice.equals("y")) {
            System.out.println("Enter the dice to remove (separated by spaces):");
            String[] diceToRemove = scanner.nextLine().trim().split(" ");
            for (String die : diceToRemove) {
                int dieValue = Integer.parseInt(die);
                keepers.remove((Integer) dieValue); // Cast to Integer to remove by value, not by index
            }
            roll = new Roll(5 - keepers.size());
        }
    }

    public List<Integer> getKeepers() {
        List<Integer> finalDice = new ArrayList<>(keepers);
        finalDice.addAll(roll.getDiceValues());
        return finalDice;
    }

    private boolean betweenRollsOptions(Scanner scanner, ScoreSheet scoreSheet) {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("A) Edit keepers");
            System.out.println("B) View score sheet");
            System.out.println("C) View opponent score sheet");
            System.out.println("D) View rules");
            if (keepers.size() == 5) {
                System.out.println("E) End turn");
            }
            System.out.println("Press enter to move to next roll");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "a":
                    handleDiceRemoval(scanner);
                    break;
                case "b":
                    scoreSheet.printScoreSheet();
                    break;
                case "c":
                    RuleSet.showRuleSet();
                    break;
                case "d":
                    //print opponent scoresheet here
                case "e":
                    if (keepers.size() == 5) {
                        return false;
                    } else {
                        System.out.println("Less than 5 dice in keepers, cannot end turn.");
                    }
                    break;
                case "":
                    return true;
                default:
                    System.out.println("Invalid input! Please try again.");
            }
        }
    }
}
