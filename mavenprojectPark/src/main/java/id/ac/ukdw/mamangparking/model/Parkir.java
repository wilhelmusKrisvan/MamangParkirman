/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.model;

import id.ac.ukdw.mamangparking.db.DBQuery;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Wilhelmus Krisvan
 */
public class Parkir {
    DBQuery db = new DBQuery();
    
    public String platNomor;
    public String jenisKendaraan;
    public String tanggalMasuk;
    public int hargaPerJam;
    public int hargaAwal;
    public String jamMasuk;
    
    

    public String getPlatNomor() {
        return platNomor;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public int getHargaAwal() {
        return hargaAwal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public void setHargaAwal(int hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }

    public void setHargaPerJam(int hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }
    
    public void InsertDBParkir() throws SQLException{
        db.InsertParkir(this.platNomor, this.jenisKendaraan, this.hargaAwal, this.hargaPerJam);
    }   
}
