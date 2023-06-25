package com.example.proyekbasisdata.MenuSet;

import com.example.proyekbasisdata.HelloApplication;
import com.example.proyekbasisdata.LokasiBioskopProperty;
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

public class SetAkunControl {
    //TEXTFIELD
    @FXML
    TextField setAkun;

    @FXML
    public Label akunnow;
    public static String akunHolder;

    @FXML
    protected Button submit;
    @FXML
    protected Button back;
    @FXML
    protected Button insert;


    //TEXTFIELD
    @FXML
    private TextField field_nama;
    @FXML
    private TextField field_nohp;
    @FXML
    private TextField field_alamat;


    //TABEL
    @FXML
    TableView <SetAkunProperty> table_viewakun;
    @FXML
    TableColumn<SetAkunProperty, Integer> kol_idakun;
    @FXML
    TableColumn<SetAkunProperty, String> kol_nama;
    @FXML
    TableColumn<SetAkunProperty,String> kol_nohp;
    @FXML
    TableColumn<SetAkunProperty, String> kol_alamat;
    ObservableList<SetAkunProperty> listtableviewakun = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "select * from akun_user";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) // ada data
            {
                while (rs.next()) {
                    int id_akun = rs.getInt(1);
                    String nama = rs.getString(2);
                    String no_telp = rs.getString(3);
                    String alamat = rs.getString(4);

                    listtableviewakun.add(new SetAkunProperty(id_akun, nama, no_telp, alamat));
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

        table_viewakun.setItems(listtableviewakun);
        table_viewakun.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("idakun"));
        table_viewakun.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("nama"));
        table_viewakun.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("nohp"));
        table_viewakun.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("alamat"));
    }


    @FXML
    protected void insertButton() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();

            String query = "INSERT INTO akun_user (nama_pemilik, no_telp, alamat) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, field_nama.getText());
            preparedStatement.setString(2, field_nohp.getText());
            preparedStatement.setString(3, field_alamat.getText());

            preparedStatement.executeUpdate();
            con.close();

            Window owner = insert.getScene().getWindow();
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                    "data berhasil disave");

            table_viewakun.getItems().clear();
            initialize();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    protected void updateButton(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "UPDATE akun_user SET nama_pemilik=?, no_telp=?, alamat=? WHERE id_akun=?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, field_nama.getText());
            preparedStatement.setString(2,field_nohp.getText());
            preparedStatement.setString(3,field_alamat.getText());
            preparedStatement.setInt(4, Integer.parseInt(setAkun.getText()));

            preparedStatement.execute();
            con.close();

            //refresh tabel
            table_viewakun.getItems().clear();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void deleteButton(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "DELETE FROM akun_user WHERE id_akun ="+"'"+Integer.parseInt(setAkun.getText())+"'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.close();

            table_viewakun.getItems().clear();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void setAkun(){
        akunnow.setText(setAkun.getText());
        akunHolder = akunnow.getText();
    }

    @FXML
    protected void Back(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }

    @FXML
    private void getSelected() {
        int index = table_viewakun.getSelectionModel().getSelectedIndex();
        setAkun.setText(String.valueOf(kol_idakun.getCellData(index)));
        field_nama.setText(kol_nama.getText());
        field_nohp.setText(kol_nohp.getText());
        field_alamat.setText(kol_alamat.getText());
    }


}
