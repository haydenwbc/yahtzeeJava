import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class Roll {
    private final List<Die> dice;

    public Roll(int numberOfDice) {
        dice = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            Die die = new Die();
            dice.add(die);
        }
    }

    public List<Integer> rollAll() {
        List<Integer> values = new ArrayList<>();

        for (Die die : dice) {
            die.roll();
            while (die.getValue() < 1 || die.getValue() > 6) {
                die.roll();
            }
            values.add(die.getValue());
        }

        return values;
    }

    public List<Integer> getDiceValues() {
        List<Integer> values = new ArrayList<>();
        for (Die die : dice) {
            values.add(die.getValue());
        }
        return values;
    }

}
