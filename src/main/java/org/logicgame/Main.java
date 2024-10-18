package org.logicgame;

import org.logicgame.fileoperation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        StatisticFileOperation dbManager = new StatisticFileOperation();
        dbManager.insertData("aaa","easy",45,3,165,false,1,3,2);


    }
}
