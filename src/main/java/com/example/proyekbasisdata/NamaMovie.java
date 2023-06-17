package com.example.proyekbasisdata;

public class NamaMovie {
    private String id_movie;
    private String id_lisensor;
    private String kode_jadwal;
    private String judul;
    private Integer durasi;
    private String genre;
    private Integer tahun_produksi;
    private String Sut;
    private String dimensi;

    public NamaMovie(String id_movie, String id_lisensor, String kode_jadwal, String judul, Integer durasi, String genre, Integer tahun_produksi, String Sut, String dimensi) {
        this.id_movie = id_movie;
        this.id_lisensor = id_lisensor;
        this.kode_jadwal = kode_jadwal;
        this.judul = judul;
        this.durasi = durasi;
        this.genre = genre;
        this.tahun_produksi = tahun_produksi;
        this.Sut = Sut;
        this.dimensi = dimensi;
    }

    public String getId_movie() {
        return id_movie;
    }

    public String getId_lisensor() {
        return id_lisensor;
    }

    public String getKode_jadwal() {
        return kode_jadwal;
    }

    public String getJudul() {
        return judul;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getTahun_produksi() {
        return tahun_produksi;
    }

    public String getSut() {
        return Sut;
    }

    public String getDimensi() {
        return dimensi;
    }
}
