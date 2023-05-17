import java.util.Random;
public class Die {
    private int value;
    private static final Random random = new Random();

    public Die() {
        this.roll();
    }

    public void roll() {
        value = random.nextInt(6) + 1;
    }

    public int getValue() {
        return value;
    }

}
