package com.example.proyekbasisdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class SceneAwal {
    //TABEL
    @FXML
    protected TableView<SceneAwalProperty> tblTransaksi;
    @FXML
    protected TableColumn<SceneAwalProperty, Integer> kol_idakun;
    @FXML
    protected TableColumn<SceneAwalProperty, String> kol_namapemilik;
    @FXML
    protected TableColumn<SceneAwalProperty, Integer> kol_jumlahtransaksi;
    ObservableList<SceneAwalProperty> Main_Transaksi = FXCollections.observableArrayList();


    @FXML
    private Label pendapatan;


    @FXML
    public void initialize() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = """
                    SELECT au.id_akun, au.nama_pemilik, COUNT(*) AS jumlah_transaksi
                    FROM transaksi t
                    INNER JOIN akun_user au ON t.id_akun = au.id_akun
                    GROUP BY au.id_akun, au.nama_pemilik
                    ORDER BY jumlah_transaksi DESC
                    LIMIT 10;
                    """;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) {
                while (rs.next()) {
                    int main_idakun = rs.getInt(1);
                    String main_namapemilik = rs.getString(2);
                    int main_jumlahtransaksi = rs.getInt(3);
                    Main_Transaksi.add(new SceneAwalProperty(main_idakun,main_namapemilik,main_jumlahtransaksi));
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        tblTransaksi.setItems(Main_Transaksi);
        kol_idakun.setCellValueFactory(new PropertyValueFactory<SceneAwalProperty, Integer>("id_akun"));
        kol_namapemilik.setCellValueFactory(new PropertyValueFactory<SceneAwalProperty, String>("nama_pemilik"));
        kol_jumlahtransaksi.setCellValueFactory(new PropertyValueFactory<SceneAwalProperty,Integer>("jumlah_transaksi"));
    }

    @FXML
    protected void refreshButton(){
        int pendapatanTotal = 0;
        tblTransaksi.getItems().clear();
        initialize();
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = """
                    SELECT SUM(t.subtotal)
                    FROM transaksi t
                    """;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                pendapatanTotal = rs.getInt(1);
            }

            pendapatan.setText(String.valueOf(pendapatanTotal));
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
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
    @FXML
    protected void sceneAddStudio(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene studiobios = app.getSceneStudioBios();
        primaryStage.setScene(studiobios);
    }
    @FXML
    protected void sceneSetLokasi(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene setLokasi = app.getSceneSetLokasi();
        primaryStage.setScene(setLokasi);
    }
    @FXML
    protected void SceneSetAkun(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene setAkun = app.getSceneSetAkun();
        primaryStage.setScene(setAkun);
    }
    @FXML
    protected void sceneJadwal(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene jadwal = app.getSceneJadwal();
        primaryStage.setScene(jadwal);
    }
    @FXML
    protected void sceneTransaksi(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene transaksi = app.getSceneTransaksi();
        primaryStage.setScene(transaksi);
    }
    @FXML
    protected void sceneLisensor(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene lisensor = app.getSceneLisensor();
        primaryStage.setScene(lisensor);
    }
}