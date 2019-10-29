/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;

import static id.ac.ukdw.mamangparking.FXMLEditProfileController.LOCAL_DATE;
import id.ac.ukdw.mamangparking.db.DBQuery;
import id.ac.ukdw.mamangparking.model.Karyawan;
import id.ac.ukdw.mamangparking.model.Kendaraan;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private Button BtnEdit,BtnUser,BtnAddUser,BtnLaporan,BtnKendaraan,BtnSearch,
                   BtnSaveBtnInsert,BtnInsert, btnUbahMOBIL, btnUbahMOTOR, btnUbahBUS;
    
    @FXML
    private Label Nik,lblNama,lblUser,edtNIK,edtLevel, trfawalMOTOR, trfawalMOBIL, trfawalBUS, trfjamMOTOR, trfjamMOBIL, trfjamBUS;
    
    @FXML
    private TextField edtNama,edtUser,edtNotelp,edtAlamat,NIK,Pass,Repass,Notelp,
                      Alamat,Nama,User, trfawalbaruMOBIL, trfawalbaruMOTOR, trfawalbaruBUS, trfjambaruMOBIL, trfjambaruMOTOR, trfjambaruBUS;
            
    @FXML
    private ComboBox<String> edtGender,Level,Gender;
    
    @FXML
    private TableView<Karyawan> tableUser;
    @FXML
    private TableColumn<Karyawan, Integer> colNIK;
    @FXML
    private TableColumn<Karyawan, String> colUser,colName;
    
    @FXML
    private DatePicker edtTgl,Tgl;
    
    @FXML
    private Pane pnlAdd, pnlUser, pnlVehicle, pnlReport;

    @FXML
    private void EditProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLEditProfile.fxml"));
        Parent Edit = loader.load();
        FXMLEditProfileController control = loader.getController();
        control.SetData(Nik.getText());
        Stage Editstage = (Stage) ((Node)event.getSource()).getScene().getWindow();                    
        Scene scene = new Scene(Edit);
        Editstage.setScene(scene);
        Editstage.show();       
    }
    
    @FXML
    private void handleClicks(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (actionEvent.getSource() == BtnUser) {
            BtnAddUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #131022;");
            BtnUser.setStyle("-fx-background-color : #42406D");
            this.ShowTable();
            pnlUser.toFront();
        }
        else if (actionEvent.getSource() == BtnAddUser) {
            BtnUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #131022;");
            BtnAddUser.setStyle("-fx-background-color : #42406D");
            pnlAdd.toFront();
        }
        else if (actionEvent.getSource() == BtnLaporan) {
            BtnUser.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #131022;");
            BtnAddUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #42406D");
            pnlReport.toFront();
        }
        else if (actionEvent.getSource() == BtnKendaraan) {
            BtnUser.setStyle("-fx-background-color : #131022;");
            BtnAddUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #42406D");
            pnlVehicle.toFront();
        }
    }      
        
    @FXML
    private void InsertNewData() throws SQLException{
        if(Pass.getText().equals("") && NIK.getText().equals("") && Nama.getText().equals("")
                && User.getText().equals("") && Alamat.getText().equals("") && Notelp.getText().equals("")
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
    
    @FXML
    private void handleUbah(ActionEvent action) throws SQLException {
        Kendaraan kndrn = new Kendaraan();
        if(action.getSource() == btnUbahMOBIL){
            boolean x;
            x = db.UpdateKendaraan("Mobil", Integer.parseInt(trfawalbaruMOBIL.getText()), Integer.parseInt(trfjambaruMOBIL.getText()));
            if(!x){
                trfawalMOBIL.setText(trfawalbaruMOBIL.getText());
                trfjamMOBIL.setText(trfjambaruMOBIL.getText());
            }
        }else if(action.getSource() == btnUbahMOTOR){
            boolean x;
            x = db.UpdateKendaraan("Motor", Integer.parseInt(trfawalbaruMOTOR.getText()), Integer.parseInt(trfjambaruMOTOR.getText()));
            if(!x){
                trfawalMOTOR.setText(trfawalbaruMOTOR.getText());
                trfjamMOTOR.setText(trfjambaruMOTOR.getText());
            }
        }else if(action.getSource() == btnUbahBUS){
            boolean x;
            x = db.UpdateKendaraan("Bus", Integer.parseInt(trfawalbaruBUS.getText()), Integer.parseInt(trfjambaruBUS.getText()));
            if(!x){
                trfawalBUS.setText(trfawalbaruBUS.getText());
                trfjamBUS.setText(trfjambaruBUS.getText());
            }
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
            kr.setAlamat(Alamat.getText());
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
    
    public void setHargaKendaraan(){
        Kendaraan kr = new Kendaraan();
        try {
            ObservableList<Kendaraan> kendaraanList = kr.setKendaraanList();
            for(int i=0; i<kendaraanList.size(); i++){
                if(kendaraanList.get(i).getJenisKendaraan().equals("Mobil")){
                    trfawalMOBIL.setText(String.valueOf(kendaraanList.get(i).getHargaAwal()));
                    trfjamMOBIL.setText(String.valueOf(kendaraanList.get(i).getHargaPerjam()));
                }else if(kendaraanList.get(i).getJenisKendaraan().equals("Motor")){
                    trfawalMOTOR.setText(String.valueOf(kendaraanList.get(i).getHargaAwal()));
                    trfjamMOTOR.setText(String.valueOf(kendaraanList.get(i).getHargaPerjam()));
                }else if(kendaraanList.get(i).getJenisKendaraan().equals("Bus")){
                    trfawalBUS.setText(String.valueOf(kendaraanList.get(i).getHargaAwal()));
                    trfjamBUS.setText(String.valueOf(kendaraanList.get(i).getHargaPerjam()));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void ShowTable() throws SQLException, ClassNotFoundException{
        colNIK.setCellValueFactory(new PropertyValueFactory("NIK"));
        colName.setCellValueFactory(new PropertyValueFactory("NamaLengkap"));
        colUser.setCellValueFactory(new PropertyValueFactory("Username"));
        ObservableList<Karyawan> setList = FXCollections.observableArrayList();
        String query = "SELECT * from Karyawan";
        ResultSet rs = db.queryResult(query);
        while(rs.next()){
            Karyawan kr = new Karyawan();
            kr.setNIK(rs.getInt(1));
            kr.setNamaLengkap(rs.getString(2));
            kr.setUsername(rs.getString(3));
            setList.add(kr);
        }
        tableUser.setItems(setList);
        tableUser.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    String nik = String.valueOf(tableUser.getSelectionModel().getSelectedItem().getNIK());
                    ShowDetail(nik);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void ShowDetail(String nik) throws SQLException{
        Karyawan kr = new Karyawan();
        kr.getDBKaryawan(db.Profilequery(nik));
        edtNIK.setText(String.valueOf(kr.getNIK()));
        edtNama.setText(kr.getNamaLengkap());
        edtUser.setText(kr.getUsername());
        edtLevel.setText(kr.getLevel());
        edtGender.setValue(kr.getGender());
        edtTgl.setValue(LOCAL_DATE(kr.getTglLahir()));
        edtNotelp.setText(kr.getNoTelp());
        edtAlamat.setText(kr.getAlamat());
    }
    
    ObservableList<String> cbGender = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
    ObservableList<String> cbStatus = FXCollections.observableArrayList("staff", "admin");
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.ShowTable();            
            this.setHargaKendaraan();
            Gender.getItems().addAll(cbGender);
            Level.getItems().addAll(cbStatus);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }    
    
}
