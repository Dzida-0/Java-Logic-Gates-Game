package org.logicgame.logic;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
    private List<Gate> gates;
    private List<ProgramInput> inputs;
    private List<ProgramOutput> outputs;
    private List<Connector> connectors;
    private int curentId;

    public Circuit(int numbInputs, int numbOutputs) {
        curentId = 0;
        inputs = new ArrayList<>();
        for (int i = 0; i < numbInputs; i++){
            inputs.add(new ProgramInput(curentId,this));
            curentId+=1;
        }
    }
    public void circuitLogicUpdate(){
        for (ProgramInput input:inputs) {
            for (Connector con: input.getOutputsList()) {
                con.connectorStateChange(input.getCurrentState());
            }
        }
    }

}
