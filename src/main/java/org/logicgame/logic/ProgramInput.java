package org.logicgame.logic;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class ProgramInput extends Circle {
    private boolean curentState = false;
    private int id;
    private List<Connector> outputs;
    private Circuit circuit;
    private GateConnector out;
    public ProgramInput(int id, Circuit circuit) {
        this.id = id;
        this.circuit = circuit;
        this.outputs = new ArrayList<>();
        this.out = new GateConnector();
        setRadius(20);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
        setOnMouseClicked(this::handleClick);
    }

    // Handle click event
    private void handleClick(MouseEvent event) {
        curentState = !curentState;
        if (curentState) {setFill(Color.WHITE); }
        else {setFill(Color.GRAY);}
        circuit.circuitLogicUpdate();
    }
    public boolean getCurrentState(){return curentState;}
    public List<Connector> getOutputsList(){
        return outputs;
    }

}