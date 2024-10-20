package org.logicgame.logic;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class NOT extends Gate{
    private boolean in1 = false;
    public NOT(double posX,Pane gameArea){
        super(posX,1, gameArea);
        setFill(Color.GREEN);
        this.setIn1(new GateConnector(this,1,gameArea),true);
        updateGate(1,false);
    }
    @Override
    public void updateGate(int inNr, boolean state){
        if (inNr == 1){
            in1 = state;
        }
        out.gateConnectorUpdate(!in1);
    }
}
