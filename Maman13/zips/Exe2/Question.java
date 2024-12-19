import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single trivia question.
 */
public class Question {

    private String questionText;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    /**
     * Constructs a new Question.
     *
     * @param questionText     The text of the question
     * @param correctAnswer    The correct answer
     * @param incorrectAnswers A list of incorrect answers
     */
    public Question(String questionText, String correctAnswer, List<String> incorrectAnswers) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    /**
     * Gets the question text.
     *
     * @return The question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Gets the correct answer.
     *
     * @return The correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gets all answers (correct and incorrect) in a shuffled order.
     *
     * @return A list of all answers shuffled
     */
    public List<String> getAllAnswersShuffled() {
        // Combine correct and incorrect answers
        List<String> allAnswers = new ArrayList<>(incorrectAnswers);
        allAnswers.add(correctAnswer);
        // Shuffle the list to randomize answer positions
        Collections.shuffle(allAnswers);
        return allAnswers;
    }

    /**
     * Checks if the selected answer is correct.
     *
     * @param selectedAnswer The answer selected by the user
     * @return True if the answer is correct; false otherwise
     */
    public boolean isCorrect(String selectedAnswer) {
        return correctAnswer.equals(selectedAnswer);
    }
}
