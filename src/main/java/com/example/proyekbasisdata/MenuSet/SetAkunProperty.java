package com.example.proyekbasisdata.MenuSet;

public class SetAkunProperty {
    private int idakun;
    private String nama;
    private String nohp;
    private String alamat;

    public SetAkunProperty(int idakun, String nama, String nohp, String alamat) {
        this.idakun = idakun;
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
    }

    public int getIdakun() {
        return idakun;
    }

    public String getNama() {
        return nama;
    }

    public String getNohp() {
        return nohp;
    }

    public String getAlamat() {
        return alamat;
    }
}
