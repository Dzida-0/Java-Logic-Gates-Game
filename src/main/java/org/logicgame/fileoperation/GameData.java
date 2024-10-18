package org.logicgame.fileoperation;

public class GameData {
    private int id;
    private String name;
    private String level;
    private int time;
    private int gates;
    private int score;
    private boolean challenge;
    private int mistakes;
    private int inputNumb;
    private int outputNumb;

    public GameData(int id, String name, String level, int time, int gates, int score, boolean challenge,
                    int mistakes, int inputNumb, int outputNumb) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.time = time;
        this.gates = gates;
        this.score = score;
        this.challenge = challenge;
        this.mistakes = mistakes;
        this.inputNumb = inputNumb;
        this.outputNumb = outputNumb;
    }

    public String toSave() {
        return "GameData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", time=" + time +
                ", gates=" + gates +
                ", score=" + score +
                ", challenge=" + challenge +
                ", mistakes=" + mistakes +
                ", inputNumb=" + inputNumb +
                ", outputNumb=" + outputNumb +
                '}';
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLevel() { return level; }
    public int getTime() { return time; }
    public int getGates() { return gates; }
    public int getScore() { return score; }
    public boolean getChallenge() { return challenge; }
    public int getMistakes() { return mistakes; }
    public int getInputNumb() { return inputNumb; }
    public int getOutputNumb() { return outputNumb; }



}
