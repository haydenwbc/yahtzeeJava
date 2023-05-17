

public interface IPlayer {
    void takeTurn(Roll roll, ScoreSheet scoreSheet);

    String chooseCategory(ScoreSheet scoreSheet, Roll roll);

    void setScoreSheet(ScoreSheet scoreSheet);

    ScoreSheet getScoreSheet();
}
