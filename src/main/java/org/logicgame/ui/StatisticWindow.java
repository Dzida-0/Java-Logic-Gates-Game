package org.logicgame.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.logicgame.fileoperation.GameData;
import org.logicgame.fileoperation.StatisticFileOperation;

public class StatisticWindow {

    private Stage stage;
    private TableView<GameData> table;

    public StatisticWindow(Stage stage) {
        this.stage = stage;
        table = new TableView<>();
        createTable();
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> goBackToMenu());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(table, backButton);
        layout.getStyleClass().add("statistic-layout");
        Scene scene = new Scene(layout, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/StartMenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Logic Gates Game");
        stage.show();
    }

    private void createTable() {
        TableColumn<GameData, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<GameData, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<GameData, String> levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        TableColumn<GameData, Integer> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        TableColumn<GameData, Integer> gatesColumn = new TableColumn<>("Gates");
        gatesColumn.setCellValueFactory(new PropertyValueFactory<>("gates"));
        TableColumn<GameData, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        TableColumn<GameData, Boolean> challengeColumn = new TableColumn<>("Challenge");
        challengeColumn.setCellValueFactory(new PropertyValueFactory<>("challenge"));
        TableColumn<GameData, Integer> mistakesColumn = new TableColumn<>("Mistakes");
        mistakesColumn.setCellValueFactory(new PropertyValueFactory<>("mistakes"));
        table.getColumns().addAll(idColumn, nameColumn, levelColumn, timeColumn, gatesColumn, scoreColumn, challengeColumn, mistakesColumn);
        StatisticFileOperation statOp = new StatisticFileOperation();
        ObservableList<GameData> data = FXCollections.observableArrayList(statOp.readData());
        table.setItems(data);
    }

    private void goBackToMenu() {
        new StartWindow(stage);
    }
}
