package com.example.proyekbasisdata;

import com.example.proyekbasisdata.MenuSet.SetAkunControl;
import com.example.proyekbasisdata.MenuSet.SetLokasiControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.*;
import java.time.LocalDate;

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
    protected TextField fieldidmovie;
    @FXML
    protected TextField judulMovie;
    @FXML
    protected TextField hargaTicket;
    @FXML
    protected DatePicker tanggal;
    @FXML
    protected TextField nomerKursi;


    //TABEL BELI TIKET
    @FXML
    protected TableView<PurchaseTicketProperty> table_purchase_ticket;
    @FXML
    protected TableColumn<PurchaseTicketProperty, String> kolom_purchaseidmovie;
    @FXML
    protected TableColumn<PurchaseTicketProperty, String> kolom_purchasejudul;
    @FXML
    protected TableColumn<PurchaseTicketProperty, Integer> kolom_harga;
    @FXML
    protected TableColumn<PurchaseTicketProperty, String> kolom_nomorkursi;
    @FXML
    protected TableColumn<PurchaseTicketProperty, String> kolom_purchasetanggal;

    protected ObservableList<PurchaseTicketProperty> listPurchaseTicket = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        try {
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "select id_movie, judul, kode_jadwal from nama_movie";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0) // ada data
            {
                while (rs.next()) {
                    String id_movie = rs.getString(1);
                    String judul = rs.getString(2);
                    String kode_jadwal = rs.getString(3);

                    listNamaMovie.add(new NamaMovieProperty(id_movie, judul, kode_jadwal));

                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

        table_nama_movie.setItems(listNamaMovie);
        table_nama_movie.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("id_movie"));
        table_nama_movie.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("judul"));
        table_nama_movie.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("kode_jadwal"));
    }

    @FXML
    protected void getSelected() {
        int index = table_nama_movie.getSelectionModel().getSelectedIndex();
        judulMovie.setText(kolom_judul.getCellData(index));
        fieldidmovie.setText(kolom_idmovie.getCellData(index));
    }

    @FXML
    protected void addTicketkeTabel() {
        String idmoviediTicket = fieldidmovie.getText();
        String juduldiTicket = judulMovie.getText();
        String nomorKursi = nomerKursi.getText();
        int harga = Integer.parseInt(hargaTicket.getText());
        LocalDate tgldiTicket = tanggal.getValue();
        String hasilTgl = String.valueOf(tgldiTicket);

        listPurchaseTicket.add(new PurchaseTicketProperty(idmoviediTicket, juduldiTicket, nomorKursi, harga, hasilTgl));
        table_purchase_ticket.setItems(listPurchaseTicket);
        kolom_purchaseidmovie.setCellValueFactory(new PropertyValueFactory<PurchaseTicketProperty, String>("purchaseidmovie"));
        kolom_purchasejudul.setCellValueFactory(new PropertyValueFactory<PurchaseTicketProperty, String>("purchasejudul"));
        kolom_nomorkursi.setCellValueFactory(new PropertyValueFactory<PurchaseTicketProperty, String>("purchasenomorkursi"));
        kolom_harga.setCellValueFactory(new PropertyValueFactory<PurchaseTicketProperty, Integer>("purchaseharga"));
        kolom_purchasetanggal.setCellValueFactory(new PropertyValueFactory<PurchaseTicketProperty, String>("purchasetanggal"));
    }

    @FXML
    protected void deletePurchaseTicket() {
        int index = table_purchase_ticket.getSelectionModel().getSelectedIndex();
        listPurchaseTicket.remove(index);
    }


    @FXML
    protected void makeOrder() {
        try {
            String namaKasir = "Alan";
            String namaAkun = SetAkunControl.akunnow.getText();

            //insert transaksi
            Connection con = HelloApplication.createDatabaseConnection();
            String query = "INSERT INTO transaksi(id_akun,nama_kasir) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, namaAkun);
            ps.setString(2, namaKasir);
            ps.executeUpdate();
            con.close();


            //insert nota
            for(int i = 0; i < listPurchaseTicket.size(); i++){
                query = "INSERT INTO transaksi(id_transaksi,id_movie,kode_studio,harga,tanggal_tayang,nomor_kursi) VALUES ((SELECT MAX(id_transaksi) FROM transaksi),?,?,?,?,?)";
                ps = con.prepareStatement(query);
                ps.setString(2,listPurchaseTicket.get(i).getPurhaseidmovie());
                ps.setString(3, SetLokasiControl.labelidstudio.getText());
                ps.setInt(4, listPurchaseTicket.get(i).getPurchaseharga());
                ps.setString(5,listPurchaseTicket.get(i).getPurchasetanggal());
                ps.setString(6,listPurchaseTicket.get(i).getPurchasenomorkursi());
            }

            listPurchaseTicket.clear();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void Back(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_awal = app.getSceneAwal();
        primaryStage.setScene(scene_awal);
    }
}
