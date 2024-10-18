package org.logicgame.logic;

import java.util.TimerTask;
import java.util.Timer;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Clock {
    private Timer timer;
    private TimerTask task;
    private int secondsElapsed;
    private Label timerLabel;

    public Clock(Label timerLabel) {
        this.timerLabel = timerLabel;
        this.secondsElapsed = 0;
    }

    public void start() {
        if (timer == null) {
            timer = new Timer();
        }
        task = new TimerTask() {
            @Override
            public void run() {
                secondsElapsed++;
                Platform.runLater(() -> timerLabel.setText(formatTime(secondsElapsed)));
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
        }
        timer = null;
    }
    public void addPenalty(){
        secondsElapsed += 30;
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
