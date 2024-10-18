package org.logicgame.fileoperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticFileOperation {
    private String dbUrl;

    public StatisticFileOperation() {

        this.dbUrl = "jdbc:sqlite:Statistic.sqlite";

        createDatabase();
    }

    private void createDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS game_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "level TEXT NOT NULL, " +
                "time INTEGER NOT NULL, " +
                "gates INTEGER NOT NULL, " +
                "score INTEGER NOT NULL, " +
                "challenge BOOLEAN NOT NULL, " +
                "mistakes INTEGER NOT NULL, " +
                "input_numb INTEGER NOT NULL, " +
                "output_numb INTEGER NOT NULL);";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // Tworzy tabelę
            stmt.execute(createTableSQL);
            System.out.println("Baza danych i tabela zostały utworzone.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertData(String name, String level, int time, int gates, int score, boolean challenge,
                           int mistakes, int inputNumb, int outputNumb) {
        String insertSQL = String.format(
                "INSERT INTO game_data (name, level, time, gates, score, challenge, mistakes, input_numb, output_numb) " +
                        "VALUES ('%s', '%s', %d, %d, %d, %b, %d, %d, %d);",
                name, level, time, gates, score, challenge, mistakes, inputNumb, outputNumb);

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute(insertSQL);
            System.out.println("Dane zostały zapisane: " + name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<GameData> readData() {
        List<GameData> dataList = new ArrayList<>();
        String selectSQL = "SELECT * FROM game_data;";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                GameData data = new GameData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("level"),
                        rs.getInt("time"),
                        rs.getInt("gates"),
                        rs.getInt("score"),
                        rs.getBoolean("challenge"),
                        rs.getInt("mistakes"),
                        rs.getInt("input_numb"),
                        rs.getInt("output_numb")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }


    public List<String> getAllNames() {
        List<String> nameList = new ArrayList<>();
        String selectSQL = "SELECT name FROM game_data;";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                nameList.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nameList;
    }
}

