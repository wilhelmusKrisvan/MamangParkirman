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
    private int id;


    
    public void getDBKendaraan(ResultSet rs) throws SQLException{
        try{
            rs.next();
            this.JenisKendaraan = rs.getString(1);
            this.HargaAwal = rs.getInt(2);
            this.HargaPerjam = rs.getInt(3);            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rs.close();
        }
    }

    public boolean UpdateHarga(String jenis, int awal, int perjam) throws SQLException{
        return db.UpdateKendaraan(this.JenisKendaraan, this.HargaAwal, this.HargaPerjam);
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
            kendaraanList.add(kd);
        }
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
