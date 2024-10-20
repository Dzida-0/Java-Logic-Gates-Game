package org.logicgame.ui;

import org.logicgame.fileoperation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.logicgame.logic.GameEngine;

public class NewGameWindow {

    private Stage stage;
    private String difficulty = "easy";
    private String name;
    private boolean challenge;
    private Button startGameButton;
    private Label errorMessage;

    public NewGameWindow(Stage stage) {
        this.stage = stage;
        // setting up all UI components
        Button backButton = new Button("Back to Menu");
        startGameButton = new Button("Start New Game");
        startGameButton.setDisable(true);
        startGameButton.setOnAction(event -> startNewGame());
        TextField gameNameField = new TextField();
        gameNameField.setPromptText("Enter Game Name");
        gameNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                gameNameField.setText(oldValue);
            }
        });
        gameNameField.setOnAction(event -> name = gameNameField.getText());
        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");
        difficultyBox.setOnAction(event -> difficulty = difficultyBox.getValue());
        errorMessage = new Label();
        errorMessage.setStyle("-fx-font-size: 14px;");
        CheckBox challengeModeBox = new CheckBox("Challenge Mode");
        Label challengeMessage = new Label("In challenge mode you cannot use NOT, AND, and OR gates.");
        challengeMessage.setStyle("-fx-font-size: 12px;");
        challengeMessage.setVisible(false);
        challengeModeBox.setOnAction(event -> {
            challengeMessage.setVisible(challengeModeBox.isSelected());
        });
        challengeModeBox.setOnAction(event -> challenge = challengeModeBox.isSelected());
        gameNameField.textProperty().addListener((observable, oldValue, newValue) -> {checkGameName(newValue);});
        backButton.setOnAction(event -> goBackToMenu());
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gameNameField,errorMessage, difficultyBox, startGameButton,challengeModeBox,challengeMessage, backButton);
        Scene scene = new Scene(layout, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/StartMenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    private void goBackToMenu() {
        new StartWindow(stage);
    }
    private void startNewGame(){
        new MainGameWindow(stage,new GameEngine(name,difficulty),challenge);
    }

    private void checkGameName(String gameName) {
        // Check if given name is available
        if (gameName.isEmpty()) {
            errorMessage.setText("Please enter a game name");
            startGameButton.setDisable(true);
        } else if (new StatisticFileOperation().isNameInDatabase(gameName)) {
            startGameButton.setDisable(true);
            errorMessage.setText("The game name is already taken");
        } else {
            startGameButton.setDisable(false);
            errorMessage.setText("");

        }
    }
}
