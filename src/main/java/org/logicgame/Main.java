package org.logicgame;
import org.logicgame.logic.Circuit;
import org.logicgame.logic.GameEngine;
import org.logicgame.ui.MainGameWindow;

import org.logicgame.fileoperation.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.logicgame.ui.StartWindow;

public class Main extends Application {
  
    @Override

    public void start(Stage primaryStage) {
     new StartWindow(primaryStage);

    }

    public static void main(String[] args) {

        launch(args);
    }

}
