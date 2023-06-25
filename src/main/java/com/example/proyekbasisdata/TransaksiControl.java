package com.example.proyekbasisdata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class TransaksiControl {
    @FXML
    TableView<TransaksiProperty> tblTransaksi;
    @FXML
    TableColumn<TransaksiProperty, String> tblIdTransaksi;
    @FXML
    TableColumn<TransaksiProperty, String> tblIdAkun;
    @FXML
    TableColumn<TransaksiProperty, String> tblNamaKasir;
    @FXML
    TableColumn<TransaksiProperty, String> tblTglTransaksi;

    @FXML
    ObservableList<TransaksiProperty> listTransaksi = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "SELECT * FROM transaksi";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) {
                while (rs.next()) {
                    String idTransaksi = rs.getString(1);
                    String idAkun = rs.getString(2);
                    String namaKasir = rs.getString(3);
                    String tglTransaksi = rs.getString(4);
                    listTransaksi.add(new TransaksiProperty(idTransaksi, idAkun, namaKasir, tglTransaksi));
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        tblTransaksi.setItems(listTransaksi);
        tblIdTransaksi.setCellValueFactory(new PropertyValueFactory<TransaksiProperty, String>("idTransaksi"));
        tblIdAkun.setCellValueFactory(new PropertyValueFactory<TransaksiProperty, String>("idAkun"));
        tblNamaKasir.setCellValueFactory(new PropertyValueFactory<TransaksiProperty, String>("namaKasir"));
        tblTglTransaksi.setCellValueFactory(new PropertyValueFactory<TransaksiProperty, String>("tglTransaksi"));
    }

    @FXML
    protected void backButton(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }

    @FXML
    protected void refreshButton(){
        tblTransaksi.getItems().clear();
        initialize();
    }

}
