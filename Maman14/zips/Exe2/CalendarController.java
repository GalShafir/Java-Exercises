import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.io.*;

/**
 * Controller class for the Calendar application.
 */
public class CalendarController implements Serializable {

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private GridPane calendarGrid;

    // HashMap to store appointments with date keys in "yyyyMMdd" format
    private Map<String, String> appointments = new HashMap<>();

    // Calendar instance for date operations
    private Calendar calendar = Calendar.getInstance();

    // File to store appointments data
    // I thought it will be cool to save the data into a file.
    // So it will be like a real calendar even after the program is closed.
    private final String DATA_FILE = ".appointments.dat";

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize year and month ComboBoxes
        initializeYearComboBox();
        initializeMonthComboBox();

        // Load appointments from file
        loadAppointments();

        // Display the calendar for the current month and year
        updateCalendar();
    }

    /**
     * Initializes the year ComboBox with a range of years.
     */
    private void initializeYearComboBox() {
        for (int year = 2015; year <= 2035; year++) {
            yearComboBox.getItems().add(year);
        }
        yearComboBox.setValue(calendar.get(Calendar.YEAR));
        yearComboBox.setOnAction(e -> updateCalendar());
    }

    /**
     * Initializes the month ComboBox with the months of the year.
     */
    private void initializeMonthComboBox() {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < 12; i++) {
            monthComboBox.getItems().add(months[i]);
        }
        monthComboBox.setValue(months[calendar.get(Calendar.MONTH)]);
        monthComboBox.setOnAction(e -> updateCalendar());
    }

    /**
     * Updates the calendar display based on the selected year and month.
     */
    private void updateCalendar() {
        // Clear the calendar grid and constraints
        calendarGrid.getChildren().clear();
        calendarGrid.getColumnConstraints().clear();
        calendarGrid.getRowConstraints().clear();

        int year = yearComboBox.getValue();
        
        // Gets the zero-based index of the selected month (0 for January, 11 for December).
        int month = monthComboBox.getSelectionModel().getSelectedIndex();

        // Set the calendar to the first day of the selected month and year
        calendar.set(year, month, 1);

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Add labels for days of the week
        String[] dayNames = new DateFormatSymbols().getShortWeekdays();
        for (int col = 0; col < 7; col++) {
            Label dayLabel = new Label(dayNames[col + 1]); // dayNames[1] is Sunday
            // Some styling :)
            dayLabel.setFont(Font.font("Arial", 16));
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            dayLabel.setStyle("-fx-alignment: center; -fx-border-color: lightgray; -fx-background-color: #f0f0f0;");
            calendarGrid.add(dayLabel, col, 0);

            // Set column constraints for equal width so the boxes will look good.
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100.0 / 7);
            calendarGrid.getColumnConstraints().add(colConstraints);
        }

        // Set row constraints for equal height
        for (int row = 0; row <= 6; row++) { // 6 rows + header
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / 7);
            calendarGrid.getRowConstraints().add(rowConstraints);
        }

        // Create buttons for each day
        int dayCounter = 1;
        int row = 1;
        int col = firstDayOfWeek - 1; // Adjust column index for first day

        while (dayCounter <= daysInMonth) {
            Button dayButton = new Button(String.valueOf(dayCounter));
            // Some styling :)
            dayButton.setFont(Font.font("Arial", 20));
            dayButton.setMaxWidth(Double.MAX_VALUE);
            dayButton.setMaxHeight(Double.MAX_VALUE);
            dayButton.setStyle("-fx-alignment: center; -fx-border-color: lightgray; -fx-background-color: white;");
            dayButton.setOnAction(e -> openAppointmentDialog(dayButton.getText()));
            calendarGrid.add(dayButton, col, row);

            dayCounter++;
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Opens a dialog to view and edit appointments for the selected day.
     * @param day The day of the month as a String
     */
    private void openAppointmentDialog(String day) {
        int selectedDay = Integer.parseInt(day);

        // Set the calendar to the selected day
        int year = yearComboBox.getValue();
        int month = monthComboBox.getSelectionModel().getSelectedIndex();
        calendar.set(year, month, selectedDay);

        // Create a date key as a string in "yyyyMMdd" format
        String dateKey = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());

        // Get existing appointments
        String existingAppointments = appointments.getOrDefault(dateKey, "");

        // Create a TextArea for the appointments
        TextArea appointmentTextArea = new TextArea(existingAppointments);
        appointmentTextArea.setWrapText(true);

        // Create the dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Appointments for " + new SimpleDateFormat("MMMM d, yyyy").format(calendar.getTime()));

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.getDialogPane().setContent(appointmentTextArea);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return appointmentTextArea.getText();
            }
            return null;
        });

        // As we learn in the book about serialization I thought it will be nice to have it here because it make sense to use it here.
        // Save the appointments if the user clicks "Save"
        dialog.showAndWait().ifPresent(result -> {
            appointments.put(dateKey, result);
            saveAppointments();
        });
    }


    /**
     * Saves the appointments map to a hidden file using serialization.
     */
    private void saveAppointments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(appointments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the appointments map from the hidden file if it exists.
     */
    private void loadAppointments() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                appointments = (HashMap<String, String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
