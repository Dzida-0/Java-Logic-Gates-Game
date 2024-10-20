package org.logicgame.logic;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

public class ProgramOutput extends Circle {
    private boolean curentState = false;
    private int id;
    private GateConnector in;
    public ProgramOutput(int id, Circuit circuit, Pane gameArea) {
        this.id = id;
        Gate not=  new NOT(1000,gameArea);
        not.out.setVisible(false);
        not.out.setDisable(true);
        not.setVisible(false);
        not.setDisable(false);
        not.in1.setVisible(false);
        not.in1.setDisable(true);
        this.in = new GateConnector(not,1,new Pane());

        setRadius(20);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
    }

    public GateConnector gateConnectorSetup(){
        in.setLayoutY(this.getLayoutY());
        in.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth()-105);
        return in;
    }
    public boolean checkUpdate(boolean stateExpected){
         curentState = in.getState();
        if (stateExpected && curentState){
            setFill(Color.GREEN);
        }
        else if (!stateExpected && curentState ) {
            setFill(Color.RED);
        }
        else if (stateExpected && !curentState) {
            setFill(Color.ORANGE);
        }
        else
        {
            setFill(Color.GREY);
        }
        return curentState == stateExpected;
    }
    public boolean getState(){return curentState;}


}

