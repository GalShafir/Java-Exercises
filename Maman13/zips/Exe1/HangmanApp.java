import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The HangmanApp class is the main application that loads the FXML file
 * and sets up the primary stage.
 */
public class HangmanApp extends Application {

    /**
     * The start method loads the FXML layout and displays the GUI.
     * @param primaryStage The primary stage for this application.
     * @throws Exception If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HangmanGUI.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method launches the JavaFX application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
