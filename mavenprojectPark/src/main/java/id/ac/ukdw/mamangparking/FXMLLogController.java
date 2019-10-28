/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;

import id.ac.ukdw.mamangparking.db.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Wilhelmus Krisvan
 */
public class FXMLLogController implements Initializable {
    DBQuery db = new DBQuery();
    @FXML
    TextField Uname;
    @FXML
    PasswordField Pass;
    @FXML
    private Button LogButton;
    @FXML
    private void LogButton(ActionEvent event) throws IOException, SQLException {
        String user = Uname.getText();
        String pass = Pass.getText();
        ResultSet rs = db.logquery(user, pass);
            if(rs.next()){
                String NIK = rs.getString(1);
                String level = rs.getString(9);
                if(level.equals("staff")){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/FXMLMainApp.fxml"));
                    Parent Main = loader.load();
                    FXMLMainAppController control = loader.getController();
                    control.SetDataFront(NIK);
                    Scene scene = new Scene(Main);
                    Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();                    
                    Primarystage.setScene(scene);
                    Primarystage.show();
                    rs.close();
                }
                else{
                    
                }
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("LOGIN FAILED");
                alert.setHeaderText("LOGIN IS FAILED");
                alert.setContentText("Username OR Password is WRONG");
                alert.showAndWait();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
