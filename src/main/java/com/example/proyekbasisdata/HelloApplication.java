package com.example.proyekbasisdata;

import com.example.proyekbasisdata.MenuSet.SetAkunControl;
import com.example.proyekbasisdata.MenuSet.SetLokasiControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
    //DATABASE CONNECTION
    final static String driver = "com.mysql.cj.jdbc.Driver";
    final static String databaseName = "proyekbd";
    final static String url = "jdbc:mysql://localhost/" + databaseName;
    final static String user = "root";
    final static String password = "";


    public static Connection createDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }


    //Scene Awal
    private Stage primaryStage;
    private Scene sceneAwal;
    private SceneAwal sceneAwalControl;


    //Scene untuk daftar movie
    private Scene sceneLokasiBios;
    private LokasiBioskopControl sceneLokasiBiosControl;


    //Scene untuk lokasi bioskop
    private Scene sceneNamaMovie;
    private NamaMovieControl sceneNamaMovieControl;


    //Scene untuk purchase ticket
    private Scene scenePurchaseTicket;
    private PurchaseTicketControl scenePurchaseControl;


    //Scene untuk add Studio Bioskop
    private Scene sceneStudioBios;
    private StudioBioskopControl sceneStudioBiosControl;


    //Scene Set Lokasi
    private Scene sceneSetLokasi;
    private SetLokasiControl sceneSetLokasiControl;


    //Scene Set Akun
    private Scene sceneSetAkun;
    private SetAkunControl sceneSetAkunControl;

    //Scene Jadwal
    private Scene sceneJadwal;
    private JadwalControl sceneJadwalControl;

    //Scene Transaksi
    private Scene sceneTransaksi;
    private TransaksiControl sceneTransaksiControl;


    //Scene Lisensor
    private Scene sceneLisensor;
    private LisensorControl sceneLisensorControl;


    public HelloApplication(){
        applicationInstance = this;
    }

    private static HelloApplication applicationInstance;

    public static HelloApplication getApplicationInstance(){
        return applicationInstance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        sceneAwal =  new Scene(fxmlLoader.load(), 800, 600);
        sceneAwalControl = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LokasiBioskop.fxml"));
        sceneLokasiBios = new Scene(fxmlLoader.load(), 800, 600);
        sceneLokasiBiosControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NamaMovie.fxml"));
        sceneNamaMovie = new Scene(fxmlLoader.load(), 1000, 500);
        sceneNamaMovieControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PurchaseTicket.fxml"));
        scenePurchaseTicket = new Scene(fxmlLoader.load(), 800, 600);
        scenePurchaseControl=  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StudioBioskop.fxml"));
        sceneStudioBios = new Scene(fxmlLoader.load(), 800, 600);
        sceneStudioBiosControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SetLokasi.fxml"));
        sceneSetLokasi = new Scene(fxmlLoader.load(), 800, 600);
        sceneSetLokasiControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SetAkun.fxml"));
        sceneSetAkun = new Scene(fxmlLoader.load(), 800, 600);
        sceneSetAkunControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Jadwal.fxml"));
        sceneJadwal = new Scene(fxmlLoader.load(), 800, 600);
        sceneJadwalControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Transaksi.fxml"));
        sceneTransaksi = new Scene(fxmlLoader.load(), 800, 600);
        sceneTransaksiControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Lisensor.fxml"));
        sceneLisensor = new Scene(fxmlLoader.load(), 800, 600);
        sceneLisensorControl =  fxmlLoader.getController();

        stage.setTitle("Hello!");
        stage.setScene(sceneAwal);
        stage.show();
    }

    public Scene getSceneLisensor() {
        return sceneLisensor;
    }

    public LisensorControl getSceneLisensorControl() {
        return sceneLisensorControl;
    }

    public Scene getSceneTransaksi() {
        return sceneTransaksi;
    }
    public TransaksiControl getSceneTransaksiControl() {
        return sceneTransaksiControl;
    }

    public Scene getSceneJadwal() {
        return sceneJadwal;
    }
    public JadwalControl getSceneJadwalControl() {
        return sceneJadwalControl;
    }
    public Scene getSceneSetAkun() {
        return sceneSetAkun;
    }
    public SetAkunControl getSceneSetAkunControl() {
        return sceneSetAkunControl;
    }

    public Scene getSceneSetLokasi() {
        return sceneSetLokasi;
    }

    public SetLokasiControl getSceneSetLokasiControl() {
        return sceneSetLokasiControl;
    }

    public Scene getSceneStudioBios() {
        return sceneStudioBios;
    }

    public StudioBioskopControl getSceneStudioBiosControl() {
        return sceneStudioBiosControl;
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getSceneAwal() {
        return sceneAwal;
    }

    public SceneAwal getSceneAwalControl() {
        return sceneAwalControl;
    }

    public Scene getSceneLokasiBios() {
        return sceneLokasiBios;
    }

    public LokasiBioskopControl getSceneLokasiBiosControl() {
        return sceneLokasiBiosControl;
    }

    public Scene getSceneNamaMovie() {
        return sceneNamaMovie;
    }

    public NamaMovieControl getSceneNamaMovieControl() {
        return sceneNamaMovieControl;
    }

    public Scene getScenePurchaseTicket() {
        return scenePurchaseTicket;
    }

    public PurchaseTicketControl getScenePurchaseControl() {
        return scenePurchaseControl;
    }

}