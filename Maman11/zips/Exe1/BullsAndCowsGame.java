import javax.swing.JOptionPane;

/**
 * Bulls and Cows Game (In Hebrew "Bull Pgiaa")
 * Author: Gal Shafir
 *
 * The game is played between the user and the computer.
 * In Hebrew the game is called differently but you can read why I called the game like this in here: https://he.wikipedia.org/wiki/%D7%91%D7%95%D7%9C_%D7%A4%D7%92%D7%99%D7%A2%D7%94
 * The computer generates a secret four-digit number with all digits being distinct.
 * The user tries to guess the secret number.
 * After each guess, the program provides feedback in terms of:
 * - "Bulls": The number of digits that are correct in both value and position.
 * - "Cows": The number of digits that are correct in value but in the wrong position.
 *
 * The game continues until the user guesses the secret number correctly.
 * The user can also display the number of attempts made or search for matching strings from previous attempts.
 */

public class BullsAndCowsGame {
    public static void main(String[] args) {
        boolean playAgain = true;

        while (playAgain) {
            GameLogic gameLogic = new GameLogic(); // Initialize game logic
            gameLogic.startGame(); // Start the game

            // Show the total number of attempts before asking to play again
            int totalAttempts = gameLogic.getAttempts();
            String message = "Congratulations! You guessed the number in " + totalAttempts + " attempts.\n"
                    + "Do you want to play again?";
            int response = JOptionPane.showConfirmDialog(null, message, "Play Again?", JOptionPane.YES_NO_OPTION);

            playAgain = (response == JOptionPane.YES_OPTION);
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}
