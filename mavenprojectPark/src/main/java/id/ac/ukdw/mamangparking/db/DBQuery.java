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
    
    public void queryUpdate(String query){
        try {
            ps=con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet queryResult(String query){
        try{
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }catch(SQLException E){
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        return rs;
    }
    
    public ResultSet logquery(String User, String pass){
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
    
    public ResultSet Profilequery(String NIK){
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
}
