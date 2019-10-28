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
    
    public void InsertKaryawan(String User, String pass, String notelp, String alamat, String NamaLeng, String tgl, String gender, String lvl, int nik) throws SQLException{
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
}
