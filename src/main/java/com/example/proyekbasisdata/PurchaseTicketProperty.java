package com.example.proyekbasisdata;

public class PurchaseTicketProperty {
    protected String purhaseidmovie;
    protected String purchasejudul;
    protected String purchasenomorkursi;
    protected int purchaseharga;
    protected String purchasetanggal;

    public PurchaseTicketProperty() {
    }

    public PurchaseTicketProperty(String purchaseidmovie, String purchasejudul, String purchasenomorkursi, int purchaseharga, String purchasetanggal) {
        this.purhaseidmovie = purchaseidmovie;
        this.purchasejudul = purchasejudul;
        this.purchasenomorkursi = purchasenomorkursi;
        this.purchaseharga = purchaseharga;
        this.purchasetanggal = purchasetanggal;
    }

    public String getPurhaseidmovie() {
        return purhaseidmovie;
    }

    public String getPurchasejudul() {
        return purchasejudul;
    }

    public String getPurchasenomorkursi() {
        return purchasenomorkursi;
    }

    public int getPurchaseharga() {
        return purchaseharga;
    }

    public String getPurchasetanggal() {
        return purchasetanggal;
    }
}
