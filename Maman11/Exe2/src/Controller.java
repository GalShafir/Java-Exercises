// Controller.java

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class handles the user interface logic for the Temperature Bar Graph application.
 *
 * It manages the drawing of the bar graph on the Canvas, responds to button presses,
 * and cycles through different years of temperature data.
 */
public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private Button nextButton;

    private List<Weather> weatherData;
    private int currentYearIndex = 0;

    /**
     * Initializes the controller by setting up temperature data and button actions.
     * This method is called automatically after the FXML elements have been loaded.
     */
    public void initialize() {
        // Initialize data for 5 years
        weatherData = new ArrayList<>();
        weatherData.add(new Weather(2017, new double[]{14.0, 16.0, 20.0, 24.0, 28.0, 32.0, 35.0, 34.0, 31.0, 27.0, 21.0, 16.0}));
        weatherData.add(new Weather(2018, new double[]{13.5, 15.5, 14.0, 36.0, 20.5, 31.5, 34.5, 30.2, 33.5, 18.5, 20.5, 11.5}));
        weatherData.add(new Weather(2019, new double[]{14.2, 16.2, 20.2, 24.2, 28.2, 32.2, 35.2, 35.1, 31.2, 27.2, 21.2, 16.2}));
        weatherData.add(new Weather(2020, new double[]{13.8, 15.8, 19.8, 23.8, 27.8, 31.8, 34.8, 38.8, 30.8, 26.8, 20.8, 15.8}));
        weatherData.add(new Weather(2021, new double[]{14.5, 16.5, 20.5, 24.5, 28.5, 32.5, 35.5, 36.9, 31.5, 27.5, 21.5, 16.5}));
        weatherData.add(new Weather(2022, new double[]{16.5, 12.5, 22.5, 24.0, 19.9, 27.6, 27.7, 30.0, 28.0, 16.0, 15.5, 15.0}));

        // Set the action for the "Next" button
        nextButton.setOnAction(event -> {
            currentYearIndex = (currentYearIndex + 1) % weatherData.size();
            drawGraph();
        });

        // Bind the canvas size to its parent (AnchorPane)
        AnchorPane parent = (AnchorPane) canvas.getParent();
        canvas.widthProperty().bind(parent.widthProperty());
        canvas.heightProperty().bind(parent.heightProperty());

        // Add listeners to redraw the graph when the canvas size changes
        // As the Maman Instructions says, the graphs should always spread all over the canvas size
        canvas.widthProperty().addListener(evt -> drawGraph());
        canvas.heightProperty().addListener(evt -> drawGraph());

        // Set a light background color to the AnchorPane
        // I thought it will be nice to play with the font and the background
        parent.setStyle("-fx-background-color: #f0f8ff;");

        // Draw the initial graph
        drawGraph();
    }

    /**
     * Draws the bar graph for the current year's temperature data.
     * This method is called whenever the "Next" button is pressed or the canvas size changes.
     */
    private void drawGraph() {
        // Clear the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set custom font for drawing text
        // I thought it will be nice to play with the font and the background
        Font font = new Font("Verdana", 14);
        gc.setFont(font);

        // Get the data for the current year
        Weather data = weatherData.get(currentYearIndex);
        double[] temperatures = data.getTemperatures();

        // Find the maximum and minimum temperatures and their indices
        double maxTemp = Double.NEGATIVE_INFINITY;
        double minTemp = Double.POSITIVE_INFINITY;
        int maxIndex = -1;
        int minIndex = -1;

        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > maxTemp) {
                maxTemp = temperatures[i];
                maxIndex = i;
            }
            if (temperatures[i] < minTemp) {
                minTemp = temperatures[i];
                minIndex = i;
            }
        }

        // Set up dimensions and scaling factors
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double padding = 60;
        double graphWidth = canvasWidth - 2 * padding;
        double graphHeight = canvasHeight - 2 * padding;

        double barWidth = graphWidth / temperatures.length - 10; // Subtract 10 for spacing
        double maxBarHeight = graphHeight - padding; // Leave space for labels
        double tempScale = maxBarHeight / maxTemp;

        // Draw the title
        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillText("Average Monthly Temperatures for: " + data.getYear(), canvasWidth / 2 - 150, padding / 2);

        // Draw the bars
        for (int i = 0; i < temperatures.length; i++) {
            double temp = temperatures[i];
            double barHeight = temp * tempScale;
            double x = padding + i * (barWidth + 10);
            double y = canvasHeight - padding - barHeight;

            // Set bar color
            if (i == maxIndex) {
                gc.setFill(Color.RED); // Highest temperature
            } else if (i == minIndex) {
                gc.setFill(Color.BLUE); // Lowest temperature
            } else {
                gc.setFill(Color.DARKGRAY); // Other temperatures
            }

            // Draw the bar
            gc.fillRect(x, y, barWidth, barHeight);

            // Draw the month label
            gc.setFill(Color.DARKSLATEGRAY);
            gc.fillText(Integer.toString(i + 1), x + barWidth / 2 - 5, canvasHeight - padding + 15);

            // Draw the temperature value above the bar
            gc.fillText(String.format("%.1f", temp), x + barWidth / 2 - 10, y - 5);
        }

        // Draw the y-axis labels (temperatures)
        gc.setFill(Color.DARKSLATEGRAY);
        for (int i = 0; i <= maxTemp; i += 5) {
            double y = canvasHeight - padding - i * tempScale;
            gc.fillText(Integer.toString(i), padding - 30, y + 5);
            gc.strokeLine(padding - 5, y, padding, y);
        }
    }
}
