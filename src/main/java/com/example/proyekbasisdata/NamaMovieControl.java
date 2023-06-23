package com.example.proyekbasisdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;

public class NamaMovieControl {
    @FXML
    Button tombolBack;
    @FXML
    TextField fieldIdMovie;
    @FXML
    TextField fieldGenre;
    @FXML
    TextField fieldIdLisensor;
    @FXML
    TextField fieldKodeJadwal;
    @FXML
    TextField fieldJudul;
    @FXML
    TextField fieldDurasi;
    @FXML
    TextField fieldTahunProduksi;
    @FXML
    TextField fieldSutradara;
    @FXML
    TextField fieldDimensi;

    @FXML
    TableView<NamaMovieProperty> tbl_nama_movie;
    @FXML
    TableColumn<NamaMovieProperty, String> colIdMovie;
    @FXML
    TableColumn<NamaMovieProperty, String> colIdLisensor;
    @FXML
    TableColumn<NamaMovieProperty, String> colKodeJadwal;
    @FXML
    TableColumn<NamaMovieProperty, String> colJudul;
    @FXML
    TableColumn<NamaMovieProperty, Integer> colDurasi;
    @FXML
    TableColumn<NamaMovieProperty, String> colGenre;
    @FXML
    TableColumn<NamaMovieProperty, Integer> colTahunProduksi;
    @FXML
    TableColumn<NamaMovieProperty, String> colSutradara;
    @FXML
    TableColumn<NamaMovieProperty, String> colDimensi;
    @FXML
    Button clearBtn;
    @FXML
    Button saveBtn;
    @FXML
    ObservableList<NamaMovieProperty> listNamaMovieProperty = FXCollections.observableArrayList();


    @FXML
    protected void onClrBtn() {
        fieldIdMovie.setText("");
        fieldIdLisensor.setText("");
        fieldKodeJadwal.setText("");
        fieldJudul.setText("");
        fieldDurasi.setText("");
        fieldGenre.setText("");
        fieldTahunProduksi.setText("");
        fieldSutradara.setText("");
        fieldDimensi.setText("");
    }


    @FXML
    public void initialize() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "select * from nama_movie";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if(column_count > 0) // ada data
            {
                while (rs.next())
                {
                    String id_movie = rs.getString(1);
                    String id_lisensor = rs.getString(2);
                    String kode_jadwal = rs.getString(3);
                    String judul = rs.getString(4);
                    Integer durasi = rs.getInt(5);
                    String genre = rs.getString(6);
                    Integer tahun = rs.getInt(7);
                    String Sut = rs.getString(8);
                    String dimensi = rs.getString(9);

                    listNamaMovieProperty.add(new NamaMovieProperty(id_movie,id_lisensor,kode_jadwal,judul,
                            durasi,genre,tahun,Sut,dimensi));
                }
            }
            con.close();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        tbl_nama_movie.setItems(listNamaMovieProperty);
        tbl_nama_movie.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("id_movie"));
        tbl_nama_movie.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("id_lisensor"));
        tbl_nama_movie.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("kode_jadwal"));
        tbl_nama_movie.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("judul"));
        tbl_nama_movie.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("durasi"));
        tbl_nama_movie.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("genre"));
        tbl_nama_movie.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("tahun_produksi"));
        tbl_nama_movie.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("sut"));
        tbl_nama_movie.getColumns().get(8).setCellValueFactory(new PropertyValueFactory("dimensi"));
    }


    @FXML
    protected void deleteData(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "DELETE FROM nama_movie WHERE id_movie ="+"'"+fieldIdMovie.getText()+"'";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.close();

            tbl_nama_movie.getItems().clear();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void updateData(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "UPDATE nama_movie " +
                    "SET judul="+"'"+fieldJudul.getText()+"',"+
                    "kode_jadwal="+"'"+fieldKodeJadwal.getText()+"',"+
                    "id_lisensor="+"'"+fieldIdLisensor.getText()+"',"+
                    "durasi="+"'"+fieldDurasi.getText()+"',"+
                    "genre="+"'"+fieldGenre.getText()+"',"+
                    "tahun_produksi="+"'"+fieldTahunProduksi.getText()+"',"+
                    "sutradara="+"'"+fieldSutradara.getText()+"',"+
                    "dimensi="+"'"+fieldDimensi.getText()+"'"+
                    "WHERE id_movie="+"'"+fieldIdMovie.getText()+"'";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.execute();
            con.close();

            //refresh tabel
            tbl_nama_movie.getItems().clear();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void getSelected(){
        int index = tbl_nama_movie.getSelectionModel().getSelectedIndex();
        fieldIdMovie.setText(colIdMovie.getCellData(index));
        fieldIdLisensor.setText(colIdLisensor.getCellData(index));
        fieldKodeJadwal.setText(colKodeJadwal.getCellData(index));
        fieldJudul.setText(colJudul.getCellData(index));
        fieldDurasi.setText(Integer.toString(colDurasi.getCellData(index)));
        fieldGenre.setText(colGenre.getCellData(index));
        fieldTahunProduksi.setText(Integer.toString(colTahunProduksi.getCellData(index)));
        fieldSutradara.setText(colSutradara.getCellData(index));
        fieldDimensi.setText(colDimensi.getCellData(index));
    }


    @FXML
    protected void insertData(){
        try{
            Connection con = HelloApplication.createDatabaseConnection();
            //String query = "INSERT INTO `member`(`email`, `namadepan`, `namabelakang`, `nomorhp`, `alamat`, `password`) " +
            //        "VALUES" + "('" + email.getText() + "','" + namadepan.getText() + "','" + namabelakang.getText() + "','"
            //        + nomorhp.getText() + "','"+ alamat.getText()+"','"+password.getText()+"')";

            String query = "INSERT INTO nama_movie (id_movie, id_lisensor, kode_jadwal, judul, durasi, genre, tahun_produksi, sutradara, dimensi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, fieldIdMovie.getText());
            preparedStatement.setString(2, fieldIdLisensor.getText());
            preparedStatement.setString(3, fieldKodeJadwal.getText());
            preparedStatement.setString(4, fieldJudul.getText());
            preparedStatement.setString(5, fieldDurasi.getText());
            preparedStatement.setString(6, fieldGenre.getText());
            preparedStatement.setString(7, fieldTahunProduksi.getText());
            preparedStatement.setString(8, fieldSutradara.getText());
            preparedStatement.setString(9, fieldDimensi.getText());

            preparedStatement.executeUpdate();
            con.close();

            Window owner = saveBtn.getScene().getWindow();
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                    "data berhasil disave");

            tbl_nama_movie.getItems().clear();
            initialize();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showAlert(Alert.AlertType _alertType, Window _owner,
                                 String _title, String _message){
        Alert alert = new Alert(_alertType);
        alert.setTitle(_title);
        alert.setHeaderText(null);
        alert.setContentText(_message);
        alert.initOwner(_owner);
        alert.show();
    }

    public void backButton(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }
}
