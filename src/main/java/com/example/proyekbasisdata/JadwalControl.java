package com.example.proyekbasisdata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import static com.example.proyekbasisdata.NamaMovieControl.showAlert;

import java.sql.*;

public class JadwalControl {
    @FXML
    Label danger;
    @FXML
    protected TableView<JadwalProperty> tblJadwal;
    @FXML
    protected TableColumn<JadwalProperty, String> tblKodeJadwal;
    @FXML
    protected TableColumn<JadwalProperty, Integer> tblJamMulai;
    @FXML
    protected ObservableList<JadwalProperty> listjadwal = FXCollections.observableArrayList();
    @FXML
    protected TextField fieldkodeJadwal;
    @FXML
    protected TextField fieldJamMulai;

    @FXML
    protected String kodejadwal;
    @FXML
    protected Button updateBtn;
    @FXML
    protected Button deleteBtn;
    @FXML
    protected Button addBtn;

    @FXML
    public void initialize() {
        // set button invisible
        danger.setVisible(false);
        updateBtn.setVisible(false);
        deleteBtn.setVisible(false);
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "select * from jadwal";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) // ada data
            {
                while (rs.next()) {
                    String kode_jadwal = rs.getString(1);
                    int jamMulai = rs.getInt(2);

                    listjadwal.add(new JadwalProperty(kode_jadwal, jamMulai));
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

        tblJadwal.setItems(listjadwal);
        tblKodeJadwal.setCellValueFactory(new PropertyValueFactory<JadwalProperty, String>("kodeJadwal"));
        tblJamMulai.setCellValueFactory(new PropertyValueFactory<JadwalProperty, Integer>("jamMulai"));
    }

    @FXML
    private void onClrBtn() {
        fieldkodeJadwal.setText("");
        fieldJamMulai.setText("");

    }

    @FXML
    private void getSelected() {
        updateBtn.setVisible(true);
        deleteBtn.setVisible(true);
        int index = tblJadwal.getSelectionModel().getSelectedIndex();
        fieldkodeJadwal.setText(tblKodeJadwal.getCellData(index));
        fieldJamMulai.setText(Integer.toString(tblJamMulai.getCellData(index)));
    }

    @FXML
    private void onAddBtn() {
        if (fieldkodeJadwal.getText().equals("") || fieldJamMulai.getText().equals("")) {
            danger.setVisible(true);
        } else {
            try {
                Connection con = HelloApplication.createDatabaseConnection();
//                String query = "INSERT INTO `jadwal`(`kode_jadwal`, `jam_mulai`) " +
//                        "VALUES" + "('" + fieldkodeJadwal.getText() + "','" + Integer.parseInt(fieldJamMulai.getText())+ "')";

                String query = "INSERT INTO jadwal (kode_jadwal,jam_mulai) VALUES (?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, fieldkodeJadwal.getText());
                preparedStatement.setString(2, fieldJamMulai.getText());
                ;

                preparedStatement.executeUpdate();
                con.close();

                Window owner = addBtn.getScene().getWindow();
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                        "data berhasil disave");

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        listjadwal.add(new JadwalProperty(fieldkodeJadwal.getText(), Integer.parseInt(fieldJamMulai.getText())));
        tblJadwal.setItems(listjadwal);
        fieldkodeJadwal.setText("");
        fieldJamMulai.setText("");
        danger.setVisible(false);
    }


    @FXML
    private void onUpdateBtn() {
        if (fieldkodeJadwal.getText().equals("") || fieldJamMulai.getText().equals("")) {
            danger.setVisible(true);
        } else {
            try {
                Connection con = HelloApplication.createDatabaseConnection();
                String query = "UPDATE jadwal SET jam_mulai=? WHERE kode_jadwal=?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(fieldJamMulai.getText()));
                preparedStatement.setString(2,fieldJamMulai.getText());

                preparedStatement.execute();
                con.close();

                //refresh tabel
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            // update di javafx
            danger.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
            int index = tblJadwal.getSelectionModel().getSelectedIndex();
            listjadwal.set(index, new JadwalProperty(fieldkodeJadwal.getText(), Integer.parseInt(fieldJamMulai.getText())));
            fieldkodeJadwal.setText("");
            fieldJamMulai.setText("");
        }
    }

    @FXML
    private void onDeleteBtn() {
        int index = tblJadwal.getSelectionModel().getSelectedIndex();

        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "DELETE FROM jadwal WHERE kode_jadwal =" + "'" + fieldkodeJadwal.getText() + "'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // update di javafx
        listjadwal.remove(index);
    }

    @FXML
    protected void Back(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }
}
