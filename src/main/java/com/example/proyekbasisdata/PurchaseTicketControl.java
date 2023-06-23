package com.example.proyekbasisdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class PurchaseTicketControl {
    //TABEL MOVIE
    @FXML
    protected TableView<NamaMovieProperty> table_nama_movie;
    @FXML
    protected TableColumn<NamaMovieProperty, String> kolom_idmovie;
    @FXML
    protected TableColumn<NamaMovieProperty, String> kolom_judul;
    @FXML
    protected TableColumn<NamaMovieProperty, String> kolom_kodejadwal;
    protected ObservableList<NamaMovieProperty> listNamaMovie = FXCollections.observableArrayList();


    //TEXTFIELD TIKET
    @FXML
    protected TextField judulMovie;
    @FXML
    protected TextField hargaTicket;
    @FXML
    protected DatePicker tanggal;
    @FXML
    protected TextField nomerKursi;



    //TABEL TIKET
    @FXML
    protected TableView<PurchaseTicketProperty> table_purchase_ticket;
    @FXML
    protected TableColumn<PurchaseTicketProperty, Integer> kolom_kodeticket;
    @FXML
    protected TableColumn<PurchaseTicketProperty, String> kolom_purchasejudul;
    @FXML
    protected TableColumn<PurchaseTicketProperty, String> kolom_kodeJadwal;




    @FXML
    public void initialize(){
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "select id_movie, judul, kode_jadwal from nama_movie";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if(column_count > 0) // ada data
            {
                while (rs.next())
                {
                    String id_movie = rs.getString(1);
                    String judul = rs.getString(2);
                    String kode_jadwal = rs.getString(3);

                    listNamaMovie.add(new NamaMovieProperty(id_movie,judul,kode_jadwal));

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

        table_nama_movie.setItems(listNamaMovie);
        table_nama_movie.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("id_movie"));
        table_nama_movie.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("judul"));
        table_nama_movie.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("kode_jadwal"));
    }

    @FXML
    protected void getSelected(){
        int index = table_nama_movie.getSelectionModel().getSelectedIndex();
        judulMovie.setText(kolom_judul.getCellData(index));
    }

    @FXML
    protected void addTicket(){

    }

}
