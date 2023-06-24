module com.example.proyekbasisdata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyekbasisdata to javafx.fxml;
    exports com.example.proyekbasisdata;
    opens com.example.proyekbasisdata.MenuSet to javafx.fxml;
    exports com.example.proyekbasisdata.MenuSet;

}