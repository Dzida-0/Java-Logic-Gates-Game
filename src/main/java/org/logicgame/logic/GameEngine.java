package org.logicgame.logic;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.logicgame.ui.YouWonWindow;

import javax.swing.plaf.metal.MetalBorders;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class GameEngine {
    private int inputNumber;
    private int outputNumber;
    private String name;
    private String level;
    private Pane gameArea;
    private Circuit circuit;
    private int mistakes;
    private Clock timer;
    public GameEngine(String name,String level){
        this.name = name;
        this.level = level;
        this.mistakes = 0;
        Random random = new Random();
        int randomNumb =  random.nextInt(2);
        if (level == "easy"){
            if (randomNumb == 1){inputNumber = 2; outputNumber = 3;}
            else {inputNumber = 3; outputNumber = 2;}
        }
        else if (level == "medium") {
            if (randomNumb == 1){inputNumber = 3; outputNumber = 4;}
            else {inputNumber = 4; outputNumber = 3;}
        }
        else{
            if (randomNumb == 1){inputNumber = 4; outputNumber = 5;}
            else {inputNumber = 5; outputNumber = 4;}
        }
    }
    public void addAND(double posX){
        new AND(posX,gameArea);
    }
    public void addOR(double posX){
        new OR(posX,gameArea);
    }
    public void addNOT(double posX){
        new NOT(posX,gameArea);
    }
    public void addNAND(double posX){
        new NAND(posX,gameArea);
    }
    public void addXOR(double posX){
        new XOR(posX,gameArea);
    }
    public void addNOR(double posX){
        new NOR(posX,gameArea);
    }
    public void addXNOR(double posX){
        new XNOR(posX,gameArea);
    }
    public int getInputNumb(){ return inputNumber;}
    public int getOutputNumb(){return outputNumber;}
    public List<ProgramInput> getInputs(){return circuit.getInputs();}
    public List<ProgramOutput> getOutputs(){return circuit.getOutputs();}
    public void createCircuit(Pane p){
        gameArea = p;
        circuit = new Circuit(inputNumber,outputNumber,gameArea);
    }
    public void addTimer(Clock t){timer = t;}
    public void testCircuit(){
        boolean test = circuit.solutionTest();
        if (test){
            timer.stop();
            timer.getResult();
            new YouWonWindow( new Stage(),timer.getResult(),timer.getResult());
        }
        else { timer.addPenalty();}
    }
    public void outputsUpdate(){circuit.outputsUpdate();}

}
