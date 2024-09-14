package SortingAlgorithms;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AlgorithmTimer {

    private Label timerLabel;
    private int secondsElapsed = 0;
    private Timeline timer;
    // Timer logic


    public AlgorithmTimer(Label timerLabel) {
        this.timerLabel = timerLabel;
        timer = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed + "ms");
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    public void startTimer() {
        timer.play();
    }

    // Pause the timer (for reset without setting time to 0)
    public void pauseTimer() {
        timer.pause();
    }

    // Reset the timer to 0
    public void resetTimer() {
        secondsElapsed = 0;
        timerLabel.setText("Time: 0s");
    }
}
