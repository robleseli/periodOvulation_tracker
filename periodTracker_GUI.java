import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeriodTrackerGUI extends Application {

    private TextField startDateField;
    private TextField cycleLengthField;
    private TextField previousCycleLengthField;
    private Label nextPeriodLabel;
    private Label ovulationLabel;

    @Override
    public void start(Stage primaryStage) {
        // Create labels and text fields
        Label startDateLabel = new Label("Start Date of Last Period:");
        startDateField = new TextField();
        Label cycleLengthLabel = new Label("Average Length of Menstrual Cycle (in days):");
        cycleLengthField = new TextField();
        Label previousCycleLengthLabel = new Label("Length of Previous Cycle (in days):");
        previousCycleLengthField = new TextField();
        nextPeriodLabel = new Label();
        ovulationLabel = new Label();

        // Create calculate button
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculateDates());

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, startDateLabel, startDateField);
        gridPane.addRow(1, cycleLengthLabel, cycleLengthField);
        gridPane.addRow(2, previousCycleLengthLabel, previousCycleLengthField);
        gridPane.add(calculateButton, 0, 3);
        gridPane.add(nextPeriodLabel, 0, 4);
        gridPane.add(ovulationLabel, 0, 5);

        // Set up scene
        Scene scene = new Scene(gridPane, 400, 200);

        // Set up stage
        primaryStage.setTitle("Period Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateDates() {
        // Parse input values
        String startDateStr = startDateField.getText();
        int cycleLength = Integer.parseInt(cycleLengthField.getText());
        int previousCycleLength = Integer.parseInt(previousCycleLengthField.getText());

        // Parse the start date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);

        // Calculate the average cycle length
        int averageCycleLength = (cycleLength + previousCycleLength) / 2;

        // Predict the next period and ovulation date
        LocalDate nextPeriodDate = startDate.plusDays(averageCycleLength);
        LocalDate ovulationDate = startDate.plusDays((averageCycleLength - 14) / 2); // Assuming ovulation occurs around day 14

        // Display the results
        nextPeriodLabel.setText("Next Period: " + nextPeriodDate.format(formatter));
        ovulationLabel.setText("Ovulation: " + ovulationDate.format(formatter));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
