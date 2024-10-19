package org.logicgame.logic;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class GateConnector extends Circle {
    private boolean state;
    private int inNr;
    private Gate gate;
    private Connector input;
    private Line tempLine = null;
    private List<Connector> outputs;
    private Pane gameArea;

    public GateConnector(Pane gameArea){
        this.state = false;
        this.gameArea = gameArea;
        this.outputs = new ArrayList<>();
        setRadius(5);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
        setOnMousePressed(this::handleClick);
        setOnMouseDragged(this::handleMouseMoved);
        setOnMouseReleased(this::handleMouseDragExit);
    }
    public GateConnector(Gate gate,int inNr,Pane gameArea){
        this.state = false;
        this.gate = gate;
        this.inNr = inNr;
        this.gameArea = gameArea;
        setRadius(5);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
        setOnMousePressed(this::handleClick);
        setOnMouseDragged(this::handleMouseMoved);
        setOnMouseReleased(this::handleMouseDragExit);
    }
    public void gateConnectorUpdate(boolean state){
        this.state = state;
        if (state) {setFill(Color.WHITE);}
        else {setFill(Color.GRAY);}
        if (gate != null) {
            gate.updateGate(inNr,state);
        }
       else{
            for (Connector con: outputs) {
                con.connectorStateChange(state);
            }
        }
    }
    private void handleClick(MouseEvent event) {
        if (gate == null) {
            tempLine = new Line();
            tempLine.setStrokeWidth(4);
            tempLine.setStroke(Color.BLACK);
            tempLine.setStartX(this.getLayoutX());
            tempLine.setStartY(this.getLayoutY());
            tempLine.setEndX(this.getLayoutX());
            tempLine.setEndY(this.getLayoutY());
            ((Pane) this.getParent()).getChildren().add(tempLine);
        }
    }
    private void handleMouseMoved(MouseEvent e) {
        if (tempLine != null) {
            tempLine.setEndX(e.getX()+tempLine.getStartX());
            tempLine.setEndY(e.getY()+tempLine.getStartY());
        }
    }

    private void handleMouseDragExit(MouseEvent e){
        gameArea.getChildren().remove(tempLine);
        for (Node node : gameArea.getChildren()) {
            if (node instanceof GateConnector) {
                if (node.getBoundsInParent().contains(e.getX()+tempLine.getStartX(), e.getY()+tempLine.getStartY())) {
                    GateConnector gateConnector = (GateConnector) node;
                    if (gateConnector.isInput()){

                        Connector c = new Connector(gateConnector);
                        c.setStartY(this.getLayoutY());
                        c.setStartX(this.getLayoutX());
                        c.setEndX(e.getX()+tempLine.getStartX());
                        c.setEndY(e.getY()+tempLine.getStartY());
                        gateConnector.setInput(c);
                        outputs.add(c);
                        ((Pane) this.getParent()).getChildren().add(c);
                        break;
                    }
                }
            }
        }
        tempLine = null;

    }

    public List<Connector> getOutputs(){return outputs;}
    public void move(double posX,double posY){
        setLayoutY(posY);
        setLayoutX(posX);
        if (outputs != null) {
            for (Connector c : outputs) {
                c.moveInput(posX, posY);
            }
        }
        if (input != null){
            input.moveOutput(posX,posY);
        }
    }
    public boolean isInput(){return (gate != null);}

    public void setInput(Connector c){input = c;}

    public boolean getState() {return state;}

}
