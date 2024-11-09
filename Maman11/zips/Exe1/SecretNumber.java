import java.util.*;

/**
 * SecretNumber class generates and stores the secret number for the game.
 *
 * The secret number is a four-digit number with all digits being distinct.
 */
public class SecretNumber {
    private String number; // The secret number as a string

    public SecretNumber() {
        generateSecretNumber();
    }

    /**
     * Generates a random four-digit number with distinct digits.
     */
    private void generateSecretNumber() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits); // Randomize the order of digits

        StringBuilder sb = new StringBuilder();
        for (int i = 0; sb.length() < 4; i++) {
            sb.append(digits.get(i));
        }
        number = sb.toString();
    }

    /**
     * Returns the secret number.
     *
     * @return The secret number as a string.
     */
    public String getNumber() {
        return number;
    }
}
