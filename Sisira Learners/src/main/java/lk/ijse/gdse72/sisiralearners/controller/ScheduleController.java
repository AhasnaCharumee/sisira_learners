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
import lk.ijse.gdse72.sisiralearners.dto.tm.ScheduleDto;
import lk.ijse.gdse72.sisiralearners.model.ScheduleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<ScheduleDto, String> colId;

    @FXML
    private TableColumn<ScheduleDto, String> colName;

    @FXML
    private TableColumn<ScheduleDto, java.sql.Date> colDate;

    @FXML
    private TableColumn<ScheduleDto, String> colStime;

    @FXML
    private TableColumn<ScheduleDto, String> colEtime;

    @FXML
    private TableColumn<ScheduleDto, Integer> colLimit;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEtime;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSlimit;

    @FXML
    private Label lblStime;

    @FXML
    private TableView<ScheduleDto> tblSchedule;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtEnTime;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtLimit;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTime;

    private ScheduleModel scheduleModel = new ScheduleModel();

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

        }    }

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
    void btnUpdateOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("schedule_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colName.setCellValueFactory(new PropertyValueFactory<>("schedule_name"));
        colStime.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        colEtime.setCellValueFactory(new PropertyValueFactory<>("end_time"));
        colLimit.setCellValueFactory(new PropertyValueFactory<>("student_limit"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<ScheduleDto> scheduleDtos = scheduleModel.getAllSchedules();
        ObservableList<ScheduleDto> scheduleTMS = FXCollections.observableArrayList(scheduleDtos);
        tblSchedule.setItems(scheduleTMS);
    }
}
