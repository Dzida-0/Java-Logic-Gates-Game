package org.logicgame;
import org.logicgame.logic.Circuit;
import org.logicgame.logic.GameEngine;
import org.logicgame.ui.MainGameWindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
     new MainGameWindow(primaryStage,new GameEngine("aaa","easy"));

    }
  
    public static void main(String[] args) {
        launch(args);
    }
}
