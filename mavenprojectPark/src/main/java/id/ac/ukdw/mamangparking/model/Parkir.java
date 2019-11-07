/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.model;

import id.ac.ukdw.mamangparking.db.DBQuery;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Wilhelmus Krisvan
 */
public class Parkir {
    DBQuery db = new DBQuery();
    
    public SimpleIntegerProperty platNomor;
    public SimpleIntegerProperty jenisKendaraan;
    public int hargaAwal;
    public int jamMasuk;
    public SimpleIntegerProperty tanggalMasuk;

    public SimpleIntegerProperty getPlatNomor() {
        return platNomor;
    }

    public SimpleIntegerProperty getJenisKendaraan() {
        return jenisKendaraan;
    }

    public int getHargaAwal() {
        return hargaAwal;
    }

    public int getJamMasuk() {
        return jamMasuk;
    }

    public SimpleIntegerProperty getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setPlatNomor(SimpleIntegerProperty platNomor) {
        this.platNomor = platNomor;
    }

    public void seltJenisKendaraan(SimpleIntegerProperty jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public void setHargaAwal(int hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public void setJamMasuk(int jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public void setTanggalMasuk(SimpleIntegerProperty tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
    
    public void InsertDBKendaraan(){
        
    }
   
}
