package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.dto.tm.VehicleDto;
import lk.ijse.gdse72.sisiralearners.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VehiclePageController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableView<VehicleDto> tblVhi;

    @FXML
    private TableColumn<VehicleDto, String> colVehiId;

    @FXML
    private TableColumn<VehicleDto, String> colVehiType;

    @FXML
    private TableColumn<VehicleDto, String> colVehiClass;

    @FXML
    private TableColumn<VehicleDto, String> colMaintenance;

    @FXML
    private TableColumn<VehicleDto, String> colLiPlate;

    @FXML
    private Label lblId;

    @FXML
    private Label lblClass;

    @FXML
    private Label lblLicense;

    @FXML
    private Label lblRecord;

    @FXML
    private Label lblType;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtClass;

    @FXML
    private JFXTextField txtRecords;
    @FXML
    private JFXButton btnBack;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (
                IOException e) {
            e.printStackTrace();

        }
    }
    @FXML
    private JFXTextField txtPlate;

    private VehicleModel vehicleModel = new VehicleModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colVehiId.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colVehiType.setCellValueFactory(new PropertyValueFactory<>("vehicle_type"));
        colVehiClass.setCellValueFactory(new PropertyValueFactory<>("vehicle_class"));
        colMaintenance.setCellValueFactory(new PropertyValueFactory<>("maintenance_records"));
        colLiPlate.setCellValueFactory(new PropertyValueFactory<>("license_plate"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtId.clear();
        txtType.clear();
        txtClass.clear();
        txtRecords.clear();
        txtPlate.clear();
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDto> vehicleDtos = vehicleModel.getAllVehicles();
        ObservableList<VehicleDto> vehicleList = FXCollections.observableArrayList(vehicleDtos);
        tblVhi.setItems(vehicleList);
    }
}
