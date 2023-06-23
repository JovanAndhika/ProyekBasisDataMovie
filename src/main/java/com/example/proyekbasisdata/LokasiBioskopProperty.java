package com.example.proyekbasisdata;

public class LokasiBioskopProperty {
    private String id_bioskop;
    private String tempat;
    private String alamat;

    public LokasiBioskopProperty(String id_bioskop, String tempat, String alamat) {
        this.id_bioskop = id_bioskop;
        this.tempat = tempat;
        this.alamat = alamat;
    }

    public String getId_bioskop() {
        return id_bioskop;
    }

    public String getTempat() {
        return tempat;
    }

    public String getAlamat() {
        return alamat;
    }
}
