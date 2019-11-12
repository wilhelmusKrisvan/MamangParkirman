/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilhelmus Krisvan
 */
public class DBQuery extends DBConnect{
    
    public DBQuery(){
        this.connect();
    }
    @Override
    public void connect() {
        try{
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:sqlite:PARKIR.db");
            st = con.createStatement();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void queryUpdate(String query) throws SQLException{
        try {
            ps=con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rs.close();
            ps.close();
        }
    }
    
    public ResultSet queryResult(String query) throws SQLException{
        try{
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }catch(SQLException E){
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        return rs;
    }
    
    public ResultSet logquery(String User, String pass) throws SQLException{
        try{
            String query = "SELECT * from Karyawan WHERE Username=? AND Password=?";
            ps=con.prepareStatement(query);
            ps.setString(1, User);
            ps.setString(2, pass);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
        return rs;
    }
    
    public ResultSet Profilequery(String NIK) throws SQLException{
        try{
            String query = "SELECT * from Karyawan WHERE NIK=?";
            ps=con.prepareStatement(query);
            ps.setString(1, NIK);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
        return rs;
    }
    
    public void UpdateProfile(String User, String pass, String notelp, String alamat, String NamaLeng, String tgl, String gender, int nik) throws SQLException{
        try{
              String queryAdmin = "UPDATE `Karyawan` SET `Username`=?,`Password`=?,`Telepon`=?,`Alamat`=?,`Nama`=?,`Tanggal Lahir`=?, `Jenis Kelamin`=? WHERE `NIK`="+nik+"";
                ps=con.prepareStatement(queryAdmin);
                ps.setString(1, User);
                ps.setString(2, pass);
                ps.setString(3, notelp);
                ps.setString(4, alamat);
                ps.setString(5, NamaLeng);
                ps.setString(6, tgl);
                ps.setString(7, gender);
                boolean x =ps.execute();
                if(x==true){
                    JOptionPane.showMessageDialog(null, "Admin Updated");
                }
          }
          catch(SQLException e){
              e.printStackTrace();
          }finally{
                ps.close();
          }       
    }
    
    public void InsertLaporan(String Plat, String jns, int hargaAwl, String jam, String tgl, int hargaJam, String bdgjam, String bdgtgl, int total) throws SQLException{      
        try{
            String queryParkir = "INSERT INTO `Laporan` (`Plat Nomor`,`Jenis Kendaraan`,`Harga Awal`,`Harga Per Jam`, `Tanggal Masuk`, `Jam Masuk`, `Tanggal Keluar`, `Jam Keluar`, `Harga Total`) VALUES (?,?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(queryParkir);
            ps.setString(1, Plat);
            ps.setString(2, jns);
            ps.setInt(3, hargaAwl);
            ps.setInt(4, hargaJam);
            ps.setString(5, tgl);
            ps.setString(6, jam);
            ps.setString(7, bdgtgl);
            ps.setString(8, bdgjam);
            ps.setInt(9, total);
            boolean x =ps.execute();
            
            this.deleteParkir(Plat);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("VEHICLE");
            alert.setHeaderText("VEHICLE SUCCESS to LEAVE");
            alert.setContentText("VEHICLE LEAVE");
            alert.showAndWait();
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            ps.close();
        }            
    }
    
    public void InsertParkir(String platNomor,String jenisKendaraan,int hargaAwal,int hargaPerJam) throws SQLException{
        try{
            String query = "SELECT * FROM Parkir WHERE `Plat Nomor`='" + platNomor + "'";
            ResultSet rs = this.queryResult(query);
            if(rs.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ADD VEHICLE");
                alert.setHeaderText("ADD VEHICLE FAILED");
                alert.setContentText("VEHICLE ALREADY EXSIST");
                alert.showAndWait();
                rs.close();  

            }else{
                    String queryParkir = "INSERT INTO `Parkir` (`Plat Nomor`,`Jenis Kendaraan`,`Harga Awal`,`Jam Masuk`,`Tanggal Masuk`,`Harga Per Jam`) VALUES "
                            + "(?,?,?,strftime('%H:%M','now','localtime'),date('now'),?)";
                    ps=con.prepareStatement(queryParkir);
                    ps.setString(1, platNomor);
                    ps.setString(2, jenisKendaraan);
                    ps.setInt(3, hargaAwal);
                    ps.setInt(4, hargaPerJam);
                    boolean x =ps.execute();
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ADD VEHICLE");
                    alert.setHeaderText("ADD VEHICLE SUCCES");
                    alert.setContentText("VEHICLE PARKED");
                    alert.showAndWait();
                    
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            ps.close();
        }
    }
    
    public void InsertKaryawan(String User, String pass, String notelp, String alamat, String NamaLeng, String tgl, String gender, String lvl, int nik) throws SQLException{
        String query = "SELECT * FROM Karyawan WHERE Username ='" + User + "'";
        ResultSet rs = this.queryResult(query);
        if(rs.next()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ADD USER");
            alert.setHeaderText("ADD USER FAILED");
            alert.setContentText("USERNAME ALREADY EXSIST");
            alert.showAndWait();
            rs.close();
        }else{
            try{
              String queryAdmin = "INSERT INTO `Karyawan` (`Username`,`Password`,`Telepon`,`Alamat`,`Nama`,`Tanggal Lahir`, `Jenis Kelamin`, `Level`, `NIK`) VALUES (?,?,?,?,?,?,?,?,?)";
                ps=con.prepareStatement(queryAdmin);
                ps.setString(1, User);
                ps.setString(2, pass);
                ps.setString(3, notelp);
                ps.setString(4, alamat);
                ps.setString(5, NamaLeng);
                ps.setString(6, tgl);
                ps.setString(7, gender);
                ps.setString(8, lvl);
                ps.setInt(9, nik);
                boolean x =ps.execute();
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD USER");
                alert.setHeaderText("ADD USER SUCCES");
                alert.setContentText("USER REGISTERED");
                alert.showAndWait(); 
              
          }
          catch(SQLException e){
              e.printStackTrace();
          }finally{
                ps.close();
          }
        }
    }
    
    public void deleteParkir(String Plat) throws SQLException{
        String query = "DELETE FROM Parkir WHERE `Plat Nomor`='"+Plat+"'";
        try {
            ps=con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            ps.close();
        } 
    }
    
    public void deleteKaryawan(String nik) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM Karyawan WHERE NIK='" + nik + "'";
        try {
            ps=con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally{
            ps.close();
        } 
    }
    
    public ResultSet ResultKendaraan(){
        try{
            String query = "SELECT * from `Kendaraan`";
            ps=con.prepareStatement(query);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
        return rs;    
    }
    
    public ResultSet ResultJenisKendaraan(String jenisKendaraan){
        try{
            String query = "SELECT * from `Kendaraan` WHERE `Jenis Kendaraan` = ? ";
            ps=con.prepareStatement(query);
            ps.setString(1, jenisKendaraan);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
        return rs;    
    }
    
        public ResultSet hitungKapasitasKendaraan(String jenisKendaraan){
        try{
            String query = "SELECT count() from `Parkir` WHERE `Jenis Kendaraan` = ? ";
            ps=con.prepareStatement(query);
            ps.setString(1, jenisKendaraan);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
        return rs;    
    }
    
    public boolean UpdateKendaraan(String kendaraan, int awal, int perjam) throws SQLException {
        boolean x = false;
        try {
            String query = "UPDATE `Kendaraan` SET `Harga Awal`=?, `Harga per Jam`=? WHERE `Jenis Kendaraan`=?";
            ps=con.prepareStatement(query);
            ps.setInt(1, awal);
            ps.setInt(2, perjam);
            ps.setString(3, kendaraan);
            x = ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ps.close();
        }
        return x;
    }
    
    public boolean UpdateKapasitas(String kendaraan, int kap) throws SQLException {
        boolean x = false;
        try {
            String query = "UPDATE `Kendaraan` SET `Kapasitas`=? WHERE `Jenis Kendaraan`=?";
            ps=con.prepareStatement(query);
            ps.setInt(1, kap);
            ps.setString(2, kendaraan);
            x = ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ps.close();
        }
        return x;
    }
}
