package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.dto.tm.CourseDto;
import lk.ijse.gdse72.sisiralearners.model.CourseModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private JFXButton btnBack, btnDelete, btnReset, btnSave, btnUpdate;

    @FXML
    private TableColumn<CourseDto, String> colDesc, colDuration, colId, colName, colStatus;
    @FXML
    private TableColumn<CourseDto, Integer> colMax;
    @FXML
    private TableColumn<CourseDto, Double> colPrice;

    @FXML
    private Label lblDesc, lblDuration, lblId, lblMax, lblName, lblPrice, lblStatus;

    @FXML
    private TableView<CourseDto> tblCor;

    @FXML
    private JFXTextField txtDesc, txtDu, txtId, txtMax, txtName, txtPrice, txtStatus;

    private final CourseModel courseModel = new CourseModel();

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
        // Handle delete action
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtDu.clear();
        txtMax.clear();
        txtPrice.clear();
        txtStatus.clear();
        txtDesc.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("max_students"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws Exception {
        ObservableList<CourseDto> courseList = FXCollections.observableArrayList(courseModel.getAllCourses());
        tblCor.setItems(courseList);
    }
}
