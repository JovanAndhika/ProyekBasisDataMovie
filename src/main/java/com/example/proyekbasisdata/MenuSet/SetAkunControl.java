package com.example.proyekbasisdata.MenuSet;

import com.example.proyekbasisdata.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetAkunControl {
    //TEXTFIELD
    @FXML
    TextField setAkun;

    @FXML
    public Label akunnow;



    @FXML
    Button submit;
    @FXML
    Button back;



    @FXML
    protected void setAkun(){
        akunnow.setText(setAkun.getText());
    }

    @FXML
    protected void Back(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }


}
