/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectpark;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wilhelmus Krisvan
 */
public class FXMLMainAppController implements Initializable {
    DBQuery db = new DBQuery();
    Karyawan kw = new Karyawan();
    
    @FXML
    private Button BtnParkIn, BtnParked, BtnParkOut, BtnInsert, BtnSearch, BtnLaporan, BtnEdit, BtnLogout, BtnHarga;

    @FXML
    private Pane pnlParkOut, pnlParked, pnlParkIn, pnlStart;
    
    @FXML
    private Label Nik, Username, Nama;
    
    @FXML
    private TextField txtPlat, txtSearch;
    
    @FXML
    private ComboBox<?> cmbJenis;
    
    @FXML
    private void EditProfile(ActionEvent event) throws IOException {
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
    private void OnLogout(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == BtnParkIn) {
            BtnParked.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnParkIn.setStyle("-fx-background-color : #3b368a");
            pnlParkIn.setStyle("-fx-background-color : #455bff");
            pnlParkIn.toFront();
        }
        else if (actionEvent.getSource() == BtnParked) {
            BtnParkIn.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnParked.setStyle("-fx-background-color : #3b368a");
            pnlParked.setStyle("-fx-background-color : #455bff");
            pnlParked.toFront();
        }
        else if (actionEvent.getSource() == BtnParkOut) {
            BtnParkIn.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnParked.setStyle("-fx-background-color : #5749d1; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #3b368a");
            pnlParkOut.setStyle("-fx-background-color : #455bff");
            pnlParkOut.toFront();
        }
    }
    
    
    
    public void SetDataFront(String user, String pass){
        kw.getDBKaryawan(db.logquery(user, pass));       
        Nik.setText(kw.getNIK());
        Nama.setText(kw.getNamaLengkap());
        Username.setText(kw.getUsername());
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pnlStart.setStyle("-fx-background-color : #1a1a1a");
        pnlStart.toFront();
    }    
    
}
