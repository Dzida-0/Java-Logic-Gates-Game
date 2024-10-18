package org.logicgame.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewGameWindow {

    private Stage stage;

    public NewGameWindow(Stage stage) {
        this.stage = stage;

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> goBackToMenu());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(backButton);
        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.setTitle("New Game");
        stage.show();
    }

    private void goBackToMenu() {
        new StartWindow(stage);
    }
}
