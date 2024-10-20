package org.logicgame.logic;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Connector extends Line {
    private int id;
    private GateConnector gateConnectorOut;
    private GateConnector GateConnectorIn;
    private boolean on;
    public Connector(GateConnector getGateConnectorIn,GateConnector gateConnectorOut,boolean state) {
        setStrokeWidth(4);
        setStroke(Color.BLACK);
        this.gateConnectorOut = gateConnectorOut;
        this.GateConnectorIn = getGateConnectorIn;
        setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                deleteConnector();
            }
        });
        connectorStateChange(state);
    }
    public void connectorStateChange(boolean state){

        this.on = state;
        if(state){setStroke(Color.WHITE);}
        else {setStroke(Color.BLACK);}
        gateConnectorOut.gateConnectorUpdate(state);

    }

    public void moveInput(double x, double y){
        setStartX(x);
        setStartY(y);
    }
    public void moveOutput(double x, double y){
        setEndX(x);
        setEndY(y);
    }
    protected void deleteConnector(){
        gateConnectorOut.gateConnectorUpdate(false);
        gateConnectorOut.removeInput();
        GateConnectorIn.removeOutput(this);

    }


}
