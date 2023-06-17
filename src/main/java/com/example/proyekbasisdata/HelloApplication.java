package com.example.proyekbasisdata;

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


    private Stage primaryStage;
    private Scene sceneAwal;
    private SceneAwal sceneAwalControl;


    //Scene untuk daftar member
    private Scene sceneLokasiBios;
    private LokasiBioskop sceneLokasiBiosControl;


    //Scene untuk daftar staf
    private Scene sceneNamaMovie;
    private NamaMovieControl sceneNamaMovieControl;


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
        sceneAwal =  new Scene(fxmlLoader.load(), 320, 240);
        sceneAwalControl = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LokasiBioskop.fxml"));
        sceneLokasiBios = new Scene(fxmlLoader.load(), 320, 240);
        sceneLokasiBiosControl =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NamaMovie.fxml"));
        sceneNamaMovie = new Scene(fxmlLoader.load(), 320, 240);
        sceneNamaMovieControl =  fxmlLoader.getController();

        stage.setTitle("Hello!");
        stage.setScene(sceneAwal);
        stage.show();
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

    public LokasiBioskop getSceneLokasiBiosControl() {
        return sceneLokasiBiosControl;
    }

    public Scene getSceneNamaMovie() {
        return sceneNamaMovie;
    }

    public NamaMovieControl getSceneNamaMovieControl() {
        return sceneNamaMovieControl;
    }
}