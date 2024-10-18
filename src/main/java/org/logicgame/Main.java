package org.logicgame;

import javafx.application.Application;
import javafx.stage.Stage;
import org.logicgame.ui.StartWindow;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) {
        new StartWindow(mainStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
