/**
 * Attempt class represents a user's attempt to guess the secret number.
 *
 * It stores the guess, and the resulting number of bulls and cows.
 */
public class Attempt {
    private String guess; // The user's guess
    private int bulls;    // Number of bulls in this attempt
    private int cows;     // Number of cows in this attempt

    public Attempt(String guess) {
        this.guess = guess;
    }

    /**
     * Returns the user's guess.
     *
     * @return The guess as a string.
     */
    public String getGuess() {
        return guess;
    }

    /**
     * Returns the number of bulls.
     *
     * @return Number of bulls.
     */
    public int getBulls() {
        return bulls;
    }

    /**
     * Sets the number of bulls.
     *
     * @param bulls Number of bulls.
     */
    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    /**
     * Returns the number of cows.
     *
     * @return Number of cows.
     */
    public int getCows() {
        return cows;
    }

    /**
     * Sets the number of cows.
     *
     * @param cows Number of cows.
     */
    public void setCows(int cows) {
        this.cows = cows;
    }
}
