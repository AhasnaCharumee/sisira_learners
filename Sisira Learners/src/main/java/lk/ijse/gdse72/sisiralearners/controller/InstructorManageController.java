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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.dto.tm.InstructorDto;
import lk.ijse.gdse72.sisiralearners.model.InstructorModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InstructorManageController implements Initializable {
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnDelete, btnReset, btnSave, btnSendEmail, btnUpdate;

    @FXML
    private TableView<InstructorDto> tblInst;

    @FXML
    private TableColumn<InstructorDto, String> colInstructorID, colName, colStatus, colAddress, colTelephone, colVehicleclass, colSalary, colEmail;

    @FXML
    private JFXTextField txtInstructorID, txtName, txtStatus, txtAddress, txtTelephone, txtVehicleclass, txtSalary, txtEmail;

    private InstructorModel instructorModel = new InstructorModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructor_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colVehicleclass.setCellValueFactory(new PropertyValueFactory<>("vehicle_class"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<InstructorDto> instructorDtos = instructorModel.getAllInstructors();
        ObservableList<InstructorDto> instructorList = FXCollections.observableArrayList(instructorDtos);
        tblInst.setItems(instructorList);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    void btnSendEmailOnAction(ActionEvent event) {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

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
}
