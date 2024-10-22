import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Controller class for the Game of Life.
 * Manages the grid and handles user interactions.
 */
public class MainController {

    private static final int SIZE = 10; // Grid size
    private static final int CELL_SIZE = 30; // Size of each cell in pixels

    private boolean[][] grid = new boolean[SIZE][SIZE]; // The life grid
    private boolean firstClick = true; // Flag for the first button click

    @FXML
    private Canvas gameCanvas; // Canvas from the FXML

    private GraphicsContext gc; // Graphics context for drawing

    /**
     * Initializes the controller after the FXML has been loaded.
     */
    @FXML
    private void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
    }

    /**
     * Handles the "Next Gen" button click.
     */
    @FXML
    private void nextGenClicked() {
        if (firstClick) {
            randomizeGrid();
            firstClick = false;
        } else {
            updateGrid();
        }
        drawGrid();
    }

    /**
     * Randomly initializes the grid with alive or dead cells.
     * Each cell has a 50% chance of being alive.
     */
    private void randomizeGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Assign a random boolean value to each cell:
                // Math.random() generates a double between 0.0 and 1.0
                // If the value is less than 0.5, the cell is alive (true)
                // If the value is 0.5 or greater, the cell is dead (false)
                grid[i][j] = Math.random() < 0.5;
            }
        }
    }

    /**
     * Updates the grid to the next generation based on Conway's Game of Life rules.
     * Applies the rules to each cell to determine its state in the next generation.
     * Uses a separate grid to store the updates to avoid interference during calculations.
     */
    private void updateGrid() {
        boolean[][] nextGrid = new boolean[SIZE][SIZE]; // Create a new grid for the next generation

        // Iterate over each cell in the current grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Count the number of alive neighbors around the cell at (i, j)
                int neighbors = countNeighbors(i, j);

                if (grid[i][j]) {
                    // The cell is currently alive
                    // Apply the rules for alive cells:
                    // - The cell survives to the next generation if it has 2 or 3 alive neighbors
                    // - Otherwise, the cell dies due to underpopulation or overpopulation
                    nextGrid[i][j] = (neighbors == 2 || neighbors == 3);
                } else {
                    // The cell is currently dead
                    // Apply the rules for dead cells:
                    // - The cell becomes alive in the next generation if it has exactly 3 alive neighbors (birth)
                    // - Otherwise, the cell remains dead
                    nextGrid[i][j] = (neighbors == 3);
                }
            }
        }

        // Update the current grid to the next generation
        grid = nextGrid;
    }

    /**
     * Counts the number of alive neighbors around a cell.
     *
     * @param x The x-coordinate (row index) of the cell.
     * @param y The y-coordinate (column index) of the cell.
     * @return The count of alive neighbors.
     */
    private int countNeighbors(int x, int y) {
        int count = 0; // Initialize neighbor count to zero

        // Iterate over the neighboring cells in a 3x3 grid centered on (x, y)
        for (int i = -1; i <= 1; i++) {
            int nx = x + i; // Neighbor's x-coordinate (row index)

            // Skip if nx is out of bounds (less than 0 or greater than SIZE - 1)
            if (nx < 0 || nx >= SIZE) continue;

            for (int j = -1; j <= 1; j++) {
                int ny = y + j; // Neighbor's y-coordinate (column index)

                // Skip if ny is out of bounds
                if (ny < 0 || ny >= SIZE) continue;

                // Skip the cell itself (when i == 0 and j == 0)
                if (i == 0 && j == 0) continue;

                // If the neighbor cell at (nx, ny) is alive, increment the count
                if (grid[nx][ny]) count++;
            }
        }

        // Return the total number of alive neighbors
        return count;
    }

    /**
     * Draws the grid on the canvas.
     */
    private void drawGrid() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j]) {
                    // Alive cell - draw filled square
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    // Dead cell - draw empty square
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
}
