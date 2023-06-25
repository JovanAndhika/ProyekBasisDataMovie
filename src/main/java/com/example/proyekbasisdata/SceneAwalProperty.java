package com.example.proyekbasisdata;

public class SceneAwalProperty {
    private int id_akun;
    private String nama_pemilik;
    private int jumlah_transaksi;

    public SceneAwalProperty(int id_akun, String nama_pemilik, int jumlah_transaksi) {
        this.id_akun = id_akun;
        this.nama_pemilik = nama_pemilik;
        this.jumlah_transaksi = jumlah_transaksi;
    }

    public int getId_akun() {
        return id_akun;
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public int getJumlah_transaksi() {
        return jumlah_transaksi;
    }
}
