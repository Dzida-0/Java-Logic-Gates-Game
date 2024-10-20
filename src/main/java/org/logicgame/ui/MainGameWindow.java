package org.logicgame.ui;
import javafx.scene.layout.Pane;
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
    private String gameName;
    private int inputNumber;
    private int outputNumber;
    private List<ProgramInput> inputsList;
    private List<ProgramOutput> outputsList;
    private Clock gameClock;
    public MainGameWindow(Stage stage, String gameName){
        this.stage = stage;
        this.gameName = gameName;

        inputNumber = 3;
        outputNumber = 15;

        ToolBar toolBar = new ToolBar();
        Label timerLabel = new Label("00:00");
        timerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        gameClock = new Clock(timerLabel);
        gameClock.start();

        Button notButton = new Button("NOT");
        Button andButton = new Button("AND");
        Button orButton = new Button("OR");
        toolBar.getItems().addAll(timerLabel,notButton, andButton, orButton);


        int outputSpace = (int) (Screen.getPrimary().getVisualBounds().getHeight())/(outputNumber+1);
        VBox leftSidebar = new VBox(outputSpace);
        leftSidebar.setAlignment(Pos.CENTER);
        leftSidebar.setPrefWidth(100);
        outputsList  = new ArrayList<>();
        for (int i = 0 ; i < outputNumber; i++)
        {
            ProgramOutput programOutput = new ProgramOutput(i);
            outputsList.add(programOutput);
            leftSidebar.getChildren().add(programOutput);
        }

        VBox rightSidebar = new VBox();
        rightSidebar.setPrefWidth(100);

        BorderPane layout = new BorderPane();
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        layout.getChildren().addAll();
        layout.setTop(toolBar);
        layout.setLeft(leftSidebar);
        layout.setRight(rightSidebar);
        layout.setCenter(centerContent);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setMaximized(true);
        scene.getStylesheets().add(getClass().getResource("/MainGameStyle.css").toExternalForm());
        stage.setTitle("Logic Gates Game");
        stage.show();

    }
}
