package SortingAlgorithms.SelectionSort;

import SortingAlgorithms.AlgorithmTimer;
import SortingAlgorithms.SortingAlgorithmSubClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SelectionSort extends SortingAlgorithmSubClass {

    public SelectionSort(Pane visualizationPane) {
        super(visualizationPane);
    }

    @Override
    public void startAnimation(double speed, AlgorithmTimer timer) {
        // Stop any existing animation
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        playSoundLoop();

        final int[] i = {0};
        final int[] min_idx = {0};

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speed), e -> {
            int size = array.length;

            if (i[0] < size - 1) {
                // Initialize min_idx for the new pass
                min_idx[0] = i[0];
                for (int j = i[0] + 1; j < size; j++) {
                    if (array[j] < array[min_idx[0]]) {
                        min_idx[0] = j;
                    }
                }

                // Swap the found minimum element with array[i]
                int temp = array[min_idx[0]];
                array[min_idx[0]] = array[i[0]];
                array[i[0]] = temp;

                // Perform the visual swap using Platform.runLater()
                final int currentI = i[0];
                final int currentMinIdx = min_idx[0];
                Platform.runLater(() -> {
                    // Swap the visual representation
                    double tempHeight = bars[currentMinIdx].getHeight();
                    bars[currentMinIdx].setHeight(bars[currentI].getHeight());
                    bars[currentI].setHeight(tempHeight);
                });

                // Move to the next element in the outer loop
                i[0]++;
            } else {
                // Sorting is done, stop the timeline
                timeline.stop();
                timer.pauseTimer();
                stopSound();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}