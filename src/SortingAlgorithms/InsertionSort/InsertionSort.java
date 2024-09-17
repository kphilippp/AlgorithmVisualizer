package SortingAlgorithms.InsertionSort;

import SortingAlgorithms.AlgorithmTimer;
import SortingAlgorithms.SortingAlgorithmSubClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class InsertionSort extends SortingAlgorithmSubClass {

    public InsertionSort(Pane visualizationPane) {
        super(visualizationPane);
    }

    @Override
    public void startAnimation(double speed, AlgorithmTimer timer) {
        if (timeline != null) {
            timeline.stop();  // Stop any existing animation
        }

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        playSoundLoop();

        final int[] i = {0};
        KeyFrame keyFrame = new KeyFrame(Duration.millis(speed), e -> {
            int j = i[0] + 1;
            while (j > 0 && array[j] < array[j - 1]) {
                // Swap the elements
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;

                // Perform the visual swap using Platform.runLater()
                final int finalJ = j;
                Platform.runLater(() -> {
                    // Swap the visual representation
                    double tempHeight = bars[finalJ].getHeight();
                    bars[finalJ].setHeight(bars[finalJ - 1].getHeight());
                    bars[finalJ - 1].setHeight(tempHeight);
                });

                j--;
            }

            i[0]++;

            // When sorting is done, stop the animation
            if (i[0] == array.length) {
                timeline.stop();
                timer.pauseTimer();  // Pause the timer when sorting finishes
                stopSound();  // Stop the sound if it's playing
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}