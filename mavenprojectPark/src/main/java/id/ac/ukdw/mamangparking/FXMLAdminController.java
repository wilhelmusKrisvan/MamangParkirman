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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                   BtnSave;
    
    @FXML
    private Label Nik,lblNama,User,edtNIK,edtLevel;
    
    @FXML
    private TextField edtNama,edtUser,edtNotelp,edtAlamat,NIK,Pass,Repass,Notelp,
                      alamat;
            
    @FXML
    private ComboBox<?> edtGender,Level,Gender;
    
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
            
            
            
            
    public void SetDataFront(String NIK) throws SQLException{
        kw.getDBKaryawan(db.Profilequery(NIK));       
        Nik.setText(String.valueOf(kw.getNIK()));
        lblNama.setText(kw.getNamaLengkap());
        User.setText(kw.getUsername());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
