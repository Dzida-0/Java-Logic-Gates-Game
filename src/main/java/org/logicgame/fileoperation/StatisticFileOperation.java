package org.logicgame.fileoperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticFileOperation {
    private String databaseLink;

    public StatisticFileOperation() {

        this.databaseLink = "jdbc:sqlite:LogicGame.sqlite";
        checkDatabaseExist();
    }

    private void checkDatabaseExist() {
        try (Connection conn = DriverManager.getConnection(databaseLink)) {
                 conn.createStatement().execute("CREATE TABLE IF NOT EXISTS statistic (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "level TEXT NOT NULL, " +
                    "time INTEGER, " +
                    "gates INTEGER, " +
                    "score INTEGER, " +
                    "challenge BOOLEAN NOT NULL, " +
                    "mistakes INTEGER, " +
                    "inputNumb INTEGER NOT NULL, " +
                    "outputNumb INTEGER NOT NULL);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveNewGameData(String name, String level, int time, int gates, int score, boolean challenge,
                           int mistakes, int inputNumb, int outputNumb) {
        String data = String.format(
                "INSERT INTO statistic (name, level, time, gates, score, challenge, mistakes, inputNumb, outputN    umb) " +
                        "VALUES ('%s', '%s', %d, %d, %d, %b, %d, %d, %d);",
                name, level, time, gates, score, challenge, mistakes, inputNumb, outputNumb);
        try (Connection conn = DriverManager.getConnection(databaseLink)){
            conn.createStatement().execute(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<GameData> readData() {
        List<GameData> dataList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseLink);
             ResultSet nameSql = conn.createStatement().executeQuery("SELECT * FROM statistic;")) {

            while (nameSql.next()) {
                GameData data = new GameData(
                        nameSql.getInt("id"),
                        nameSql.getString("name"),
                        nameSql.getString("level"),
                        nameSql.getInt("time"),
                        nameSql.getInt("gates"),
                        nameSql.getInt("score"),
                        nameSql.getBoolean("challenge"),
                        nameSql.getInt("mistakes"),
                        nameSql.getInt("inputNumb"),
                        nameSql.getInt("outputNumb")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }

    public boolean isNameInDatabase(String name) {
        boolean isIn = false;
        try (Connection conn = DriverManager.getConnection(databaseLink);
             java.sql.PreparedStatement nameIn = conn.prepareStatement("SELECT name FROM game_data WHERE name = ?;")) {
            nameIn.setString(1, name);
            try (ResultSet rs = nameIn.executeQuery()) {
                isIn = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isIn;
    }
}

