package org.logicgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.logicgame.ui.MainGameWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
     new MainGameWindow(primaryStage,"name");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
