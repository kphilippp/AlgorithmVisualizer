import SortingAlgorithms.AlgorithmTimer;
import SortingAlgorithms.BubbleSort.BubbleSort;
import SortingAlgorithms.InsertionSort.InsertionSort;
import SortingAlgorithms.SelectionSort.SelectionSort;

import SortingAlgorithms.SortingAlgorithmSubClass;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;


public class Visualizer extends Application {

    private Pane visualizationPane;
    private SortingAlgorithmSubClass currentAlgorithm;
    private String selectedAlgorithm;

    private AlgorithmTimer algorithmTimer;

    @Override
    public void start(Stage primaryStage) {

        // Timer Label to display the time
        Label timerLabel = new Label("Time: 0s");
        timerLabel.setStyle("-fx-font-size: 16px;");

        // Timer
        algorithmTimer = new AlgorithmTimer(timerLabel);

        // Speed Slider
        Slider speedSlider = new Slider(10, 300, 50);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);

        // Size Slider
        Slider sizeSlider = new Slider(10, 500, 150);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);

        // Dropdown Menu: Dropdown for algorithm selection
        ComboBox<String> algorithmDropdown = new ComboBox<>();
        algorithmDropdown.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort");
        algorithmDropdown.setPromptText("Select Algorithm");

        algorithmDropdown.setOnAction(e -> {
            selectedAlgorithm = algorithmDropdown.getValue();
            if (selectedAlgorithm != null) {
                System.out.println("Visualize Algorithm: " + selectedAlgorithm);
                stopCurrentAnimation();
                algorithmTimer.resetTimer();

                int size = (int) sizeSlider.getValue();
                selectAlgorithm(selectedAlgorithm);
                currentAlgorithm.visualize(size);  // Show the initial array
            }
        });

        // Button: Start algorithm visualization button
        Button startButton = new Button("Start Visualization");
        Button resetButton = new Button("Reset Visualization");

        startButton.setOnAction(e -> {
            if (selectedAlgorithm != null) {
                System.out.println("Starting Animation for: " + selectedAlgorithm);
                algorithmTimer.resetTimer();  // Reset the timer when starting new visualization
                algorithmTimer.startTimer();  // Start the timer
                double speed = speedSlider.getValue();
                currentAlgorithm.startAnimation(speed, algorithmTimer);  // Start sorting
            }
        });

        resetButton.setOnAction(e -> {
            if (currentAlgorithm != null) {
                System.out.println("Resetting Algorithm for: " + selectedAlgorithm);
                currentAlgorithm.stop();
                algorithmTimer.pauseTimer();  // Pause the timer without resetting it
                currentAlgorithm.visualize((int)sizeSlider.getValue());
            }
        });

        // Arrange buttons side by side
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(startButton, resetButton);


        // Visualization Pane
        visualizationPane = new Pane();
        visualizationPane.setPrefSize(600, 300);

        // Top Bar for Timer and Algorithm Dropdown
        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.getChildren().addAll(algorithmDropdown);

        // Add the timer to the top-right corner
        HBox timerBox = new HBox();
        timerBox.setAlignment(Pos.CENTER_RIGHT);
        timerBox.getChildren().add(timerLabel);

        // Combine both into a top-level HBox
        HBox topLayout = new HBox();
        topLayout.getChildren().addAll(topBar, timerBox);
        topLayout.setHgrow(timerBox, Priority.ALWAYS);  // Makes the timer stay at the right

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(topLayout, buttonBox, speedSlider, sizeSlider, visualizationPane);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Algorithm Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectAlgorithm(String algorithmName) {
        switch (algorithmName) {
            case "Bubble Sort":
                currentAlgorithm = new BubbleSort(visualizationPane);
                break;
            case "Selection Sort":
                currentAlgorithm = new SelectionSort(visualizationPane);
                break;
            case "Insertion Sort":
                currentAlgorithm = new InsertionSort(visualizationPane);
                break;
            default:
                System.out.println("Unknown Algorithm");
                break;
        }
    }


    private void stopCurrentAnimation() {
        if (currentAlgorithm != null) {
            currentAlgorithm.stop();
        }
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}