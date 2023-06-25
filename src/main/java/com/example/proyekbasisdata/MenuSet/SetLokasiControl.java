package com.example.proyekbasisdata.MenuSet;


import com.example.proyekbasisdata.HelloApplication;
import com.example.proyekbasisdata.LokasiBioskopProperty;
import com.example.proyekbasisdata.StudioBioskopProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetLokasiControl {
    //TEXTFIELD
    @FXML
    TextField field_idbioskop;
    @FXML
    TextField field_idstudio;
    @FXML
    public Label labelidbioskop;
    @FXML
    public Label labelidstudio;

    public static String idBioskopHolder;
    public static String idStudioHolder;

    //TABEL LOKASI
    @FXML
    TableView<LokasiBioskopProperty> table_setlokasiBioskop;
    @FXML
    TableColumn<LokasiBioskopProperty, String> kol_setid;
    @FXML
    TableColumn<LokasiBioskopProperty, String> kol_settempat;
    @FXML
    TableColumn<LokasiBioskopProperty, String> kol_setalamat;
    @FXML
    ObservableList<LokasiBioskopProperty> listLokasiBioskop = FXCollections.observableArrayList();


    //TABEL STUDIO
    @FXML
    TableView<StudioBioskopProperty> tblsetStudio;
    @FXML
    TableColumn<StudioBioskopProperty, String> tblkodeStudio;
    @FXML
    TableColumn<StudioBioskopProperty, String> tblIdBioskop;
    @FXML
    TableColumn<StudioBioskopProperty, Integer> tblJumlahKursi;
    @FXML
    ObservableList<StudioBioskopProperty> listStudio = FXCollections.observableArrayList();


@FXML
    public void initialize(){
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

        table_setlokasiBioskop.setItems(listLokasiBioskop);
        kol_setid.setCellValueFactory(new PropertyValueFactory<LokasiBioskopProperty, String>("id_bioskop"));
       kol_settempat.setCellValueFactory(new PropertyValueFactory<LokasiBioskopProperty, String>("tempat"));
        kol_setalamat.setCellValueFactory(new PropertyValueFactory<LokasiBioskopProperty, String>("alamat"));
    }

    public void ConfirmButton() {
        labelidbioskop.setText(field_idbioskop.getText());
        labelidstudio.setText(field_idstudio.getText());
        idBioskopHolder = labelidbioskop.getText();
        idStudioHolder = labelidstudio.getText();
    }

    @FXML
    protected void getSelected() {
        int index = table_setlokasiBioskop.getSelectionModel().getSelectedIndex();
        field_idbioskop.setText(kol_setid.getCellData(index));
    }

    @FXML
    protected void getSelectedStudio(){
        int index = tblsetStudio.getSelectionModel().getSelectedIndex();
        field_idstudio.setText(tblkodeStudio.getCellData(index));
    }


    @FXML
    protected void refresh(){
    listStudio.clear();
        try{
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "SELECT * FROM studio_bioskop WHERE id_bioskop="+"'"+field_idbioskop.getText()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count >0){
                while (rs.next()){
                    String kodeStudio = rs.getString(1);
                    String idBioskop = rs.getString(2);
                    int jumlahKursi = rs.getInt(3);
                    listStudio.add(new StudioBioskopProperty(kodeStudio,idBioskop,jumlahKursi));
                }
            }
            con.close();
        }catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

        tblsetStudio.setItems(listStudio);
        tblkodeStudio.setCellValueFactory(new PropertyValueFactory<StudioBioskopProperty, String>("kodeStudio"));
        tblIdBioskop.setCellValueFactory(new PropertyValueFactory<StudioBioskopProperty, String>("idBioskop"));
        tblJumlahKursi.setCellValueFactory(new PropertyValueFactory<StudioBioskopProperty, Integer>("jumlahKursi"));
    }

    @FXML
    protected void Back(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }


}
