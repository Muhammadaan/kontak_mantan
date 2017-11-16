package com.example.ade.kontak_mantan.entities;

/**
 * Created by Ade on 15/11/2017.
 */

public class Kontak {
    private int id;
    private String nama;
    private String nomor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public Kontak()
    {
        this.id = -1;
    }
}
