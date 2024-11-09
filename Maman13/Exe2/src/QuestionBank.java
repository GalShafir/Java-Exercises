import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Manages the collection of trivia questions.
 */
public class QuestionBank {

    private List<Question> questions;
    private List<Question> usedQuestions;

    /**
     * Constructs a new QuestionBank and loads questions from a file.
     *
     * @param filename The name of the file containing questions
     */
    public QuestionBank(String filename) {
        questions = new ArrayList<>();
        usedQuestions = new ArrayList<>();
        loadQuestionsFromFile(filename);
    }

    /**
     * Loads questions from a text file.
     * Each question is expected to have five lines in the file:
     * Question text, correct answer, and three incorrect answers.
     *
     * @param filename The name of the file containing questions
     */
    private void loadQuestionsFromFile(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            while (input.hasNextLine()) {
                // Read the question text
                String questionText = input.nextLine();
                if (!input.hasNextLine()) break;
                // Read the correct answer
                String correctAnswer = input.nextLine();
                List<String> incorrectAnswers = new ArrayList<>();
                // Read the three incorrect answers
                for (int i = 0; i < 3; i++) {
                    if (input.hasNextLine()) {
                        incorrectAnswers.add(input.nextLine());
                    }
                }
                // Create a new Question object and add it to the list
                questions.add(new Question(questionText, correctAnswer, incorrectAnswers));
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Show an error alert if the file is not found
            Alert alert = new Alert(Alert.AlertType.ERROR, "Question file not found!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Retrieves a random question from the bank.
     *
     * @return A random Question object
     */
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        // Generate a random index to select a question
        Random rand = new Random();
        int index = rand.nextInt(questions.size());
        // Remove the question from the list and add it to used questions
        Question q = questions.remove(index);
        usedQuestions.add(q);
        return q;
    }

    /**
     * Resets the question bank to its initial state.
     * Moves all used questions back into the main question list.
     */
    public void resetQuestions() {
        questions.addAll(usedQuestions);
        usedQuestions.clear();
    }

    /**
     * Checks if there are remaining questions.
     *
     * @return True if there are questions left; false otherwise
     */
    public boolean hasQuestions() {
        return !questions.isEmpty();
    }
}
