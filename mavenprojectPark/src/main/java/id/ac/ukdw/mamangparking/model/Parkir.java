/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.model;

import id.ac.ukdw.mamangparking.db.DBQuery;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Wilhelmus Krisvan
 */
public class Parkir {
    DBQuery db = new DBQuery();
    
    public SimpleStringProperty platNomor;
    public SimpleStringProperty jenisKendaraan;
    public SimpleStringProperty tanggalMasuk;
    public int hargaPerJam;
    public int hargaAwal;
    public SimpleStringProperty jamMasuk;
    
    

    public String getPlatNomor() {
        return platNomor.get();
    }

    public String getJenisKendaraan() {
        return jenisKendaraan.get();
    }

    public int getHargaAwal() {
        return hargaAwal;
    }

    public String getJamMasuk() {
        return jamMasuk.get();
    }

    public String getTanggalMasuk() {
        return tanggalMasuk.get();
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = new SimpleStringProperty(platNomor);
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = new SimpleStringProperty(jenisKendaraan);
    }

    public void setHargaAwal(int hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = new SimpleStringProperty(jamMasuk);
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = new SimpleStringProperty(tanggalMasuk);
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }

    public void setHargaPerJam(int hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }
    
    public void InsertDBParkir() throws SQLException{
        db.InsertParkir(this.platNomor.get(), this.jenisKendaraan.get(), this.hargaAwal, this.hargaPerJam);
    }   
}
