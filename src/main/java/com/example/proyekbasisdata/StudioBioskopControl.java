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

public class StudioBioskopControl {
    @FXML
    protected Button save;
    @FXML
    TableView<StudioBioskopProperty> tblStudio;
    @FXML
    TableColumn<StudioBioskopProperty, String> tblkodeStudio;
    @FXML
    TableColumn<StudioBioskopProperty, String> tblIdBioskop;
    @FXML
    TableColumn<StudioBioskopProperty, Integer> tblJumlahKursi;
    @FXML
    TextField fieldKodeStudio;
    @FXML
    TextField fieldIdBioskop;
    @FXML
    TextField fieldJumlahKursi;
    @FXML
    ObservableList<StudioBioskopProperty> listStudio = FXCollections.observableArrayList();



    //TABEL LOKASI BIOSKOP
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
    protected Button updatebtn;
    @FXML
    protected Button deleteBtn;
    @FXML
    protected Label danger;
    @FXML
    protected Button back;

    public void initialize(){
        danger.setVisible(false);
        updatebtn.setVisible(false);
        deleteBtn.setVisible(false);
        try{
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "SELECT * FROM studio_bioskop";
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

        tblStudio.setItems(listStudio);
        tblkodeStudio.setCellValueFactory(new PropertyValueFactory<StudioBioskopProperty, String>("kodeStudio"));
        tblIdBioskop.setCellValueFactory(new PropertyValueFactory<StudioBioskopProperty, String>("idBioskop"));
        tblJumlahKursi.setCellValueFactory(new PropertyValueFactory<StudioBioskopProperty, Integer>("jumlahKursi"));



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
    private void getSelectedIdMovie() {
        int index = table_lokasiBioskop.getSelectionModel().getSelectedIndex();
        fieldIdBioskop.setText(kol_id.getCellData(index));
    }
    @FXML
    private void getSelected(){
        int index = tblStudio.getSelectionModel().getSelectedIndex();
        updatebtn.setVisible(true);
        deleteBtn.setVisible(true);
        fieldIdBioskop.setText(tblIdBioskop.getCellData(index));
        fieldKodeStudio.setText(tblkodeStudio.getCellData(index));
        fieldJumlahKursi.setText(Integer.toString(tblJumlahKursi.getCellData(index)));

    }
    @FXML
    private void onClrBtn(){
        fieldIdBioskop.setText("");
        fieldKodeStudio.setText("");
        fieldJumlahKursi.setText("");
    }
    @FXML
    private void insertData(){
        if(fieldIdBioskop.getText().equals("") || fieldKodeStudio.getText().equals("") || fieldJumlahKursi.getText().equals("")){
            danger.setVisible(true);
        }
        else{

            try {
                Connection con = HelloApplication.createDatabaseConnection();
//                String query = "INSERT INTO `jadwal`(`kode_jadwal`, `jam_mulai`) " +
//                        "VALUES" + "('" + fieldkodeJadwal.getText() + "','" + Integer.parseInt(fieldJamMulai.getText())+ "')";

                String query = "INSERT INTO studio_bioskop (kode_studio, id_bioskop, jumlah_kursi) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, fieldKodeStudio.getText());
                preparedStatement.setString(2, fieldIdBioskop.getText());
                preparedStatement.setInt(3,Integer.parseInt(fieldJumlahKursi.getText()));
                ;

                preparedStatement.executeUpdate();
                con.close();

                Window owner = save.getScene().getWindow();
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                        "data berhasil disave");

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        danger.setVisible(false);
        listStudio.add(new StudioBioskopProperty(fieldKodeStudio.getText(),fieldIdBioskop.getText(),Integer.parseInt(fieldJumlahKursi.getText())));
        tblStudio.setItems(listStudio);
        fieldIdBioskop.setText("");
        fieldKodeStudio.setText("");
        fieldJumlahKursi.setText("");
    }
    @FXML
    private void onDeleteBtn(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "DELETE FROM studio_bioskop WHERE kode_studio =" + "'"+fieldKodeStudio.getText()+"'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int index = tblStudio.getSelectionModel().getSelectedIndex();
        listStudio.remove(index);
        updatebtn.setVisible(false);
        deleteBtn.setVisible(false);

    }
    @FXML
    private void onUpdateBtn(){
        int index = tblStudio.getSelectionModel().getSelectedIndex();
        if (fieldKodeStudio.getText().equals("") || fieldJumlahKursi.getText().equals("") || fieldIdBioskop.getText().equals("")) {
            danger.setVisible(true);

        }
        else {
            try {
                Connection con = HelloApplication.createDatabaseConnection();
                String query = "UPDATE studio_bioskop SET jumlah_kursi=? WHERE kode_studio=?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,Integer.parseInt(fieldJumlahKursi.getText()));
                preparedStatement.setString(2,fieldKodeStudio.getText());
                preparedStatement.execute();
                con.close();

                //refresh tabel
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            listStudio.set(index, new StudioBioskopProperty(fieldKodeStudio.getText(),fieldIdBioskop.getText(),Integer.parseInt(fieldJumlahKursi.getText())));
            tblStudio.setItems(listStudio);
            fieldIdBioskop.setText("");
            fieldKodeStudio.setText("");
            fieldJumlahKursi.setText("");
            danger.setVisible(false);
            deleteBtn.setVisible(false);
            updatebtn.setVisible(false);
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
