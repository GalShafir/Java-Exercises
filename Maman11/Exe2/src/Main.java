// Main.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Author: Gal Shafir
 * The Main class serves as the entry point for the JavaFX application.
 *
 * It loads the FXML file and initializes the primary weather window.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Temperature Bar Graph");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * The main method launches the JavaFX application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
