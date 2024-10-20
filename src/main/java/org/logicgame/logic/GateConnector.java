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
        setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                handleClick(event);
            }
        });
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
    private void handleMouseMoved(MouseEvent event) {
        if (tempLine != null) {
            tempLine.setEndX(event.getX()+tempLine.getStartX());
            tempLine.setEndY(event.getY()+tempLine.getStartY());
        }
    }

    private void handleMouseDragExit(MouseEvent event){
        gameArea.getChildren().remove(tempLine);
        for (Node node : gameArea.getChildren()) {
            if (node instanceof GateConnector) {
                if (event != null) {
                    if (node.getBoundsInParent().contains(event.getX() + tempLine.getStartX(), event.getY() + tempLine.getStartY())) {
                        GateConnector gateConnector = (GateConnector) node;
                        if (gateConnector.isInput()) {
                            if (gateConnector.input == null) {
                                Connector c = new Connector(this, gateConnector, state);
                                c.setStartY(this.getLayoutY());
                                c.setStartX(this.getLayoutX());
                                c.setEndX(event.getX() + tempLine.getStartX());
                                c.setEndY(event.getY() + tempLine.getStartY());
                                gateConnector.setInput(c);
                                outputs.add(c);
                                ((Pane) this.getParent()).getChildren().add(c);
                                break;
                            }
                        }
                    }
                }
            }
        }
        tempLine = null;

    }

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

    public void removeInput() {input = null;}
    public void removeOutput(Connector c) {

        ((Pane) this.getParent()).getChildren().remove(c);
       outputs.remove(c);
    }
    public void removeGate(){
        if (outputs != null) {
            for (int i = 0; i <outputs.size();i++) {
                outputs.get(0).deleteConnector();
            }
        }
        else if (input != null) {
            input.deleteConnector();
        }
    }


}
