package com.example.proyekbasisdata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class JadwalControl {
    @FXML
    Label danger;
    @FXML
    TableView<JadwalProperty> tblJadwal;
    @FXML
    TableColumn<JadwalProperty, String> tblKodeJadwal;
    @FXML
    TableColumn<JadwalProperty, Integer> tblJamMulai;
    @FXML
    ObservableList<JadwalProperty> listjadwal = FXCollections.observableArrayList();
    @FXML
    TextField fieldkodeJadwal;
    @FXML
    TextField fieldJamMulai;

    @FXML
    String kodejadwal;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;

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
//
//                Window owner = saveBtn.getScene().getWindow();
//                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
//                        "data berhasil disave");

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

//        public static void showAlert(
//                Alert.AlertType _alertType, Window _owner,
//                String _title, String _message){
//            Alert alert = new Alert(_alertType);
//            alert.setTitle(_title);
//            alert.setHeaderText(null);
//            alert.setContentText(_message);
//            alert.initOwner(_owner);
//            alert.show();
//        }

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
                String query = "UPDATE jadwal " +
                        "SET kode_jadwal =" + "'" + fieldkodeJadwal.getText() + "'," +
                        "jam_mulai =" + Integer.parseInt(fieldJamMulai.getText()) + "," +
                        "WHERE kode_jadwal =" + "'" + fieldkodeJadwal.getText() + "'";

                PreparedStatement preparedStatement = con.prepareStatement(query);

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
}
