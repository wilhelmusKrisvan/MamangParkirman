/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.model;

import id.ac.ukdw.mamangparking.db.DBQuery;
import java.sql.SQLException;

/**
 *
 * @author Wilhelmus Krisvan
 */
public class Laporan {

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    DBQuery db = new DBQuery();
    private int id;
    private String platNomor;
    private String jenisKendaraan;
    private String tanggalMasuk;
    private String tanggalKeluar;
    private int hargaPerJam;
    private int hargaAwal;
    private String jamMasuk;
    private String jamKeluar;
    private int total;

    public String getPlatNomor() {
        return platNomor;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }

    public int getHargaAwal() {
        return hargaAwal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public int getTotal() {
        return total;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public void setHargaPerJam(int hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }

    public void setHargaAwal(int hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public void InserDBLaporan(String Plat, String jns, int hargaAwl, String jam, String tgl, int hargaJam, String bdgjam, String bdgtgl, int total) throws SQLException{
        this.platNomor = Plat;
        this.jenisKendaraan = jns;
        this.hargaAwal = hargaAwl;
        this.hargaPerJam = hargaJam;
        this.tanggalMasuk = tgl;
        this.jamMasuk = jam;
        this.tanggalKeluar = bdgtgl;
        this.jamKeluar = bdgjam;
        this.total = total;
        db.InsertLaporan(this.getPlatNomor(), this.getJenisKendaraan(), this.getHargaAwal(), this.getJamMasuk(), this.getTanggalMasuk(), this.getHargaPerJam(), this.getJamKeluar(), this.getTanggalKeluar(), this.getTotal());
    }
}
