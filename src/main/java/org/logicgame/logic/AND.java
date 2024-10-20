package org.logicgame.logic;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AND extends Gate{
    public boolean in1 = false;
    public boolean in2 = false;
    public AND(double posX, Pane gameArea){
        super(posX,2,gameArea);
        setFill(Color.RED);
        this.setIn1(new GateConnector(this,1,gameArea),false);
        this.setIn2(new GateConnector(this,2,gameArea));
    }
    @Override
    public void updateGate(int inNr, boolean state){
        if (inNr == 1){
            in1 = state;
        }
        else if (inNr == 2) {
            in2 = state;
        }
        out.gateConnectorUpdate(in1&&in2);
    }
}
