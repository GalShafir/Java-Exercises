import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * The HangmanGame class encapsulates the game logic for Hangman.
 * It handles word selection, tracking guessed letters, and determining game status.
 */
public class HangmanGame {
    private String wordToGuess;             // The word the user needs to guess
    private Set<Character> guessedLetters;  // Letters that have been guessed
    private int incorrectGuesses;           // Number of incorrect guesses made
    private static final int MAX_INCORRECT_GUESSES = 8; // 8 incorrect guesses allowed
    private Set<Character> allLetters;      // All possible letters (A-Z)

    /**
     * Constructor initializes the game and starts a new game.
     */
    public HangmanGame() {
        // Initialize the set of all letters (A-Z)
        allLetters = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            allLetters.add(c);
        }
        startNewGame();
    }

    /**
     * Starts a new game by selecting a new word and resetting game variables.
     */
    public void startNewGame() {
        wordToGuess = selectRandomWord().toUpperCase(); // Select and store a new word
        guessedLetters = new HashSet<>();               // Reset guessed letters
        incorrectGuesses = 0;                           // Reset incorrect guesses
    }

    /**
     * Selects a random word from the word bank file.
     * @return A randomly selected word as a String.
     */
    private String selectRandomWord() {
        List<String> words = new ArrayList<>();
        try {
            // Read all lines from the word bank file
            words = Files.readAllLines(Paths.get("wordbank.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random rand = new Random();
        // Return a random word from the list
        return words.get(rand.nextInt(words.size()));
    }

    /**
     * Processes the user's guess for a letter.
     * @param letter The letter guessed by the user.
     * @return True if the letter is in the word, False otherwise.
     */
    public boolean guessLetter(char letter) {
        letter = Character.toUpperCase(letter); // Ensure uppercase for consistency
        if (guessedLetters.contains(letter)) {
            return false; // Letter has already been guessed
        }
        guessedLetters.add(letter);             // Add the letter to guessed letters
        if (!wordToGuess.contains(String.valueOf(letter))) {
            incorrectGuesses++;                 // Increment incorrect guesses if letter not in word
            return false;
        }
        return true;
    }

    /**
     * Generates the word display with guessed letters revealed and others as underscores.
     * @return The display string of the word.
     */
    public String getDisplayedWord() {
        StringBuilder displayed = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(c)) {
                displayed.append(c); // Reveal guessed letters
            } else {
                displayed.append('_'); // Hide unguessed letters
            }
        }
        return displayed.toString();
    }

    /**
     * Checks if the game is over due to too many incorrect guesses or the word being guessed.
     * @return True if the game is over, False otherwise.
     */
    public boolean isGameOver() {
        return incorrectGuesses >= MAX_INCORRECT_GUESSES || isWordGuessed();
    }

    /**
     * Checks if the entire word has been successfully guessed.
     * @return True if the word is fully guessed, False otherwise.
     */
    public boolean isWordGuessed() {
        for (char c : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false; // Return false if any letter is not yet guessed
            }
        }
        return true; // All letters have been guessed
    }

    /**
     * Gets the number of incorrect guesses made.
     * @return The number of incorrect guesses.
     */
    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    /**
     * Gets the set of letters that have been guessed.
     * @return A Set of guessed characters.
     */
    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Calculates and returns the set of letters that have not yet been guessed.
     * @return A Set of remaining characters to guess.
     */
    public Set<Character> getRemainingLetters() {
        Set<Character> remaining = new HashSet<>(allLetters);
        remaining.removeAll(guessedLetters); // Remove guessed letters from all letters
        return remaining;
    }

    /**
     * Gets the word that needs to be guessed.
     * @return The word to guess as a String.
     */
    public String getWordToGuess() {
        return wordToGuess;
    }
}
