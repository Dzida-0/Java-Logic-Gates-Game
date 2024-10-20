package org.logicgame.logic;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Circuit {
    private List<Gate> gates;
    private List<ProgramInput> inputs;
    private List<ProgramOutput> outputs;
    private List<Connector> connectors;
    private CircuitTest circuitTest;
    private int curentId;

    public Circuit(int numbInputs, int numbOutputs, Pane gameArea) {
        circuitTest = generateSolution(numbInputs, numbOutputs);
        curentId = 0;
        inputs = new ArrayList<>();
        for (int i = 0; i < numbInputs; i++){
            ProgramInput in = new ProgramInput(curentId,this,gameArea,circuitTest);
            circuitTest.addInput(in);
            inputs.add(in);
            curentId+=1;
        }
        outputs = new ArrayList<>();
        for (int i = 0; i < numbOutputs; i++){
            ProgramOutput out = new ProgramOutput(curentId,this,gameArea);
            circuitTest.addOutput(out);
            outputs.add(out);
            curentId+=1;
        }
    }
    public List<ProgramInput> getInputs() {return inputs;}
    public List<ProgramOutput> getOutputs() {return outputs;}
    private CircuitTest generateSolution(int numbInputs, int numbOutputs){
        List<List<List<Boolean>>> solution = new ArrayList<>();
        List<List<Boolean>> start = new ArrayList<>();
        List<Boolean> start0 = new ArrayList<>();
        List<Boolean> start1 = new ArrayList<>();
        start0.add(false);
        start1.add(true);
        start.add(start0);
        start.add(start1);
        for (List<Boolean> a: circuitSolutionBuildingHelper(start, numbInputs-1))
        {
            List<List<Boolean>> partSol = new ArrayList<>();
            partSol.add(a);
            List<Boolean> b = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < numbOutputs; i++)
            {
                b.add(random.nextBoolean());
            }
            partSol.add(b);
            solution.add(partSol);
        }
        return new CircuitTest(solution);
    }
    private List<List<Boolean>> circuitSolutionBuildingHelper(List<List<Boolean>> sol, int left){

        if (left > 0){
            List<List<Boolean>> ret = new ArrayList<>();
            for (List<Boolean> lis: sol){
                List<List<Boolean>> sum = new ArrayList<>();
                List<Boolean> a = new ArrayList<>();
                List<Boolean> b = new ArrayList<>();
                a.addAll(lis);
                b.addAll(lis);
                a.add(false);
                b.add(true);
                sum.add(a);
                sum.add(b);
                ret.addAll(circuitSolutionBuildingHelper(sum,left-1));
            }
            return ret;
        }
        return sol;
    }
    public boolean solutionTest(){return circuitTest.solutionTest();}

    public void outputsUpdate(){circuitTest.outputsUpdate();}

}
