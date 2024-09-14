package SortingAlgorithms;

import javafx.scene.media.AudioClip;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public abstract class SortingAlgorithmSubClass {
    protected Rectangle[] bars;  // Protected so subclasses can access
    protected Pane visualizationPane;
    protected Timeline timeline;
    protected int[] array;
    private AudioClip audioClip;
    private Timeline soundTimeline;  // Use a Timeline to control sound playback with delay

    // Constructor to initialize the pane
    public SortingAlgorithmSubClass(Pane visualizationPane) {
        this.visualizationPane = visualizationPane;

        // Load the sound effect once
        try {
            String soundPath = getClass().getResource("/sounds/tick.wav").toString();
            audioClip = new AudioClip(soundPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading sound.");
        }
    }

    // Common visualize method to generate and display bars
    public void visualize(int size) {
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
        double paneHeight = visualizationPane.getHeight();
        double paneWidth = visualizationPane.getWidth();
        double maxBarHeight = paneHeight * 0.8;
        double maxSpacing = 5.0;
        double minSpacing = 1.0;
        double spacing = maxSpacing - ((maxSpacing - minSpacing) / 100) * size;
        spacing = Math.max(spacing, minSpacing);  // Ensure the spacing doesn't go below the minimum
        double barWidth = (paneWidth - (size) * spacing) / size;

        for (int i = 0; i < array.length; i++) {
            double barHeight = (array[i] / 100.0) * maxBarHeight;
            Rectangle bar = new Rectangle(barWidth, barHeight);
            bar.setFill(Color.rgb(5, 59, 129));
            bar.setX(i * (barWidth + spacing));
            bars[i] = bar;
            visualizationPane.getChildren().add(bar);
        }
    }

    // Abstract method for starting animation, each subclass must implement this
    public abstract void startAnimation(double speed, AlgorithmTimer timer);

    // Play sound effect without recreating MediaPlayer
    public void playSoundLoop() {
        if (audioClip != null) {
            new Thread(() -> {
                soundTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> audioClip.play()));
                soundTimeline.setCycleCount(Timeline.INDEFINITE);
                soundTimeline.play();
            }).start();
        }
    }

    public void stopSound() {
        if (soundTimeline != null) {
            soundTimeline.stop();
        }
        if (audioClip != null) {
            audioClip.stop();  // Stop any currently playing sound
        }
    }


    // Common stop method to stop the timeline
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}
