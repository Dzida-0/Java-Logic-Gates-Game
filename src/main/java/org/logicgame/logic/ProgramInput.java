package org.logicgame.logic;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class ProgramInput extends Circle{
    private boolean currentState = false;
    private int id;
    private Circuit circuit;
    private GateConnector out;
    public ProgramInput(int id, Circuit circuit, Pane gameArea) {
        this.id = id;
        this.circuit = circuit;
        this.out = new GateConnector(gameArea);
        setRadius(20);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
        setOnMouseClicked(this::handleClick);

    }


    private void handleClick(MouseEvent event) {
        currentState = !currentState;
        if (currentState) {setFill(Color.WHITE); }
        else {setFill(Color.GRAY);}
        out.gateConnectorUpdate(currentState);
    }
    public boolean getCurrentState(){return currentState;}
    public List<Connector> getOutputsList(){
        return out.getOutputs();
    }
    public GateConnector gateConnectorSetup(){
        out.setLayoutY(this.getLayoutY());
        out.setLayoutX(5);
        return out;
    }

}

