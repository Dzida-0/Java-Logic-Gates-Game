package org.logicgame.logic;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Gate extends Rectangle {
    private int id;
    private GateConnector in1;
    private GateConnector in2;
    protected GateConnector out;
    private int gateInNumb;
    private int width;
    private int heigth;
    private Pane gameArea;
    private Color gateColor;
    public Gate(double posX,int gateInNumb,Pane gameArea){
        width = 100;
        heigth = 60;
        setLayoutX(posX);
        setLayoutY(200);
        setWidth(width);
        setHeight(heigth);
        this.gateInNumb = gateInNumb;
        this.gameArea = gameArea;
        out = new GateConnector(gameArea);
        out.move(posX+width,200+ (double) heigth /2);
        gameArea.getChildren().add(this);
        gameArea.getChildren().add(this.out);
        setOnMouseDragged(this::handleClick);
    }

    public void updateGate(int inNr, boolean state){}
    private void handleClick(MouseEvent event) {
        setLayoutX(event.getScreenX()-100);
        if (getLayoutX() < 10){setLayoutX(10);}
        setLayoutY(event.getScreenY()-100);
        if (getLayoutY() < 10){setLayoutY(10);}
        out.move(getLayoutX()+width,getLayoutY()+ (double) heigth /2);
        in1.move(getLayoutX(),getLayoutY()+ (double) heigth /2-50);
        if (in2 != null) { in2.move(getLayoutX(),getLayoutY()+ (double) heigth /2+50);}
    }
    protected void setIn1(GateConnector in){
        in1 = in;
        in1.move(this.getLayoutX(),50+ (double) heigth /2);
        gameArea.getChildren().add(in1);
    }
    protected void setIn2(GateConnector in){
        in2 = in;
        in2.move(this.getLayoutX(),150+ (double) heigth /2);
        gameArea.getChildren().add(in2);
    }



}
