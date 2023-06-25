package com.example.proyekbasisdata;

import java.security.Timestamp;

public class TransaksiProperty {
    String idTransaksi;
    String idAkun;
    String namaKasir;
    String tglTransaksi;

    public TransaksiProperty() {
    }

    public TransaksiProperty(String idTransaksi, String idAkun, String namaKasir, String tglTransaksi) {
        this.idTransaksi = idTransaksi;
        this.idAkun = idAkun;
        this.namaKasir = namaKasir;
        this.tglTransaksi = tglTransaksi;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
    }

    public String getNamaKasir() {
        return namaKasir;
    }

    public void setNamaKasir(String namaKasir) {
        this.namaKasir = namaKasir;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }
}

