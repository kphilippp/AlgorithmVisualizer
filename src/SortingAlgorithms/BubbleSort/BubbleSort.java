package SortingAlgorithms.BubbleSort;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Text;
import java.util.Random;

public class BubbleSort {
    private Rectangle[] bars;
    private Pane visualizationPane;
    private Timeline timeline;  // Keep track of the timeline for stopping it
    private int[] array;  // Store the array to be sorted

    // Constructor
    public BubbleSort(Pane visualizationPane) {
        this.visualizationPane = visualizationPane;
    }

    // Method to visualize the bubble sort (without starting the animation)
    public void visualizeBubbleSortScreen(int size) {

        // Generate an array of random values
        Random random = new Random();
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);  // Random values between 0 and 100
        }

        // Clear the visualization pane
        visualizationPane.getChildren().clear();

        // Create rectangles representing the array
        bars = new Rectangle[array.length];
        HBox barsBox = new HBox(5);  // Horizontal layout for bars, 5px spacing
        for (int i = 0; i < array.length; i++) {
            Rectangle bar = new Rectangle(10, array[i] * 3);  // Width: 40, Height scaled by 3
            bar.setFill(Color.rgb(5, 59, 129));  // Set color of the bars
            bars[i] = bar;

            // Adjust the bar's Y position so it starts at the bottom of the pane
            bar.setY(300 - array[i] * 3);  // Pane height is 300, subtract the bar's height


            barsBox.getChildren().add(bar);
        }

        // Add the bars to the visualization pane
        visualizationPane.getChildren().add(barsBox);
    }

    // Method to start the animation (when the Start button is pressed)
    public void startBubbleSortAnimation(double speed) {
        if (timeline != null) {
            timeline.stop();  // Stop any existing animation
        }

        // Reset the timeline
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speed), e -> {
            boolean swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    // Swap the values in the array
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    // Swap the visual representation
                    double tempHeight = bars[i].getHeight();
                    bars[i].setHeight(bars[i + 1].getHeight());
                    bars[i + 1].setHeight(tempHeight);

                    swapped = true;
                }
            }
            if (!swapped) {
                timeline.stop();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    // Method to stop any ongoing animation
    public void stop() {
        if (timeline != null) {
            timeline.stop();  // Stop any ongoing animation
        }
    }
}
