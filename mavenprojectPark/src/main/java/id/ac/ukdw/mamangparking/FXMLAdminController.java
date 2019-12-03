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
import id.ac.ukdw.mamangparking.model.Laporan;
import id.ac.ukdw.mamangparking.model.Parkir;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Yusuf
 */
public class FXMLAdminController implements Initializable {

    DBQuery db = new DBQuery();
    Karyawan kw = new Karyawan();

    @FXML
    private ChoiceBox<String> cbcb;

    @FXML
    private Button BtnEdit, BtnUser, BtnAddUser, BtnLaporan, BtnKendaraan, BtnSearch,
            BtnDelete, BtnInsert, btnUbahMOBIL, btnUbahMOTOR, btnUbahBUS,
            btnKapasitasMOTOR, btnKapasitasMOBIL, btnKapasitasBUS, btnGrafKendaraan, btnTabelReport;

    @FXML
    private Label Nik, lblNama, lblUser, edtNIK, edtLevel, trfawalMOTOR, trfawalMOBIL, trfawalBUS, trfjamMOTOR, trfjamMOBIL, trfjamBUS,
            kapasitasMOTOR, kapasitasMOBIL, kapasitasBUS, edtGender, edtUser, edtTgl, edtNotelp, edtAlamat, edtNama, totalLaporan;

    @FXML
    private TextField NIK, Pass, Repass, Notelp,
            Alamat, Nama, User, trfawalbaruMOBIL, trfawalbaruMOTOR, trfawalbaruBUS, trfjambaruMOBIL, trfjambaruMOTOR, trfjambaruBUS,
            kapbaruMOTOR, kapbaruMOBIL, kapbaruBUS, txtSearch, txtTahun;

    @FXML
    private ComboBox<String> Level, Gender;

    @FXML
    private ComboBox<String> rptJenisKendaraan;

    @FXML
    private TableView<Karyawan> tableUser;

    @FXML
    private TableView<Laporan> tblReport;

    @FXML
    private TableColumn<Karyawan, Integer> colNIK;

    @FXML
    private TableColumn<Parkir, String> rptPlat, rptJenis, rptMasuk, rptKeluar, rptTglIn, rptTglOut;

    @FXML
    private TableColumn<Parkir, Integer> rptBiaya, rptNo;

    @FXML
    private LineChart<?, ?> graftPendapatan;

    @FXML
    private BarChart<String, Integer> bcGrafKendaraan;

    @FXML
    private NumberAxis graftPendapatanY;

    @FXML
    private CategoryAxis graftPendapatanX;

    @FXML
    private TableColumn<Karyawan, String> colUser, colName;

    @FXML
    private DatePicker Tgl, tglGrafKendaraan, tglReportFrom, tglReportTo;

    @FXML
    private Pane pnlAdd, pnlUser, pnlVehicle, pnlReport;

    @FXML
    private void EditProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLEditProfile.fxml"));
        Parent Edit = loader.load();
        FXMLEditProfileController control = loader.getController();
        control.SetData(Nik.getText());
        Stage Editstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(Edit);
        Editstage.setScene(scene);
        Editstage.show();
    }

    @FXML
    private void showChartPendapatan() throws SQLException {
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        graftPendapatan.getData().clear();
        String tahun = txtTahun.getText();
        ObservableList<Integer> pendapatan = db.hitungPendapatanTahunan(tahun);
        ObservableList<Integer> pendapatanMotor = db.hitungPendapatanMotor(tahun);
        ObservableList<Integer> pendapatanMobil = db.hitungPendapatanMobil(tahun);
        ObservableList<Integer> pendapatanBus = db.hitungPendapatanBus(tahun);

        series.getData().add(new XYChart.Data("January", pendapatan.get(0)));
        series.getData().add(new XYChart.Data("February", pendapatan.get(1)));
        series.getData().add(new XYChart.Data("Maret", pendapatan.get(2)));
        series.getData().add(new XYChart.Data("April", pendapatan.get(3)));
        series.getData().add(new XYChart.Data("Mei", pendapatan.get(4)));
        series.getData().add(new XYChart.Data("Juni", pendapatan.get(5)));
        series.getData().add(new XYChart.Data("Juli", pendapatan.get(6)));
        series.getData().add(new XYChart.Data("Agustus", pendapatan.get(7)));
        series.getData().add(new XYChart.Data("September", pendapatan.get(8)));
        series.getData().add(new XYChart.Data("Oktober", pendapatan.get(9)));
        series.getData().add(new XYChart.Data("November", pendapatan.get(10)));
        series.getData().add(new XYChart.Data("December", pendapatan.get(11)));
        series.setName("Total");

        series1.getData().add(new XYChart.Data("January", pendapatanMotor.get(0)));
        series1.getData().add(new XYChart.Data("February", pendapatanMotor.get(1)));
        series1.getData().add(new XYChart.Data("Maret", pendapatanMotor.get(2)));
        series1.getData().add(new XYChart.Data("April", pendapatanMotor.get(3)));
        series1.getData().add(new XYChart.Data("Mei", pendapatanMotor.get(4)));
        series1.getData().add(new XYChart.Data("Juni", pendapatanMotor.get(5)));
        series1.getData().add(new XYChart.Data("Juli", pendapatanMotor.get(6)));
        series1.getData().add(new XYChart.Data("Agustus", pendapatanMotor.get(7)));
        series1.getData().add(new XYChart.Data("September", pendapatanMotor.get(8)));
        series1.getData().add(new XYChart.Data("Oktober", pendapatanMotor.get(9)));
        series1.getData().add(new XYChart.Data("November", pendapatanMotor.get(10)));
        series1.getData().add(new XYChart.Data("December", pendapatanMotor.get(11)));
        series1.setName("Motor");

        series2.getData().add(new XYChart.Data("January", pendapatanMobil.get(0)));
        series2.getData().add(new XYChart.Data("February", pendapatanMobil.get(1)));
        series2.getData().add(new XYChart.Data("Maret", pendapatanMobil.get(2)));
        series2.getData().add(new XYChart.Data("April", pendapatanMobil.get(3)));
        series2.getData().add(new XYChart.Data("Mei", pendapatanMobil.get(4)));
        series2.getData().add(new XYChart.Data("Juni", pendapatanMobil.get(5)));
        series2.getData().add(new XYChart.Data("Juli", pendapatanMobil.get(6)));
        series2.getData().add(new XYChart.Data("Agustus", pendapatanMobil.get(7)));
        series2.getData().add(new XYChart.Data("September", pendapatanMobil.get(8)));
        series2.getData().add(new XYChart.Data("Oktober", pendapatanMobil.get(9)));
        series2.getData().add(new XYChart.Data("November", pendapatanMobil.get(10)));
        series2.getData().add(new XYChart.Data("December", pendapatanMobil.get(11)));
        series2.setName("Mobil");

        series3.getData().add(new XYChart.Data("January", pendapatanBus.get(0)));
        series3.getData().add(new XYChart.Data("February", pendapatanBus.get(1)));
        series3.getData().add(new XYChart.Data("Maret", pendapatanBus.get(2)));
        series3.getData().add(new XYChart.Data("April", pendapatanBus.get(3)));
        series3.getData().add(new XYChart.Data("Mei", pendapatanBus.get(4)));
        series3.getData().add(new XYChart.Data("Juni", pendapatanBus.get(5)));
        series3.getData().add(new XYChart.Data("Juli", pendapatanBus.get(6)));
        series3.getData().add(new XYChart.Data("Agustus", pendapatanBus.get(7)));
        series3.getData().add(new XYChart.Data("September", pendapatanBus.get(8)));
        series3.getData().add(new XYChart.Data("Oktober", pendapatanBus.get(9)));
        series3.getData().add(new XYChart.Data("November", pendapatanBus.get(10)));
        series3.getData().add(new XYChart.Data("December", pendapatanBus.get(11)));
        series3.setName("Bus");

        graftPendapatan.getData().addAll(series);
        graftPendapatan.getData().addAll(series1);
        graftPendapatan.getData().addAll(series2);
        graftPendapatan.getData().addAll(series3);
    }

    @FXML
    private void showChartKendaraan() throws SQLException {
        XYChart.Series series = new XYChart.Series();
        bcGrafKendaraan.getData().clear();
        String tanggal = tglGrafKendaraan.getValue().toString();
        ObservableList<Integer> kendaraan = db.hitungKendaraanHarian(tanggal);

        series.getData().add(new XYChart.Data("Motor", kendaraan.get(0)));
        series.getData().add(new XYChart.Data("Mobil", kendaraan.get(1)));
        series.getData().add(new XYChart.Data("Bus", kendaraan.get(2)));

        bcGrafKendaraan.getData().addAll(series);
    }

    @FXML
    public void showReportTable() throws SQLException, ClassNotFoundException {
        if (tglReportFrom.getValue()!=null && tglReportTo.getValue()!=null) {
            rptNo.setCellValueFactory(new PropertyValueFactory("id"));
            rptPlat.setCellValueFactory(new PropertyValueFactory("platNomor"));
            rptJenis.setCellValueFactory(new PropertyValueFactory("jenisKendaraan"));
            rptTglIn.setCellValueFactory(new PropertyValueFactory("tanggalMasuk"));
            rptMasuk.setCellValueFactory(new PropertyValueFactory("jamMasuk"));
            rptTglOut.setCellValueFactory(new PropertyValueFactory("tanggalKeluar"));
            rptKeluar.setCellValueFactory(new PropertyValueFactory("jamKeluar"));
            rptBiaya.setCellValueFactory(new PropertyValueFactory("total"));
            tglReportFrom.setConverter(new StringConverter<LocalDate>() {
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                {
                    tglReportFrom.setPromptText(pattern.toLowerCase());
                }

                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            });
            tglReportTo.setConverter(new StringConverter<LocalDate>() {
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                {
                    tglReportTo.setPromptText(pattern.toLowerCase());
                }

                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            });
            String tglFrom = tglReportFrom.getValue().toString();
            String tglTo = tglReportTo.getValue().toString();
            if (!rptJenisKendaraan.getValue().toString().equals(" ")) {
                ObservableList<Laporan> setList = db.showTableSearch(tglFrom, tglTo, rptJenisKendaraan.getValue().toString());
                tblReport.setItems(setList);
                ResultSet r = db.ResultPendapatan(tglFrom, tglTo, rptJenisKendaraan.getValue().toString());
                r.next();
                totalLaporan.setText(String.valueOf(r.getInt(1)));
                r.close();
            } else {
                this.ShowLaporanSearch(tglFrom, tglTo);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SEARCH ERROR");
            alert.setHeaderText("SEARCH IS FAILED");
            alert.setContentText("DATE NEED TO BE CHOOSED!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!edtNIK.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("DELETE USER");
            alert.setContentText("ARE YOU SURE WANT TO DELETE?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                db.deleteKaryawan(edtNIK.getText());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DELETE ERROR");
            alert.setHeaderText("DELETE USER IS FAILED");
            alert.setContentText("DATA NEED TO BE CHOOSED!");
            alert.showAndWait();
        }
        this.ShowTable();
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
        } else if (actionEvent.getSource() == BtnAddUser) {
            BtnUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #131022;");
            BtnAddUser.setStyle("-fx-background-color : #42406D");
            pnlAdd.toFront();
        } else if (actionEvent.getSource() == BtnLaporan) {
            BtnUser.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #131022;");
            BtnAddUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #42406D");
            pnlReport.toFront();
        } else if (actionEvent.getSource() == BtnKendaraan) {
            BtnUser.setStyle("-fx-background-color : #131022;");
            BtnAddUser.setStyle("-fx-background-color : #131022;");
            BtnLaporan.setStyle("-fx-background-color : #131022;");
            BtnKendaraan.setStyle("-fx-background-color : #42406D");
            pnlVehicle.toFront();
        }
    }

    @FXML
    private void InsertNewData() throws SQLException {
        if (!NIK.getText().equals("") && !Nama.getText().equals("") && !User.getText().equals("")
                && !Pass.getText().equals("") && !Repass.getText().equals("") && !Level.getValue().equals(null)
                && !Gender.getValue().equals(null) && !Tgl.getValue().equals(null) && !Notelp.getText().equals("")
                && !Alamat.getText().equals("")) {

            if (!Pass.getText().equals(Repass.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ADD USER");
                alert.setHeaderText("ADD USER FAILED");
                alert.setContentText("PLEASE RE-TYPE PASSWORD CORRECTLY");
                alert.showAndWait();
            } else {
                setData();
            }
        }
    }

    @FXML
    public void showSearchKaryawan() throws SQLException, ClassNotFoundException {
        colNIK.setCellValueFactory(new PropertyValueFactory("NIK"));
        colName.setCellValueFactory(new PropertyValueFactory("NamaLengkap"));
        colUser.setCellValueFactory(new PropertyValueFactory("Username"));
        ObservableList<Karyawan> setList = FXCollections.observableArrayList();
        String search = txtSearch.getText();
        String query = "SELECT * FROM `Karyawan` WHERE `Nama` LIKE '%" + search + "%'";
        ResultSet rs = db.queryResult(query);
        while (rs.next()) {
            Karyawan kr = new Karyawan();
            kr.setNIK(rs.getInt(1));
            kr.setNamaLengkap(rs.getString(2));
            kr.setUsername(rs.getString(3));
            setList.add(kr);
        }
        tableUser.setItems(setList);
        rs.close();
    }

    @FXML
    private void handlekapUbah(ActionEvent action) throws SQLException {
        Kendaraan kndrn = new Kendaraan();
        if (action.getSource() == btnKapasitasMOTOR) {
            if (!kapbaruMOTOR.getText().equals("")) {
                boolean x;
                x = db.UpdateKapasitas("Motor", Integer.parseInt(kapbaruMOTOR.getText()));
                if (!x) {
                    kapasitasMOTOR.setText(kapbaruMOTOR.getText());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ADD USER");
                alert.setHeaderText("CHANGE MOTOR'S CAPACITY FAILED");
                alert.setContentText("PLEASE INSERT NEW INPUT");
                alert.showAndWait();
            }
        } else if (action.getSource() == btnKapasitasMOBIL) {
            if (!kapbaruMOBIL.getText().equals("")) {
                boolean x;
                x = db.UpdateKapasitas("Mobil", Integer.parseInt(kapbaruMOBIL.getText()));
                if (!x) {
                    kapasitasMOBIL.setText(kapbaruMOBIL.getText());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ADD USER");
                alert.setHeaderText("CHANGE CAR'S CAPACITY FAILED");
                alert.setContentText("PLEASE INSERT NEW INPUT");
                alert.showAndWait();
            }
        } else if (action.getSource() == btnKapasitasBUS) {
            if (!kapbaruBUS.getText().equals("")) {
                boolean x;
                x = db.UpdateKapasitas("Bus", Integer.parseInt(kapbaruBUS.getText()));
                if (!x) {
                    kapasitasBUS.setText(kapbaruBUS.getText());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ADD USER");
                alert.setHeaderText("CHANGE BUS'S CAPACITY FAILED");
                alert.setContentText("PLEASE INSERT NEW INPUT");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void OnLogout(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleUbah(ActionEvent action) throws SQLException {
        Kendaraan kndrn = new Kendaraan();
        if (action.getSource() == btnUbahMOBIL) {
            boolean x;
            if (trfawalbaruMOBIL.getText().equals("") && !trfjambaruMOBIL.getText().equals("")) {
                x = db.UpdateKendaraan("Mobil", Integer.parseInt(trfawalMOBIL.getText()), Integer.parseInt(trfjambaruMOBIL.getText()));
                if (!x) {
                    trfjamMOBIL.setText(trfjambaruMOBIL.getText());
                }
            } else if (trfjambaruMOBIL.getText().equals("") && !trfawalbaruMOBIL.getText().equals("")) {
                x = db.UpdateKendaraan("Mobil", Integer.parseInt(trfjamMOBIL.getText()), Integer.parseInt(trfawalbaruMOBIL.getText()));
                if (!x) {
                    trfawalMOBIL.setText(trfawalbaruMOBIL.getText());
                }
            } else if (!trfawalbaruMOBIL.getText().equals("") && !trfjambaruMOBIL.getText().equals("")) {
                x = db.UpdateKendaraan("Mobil", Integer.parseInt(trfawalbaruMOBIL.getText()), Integer.parseInt(trfjambaruMOBIL.getText()));
                if (!x) {
                    trfawalMOBIL.setText(trfawalbaruMOBIL.getText());
                    trfjamMOBIL.setText(trfjambaruMOBIL.getText());
                }
            }
        } else if (action.getSource() == btnUbahMOTOR) {
            boolean x;
            if (trfawalbaruMOTOR.getText().equals("") && !trfjambaruMOTOR.getText().equals("")) {
                x = db.UpdateKendaraan("Motor", Integer.parseInt(trfawalMOTOR.getText()), Integer.parseInt(trfjambaruMOTOR.getText()));
                if (!x) {
                    trfjamMOTOR.setText(trfjambaruMOTOR.getText());
                }
            } else if (trfjambaruMOTOR.getText().equals("") && !trfawalbaruMOTOR.getText().equals("")) {
                x = db.UpdateKendaraan("Motor", Integer.parseInt(trfjamMOTOR.getText()), Integer.parseInt(trfawalbaruMOTOR.getText()));
                if (!x) {
                    trfawalMOTOR.setText(trfawalbaruMOTOR.getText());
                }
            } else if (!trfawalbaruMOTOR.getText().equals("") && !trfjambaruMOTOR.getText().equals("")) {
                x = db.UpdateKendaraan("Motor", Integer.parseInt(trfawalbaruMOTOR.getText()), Integer.parseInt(trfjambaruMOTOR.getText()));
                if (!x) {
                    trfawalMOTOR.setText(trfawalbaruMOTOR.getText());
                    trfjamMOTOR.setText(trfjambaruMOTOR.getText());
                }
            }
        } else if (action.getSource() == btnUbahBUS) {
            boolean x;
            if (trfawalbaruBUS.getText().equals("") && !trfjambaruBUS.getText().equals("")) {
                x = db.UpdateKendaraan("Bus", Integer.parseInt(trfawalBUS.getText()), Integer.parseInt(trfjambaruBUS.getText()));
                if (!x) {
                    trfjamBUS.setText(trfjambaruBUS.getText());
                }
            } else if (trfjambaruBUS.getText().equals("") && !trfawalbaruBUS.getText().equals("")) {
                x = db.UpdateKendaraan("Bus", Integer.parseInt(trfjamBUS.getText()), Integer.parseInt(trfawalbaruBUS.getText()));
                if (!x) {
                    trfawalBUS.setText(trfawalbaruBUS.getText());
                }
            } else if (!trfawalbaruBUS.getText().equals("") && !trfjambaruBUS.getText().equals("")) {
                x = db.UpdateKendaraan("Bus", Integer.parseInt(trfawalbaruBUS.getText()), Integer.parseInt(trfjambaruBUS.getText()));
                if (!x) {
                    trfawalBUS.setText(trfawalbaruBUS.getText());
                    trfjamBUS.setText(trfjambaruBUS.getText());
                }
            }
        }
    }

    public void ShowLaporanSearch(String tglFrom, String tglTo) throws SQLException {
        rptNo.setCellValueFactory(new PropertyValueFactory("id"));
        rptPlat.setCellValueFactory(new PropertyValueFactory("platNomor"));
        rptJenis.setCellValueFactory(new PropertyValueFactory("jenisKendaraan"));
        rptTglIn.setCellValueFactory(new PropertyValueFactory("tanggalMasuk"));
        rptMasuk.setCellValueFactory(new PropertyValueFactory("jamMasuk"));
        rptTglOut.setCellValueFactory(new PropertyValueFactory("tanggalKeluar"));
        rptKeluar.setCellValueFactory(new PropertyValueFactory("jamKeluar"));
        rptBiaya.setCellValueFactory(new PropertyValueFactory("total"));
        ObservableList<Laporan> setList = db.showTableSearchBlank(tglFrom, tglTo);
        tblReport.setItems(setList);
        ResultSet r = db.ResultPendapatanBlank(tglFrom, tglTo);
        r.next();
        totalLaporan.setText(String.valueOf(r.getInt(1)));
        r.close();
    }

    public void ShowLaporan() throws SQLException {
        rptNo.setCellValueFactory(new PropertyValueFactory("id"));
        rptPlat.setCellValueFactory(new PropertyValueFactory("platNomor"));
        rptJenis.setCellValueFactory(new PropertyValueFactory("jenisKendaraan"));
        rptTglIn.setCellValueFactory(new PropertyValueFactory("tanggalMasuk"));
        rptMasuk.setCellValueFactory(new PropertyValueFactory("jamMasuk"));
        rptTglOut.setCellValueFactory(new PropertyValueFactory("tanggalKeluar"));
        rptKeluar.setCellValueFactory(new PropertyValueFactory("jamKeluar"));
        rptBiaya.setCellValueFactory(new PropertyValueFactory("total"));
        ObservableList<Laporan> setList = db.showTable();
        tblReport.setItems(setList);
        ResultSet r = db.ResultPendapatanDefault();
        r.next();
        totalLaporan.setText(String.valueOf(r.getInt(1)));
        r.close();
    }

    public void setData() throws SQLException {
        Karyawan kr = new Karyawan();

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

    }

    public void SetDataFront(String NIK) throws SQLException {
        kw.getDBKaryawan(db.Profilequery(NIK));
        Nik.setText(String.valueOf(kw.getNIK()));
        lblNama.setText(kw.getNamaLengkap());
        lblUser.setText(kw.getUsername());
    }

    public void setHargaKendaraan() {
        Kendaraan kr = new Kendaraan();
        try {
            ObservableList<Kendaraan> kendaraanList = kr.setKendaraanList();
            for (int i = 0; i < kendaraanList.size(); i++) {
                if (kendaraanList.get(i).getJenisKendaraan().equals("Mobil")) {
                    trfawalMOBIL.setText(String.valueOf(kendaraanList.get(i).getHargaAwal()));
                    trfjamMOBIL.setText(String.valueOf(kendaraanList.get(i).getHargaPerjam()));
                    kapasitasMOBIL.setText(String.valueOf(kendaraanList.get(i).getKapasitas()));
                } else if (kendaraanList.get(i).getJenisKendaraan().equals("Motor")) {
                    trfawalMOTOR.setText(String.valueOf(kendaraanList.get(i).getHargaAwal()));
                    trfjamMOTOR.setText(String.valueOf(kendaraanList.get(i).getHargaPerjam()));
                    kapasitasMOTOR.setText(String.valueOf(kendaraanList.get(i).getKapasitas()));
                } else if (kendaraanList.get(i).getJenisKendaraan().equals("Bus")) {
                    trfawalBUS.setText(String.valueOf(kendaraanList.get(i).getHargaAwal()));
                    trfjamBUS.setText(String.valueOf(kendaraanList.get(i).getHargaPerjam()));
                    kapasitasBUS.setText(String.valueOf(kendaraanList.get(i).getKapasitas()));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void ShowTable() throws SQLException, ClassNotFoundException {
        colNIK.setCellValueFactory(new PropertyValueFactory("NIK"));
        colName.setCellValueFactory(new PropertyValueFactory("NamaLengkap"));
        colUser.setCellValueFactory(new PropertyValueFactory("Username"));
        ObservableList<Karyawan> setList = FXCollections.observableArrayList();
        String query = "SELECT * from Karyawan";
        ResultSet rs = db.queryResult(query);
        while (rs.next()) {
            Karyawan kr = new Karyawan();
            kr.setNIK(rs.getInt(1));
            kr.setNamaLengkap(rs.getString(2));
            kr.setUsername(rs.getString(3));
            setList.add(kr);
        }
        tableUser.setItems(setList);
        tableUser.setOnMousePressed(new EventHandler<MouseEvent>() {
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
        rs.close();
    }

    public void ShowDetail(String nik) throws SQLException {
        Karyawan kr = new Karyawan();
        kr.getDBKaryawan(db.Profilequery(nik));
        edtNIK.setText(String.valueOf(kr.getNIK()));
        edtNama.setText(kr.getNamaLengkap());
        edtUser.setText(kr.getUsername());
        edtLevel.setText(kr.getLevel());
        edtGender.setText(kr.getGender());
        edtTgl.setText(kr.getTglLahir());
        edtNotelp.setText(kr.getNoTelp());
        edtAlamat.setText(kr.getAlamat());
    }

    ObservableList<String> cbGender = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
    ObservableList<String> cbStatus = FXCollections.observableArrayList("staff", "admin");
    ObservableList<String> cbKendaraan = FXCollections.observableArrayList("Motor", "Mobil", "Bus", " ");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.ShowTable();
            this.setHargaKendaraan();
            this.ShowLaporan();
            Gender.getItems().addAll(cbGender);
            Level.getItems().addAll(cbStatus);
            rptJenisKendaraan.getItems().addAll(cbKendaraan);
            rptJenisKendaraan.setValue(" ");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
