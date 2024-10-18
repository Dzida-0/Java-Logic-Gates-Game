package org.logicgame.logic;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;

public class ProgramOutput extends Circle {
    private boolean curentState = false;
    private int id;
    public ProgramOutput(int id) {
        this.id = id;
        setRadius(20);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(3);
        setOnMouseClicked(this::handleClick);
    }

    private void handleClick(MouseEvent event) {
        curentState = !curentState;
        if (curentState) {setFill(Color.WHITE); }
        else {setFill(Color.GRAY);}
    }

}

