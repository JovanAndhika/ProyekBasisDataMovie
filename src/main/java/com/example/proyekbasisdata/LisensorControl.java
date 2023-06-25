package com.example.proyekbasisdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.*;

import static com.example.proyekbasisdata.NamaMovieControl.showAlert;

public class LisensorControl {

    @FXML
    protected TableView<LisensorProperty> tabel_Lisensor;
    @FXML
    protected TableColumn<LisensorProperty, String> kol_idlisensor;
    @FXML
    protected TableColumn<LisensorProperty, String> kol_namalisensor;
    @FXML
    protected TextField fieldIdlisensor;
    @FXML
    protected TextField fieldnamaLisensor;
    @FXML
    protected ObservableList<LisensorProperty> listLisensor = FXCollections.observableArrayList();


    //LABELS
    @FXML
    Label danger;
    @FXML
    Button submit;


    @FXML
    public void initialize() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "SELECT * FROM nama_lisensor";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) {
                while (rs.next()) {
                    String idLisensor = rs.getString(1);
                    String namaLisensor = rs.getString(2);

                    listLisensor.add(new LisensorProperty(idLisensor, namaLisensor));
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        tabel_Lisensor.setItems(listLisensor);
        kol_idlisensor.setCellValueFactory(new PropertyValueFactory<LisensorProperty, String>("idLisensor"));
        kol_namalisensor.setCellValueFactory(new PropertyValueFactory<LisensorProperty, String>("namaLisensor"));

    }


    @FXML
    private void onAddBtn() {
        if (fieldnamaLisensor.getText().equals("") || fieldIdlisensor.getText().equals("")) {
            danger.setVisible(true);
        } else {
            try {
                Connection con = HelloApplication.createDatabaseConnection();
                String query = "INSERT INTO nama_lisensor (id_lisensor,nama_lisensor) VALUES (?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, fieldIdlisensor.getText());
                preparedStatement.setString(2, fieldnamaLisensor.getText());
                ;

                preparedStatement.executeUpdate();
                con.close();

                Window owner = submit.getScene().getWindow();
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                        "data berhasil disave");

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        listLisensor.add(new LisensorProperty(fieldIdlisensor.getText(), fieldnamaLisensor.getText()));
        tabel_Lisensor.setItems(listLisensor);
        fieldIdlisensor.setText("");
        fieldnamaLisensor.setText("");
    }

    @FXML
    private void onUpdateBtn() {
        int index = tabel_Lisensor.getSelectionModel().getSelectedIndex();
        if (fieldIdlisensor.getText().equals("") || fieldnamaLisensor.getText().equals("")) {
            danger.setVisible(true);

        } else {
            try {
                Connection con = HelloApplication.createDatabaseConnection();
                String query = "UPDATE nama_lisensor SET nama_lisensor=? WHERE id_lisensor=?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, fieldIdlisensor.getText());
                preparedStatement.setString(2, fieldnamaLisensor.getText());
                preparedStatement.execute();
                con.close();

                //refresh tabel
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            listLisensor.set(index, new LisensorProperty(fieldIdlisensor.getText(), fieldnamaLisensor.getText()));
            tabel_Lisensor.setItems(listLisensor);
            fieldnamaLisensor.setText("");
            fieldIdlisensor.setText("");
        }
    }

    @FXML
    private void onDeleteBtn() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "DELETE FROM nama_lisensor WHERE id_lisensor =" + "'" + fieldIdlisensor.getText() + "'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int index = tabel_Lisensor.getSelectionModel().getSelectedIndex();
        listLisensor.remove(index);
    }

    @FXML
    protected void onClrBtn() {
        fieldIdlisensor.setText("");
        fieldnamaLisensor.setText("");
    }

    @FXML
    protected void getSelected(){
        int index = tabel_Lisensor.getSelectionModel().getSelectedIndex();
        fieldIdlisensor.setText(kol_idlisensor.getCellData(index));
        fieldnamaLisensor.setText(kol_namalisensor.getCellData(index));
    }

    @FXML
    protected void tombolBack() {
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }

}


