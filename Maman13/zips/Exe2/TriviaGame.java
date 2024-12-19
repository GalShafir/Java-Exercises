import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Trivia Game.
 */
public class TriviaGame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file for the main GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trivia.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
