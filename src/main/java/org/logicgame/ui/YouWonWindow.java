package org.logicgame.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class YouWonWindow {

    private Stage stage;
    private int score;
    private int timeInSeconds;

    public YouWonWindow(Stage stage, int score, int timeInSeconds) {
        this.stage = stage;
        this.score = score;
        this.timeInSeconds = timeInSeconds;

        Label youWonLabel = new Label("You Won!");
        youWonLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

        Label timeLabel = new Label("Time: " + formatTime(timeInSeconds));
        Label scoreLabel = new Label("Score: " + score);
        timeLabel.setStyle("-fx-font-size: 18px;");
        scoreLabel.setStyle("-fx-font-size: 18px;");

        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(e -> saveGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> stage.close());
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(youWonLabel, timeLabel, scoreLabel, saveButton, exitButton);

        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/MainGameStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("You Won");
        stage.show();
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void saveGame() {

    }
}
