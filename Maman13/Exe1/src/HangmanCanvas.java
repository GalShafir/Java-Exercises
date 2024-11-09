import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The HangmanCanvas class extends Canvas and provides methods to draw the hangman figure
 * based on the number of incorrect guesses. The drawing order is: man first, then the hanger.
 */
public class HangmanCanvas extends Canvas {

    /**
     * Constructor initializes the canvas with specified width and height.
     * @param width The width of the canvas.
     * @param height The height of the canvas.
     */
    public HangmanCanvas(double width, double height) {
        super(width, height);
    }

    /**
     * Draws the hangman figure on the canvas based on the number of incorrect guesses.
     * The man is drawn first, followed by the hanger.
     * @param incorrectGuesses The number of incorrect guesses made.
     */
    public void drawHangman(int incorrectGuesses) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight()); // Clear the canvas before redrawing

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        double centerX = getWidth() / 2;
        double baseY = getHeight() - 30;

        // Draw hangman parts based on incorrect guesses

        // 1. Head
        if (incorrectGuesses > 0) {
            gc.strokeOval(centerX - 15, 40, 30, 30);
        }
        // 2. Body
        if (incorrectGuesses > 1) {
            gc.strokeLine(centerX, 70, centerX, 120);
        }
        // 3. Left Arm
        if (incorrectGuesses > 2) {
            gc.strokeLine(centerX, 80, centerX - 20, 100);
        }
        // 4. Right Arm
        if (incorrectGuesses > 3) {
            gc.strokeLine(centerX, 80, centerX + 20, 100);
        }
        // 5. Left Leg
        if (incorrectGuesses > 4) {
            gc.strokeLine(centerX, 120, centerX - 20, 150);
        }
        // 6. Right Leg
        if (incorrectGuesses > 5) {
            gc.strokeLine(centerX, 120, centerX + 20, 150);
        }

        // After the man is fully drawn, draw the hanger components

        // 7. Rope 
        if (incorrectGuesses > 6) {
            gc.strokeLine(centerX, 20, centerX, 40);
            gc.strokeOval(centerX - 15, 40, 30, 30);
        }

        // 8. Gallows (Base and Pole)
        if (incorrectGuesses > 7) {
            // Pole
            gc.strokeLine(60, baseY, 60, 20);
            gc.strokeLine(60, 20, centerX, 20);
            // Base
            gc.strokeLine(20, baseY, getWidth() - 20, baseY);
        }
    }
}
