package org.logicgame.logic;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Gate extends Rectangle {
    private int id;
    protected GateConnector in1;
    private GateConnector in2;
    protected GateConnector out;
    private int gateInNumb;
    private int width;
    private int heigth;
    private Pane gameArea;
    private Color gateColor;
    private double posY;
    public Gate(double posX,int gateInNumb,Pane gameArea){
        width = 100;
        heigth = 60;
        setLayoutX(posX);
        setLayoutY(0);
        setWidth(width);
        setHeight(heigth);
        this.gateInNumb = gateInNumb;
        this.gameArea = gameArea;
        out = new GateConnector(gameArea);
        out.move(posX+width, (double) heigth /2);
        gameArea.getChildren().add(this);
        gameArea.getChildren().add(this.out);
        setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                handleClick(event);
            }
        });
        setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                deleteGate();
            }
        });
    }

    public void updateGate(int inNr, boolean state){}
    private void handleClick(MouseEvent event) {
        setLayoutX(event.getScreenX()-100);
        if (getLayoutX() < 10){setLayoutX(10);}
        setLayoutY(event.getScreenY()-100);
        if (getLayoutY() < 10){setLayoutY(10);}
        out.move(getLayoutX()+width,getLayoutY()+ (double) heigth /2);
        in1.move(getLayoutX(),getLayoutY()+ posY);
        if (in2 != null) { in2.move(getLayoutX(),getLayoutY()+ (double) heigth /4*3);}
    }
    protected void setIn1(GateConnector in,boolean one){
        in1 = in;
        if (one) {
            posY = (double) heigth / 2;}
        else {
            posY = (double) heigth / 4;}
        in1.move(this.getLayoutX(), posY);
        gameArea.getChildren().add(in1);
    }
    protected void setIn2(GateConnector in){
        in2 = in;
        in2.move(this.getLayoutX(),(double) heigth /4*3);
        gameArea.getChildren().add(in2);
    }
    private void deleteGate() {
        out.removeGate();
        gameArea.getChildren().remove(out);
        in1.removeGate();
        gameArea.getChildren().remove(in1);
        if (in2 != null) {
            in2.removeGate();
            gameArea.getChildren().remove(in2);

        }
        gameArea.getChildren().remove(this);
    }

}
