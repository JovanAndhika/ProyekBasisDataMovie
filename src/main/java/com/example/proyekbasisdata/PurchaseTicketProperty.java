package com.example.proyekbasisdata;

import java.sql.Date;
import java.time.LocalDate;

public class PurchaseTicketProperty {
    protected String purchaseidmovie;
    protected String purchasejudul;
    protected String purchasenomorkursi;
    protected int purchaseharga;
    protected LocalDate purchasetanggal;

    public PurchaseTicketProperty() {
    }

    public PurchaseTicketProperty(String purchaseidmovie, String purchasejudul, String purchasenomorkursi, int purchaseharga, LocalDate purchasetanggal) {
        this.purchaseidmovie = purchaseidmovie;
        this.purchasejudul = purchasejudul;
        this.purchasenomorkursi = purchasenomorkursi;
        this.purchaseharga = purchaseharga;
        this.purchasetanggal = purchasetanggal;
    }


    public String getPurchaseidmovie() {
        return purchaseidmovie;
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

    public LocalDate getPurchasetanggal() {
        return purchasetanggal;
    }
}
