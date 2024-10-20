package org.logicgame.logic;

public class Connector {
    private int id;
    private GateConnector gateConnector;
    private boolean on;
    public Connector(GateConnector gateConnector) {
        this.gateConnector = gateConnector;
    }
    public void connectorStateChange(boolean state){
        this.on = state;
        gateConnector.gateConnectorUpdate(state);
    }


}
