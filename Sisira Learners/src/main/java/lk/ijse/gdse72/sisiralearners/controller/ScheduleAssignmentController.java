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
import lk.ijse.gdse72.sisiralearners.dto.tm.ScheduleAssignmentDto;
import lk.ijse.gdse72.sisiralearners.model.ScheduleAssignmentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleAssignmentController implements Initializable {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdat;

    @FXML
    private TableColumn<ScheduleAssignmentDto, String> colAssId;

    @FXML
    private TableColumn<ScheduleAssignmentDto, String> colScId;

    @FXML
    private TableColumn<ScheduleAssignmentDto, String> colInsId;

    @FXML
    private TableColumn<ScheduleAssignmentDto, String> colVehiId;

    @FXML
    private TableColumn<ScheduleAssignmentDto, String> colStId;

    @FXML
    private Label lblAssId;

    @FXML
    private Label lblInsId;

    @FXML
    private Label lblSCHId;

    @FXML
    private Label lblStId;

    @FXML
    private Label lblVhiId;

    @FXML
    private TableView<ScheduleAssignmentDto> tblASS;

    @FXML
    private JFXTextField txtAId;

    @FXML
    private JFXTextField txtInsId;

    @FXML
    private JFXTextField txtSTId;

    @FXML
    private JFXTextField txtScId;

    @FXML
    private JFXTextField txtVhiId;

    private ScheduleAssignmentModel scheduleAssignmentModel = new ScheduleAssignmentModel();

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
    void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    void btnUpdatOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAssId.setCellValueFactory(new PropertyValueFactory<>("assignment_id"));
        colScId.setCellValueFactory(new PropertyValueFactory<>("schedule_id"));
        colInsId.setCellValueFactory(new PropertyValueFactory<>("instructor_id"));
        colVehiId.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("student_id"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<ScheduleAssignmentDto> assignmentDtos = scheduleAssignmentModel.getAllAssignments();
        ObservableList<ScheduleAssignmentDto> assignmentList = FXCollections.observableArrayList(assignmentDtos);
        tblASS.setItems(assignmentList);
    }
}
