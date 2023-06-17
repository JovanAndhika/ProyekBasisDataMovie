package com.example.proyekbasisdata;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LokasiBioskop {

    @FXML
    protected void submitData() {
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene sceneAwal = app.getSceneAwal();
        primaryStage.setScene(sceneAwal);
    }
}
