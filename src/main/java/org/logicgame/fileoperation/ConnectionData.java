package org.logicgame.fileoperation;

public class ConnectionData {
    private int id;
    private int conInId;
    private int conOutId;
    public ConnectionData(int id, int conInId, int conOutId) {
        this.id = id;
        this.conInId = conInId;
        this.conOutId = conOutId;
    }
    public int getId() {return id;}
    public int getConInId() {return conInId;}
    public int getConOutId() {return conOutId;}
}