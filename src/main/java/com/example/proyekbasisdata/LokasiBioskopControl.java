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

public class LokasiBioskopControl {
    @FXML
    TextField field_id;
    @FXML
    TextField field_tempat;
    @FXML
    TextField field_alamat;
    @FXML
    Button tombolSubmit;
    @FXML
    TableView<LokasiBioskopProperty> table_lokasiBioskop;
    @FXML
    TableColumn<LokasiBioskopProperty, String> kol_id;
    @FXML
    TableColumn<LokasiBioskopProperty, String> kol_tempat;
    @FXML
    TableColumn<LokasiBioskopProperty, String> kol_alamat;
    @FXML
    ObservableList<LokasiBioskopProperty> listLokasiBioskop = FXCollections.observableArrayList();


    @FXML
    protected void getSelected(){
        int index = table_lokasiBioskop.getSelectionModel().getSelectedIndex();
        field_id.setText(kol_id.getCellData(index));
        field_tempat.setText(kol_tempat.getCellData(index));
        field_alamat.setText(kol_alamat.getCellData(index));
    }
    @FXML
    public void initialize() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "select * from lokasi_bioskop";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) // ada data
            {
                while (rs.next()) {
                    String id_bioskop = rs.getString(1);
                    String tempat = rs.getString(2);
                    String alamat = rs.getString(3);

                    listLokasiBioskop.add(new LokasiBioskopProperty(id_bioskop, tempat, alamat));
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

        table_lokasiBioskop.setItems(listLokasiBioskop);
        table_lokasiBioskop.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("id_bioskop"));
        table_lokasiBioskop.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("tempat"));
        table_lokasiBioskop.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("alamat"));
    }


    @FXML
    protected void insertData() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();

            String query = "INSERT INTO lokasi_bioskop (id_bioskop, tempat, alamat) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, field_id.getText());
            preparedStatement.setString(2, field_tempat.getText());
            preparedStatement.setString(3, field_tempat.getText());

            preparedStatement.executeUpdate();
            con.close();

            Window owner = tombolSubmit.getScene().getWindow();
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                    "data berhasil disave");

            table_lokasiBioskop.getItems().clear();
            initialize();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void updateData(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "UPDATE lokasi_bioskop " +
                    "SET tempat="+"'"+field_tempat.getText()+"',"+
                    "alamat="+"'"+field_alamat.getText()+"'"+
                    "WHERE id_bioskop="+"'"+field_id.getText()+"'";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.execute();
            con.close();

            //refresh tabel
            table_lokasiBioskop.getItems().clear();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void deleteData(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "DELETE FROM lokasi_bioskop WHERE id_bioskop ="+"'"+field_id.getText()+"'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.close();

            table_lokasiBioskop.getItems().clear();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void backButton(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }
}
