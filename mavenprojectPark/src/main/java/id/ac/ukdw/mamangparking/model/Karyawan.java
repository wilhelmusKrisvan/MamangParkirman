/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.model;
import id.ac.ukdw.mamangparking.db.DBQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Wilhelmus Krisvan
 */
public class Karyawan {
    DBQuery db = new DBQuery();
    private SimpleIntegerProperty NIK;
    private SimpleStringProperty NamaLengkap;
    private SimpleStringProperty Username;
    private SimpleStringProperty Password;
    private SimpleStringProperty TglLahir;
    private SimpleStringProperty Gender;
    private SimpleStringProperty Alamat;
    private SimpleStringProperty NoTelp;
    private SimpleStringProperty Level;

    public void setNIK(int NIK) {
        this.NIK = new SimpleIntegerProperty(NIK);
    }

    public void setLevel(String Level) {
        this.Level = new SimpleStringProperty(Level);
    }
    
    public void setNamaLengkap(String NamaLengkap) {
        this.NamaLengkap = new SimpleStringProperty(NamaLengkap);
    }

    public void setUsername(String Username) {
        this.Username = new SimpleStringProperty(Username);
    }

    public void setPassword(String Password) {
        this.Password = new SimpleStringProperty(Password);
    }

    public void setTglLahir(String TglLahir) {
        this.TglLahir = new SimpleStringProperty(TglLahir);
    }

    public void setGender(String Gender) {
        this.Gender = new SimpleStringProperty(Gender);
    }

    public void setAlamat(String Alamat) {
        this.Alamat = new SimpleStringProperty(Alamat);
    }

    public void setNoTelp(String NoTelp) {
        this.NoTelp = new SimpleStringProperty(NoTelp);
    }

    public int getNIK() {
        return NIK.get();
    }

    public String getLevel() {
        return Level.get();
    }
    
    public String getNamaLengkap() {
        return NamaLengkap.get();
    }

    public String getUsername() {
        return Username.get();
    }

    public String getPassword() {
        return Password.get();
    }

    public String getTglLahir() {
        return TglLahir.get();
    }

    public String getGender() {
        return Gender.get();
    }

    public String getAlamat() {
        return Alamat.get();
    }

    public String getNoTelp() {
        return NoTelp.get();
    }

    
    public void getDBKaryawan(ResultSet rs) throws SQLException{
        try{
            rs.next();
            this.NIK = new SimpleIntegerProperty(rs.getInt(1));
            this.NamaLengkap = new SimpleStringProperty(rs.getString(2));
            this.Username= new SimpleStringProperty(rs.getString(3));
            this.Password= new SimpleStringProperty(rs.getString(4));
            this.TglLahir= new SimpleStringProperty(rs.getString(5));
            this.Gender= new SimpleStringProperty(rs.getString(6));
            this.Alamat= new SimpleStringProperty(rs.getString(7));
            this.NoTelp= new SimpleStringProperty(rs.getString(8));
            this.Level= new SimpleStringProperty(rs.getString(9));
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rs.close();
        }
    }
    
    public ResultSet ListDBKaryawan() throws SQLException{
        String query = "SELECT * from Karyawan";
        ResultSet rs = db.queryResult(query);
        return rs;
    }
    
    public ObservableList<Karyawan> setKaryawanList() throws SQLException, ClassNotFoundException{
        ResultSet rs = this.ListDBKaryawan();
        ObservableList<Karyawan> karyawanList = FXCollections.observableArrayList();
        while(rs.next()){
            Karyawan kr = new Karyawan();
            kr.setNIK(rs.getInt(1));
            kr.setNamaLengkap(rs.getString(2));
            kr.setUsername(rs.getString(3));
            kr.setPassword(rs.getString(4));
            kr.setTglLahir(rs.getString(5));
            kr.setGender(rs.getString(6));
            kr.setNoTelp(rs.getString(7));
            kr.setAlamat(rs.getString(8));
            kr.setLevel(rs.getString(9));
            karyawanList.add(kr);
        }
        rs.close();
        return karyawanList;
    }
    
    public void UpdateDBKaryawan() throws SQLException{
        db.UpdateProfile(this.Username.get(), this.Password.get(), this.NoTelp.get(), this.Alamat.get(), this.NamaLengkap.get(), this.TglLahir.get(), this.Gender.get(), this.NIK.get());
    }
    
    public void InsertDBKaryawan() throws SQLException{
        db.InsertKaryawan(this.Username.get(), this.Password.get(), this.NoTelp.get(), this.Alamat.get(), this.NamaLengkap.get(), this.TglLahir.get(), this.Gender.get(), this.Level.get(), this.NIK.get());
    }
    
    

}
