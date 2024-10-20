package org.logicgame.logic;

import java.util.ArrayList;
import java.util.List;

public class CircuitTest {
    private List<ProgramOutput> outputs;
    private List<ProgramInput> inputs;
    private List<List<List<Boolean>>> solution;
    public CircuitTest(List<List<List<Boolean>>> solution){
        this.solution = solution;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void addOutput(ProgramOutput out){outputs.add(out);}
    public void addInput(ProgramInput in){inputs.add(in);}
    public void outputsUpdate(){
        List<Boolean> current = new ArrayList<>();
        for (ProgramInput pIn: inputs) {
            current.add(pIn.getCurrentState());
        }
        for (List<List<Boolean>> a: solution) {
                if(current.equals(a.get(0))){
                    for (int i = 0; i < outputs.size(); i++)
                    {
                        outputs.get(i).checkUpdate(a.get(1).get(i));
                    }
                    break;
            }

        }
    }
    public boolean solutionTest(){
        boolean pass = true;
        List<Boolean> current = new ArrayList<>();
        for (ProgramInput pIn: inputs) {
            current.add(pIn.getCurrentState());
        }
        System.out.println(solution);
        for (List<List<Boolean>> sol: solution) {
            List<Boolean> current_out = new ArrayList<>();
            for (int i = 0; i < inputs.size();i++){
              inputs.get(i).setState(sol.get(0).get(i));
            }
            for (ProgramOutput pOut:outputs){
                current_out.add(pOut.getState());
            }
            if(!current_out.equals(sol.get(1))){
                System.out.println(current_out);
                System.out.println(sol.get(1));
                System.out.println();
                pass = false;
                break;
            }
        }
        for (int i =0;i < inputs.size();i++) {
            inputs.get(i).setState(current.get(i));
        }
        return pass;
    }





}
