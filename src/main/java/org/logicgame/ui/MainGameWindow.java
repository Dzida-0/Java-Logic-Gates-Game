package org.logicgame.ui;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import org.logicgame.logic.*;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainGameWindow {
    private Stage stage;
    private Clock gameClock;
    private GameEngine gameEngine;
    private Line tempLine = null;
    public MainGameWindow(Stage stage, GameEngine gameEngine){
        this.stage = stage;
        this.gameEngine = gameEngine;
        //
        BorderPane layout = new BorderPane();
        Pane centerContent = new Pane();
        ToolBar toolBar = new ToolBar();
        Pane leftSidebar = new Pane();
        Pane rightSidebar = new Pane();
        layout.getChildren().addAll();
        layout.setTop(toolBar);
        layout.setLeft(leftSidebar);
        layout.setRight(rightSidebar);
        layout.setCenter(centerContent);
        gameEngine.createCircuit(centerContent);
        ///

        Label timerLabel = new Label("00:00");
        timerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        gameClock = new Clock(timerLabel);
        gameClock.start();
        Button notButton = new Button("NOT");
        notButton.setOnAction(event -> gameEngine.addNOT(notButton.getLayoutX()));
        Button andButton = new Button("AND");
        andButton.setOnAction(event -> gameEngine.addAND(andButton.getLayoutX()));
        Button orButton = new Button("OR");
        orButton.setOnAction(event -> gameEngine.addOR(orButton.getLayoutX()));
        toolBar.getItems().addAll(timerLabel,notButton, andButton, orButton);
        //

        int inputSpace = (int) (Screen.getPrimary().getVisualBounds().getHeight())/(gameEngine.getInputNumb()+1);
        leftSidebar.getStyleClass().add("SideBar");
        leftSidebar.setPrefWidth(50);
        //
        int outputSpace = (int) (Screen.getPrimary().getVisualBounds().getHeight())/(gameEngine.getOutputNumb()+1);
        rightSidebar.getStyleClass().add("SideBar");
        rightSidebar.setPrefWidth(50);
        //


        for (ProgramInput imp: gameEngine.getInputs()) {
            imp.setLayoutX(25);
            imp.setLayoutY(inputSpace);
            leftSidebar.getChildren().add(imp);
            centerContent.getChildren().add(imp.gateConnectorSetup());
            inputSpace += inputSpace;
        }
        for (ProgramOutput out: gameEngine.getOutputs()) {
            out.setLayoutX(25);
            out.setLayoutY(outputSpace);
            rightSidebar.getChildren().add(out);
            centerContent.getChildren().add(out.gateConnectorSetup());
            outputSpace += outputSpace;
        }




        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setMaximized(true);
        scene.getStylesheets().add(getClass().getResource("/MainGameStyle.css").toExternalForm());
        stage.setTitle("Logic Gates Game");
        stage.show();

    }
}
