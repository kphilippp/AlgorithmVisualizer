import SortingAlgorithms.BubbleSort.BubbleSort;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Visualizer extends Application {

    private Pane visualizationPane;
    private BubbleSort bubbleSort;
    private String selectedAlgorithm;

    @Override
    public void start(Stage primaryStage) {

        // Speed Slider
        Slider speedSlider = new Slider(50, 300, 100);  // Min speed 100ms, max 1000ms, default 500ms
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);

        // Size Slider
        Slider sizeSlider = new Slider(10, 100, 50);  // Min size 10, max 100, default 50
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);

        // Dropdown Menu: Dropdown for algorithm selection
        ComboBox<String> algorithmDropdown = new ComboBox<>();
        algorithmDropdown.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort");
        algorithmDropdown.setPromptText("Select Algorithm");

        // Event: When an algorithm is selected from the dropdown
        algorithmDropdown.setOnAction(e -> {
            selectedAlgorithm = algorithmDropdown.getValue();
            if (selectedAlgorithm != null) {
                System.out.println("Selected Algorithm: " + selectedAlgorithm);
                stopCurrentAnimation(selectedAlgorithm);

                int size = (int) sizeSlider.getValue();  // Get the size from the size slider
                visualizeAlgorithmScreen(selectedAlgorithm, size);  // Show the initial array with the current size
            }
        });

        // Button: Start algorithm visualization button
        Button startButton = new Button("Start Visualization");

        // Event: Set up action for when the "Start" button is clicked
        startButton.setOnAction(e -> {
            if (selectedAlgorithm != null) {
                System.out.println("Starting Animation for: " + selectedAlgorithm);
                double speed = speedSlider.getValue();  // Get the slider value (speed)
                runAlgorithm(selectedAlgorithm, speed);  // Start the sorting animation
            } else {
                System.out.println("Please select an algorithm.");
            }
        });

        // Visualization Pane: for displaying the sorting process
        visualizationPane = new Pane();
        visualizationPane.setPrefSize(600, 300);

        // Initialize the bubble sort visualizer
        bubbleSort = new BubbleSort(visualizationPane);

        // Layout: (VBox stacks elements vertically)
        VBox layout = new VBox(10);  // Spacing of 10 between elements
        layout.getChildren().addAll(algorithmDropdown, startButton, speedSlider, sizeSlider, visualizationPane);  // Add sliders and visualizationPane

        // Set up the scene (what's in the window/stage)
        Scene scene = new Scene(layout, 600, 400);

        // Set up the stage (window)
        primaryStage.setTitle("Algorithm Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to run the algorithm's animation when the start button is clicked
    private void runAlgorithm(String algorithm, double speed) {
        switch (algorithm) {
            case "Bubble Sort":
                System.out.println("Running Bubble Sort...");
                bubbleSort.startBubbleSortAnimation(speed);  // Start sorting with the speed
                break;
            case "Selection Sort":
                System.out.println("Selection Sort not yet implemented.");
                break;
            case "Insertion Sort":
                System.out.println("Insertion Sort not yet implemented.");
                break;
            default:
                System.out.println("Unknown Algorithm");
                break;
        }
    }

    // New Method to visualize the selected algorithm's initial state (without animation)
    private void visualizeAlgorithmScreen(String algorithm, int size) {
        switch (algorithm) {
            case "Bubble Sort":
                System.out.println("Displaying Bubble Sort visualization screen...");
                bubbleSort.visualizeBubbleSortScreen(size);  // Display the initial array with the current size
                break;
            case "Selection Sort":
                System.out.println("Selection Sort visualization not yet implemented.");
                break;
            case "Insertion Sort":
                System.out.println("Insertion Sort visualization not yet implemented.");
                break;
            default:
                System.out.println("Unknown Algorithm");
                break;
        }
    }

    // Method to stop the current animation when switching algorithms
    private void stopCurrentAnimation(String algorithm) {
        // Stop any ongoing bubble sort animation (if applicable)
        switch (algorithm) {
            case "Bubble Sort":
                bubbleSort.stop();
                break;
            case "Selection Sort":
                System.out.println("Selection Sort animation not yet implemented.");
                break;
            case "Insertion Sort":
                System.out.println("Insertion Sort animation not yet implemented.");
                break;
            default:
                System.out.println("Unknown Algorithm");
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

