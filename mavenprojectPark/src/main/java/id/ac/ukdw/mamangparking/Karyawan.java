/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;
import java.sql.ResultSet;
/**
 *
 * @author Wilhelmus Krisvan
 */
public class Karyawan {
    private String NIK;
    private String NamaLengkap;
    private String Username;
    private String Password;
    private String TglLahir;
    private String Gender;
    private String Alamat;
    private String NoTelp;
    private String Level;

    
    public void getDBKaryawan(ResultSet rs){
        try{
            rs.next();
            this.NIK = rs.getString(1);
            this.NamaLengkap=rs.getString(2);
            this.Username = rs.getString(3);
            this.Password = rs.getString(4);
            this.TglLahir = rs.getString(5);
            this.Gender = rs.getString(6);
            this.Alamat = rs.getString(8);
            this.NoTelp = rs.getString(7);
            this.Level = rs.getString(9);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public void setNamaLengkap(String NamaLengkap) {
        this.NamaLengkap = NamaLengkap;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setTglLahir(String TglLahir) {
        this.TglLahir = TglLahir;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public void setNoTelp(String NoTelp) {
        this.NoTelp = NoTelp;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getNIK() {
        return NIK;
    }

    public String getNamaLengkap() {
        return NamaLengkap;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getTglLahir() {
        return TglLahir;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getNoTelp() {
        return NoTelp;
    }

    public String getLevel() {
        return Level;
    }

}
