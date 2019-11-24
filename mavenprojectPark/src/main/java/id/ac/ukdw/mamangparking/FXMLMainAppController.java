/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking;

import id.ac.ukdw.mamangparking.model.Karyawan;
import id.ac.ukdw.mamangparking.db.DBQuery;
import id.ac.ukdw.mamangparking.model.Kendaraan;
import id.ac.ukdw.mamangparking.model.Laporan;
import id.ac.ukdw.mamangparking.model.Parkir;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button BtnParkIn, BtnParked, BtnParkOut, BtnInsert, BtnSearch, BtnLaporan, BtnEdit, BtnLogout, BtnHarga,
            btnKeluarkan, btnCariOut;

    @FXML
    private Pane pnlParkOut, pnlParked, pnlParkIn, pnlStart,pnlPrice, pnlDetailOut;
    
    @FXML
    private Label Nik, Username, Nama, lblHrgAwal,lblHrgPerJam, lblPlat, lblJenis, 
            lblHrgAwl, lblHrgJam, lblJamMsk, lblTglMsk, lblJamKlr, lblTglKlr, lblTotal,kapasitasMOTOR
            ,kapasitasMOBIL,kapasitasBUS,isiMOTOR,isiMOBIL,isiBUS;
    
    @FXML
    private TextField txtPlat, txtSearch, txtMasuk, txtKeluar;
    
    @FXML
    private ComboBox<String> cmbJenis,cmbHrgJns;
    
    @FXML
    private TableView<Parkir> tableKendaraan;
    @FXML
    private TableColumn<Parkir, Integer> colHargaAwal,colHargaPerJam;
    @FXML
    private TableColumn<Parkir, String> colPlat,colKendaraan,colTanggal,colJam;
    
    @FXML
    private void InsertPark(ActionEvent event) throws SQLException{
        if(!txtMasuk.getText().equals("") && !cmbJenis.getValue().equals("")){
            String query = "SELECT kapasitas FROM Kendaraan WHERE `Jenis Kendaraan` = '"+cmbJenis.getValue()+"'";
            ResultSet rskap = db.queryResult(query);
            int kap = rskap.getInt(1);
            rskap.close();
            String query1 = "SELECT count() FROM Parkir WHERE `Jenis Kendaraan` = '"+cmbJenis.getValue()+"'";
            ResultSet rsbdg = db.queryResult(query1);
            int bdg = rsbdg.getInt(1);
            rsbdg.close();
            if(bdg<kap){
                Parkir pk = new Parkir();
                ResultSet rs = db.ResultJenisKendaraan(cmbJenis.getValue());
                pk.setHargaAwal(rs.getInt(3));
                pk.setHargaPerJam(rs.getInt(4));
                pk.setJenisKendaraan(cmbJenis.getValue());
                pk.setPlatNomor(txtMasuk.getText());
                rs.close();
                pk.InsertDBParkir();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ADD VEHICLE");
                alert.setHeaderText("ADD VEHICLE FAILED");
                alert.setContentText("PARK ALREADY FULL");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ADD VEHICLE");
            alert.setHeaderText("ADD VEHICLE FAILED");
            alert.setContentText("PLEASE FILL ALL DATA");
            alert.showAndWait();
        }
      
    }  
    
    @FXML
    void onOutSearch(ActionEvent event) throws SQLException {
        ResultSet rs = db.queryResult("SELECT * FROM Parkir WHERE `Plat Nomor`='"+txtKeluar.getText()+"'");
        if(rs.next()){
            String plat = rs.getString(1);
            String jns = rs.getString(2);
            int hargaAwl = rs.getInt(3);
            String jam = rs.getString(4);
            String tgl = rs.getString(5);
            int hargaJam = rs.getInt(6);
            rs.close();
            pnlDetailOut.setVisible(true);
            Hitung(plat, jns, hargaAwl, jam, tgl, hargaJam);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("VEHICLE");
            alert.setHeaderText("SEARCH FAILED");
            alert.setContentText("VEHICLE NOT EXSIST");
            alert.showAndWait();
        }
    }
    
    @FXML
    void onOutPark(ActionEvent event) throws SQLException {
        Laporan lp = new Laporan();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OUT VEHICLE");
            alert.setContentText("ARE YOU SURE WANT TO EJECT VEHICLE?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                lp.InserDBLaporan(lblPlat.getText(), lblJenis.getText(), Integer.parseInt(lblHrgAwl.getText()), lblJamMsk.getText(), lblTglMsk.getText(), Integer.parseInt(lblHrgJam.getText()), lblJamKlr.getText(), lblTglKlr.getText(), Integer.parseInt(lblTotal.getText()));
            }
    }
    
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
    private void OnLogout(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleClicks(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (actionEvent.getSource() == BtnParkIn) {
            BtnParked.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParkIn.setStyle("-fx-background-color : #42406D");
            //pnlParkIn.setStyle("-fx-background-color : #455bff");
            pnlParkIn.toFront();
        }
        else if (actionEvent.getSource() == BtnParked) {
            BtnParkIn.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParked.setStyle("-fx-background-color : #42406D");
            //pnlParked.setStyle("-fx-background-color : #455bff");
            showTableKendaraan();
            setLabelKapasitas();
            pnlParked.toFront();
        }
        else if (actionEvent.getSource() == BtnParkOut) {
            BtnParkIn.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParked.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #42406D");
            //pnlParkOut.setStyle("-fx-background-color : #455bff");
            pnlParkOut.toFront();
        }
         else if (actionEvent.getSource() == BtnHarga) {
            BtnParkIn.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParked.setStyle("-fx-background-color : #131022; -fx-font-size: 15;");
            BtnParkOut.setStyle("-fx-background-color : #42406D");
            //pnlParkOut.setStyle("-fx-background-color : #455bff");
            pnlPrice.toFront();
        }
    }

    public void Hitung(String Plat, String jns, int hargaAwl, String jam, String tgl, int hargaJam) throws SQLException{
        ResultSet rs = db.queryResult("SELECT strftime('%H:%M','now','localtime'),date('now')");
        String bdgjam = rs.getString(1);
        String bdgtgl = rs.getString(2);
        rs.close();
        int total = 0;
        if(!jam.equals(bdgjam) && tgl.equals(bdgtgl)){
            rs=db.queryResult("SELECT time('"+bdgjam+"','-"+jam+"')");
            String selisih = rs.getString(1);
            rs.close();
            int count = Integer.parseInt(getSelisihJam(selisih));
            if(count>2 && count<=6){
                total = hargaAwl + (hargaJam*(count-2));
            }
            else if(count>6){
                total = hargaAwl + (hargaJam*(6-2));
            }
            else{
                total = hargaAwl;
            }
        }
        else{
            total = hargaAwl + (hargaJam*(6-2));
        }
        setLabel(Plat, jns, hargaAwl, jam, tgl, hargaJam, bdgjam, bdgtgl, total);
    }

    public void setLabel(String Plat, String jns, int hargaAwl, String jam, String tgl, int hargaJam, String bdgjam, String bdgtgl, int total){
        lblPlat.setText(Plat);
        lblJenis.setText(jns);
        lblHrgAwl.setText(String.valueOf(hargaAwl));
        lblHrgJam.setText(String.valueOf(hargaJam));
        lblJamMsk.setText(jam);
        lblTglMsk.setText(tgl);
        lblJamKlr.setText(bdgjam);
        lblTglKlr.setText(bdgtgl);
        lblTotal.setText(String.valueOf(total));
    }

    public String getSelisihJam(String selisih){
        String jam="";
        for(int i=0; i<selisih.length(); i++){
            if(selisih.charAt(i) != ':'){
                jam += selisih.charAt(i);
            }
            break;
        }
        return jam;
    }

    public void SetDataFront(String NIK) throws SQLException{
        kw.getDBKaryawan(db.Profilequery(NIK));       
        Nik.setText(String.valueOf(kw.getNIK()));
        Nama.setText(kw.getNamaLengkap());
        Username.setText(kw.getUsername());
    }
    
    public void setLabelHarga() throws SQLException{
        ResultSet rs = db.ResultJenisKendaraan(cmbHrgJns.getValue());
        lblHrgAwal.setText(String.valueOf(rs.getInt(3)));
        lblHrgPerJam.setText(String.valueOf(rs.getInt(4)));
    }
    
    public void setLabelKapasitas() throws SQLException{
        ResultSet rs = db.ResultKendaraan();
        
        while(rs.next()){
            if(rs.getString(2).equals("Motor")){
                
              ResultSet kapasitas = db.hitungKapasitasKendaraan(rs.getString(2));
              isiMOTOR.setText(String.valueOf(kapasitas.getInt(1)));
              kapasitasMOTOR.setText(String.valueOf(rs.getInt(5))); 
              kapasitas.close();
           
            }

            if(rs.getString(2).equals("Mobil")){
                
              ResultSet kapasitas = db.hitungKapasitasKendaraan(rs.getString(2));
              isiMOBIL.setText(String.valueOf(kapasitas.getInt(1)));
              kapasitasMOBIL.setText(String.valueOf(rs.getInt(5)));  
              kapasitas.close();
            }

            if(rs.getString(2).equals("Bus")){
                
              ResultSet kapasitas = db.hitungKapasitasKendaraan(rs.getString(2));
              isiBUS.setText(String.valueOf(kapasitas.getInt(1)));
              kapasitasBUS.setText(String.valueOf(rs.getInt(5)));  
              kapasitas.close();
            }
        }
        
        rs.close();      
    }
    
    @FXML
    public void showSearchTable() throws SQLException, ClassNotFoundException{
        colPlat.setCellValueFactory(new PropertyValueFactory("platNomor"));
        colKendaraan.setCellValueFactory(new PropertyValueFactory("jenisKendaraan"));
        colHargaAwal.setCellValueFactory(new PropertyValueFactory("hargaAwal"));
        colHargaPerJam.setCellValueFactory(new PropertyValueFactory("hargaPerJam"));
        colJam.setCellValueFactory(new PropertyValueFactory("jamMasuk"));
        colTanggal.setCellValueFactory(new PropertyValueFactory("tanggalMasuk"));
        ObservableList<Parkir> setList = FXCollections.observableArrayList();
        String search = txtSearch.getText();
        String query = "SELECT * FROM `Parkir` WHERE `Plat Nomor` LIKE '%" + search + "%'";
        ResultSet rs = db.queryResult(query);
        while(rs.next()){
            Parkir pk = new Parkir();
            pk.setPlatNomor(rs.getString(1));
            pk.setJenisKendaraan(rs.getString(2));
            pk.setHargaAwal(rs.getInt(3));
            pk.setJamMasuk(rs.getString(4));
            pk.setTanggalMasuk(rs.getString(5));
            pk.setHargaPerJam(rs.getInt(6));
            setList.add(pk);
        }
        tableKendaraan.setItems(setList);
        rs.close();    
    }
    
    @FXML
    public void showTableKendaraan() throws SQLException, ClassNotFoundException{
        colPlat.setCellValueFactory(new PropertyValueFactory("platNomor"));
        colKendaraan.setCellValueFactory(new PropertyValueFactory("jenisKendaraan"));
        colHargaAwal.setCellValueFactory(new PropertyValueFactory("hargaAwal"));
        colHargaPerJam.setCellValueFactory(new PropertyValueFactory("hargaPerJam"));
        colJam.setCellValueFactory(new PropertyValueFactory("jamMasuk"));
        colTanggal.setCellValueFactory(new PropertyValueFactory("tanggalMasuk"));
        ObservableList<Parkir> setList = FXCollections.observableArrayList();
        String query = "SELECT * from Parkir";
        ResultSet rs = db.queryResult(query);
        while(rs.next()){
            Parkir pk = new Parkir();
            pk.setPlatNomor(rs.getString(1));
            pk.setJenisKendaraan(rs.getString(2));
            pk.setHargaAwal(rs.getInt(3));
            pk.setJamMasuk(rs.getString(4));
            pk.setTanggalMasuk(rs.getString(5));
            pk.setHargaPerJam(rs.getInt(6));
            setList.add(pk);
        }
        tableKendaraan.setItems(setList);
        rs.close();    
    }
    
    ObservableList<String> enumKendaraan = FXCollections.observableArrayList("Motor", "Mobil","Bus");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      //  pnlStart.setStyle("-fx-background-color : #1a1a1a");
//        pnlStart.toFront();
        cmbHrgJns.getItems().addAll(enumKendaraan);
        cmbJenis.getItems().addAll(enumKendaraan);
        try {
            showTableKendaraan();
            setLabelKapasitas();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLMainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }    
    
}
