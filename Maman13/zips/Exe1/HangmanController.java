import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * The HangmanController class handles the interaction between the GUI components
 * defined in the FXML file and the game logic.
 */
public class HangmanController {

    @FXML
    private Label wordLabel; // Label to display the word with guessed letters

    @FXML
    private Label guessedLettersLabel; // Label to display guessed letters

    @FXML
    private ComboBox<Character> letterComboBox; // ComboBox for user to select a letter

    @FXML
    private Canvas hangmanCanvas; // Canvas for drawing the hangman

    @FXML
    private VBox root; // Root layout (can be used for dynamic resizing)

    private HangmanGame game; // The game logic instance
    private HangmanCanvas hangmanDrawer; // Utility to draw hangman

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        game = new HangmanGame(); // Initialize the game logic
        hangmanDrawer = new HangmanCanvas(hangmanCanvas.getWidth(), hangmanCanvas.getHeight());
        updateGUI(); // Update UI components
    }

    /**
     * Handles the action when the guess button is pressed.
     * Processes the selected letter and updates the UI.
     */
    @FXML
    private void handleGuess() {
        Character selectedLetter = letterComboBox.getValue();
        if (selectedLetter != null) {
            game.guessLetter(selectedLetter); // Process the guess
            updateGUI();                        // Update UI components
            checkGameOver();                   // Check if the game has ended
        }
    }

    /**
     * Handles the action when the new game button is pressed.
     * Starts a new game and updates the UI.
     */
    @FXML
    private void handleNewGame() {
        game.startNewGame();
        updateGUI();
    }

    /**
     * Updates the UI components to reflect the current game state.
     */
    private void updateGUI() {
        wordLabel.setText(game.getDisplayedWord());
        guessedLettersLabel.setText("Guessed Letters: " + game.getGuessedLetters());
        letterComboBox.getItems().clear();
        letterComboBox.getItems().addAll(game.getRemainingLetters());
        letterComboBox.setValue(null); // Reset selection

        // Draw the hangman
        hangmanDrawer.drawHangman(game.getIncorrectGuesses());

        // Clear the existing canvas and redraw
        GraphicsContext gc = hangmanCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, hangmanCanvas.getWidth(), hangmanCanvas.getHeight());
        gc.drawImage(hangmanDrawer.snapshot(null, null), 0, 0);
    }

    /**
     * Checks if the game is over and displays an alert if it is.
     * Starts a new game upon acknowledgment.
     */
    private void checkGameOver() {
        if (game.isGameOver()) {
            String message = game.isWordGuessed() ? "Congratulations! You guessed the word!" : "Game Over! The word was: " + game.getWordToGuess();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.showAndWait(); // Wait for the user to acknowledge
            handleNewGame();     // Start a new game
        }
    }
}
