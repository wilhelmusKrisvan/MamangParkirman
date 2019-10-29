/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;

import id.ac.ukdw.mamangparking.db.DBQuery;
import id.ac.ukdw.mamangparking.model.Karyawan;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yusuf
 */
public class FXMLAdminController implements Initializable {
    DBQuery db = new DBQuery();
    Karyawan kw = new Karyawan();
    
    @FXML
    private Button BtnEdit,BtnUser,BtnUser1,BtnLaporan,BtnKendaraan,BtnSearch,
                   BtnSaveBtnInsert,BtnInsert;
    
    @FXML
    private Label Nik,lblNama,lblUser,edtNIK,edtLevel;
    
    @FXML
    private TextField edtNama,edtUser,edtNotelp,edtAlamat,NIK,Pass,Repass,Notelp,
                      alamat,Nama,User;
            
    @FXML
    private ComboBox<String> edtGender,Level,Gender;
    
    @FXML
    private DatePicker edtTgl,Tgl;
    
    @FXML
    private Pane pnlAdd,pnlUser;

    @FXML
    private void EditProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLEditProfile.fxml"));
        Parent Edit = loader.load();
        FXMLEditProfileController control = loader.getController();
        control.SetData(Nik.getText().toString());
        Stage Editstage = (Stage) ((Node)event.getSource()).getScene().getWindow();                    
        Scene scene = new Scene(Edit);
        Editstage.setScene(scene);
        Editstage.show();       
    }
    
    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == BtnUser) {
            BtnUser.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnUser.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnUser.setStyle("-fx-background-color : #3b368a");
            pnlUser.toFront();
        }
        else if (actionEvent.getSource() == BtnUser1) {
            BtnUser1.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnUser1.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnUser1.setStyle("-fx-background-color : #3b368a");
            pnlAdd.toFront();
        }
    }      
    
    
    ObservableList<String> cbGender = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
    ObservableList<String> cbStatus = FXCollections.observableArrayList("staff", "admin");
    
    
    @FXML
    private void InsertNewData() throws SQLException{
        if(Pass.getText().equals("") && NIK.getText().equals("") && Nama.getText().equals("")
                && User.getText().equals("") && alamat.getText().equals("") && Notelp.getText().equals("")
                && Level.getValue().equals("") && Gender.getValue().equals("") && Tgl.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ADD USER");
            alert.setHeaderText("ADD USER IS FAILED");
            alert.setContentText("YOU MUST FILL ALL DATA");
            alert.showAndWait();
        }else{
            this.setData();
        }
       
    }
            
    public void setData() throws SQLException{
        Karyawan kr = new Karyawan();
        
         if(Pass.getText().equals(Repass.getText())){
            kr.setPassword(Pass.getText());
            kr.setNIK(Integer.valueOf(NIK.getText()));
            kr.setNamaLengkap(Nama.getText());
            kr.setUsername(User.getText());
            kr.setLevel(Level.getValue().toString());
            kr.setGender(Gender.getValue().toString());
            kr.setTglLahir(Tgl.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            kr.setNoTelp(Notelp.getText());
            kr.setAlamat(alamat.getText());
            kr.InsertDBKaryawan();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ADD USER");
            alert.setHeaderText("ADD USER IS FAILED");
            alert.setContentText("PLEASE RE-TYPE PASSWORD CORECTLY");
            alert.showAndWait();
         }
        
    }

    public void SetDataFront(String NIK) throws SQLException{
        kw.getDBKaryawan(db.Profilequery(NIK));       
        Nik.setText(String.valueOf(kw.getNIK()));
        lblNama.setText(kw.getNamaLengkap());
        lblUser.setText(kw.getUsername());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Gender.getItems().addAll(cbGender);
        Level.getItems().addAll(cbStatus);
    }    
    
}
