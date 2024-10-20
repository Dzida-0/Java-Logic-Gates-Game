package org.logicgame.fileoperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameFileOperation {
    private String databaseLink;
    public GameFileOperation() {
        this.databaseLink = "jdbc:sqlite:LogicGame.sqlite";
        checkDatabaseExist();
    }

    private void checkDatabaseExist() {
        try (Connection conn = DriverManager.getConnection(databaseLink)){
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS gates (" +
                    "id INTEGER, " +
                    "gateName TEXT NOT NULL, " +
                    "in1Id INTEGER, " +
                    "in2Id INTEGER, " +
                    "outId INTEGER NOT NULL, " +
                    "posX REAL NOT NULL, " +
                    "posY REAL NOT NULL);");
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS connections (" +
                    "id INTEGER, " +
                    "conInId INTEGER NOT NULL, " +
                    "conOutId INTEGER NOT NULL);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveGates(int id, String gateName, int in1Id, int in2Id, int outId, double posX, double posY) {
        try (Connection conn = DriverManager.getConnection(databaseLink);
             PreparedStatement gatesSql = conn.prepareStatement("INSERT INTO gates (id, gateName, in1Id, in2Id, outId, posX, posY) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?);")) {

            gatesSql.setInt(1, id);
            gatesSql.setString(2, gateName);
            gatesSql.setInt(3, in1Id);
            gatesSql.setInt(4, in2Id);
            gatesSql.setInt(5, outId);
            gatesSql.setDouble(6, posX);
            gatesSql.setDouble(7, posY);

            gatesSql.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<GateData> loadGates() {
        List<GateData> gateList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseLink);
             ResultSet gatesSet = conn.createStatement().executeQuery("SELECT * FROM gates;")) {

            while (gatesSet.next()) {
                GateData gate = new GateData(
                        gatesSet.getInt("id"),
                        gatesSet.getString("gateName"),
                        gatesSet.getInt("in1Id"),
                        gatesSet.getInt("in2Id"),
                        gatesSet.getInt("outId"),
                        gatesSet.getDouble("posX"),
                        gatesSet.getDouble("posY")
                );
                gateList.add(gate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gateList;
    }

    public void saveConnection(int id, int conInId, int conOutId) {
        try (Connection conn = DriverManager.getConnection(databaseLink);
             PreparedStatement connectionSql = conn.prepareStatement("INSERT INTO connections (id, conInId, conOutId) " +
                     "VALUES (?, ?, ?);")) {
            connectionSql.setInt(1, id);
            connectionSql.setInt(2, conInId);
            connectionSql.setInt(3, conOutId);
            connectionSql.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ConnectionData> loadConnections() {
        List<ConnectionData> connectionList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseLink);
             ResultSet connectionSql = conn.createStatement().executeQuery("SELECT * FROM connections;")) {

            while (connectionSql.next()) {
                ConnectionData connection = new ConnectionData(
                        connectionSql.getInt("id"),
                        connectionSql.getInt("conInId"),
                        connectionSql.getInt("conOutId")
                );
                connectionList.add(connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connectionList;
    }
}
