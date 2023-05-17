import java.util.Scanner;

public class Game {
    private final IPlayer player1;
    private final IPlayer player2;


    public Game(IPlayer player1, IPlayer player2, ScoreSheet scoreSheet1, ScoreSheet scoreSheet2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setScoreSheet(scoreSheet1);
        player2.setScoreSheet(scoreSheet2);
    }

    public void play() {
        System.out.println("Welcome to Yahtzee!");

        for (int i = 1; i <= 13; i++) {
            System.out.println("Round " + i);

            // Player 1's turn
            System.out.println("Player 1's turn");
            Roll roll1 = new Roll(5);
            player1.takeTurn(roll1, player1.getScoreSheet());

            // Player 2's turn
            System.out.println("Player 2's turn");
            Roll roll2 = new Roll(5);
            player2.takeTurn(roll2, player2.getScoreSheet());
        }

        // Display final scores
        System.out.println("Game over!");
        player1.getScoreSheet().displayFinalScores();
        player2.getScoreSheet().displayFinalScores();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter '1' to play against the computer, '2' to play against another player, or '3' to view rules:");
            int numPlayers = scanner.nextInt();

            IPlayer player1, player2;

            if (numPlayers == 1) {
                ScoreSheet scoreSheet1 = new ScoreSheet();
                ScoreSheet scoreSheet2 = new ScoreSheet();
                player1 = new HumanPlayer();
                player2 = new ComputerPlayer();
                Game game = new Game(player1, player2, scoreSheet1, scoreSheet2);
                game.play();
                break;
            } else if (numPlayers == 2) {
                ScoreSheet scoreSheet1 = new ScoreSheet();
                ScoreSheet scoreSheet2 = new ScoreSheet();
                player1 = new HumanPlayer();
                player2 = new HumanPlayer();
                Game game = new Game(player1, player2, scoreSheet1, scoreSheet2);
                game.play();
                break;
            } else if (numPlayers == 3) {
                RuleSet.showRuleSet();
            } else {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
            }
        }
    }
}

