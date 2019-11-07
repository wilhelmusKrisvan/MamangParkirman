/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.model;

import id.ac.ukdw.mamangparking.db.DBQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class Kendaraan {
    DBQuery db = new DBQuery();
    private String JenisKendaraan;
    private int HargaAwal;
    private int HargaPerjam;
    private int Kapasitas; 
    private int id;
    
    public void getDBKendaraan(ResultSet rs) throws SQLException{
        try{
            rs.next();
            this.id = rs.getInt(1);
            this.JenisKendaraan = rs.getString(2);
            this.HargaAwal = rs.getInt(3);
            this.HargaPerjam = rs.getInt(4);
            this.Kapasitas = rs.getInt(5);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rs.close();
        }
    }

    public boolean UpdateKapasitas(String jenis, int kap) throws SQLException{
        return db.UpdateKapasitas(jenis, kap);
    }

    public boolean UpdateHarga(String jenis, int awal, int perjam) throws SQLException{
        return db.UpdateKendaraan(jenis, awal, perjam);
    }
    
    public ObservableList<Kendaraan> setKendaraanList() throws SQLException, ClassNotFoundException{
        ResultSet rs = db.ResultKendaraan();
        ObservableList<Kendaraan> kendaraanList = FXCollections.observableArrayList();
        while(rs.next()){
            Kendaraan kd = new Kendaraan();
            kd.setId(rs.getInt(1));
            kd.setJenisKendaraan(rs.getString(2));
            kd.setHargaAwal(rs.getInt(3));
            kd.setHargaPerjam(rs.getInt(4));
            kd.setKapasitas(rs.getInt(5));
            kendaraanList.add(kd);
        }
        rs.close();
        return kendaraanList;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getJenisKendaraan() {
        return JenisKendaraan;
    }

    public int getHargaAwal() {
        return HargaAwal;
    }

    public int getHargaPerjam() {
        return HargaPerjam;
    }

    public int getKapasitas() {
        return Kapasitas;
    }
    
    public void setKapasitas(int Kapasitas) {
        this.Kapasitas = Kapasitas;
    }

    public void setJenisKendaraan(String JenisKendaraan) {
        this.JenisKendaraan = JenisKendaraan;
    }

    public void setHargaAwal(int HargaAwal) {
        this.HargaAwal = HargaAwal;
    }

    public void setHargaPerjam(int HargaPerjam) {
        this.HargaPerjam = HargaPerjam;
    }
   
    
    
}
