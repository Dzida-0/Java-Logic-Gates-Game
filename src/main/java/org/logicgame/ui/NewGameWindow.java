package org.logicgame.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class NewGameWindow {

    private Stage stage;
    private String difficulty = "Easy";
    private Set<String> takenNames = new HashSet<>();
    private Button startGameButton;
    private Label errorMessage;

    public NewGameWindow(Stage stage) {
        this.stage = stage;

        takenNames.add("aaa");
        takenNames.add("bbb");
        // setting up all UI components
        Button backButton = new Button("Back to Menu");
        startGameButton = new Button("Start New Game");
        startGameButton.setDisable(true);
        TextField gameNameField = new TextField();
        gameNameField.setPromptText("Enter Game Name");
        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");
        difficultyBox.setOnAction(e -> difficulty = difficultyBox.getValue());
        errorMessage = new Label();
        errorMessage.setStyle("-fx-font-size: 14px;");

        gameNameField.textProperty().addListener((observable, oldValue, newValue) -> {checkGameName(newValue);});
        backButton.setOnAction(e -> goBackToMenu());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gameNameField, difficultyBox, startGameButton, errorMessage, backButton);
        Scene scene = new Scene(layout, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/StartMenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void goBackToMenu() {
        new StartWindow(stage);
    }

    private void checkGameName(String gameName) {
        // Check if given name is available
        if (gameName.isEmpty()) {
            errorMessage.setText("Please enter a game name");
            startGameButton.setDisable(true);
        } else if (takenNames.contains(gameName)) {
            startGameButton.setDisable(true);
            errorMessage.setText("The game name is already taken");
        } else {
            startGameButton.setDisable(false);
            errorMessage.setText("");

        }
    }
}
