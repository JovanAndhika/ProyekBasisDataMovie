package com.example.proyekbasisdata;

public class JadwalProperty {
    private String kodeJadwal;
    private int jamMulai;

    public JadwalProperty(String kodeJadwal, int jamMulai) {
        this.kodeJadwal = kodeJadwal;
        this.jamMulai = jamMulai;
    }

    public String getKodeJadwal() {
        return kodeJadwal;
    }

    public void setKodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
    }

    public int getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(int jamMulai) {
        this.jamMulai = jamMulai;
    }
}
