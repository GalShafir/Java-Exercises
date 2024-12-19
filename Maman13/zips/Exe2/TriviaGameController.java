import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Controller class for handling GUI interactions and game logic.
 */
public class TriviaGameController {

    @FXML
    private Label scoreLabel;

    @FXML
    private Text questionText;

    @FXML
    private VBox answersBox;

    @FXML
    private Button submitButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button endGameButton;

    private ToggleGroup answersGroup;

    private QuestionBank questionBank;
    private Question currentQuestion;
    private int score;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. Sets up the initial game state.
     */
    @FXML
    public void initialize() {
        questionBank = new QuestionBank("trivia.txt");
        answersGroup = new ToggleGroup();
        score = 0;
        updateScoreLabel();
        resetGameGUI();
    }

    /**
     * Starts a new game when the "New Game" button is clicked.
     * Resets the score and question bank, and displays the first question.
     *
     * @param event The action event triggered by clicking the button
     */
    @FXML
    void onNewGameClicked(ActionEvent event) {
        score = 0;
        updateScoreLabel();
        questionBank.resetQuestions();
        displayNextQuestion();
        setGameButtons(true);
    }

    /**
     * Submits the selected answer when the "Submit" button is clicked.
     * Checks if the answer is correct and updates the score accordingly.
     *
     * @param event The action event triggered by clicking the button
     */
    @FXML
    void onSubmitClicked(ActionEvent event) {
        // Get the selected radio button from the toggle group
        RadioButton selectedRadioButton = (RadioButton) answersGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            // If no answer is selected, show a warning alert
            showAlert(Alert.AlertType.WARNING, "Please select an answer.");
            return;
        }

        // Retrieve the text of the selected answer
        String selectedAnswer = selectedRadioButton.getText();
        // Check if the selected answer is correct
        if (currentQuestion.isCorrect(selectedAnswer)) {
            // Correct answer: increment score and inform the user
            score += 10;
            showAlert(Alert.AlertType.INFORMATION, "Correct!");
        } else {
            // Incorrect answer: decrement score and provide the correct answer
            score -= 5;
            showAlert(Alert.AlertType.INFORMATION, "Incorrect! The correct answer was: " + currentQuestion.getCorrectAnswer());
        }
        // Update the score label in the GUI
        updateScoreLabel();
        // Disable the submit button to prevent multiple submissions
        submitButton.setDisable(true);
        // Enable the next question button
        nextButton.setDisable(false);

        // Check if there are no more questions left
        if (!questionBank.hasQuestions()) {
            // End the game if all questions have been answered
            endGame();
        }
    }

    /**
     * Displays the next question when the "Next Question" button is clicked.
     * Retrieves a new question from the question bank and updates the GUI.
     *
     * @param event The action event triggered by clicking the button
     */
    @FXML
    void onNextClicked(ActionEvent event) {
        displayNextQuestion();
    }

    /**
     * Ends the game when the "End Game" button is clicked.
     * Shows the final score and resets the game GUI.
     *
     * @param event The action event triggered by clicking the button
     */
    @FXML
    void onEndGameClicked(ActionEvent event) {
        endGame();
    }

    /**
     * Displays the next question and its possible answers.
     * Updates the question text and creates radio buttons for each answer.
     */
    private void displayNextQuestion() {
        if (!questionBank.hasQuestions()) {
            // No more questions available; end the game
            endGame();
            return;
        }
        // Get a random question from the question bank
        currentQuestion = questionBank.getRandomQuestion();
        // Set the question text in the GUI
        questionText.setText(currentQuestion.getQuestionText());

        // Get all possible answers shuffled
        List<String> allAnswers = currentQuestion.getAllAnswersShuffled();
        // Clear previous answers and reset the toggle group
        answersGroup = new ToggleGroup();
        answersBox.getChildren().clear();

        // Create radio buttons for each possible answer
        for (String answer : allAnswers) {
            RadioButton rb = new RadioButton(answer);
            rb.setToggleGroup(answersGroup);
            answersBox.getChildren().add(rb);
        }
        // Enable the submit button and disable the next button
        submitButton.setDisable(false);
        nextButton.setDisable(true);
    }

    /**
     * Ends the game and displays the final score.
     * Resets buttons and shows an alert with the final score.
     */
    private void endGame() {
        // Disable game interaction buttons
        submitButton.setDisable(true);
        nextButton.setDisable(true);
        endGameButton.setDisable(true);
        // Enable the new game button
        newGameButton.setDisable(false);
        // Display final score in the question text area
        questionText.setText("Game Over! Your final score is: " + score);
        // Clear any remaining answers
        answersBox.getChildren().clear();
        // Show an informational alert with the final score
        showAlert(Alert.AlertType.INFORMATION, "Game Over! Your final score is: " + score);
    }

    /**
     * Updates the score label to display the current score.
     */
    private void updateScoreLabel() {
        // Set the score label text to reflect the current score
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Shows an alert dialog with the specified type and message.
     *
     * @param alertType The type of the alert (e.g., INFORMATION, WARNING)
     * @param message   The message to display in the alert
     */
    private void showAlert(Alert.AlertType alertType, String message) {
        // Create and display an alert dialog
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Resets the game GUI to the initial state.
     * Clears questions and answers and disables appropriate buttons.
     */
    private void resetGameGUI() {
        // Set the welcome message in the question text area
        questionText.setText("Welcome to the Trivia Game! Click 'New Game' to start.");
        // Clear any existing answers
        answersBox.getChildren().clear();
        // Disable game interaction buttons
        submitButton.setDisable(true);
        nextButton.setDisable(true);
        endGameButton.setDisable(true);
    }

    /**
     * Enables or disables game buttons based on the game state.
     *
     * @param inGame True if the game is in progress; false otherwise
     */
    private void setGameButtons(boolean inGame) {
        // Enable or disable buttons based on whether the game is active
        submitButton.setDisable(!inGame);
        nextButton.setDisable(!inGame);
        endGameButton.setDisable(!inGame);
        newGameButton.setDisable(inGame);
    }
}
