/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;

import id.ac.ukdw.mamangparking.model.Karyawan;
import id.ac.ukdw.mamangparking.db.DBQuery;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raging Bull
 */
public class FXMLEditProfileController implements Initializable {
    DBQuery db = new DBQuery();
    Karyawan kw = new Karyawan();
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField Nama, User, Pass, NewPass, NewRepass, NoTelp;

    @FXML
    private TextArea Alamat;

    @FXML
    private DatePicker Tgl;

    @FXML
    private Label NIK, Level;

    @FXML
    private ComboBox<?> Gender;
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    public void SetData(String Nik){
        kw.getDBKaryawan(db.Profilequery(Nik));
        Tgl.setValue(LOCAL_DATE(kw.getTglLahir()));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
