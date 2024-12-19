import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CalendarApp that displays a monthly calendar
 * and allows the user to input appointments for each day.
 */
public class CalendarApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
        Scene scene = new Scene(loader.load());

        // Set the title of the window
        primaryStage.setTitle("Calendar");

        // Set the scene and display the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
