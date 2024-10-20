package org.logicgame.fileoperation;

public class GateData {
    private int id;
    private String gateName;
    private int in1Id;
    private int in2Id;
    private int outId;
    private double posX;
    private double posY;

    public GateData(int id, String gateName, int in1Id, int in2Id, int outId, double posX, double posY) {
        this.id = id;
        this.gateName = gateName;
        this.in1Id = in1Id;
        this.in2Id = in2Id;
        this.outId = outId;
        this.posX = posX;
        this.posY = posY;
    }
    public int getId() {return id;}
    public String getGateName() {return gateName;}
    public int getIn1Id() {return in1Id;}
    public int getIn2Id() {return in2Id;}

    public int getOutId() {return outId;}
    public double getPosX() {return posX;}
    public double getPosY() {return posY;}
}
