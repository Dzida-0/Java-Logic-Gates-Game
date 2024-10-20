package org.logicgame.logic;

public class GateConnector {
    private boolean state;
    private int inNr;
    private Gate gate;

    public GateConnector(){
        this.state = false;
    }
    public GateConnector(Gate gate,int inNr){
        this.state = false;
        this.gate = gate;
        this.inNr = inNr;
    }
    public void gateConnectorUpdate(boolean state){
        this.state = state;
        if (gate != null) {
            gate.updateGate(inNr,state);
        }
    }

}
