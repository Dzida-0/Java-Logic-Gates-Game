package org.logicgame.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartWindow {

    private Stage stage;

    public StartWindow(Stage stage) {
        //handles the main menu
        this.stage = stage;
        // labels, buttons and their action
        Label gameName = new Label("Logic Gates Game");
        gameName.getStyleClass().add("gameMainTitle");
        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load Game");
        Button statisticButton = new Button("Statistics");
        Button exitButton = new Button("Exit");
        newGameButton.setOnAction(e -> newGame());
        loadGameButton.setOnAction(e -> loadGame());
        statisticButton.setOnAction(e -> statistic());
        exitButton.setOnAction(e -> exit());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(gameName, newGameButton, loadGameButton, statisticButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/StartMenuStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Logic Gates Game");
        stage.show();
    }

    private void newGame() {
        new NewGameWindow(stage);
    }

    private void loadGame() {
        new LoadGameWindow(stage);
    }

    private void statistic() {
        new StatisticWindow(stage);
    }

    private void exit() {
        stage.close();
    }
}
