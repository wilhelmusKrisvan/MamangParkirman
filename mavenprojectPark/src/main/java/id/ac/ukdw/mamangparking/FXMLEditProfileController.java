/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;

import id.ac.ukdw.mamangparking.model.Karyawan;
import id.ac.ukdw.mamangparking.db.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField Nama, User, NoTelp;
    
    @FXML
    private PasswordField NewPass, NewRepass;
    
    @FXML
    private TextArea Alamat;

    @FXML
    private DatePicker Tgl;

    @FXML
    private Label NIK, Level;

    @FXML
    private ComboBox<String> Gender ;
    
    @FXML
    private Button BtnSave, BtnCancel;

    @FXML
    void OnCancel(ActionEvent event) throws IOException, SQLException {
        this.BackToMain(event);
    }

    @FXML
    void OnSave(ActionEvent event) throws IOException, SQLException {
        if(!Nama.getText().equals("") && !User.getText().equals("") && !NoTelp.getText().equals("") && !Alamat.getText().equals("") && !Tgl.getValue().equals("")){
            if(!NewPass.getText().equals("") && !NewRepass.getText().equals("")){
                if(NewPass.getText().equals(NewRepass.getText()) && !NewPass.getText().equals(kw.getPassword())){
                    this.UpdateData(NewPass.getText());
                    this.BackToMain(event);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EDIT PROFILE");
                    alert.setHeaderText("Password is Wrong");
                    alert.setContentText("New Password needed to SET");
                    alert.showAndWait();
                }
            }else{
                this.UpdateData(kw.getPassword());
                this.BackToMain(event);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EDIT PROFILE");
            alert.setHeaderText("EDIT IS FAILED");
            alert.setContentText("YOU MUST FILL THE DATA");
            alert.showAndWait();
        }
    }
    
    ObservableList<String> options = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    public void BackToMain(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLMainApp.fxml"));
        Parent Main = loader.load();
        FXMLMainAppController control = loader.getController();
        control.SetDataFront(NIK.getText());
        Scene scene = new Scene(Main);
        Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();                    
        Primarystage.setScene(scene);
        Primarystage.show();
    }
    
    public void SetData(String Nik) throws SQLException{
        kw.getDBKaryawan(db.Profilequery(Nik));
        Tgl.setValue(LOCAL_DATE(kw.getTglLahir()));
        NIK.setText(String.valueOf(kw.getNIK()));
        Nama.setText(kw.getNamaLengkap());
        User.setText(kw.getUsername());
        NoTelp.setText(kw.getNoTelp());
        Alamat.setText(kw.getAlamat());
        Gender.setValue(kw.getGender());
        Gender.getItems().addAll(options);
        Level.setText(kw.getLevel());
    }
    
    public void UpdateData(String pass) throws SQLException{
        kw.setNamaLengkap(Nama.getText());
        kw.setUsername(User.getText());
        kw.setPassword(pass);
        kw.setTglLahir(Tgl.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        kw.setGender(Gender.getValue());
        kw.setNoTelp(NoTelp.getText());
        kw.setAlamat(Alamat.getText());
        kw.UpdateDBKaryawan();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
