package SortingAlgorithms.BubbleSort;

import javafx.application.Platform;
import SortingAlgorithms.AlgorithmTimer;
import SortingAlgorithms.SortingAlgorithmSubClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BubbleSort extends SortingAlgorithmSubClass {

    // Constructor to initialize the pane
    public BubbleSort(Pane visualizationPane) {
        super(visualizationPane);  // Initialize the parent class with the pane
    }

    @Override
    public void startAnimation(double speed, AlgorithmTimer timer) {
        if (timeline != null) {
            timeline.stop();  // Stop any existing animation
        }

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        playSoundLoop();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(speed), e -> {
            boolean swapped = false;
            int lastUnsorted = array.length - 1;
            for (int i = 0; i < lastUnsorted; i++) {
                if (array[i] > array[i + 1]) {
                    // Swap the values in the array
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    // Create a final copy of i for use inside the lambda
                    final int currentIndex = i;

                    // Swap the visual representation
                    Platform.runLater(() -> {
                        // Only update the UI and play sound in small batches
                        double tempHeight = bars[currentIndex].getHeight();
                        bars[currentIndex].setHeight(bars[currentIndex + 1].getHeight());
                        bars[currentIndex + 1].setHeight(tempHeight);
                    });

                    swapped = true;
                }
            }

            if (!swapped) {
                timeline.stop();
                timer.pauseTimer();
                stopSound();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}