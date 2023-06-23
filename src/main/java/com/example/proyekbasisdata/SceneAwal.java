package com.example.proyekbasisdata;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SceneAwal {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void sceneAddLokasi(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene lokasiBios = app.getSceneLokasiBios();
        primaryStage.setScene(lokasiBios);

    }


    @FXML
    protected void sceneInputMovie(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene inputMovie = app.getSceneNamaMovie();
        primaryStage.setScene(inputMovie);
    }

    @FXML
    protected void scenePurchaseTicket(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene purchase_ticket = app.getScenePurchaseTicket();
        primaryStage.setScene(purchase_ticket);
    }
}