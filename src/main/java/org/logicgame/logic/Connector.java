package org.logicgame.logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Connector extends Line {
    private int id;
    private GateConnector gateConnector;
    private boolean on;
    public Connector(GateConnector gateConnector) {
        setStrokeWidth(4);
        setStroke(Color.BLACK);
        this.gateConnector = gateConnector;
    }
    public void connectorStateChange(boolean state){
        this.on = state;
        if(state){setStroke(Color.WHITE);}
        else {setStroke(Color.BLACK);}
        gateConnector.gateConnectorUpdate(state);

    }

    public void moveInput(double x, double y){
        setStartX(x);
        setStartY(y);
    }
    public void moveOutput(double x, double y){
        setEndX(x);
        setEndY(y);
    }


}
