package com.example.proyekbasisdata;

public class LisensorProperty {

    String idLisensor;
    String namaLisensor;

    public LisensorProperty(String idLisensor, String namaLisensor) {
        this.idLisensor = idLisensor;
        this.namaLisensor = namaLisensor;
    }

    public String getIdLisensor() {
        return idLisensor;
    }

    public void setIdLisensor(String idLisensor) {
        this.idLisensor = idLisensor;
    }

    public String getNamaLisensor() {
        return namaLisensor;
    }

    public void setNamaLisensor(String namaLisensor) {
        this.namaLisensor = namaLisensor;
    }

}
