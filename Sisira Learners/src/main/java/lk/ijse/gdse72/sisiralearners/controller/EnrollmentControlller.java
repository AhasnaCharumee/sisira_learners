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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.dto.tm.EnrollmentDto;
import lk.ijse.gdse72.sisiralearners.model.EnrollmentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnrollmentControlller implements Initializable {

    @FXML
    private JFXButton btnAssign;

    @FXML
    private JFXButton btnCour;

    @FXML
    private JFXButton btnDash1;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnExam1;

    @FXML
    private JFXButton btnIns;

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXButton btnReg;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSche;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnVehi1;

    @FXML
    private TableColumn<EnrollmentDto, String> colCourse;

    @FXML
    private TableColumn<EnrollmentDto, String> colEnId;

    @FXML
    private TableColumn<EnrollmentDto, java.sql.Date> colEnd;

    @FXML
    private TableColumn<EnrollmentDto, java.sql.Date> colEnroll;

    @FXML
    private TableColumn<EnrollmentDto, String> colPay;

    @FXML
    private TableColumn<EnrollmentDto, String> colSid;

    @FXML
    private TableColumn<EnrollmentDto, java.sql.Date> colStart;

    @FXML
    private TableColumn<EnrollmentDto, String> colStatus;



    @FXML
    private Label iblId;

    @FXML
    private Label lblCorId;

    @FXML
    private Label lblEnDate;

    @FXML
    private Label lblEndDate;

    @FXML
    private Label lblPayStatus;

    @FXML
    private Label lblSId;

    @FXML
    private Label lblStDa;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<EnrollmentDto> tblEnroll;

    @FXML
    private JFXTextField txtCor;

    @FXML
    private JFXTextField txtEnDate;

    @FXML
    private JFXTextField txtEnId;

    @FXML
    private JFXTextField txtEnd;

    @FXML
    private JFXTextField txtPay;

    @FXML
    private JFXTextField txtSid;

    @FXML
    private JFXTextField txtStart;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    void btnAssignOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ScheduleAssignment.fxml"));
            Stage stage = (Stage) btnAssign.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading ScheduleAssignment.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Course.fxml"));
            Stage stage = (Stage) btnCour.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Course.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnDashOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
            Stage stage = (Stage) btnDash1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Dashboard.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    void btnScheduleOnAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        Stage stage = (Stage) btnSche.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        System.err.println("Error loading Schedule.fxml: " + e.getMessage());
        e.printStackTrace();
    } /* Implementation for Schedule button action */ }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/view/StudentPage.fxml"));
        Stage stage = (Stage) btnStudent.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        System.err.println("Error loading Payment.fxml: " + e.getMessage());
        e.printStackTrace();
    }  }

    @FXML
    void  btnDeleteOnAction(ActionEvent event) {
        EnrollmentDto selectedStudent = tblEnroll.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this student?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                boolean isDeleted = enrollmentModel.deleteEnrollmentById(selectedStudent.getEnrollment_id());

                if (isDeleted) {
                    tblEnroll.getItems().remove(selectedStudent);
                    new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete student!").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No student selected for deletion!").show();
        }
    }

    @FXML
    void btnExamOnAction(ActionEvent event) {

    }

    @FXML
    void btnInsOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/InstructorManage.fxml"));
            Stage stage = (Stage) btnIns.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Instructor.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));
            Stage stage = (Stage) btnPay.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Enrollment.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnRegOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/RegistrationPage.fxml"));
            Stage stage = (Stage) btnReg.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Student.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        resetFields();
    }
    private void resetFields() {
        txtCor.clear();
        txtEnDate.clear();
        txtEnId.clear();
        txtEnd.clear();
        txtPay.clear();
        txtSid.clear();
        txtStatus.clear();
        txtStart.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String enrollment_id = txtEnId.getText();
        String student_id = txtSid.getText();
        String course_id = txtCor.getText();
        String status = txtStatus.getText();
        String payment_status = txtPay.getText();

        Date enrollment_date = null;
        Date start_date = null;
        Date end_date = null;

        try {
            if (!txtEnDate.getText().isEmpty()) {
                enrollment_date = Date.valueOf(txtEnDate.getText());
            }
            if (!txtStart.getText().isEmpty()) {
                start_date = Date.valueOf(txtStart.getText());
            }
            if (!txtEnd.getText().isEmpty()) {
                end_date = Date.valueOf(txtEnd.getText());
            }
        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid date format. Please use YYYY-MM-DD.").show();
            return;
        }

        if (isValidEnrollmentInput(enrollment_id, student_id, course_id,enrollment_date, status, payment_status,start_date,end_date)) {
            EnrollmentDto enrollmentDTO = new EnrollmentDto(
                    enrollment_id,
                    student_id,
                    course_id,
                    enrollment_date,
                    status,
                    payment_status,
                    start_date,
                    end_date
            );

            boolean isSaved = enrollmentModel.saveEnrollment(enrollmentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Enrollment saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save enrollment!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please correct highlighted fields.").show();
        }
    }


    private boolean isValidEnrollmentInput(String enrollment_id, String student_id, String course_id, Date enrollment_date, String status, String payment_status,Date start_date,Date end_date) {
        if (enrollment_id.isEmpty() || student_id.isEmpty() || course_id.isEmpty() || status.isEmpty() || payment_status.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All fields are required.").show();
            return false;
        }

        if (enrollment_date == null) {
            new Alert(Alert.AlertType.WARNING, "Enrollment Date is required and must be valid.").show();
            return false;
        }

        if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("Dropped")) {
            new Alert(Alert.AlertType.WARNING, "Status must be Active, Completed, or Dropped.").show();
            return false;
        }

        if (!payment_status.equalsIgnoreCase("Paid") && !payment_status.equalsIgnoreCase("Pending") && !payment_status.equalsIgnoreCase("Unpaid")) {
            new Alert(Alert.AlertType.WARNING, "Payment Status must be Paid, Pending, or Unpaid.").show();
            return false;
        }

        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Check if any required fields are empty
        if (txtEnId.getText().isEmpty() || txtCor.getText().isEmpty()) {
            showAlert("Validation Error", "Enrollment ID and Course ID are required!");
            return;
        }

        // Validate Start Date, End Date, and Status
        String startDate = txtStart.getText();
        String endDate = txtEnDate.getText();
        String status = txtStatus.getText();

        if (startDate.isEmpty() || endDate.isEmpty() || status.isEmpty()) {
            showAlert("Validation Error", "Start date, End date, and Status must be provided!");
            return;
        }

        // Convert the text dates into SQL Date format (if valid)
        Date startSqlDate = null;
        Date endSqlDate = null;

        try {
            startSqlDate = Date.valueOf(startDate); // Convert from String to Date
            endSqlDate = Date.valueOf(endDate);     // Convert from String to Date
        } catch (IllegalArgumentException e) {
            showAlert("Date Format Error", "Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setEnrollment_id(txtEnId.getText());
        enrollmentDto.setCourse_id(txtCor.getText());
        enrollmentDto.setStatus(status); // Set status from the TextField
        enrollmentDto.setStart_date(startSqlDate);
        enrollmentDto.setEnd_date(endSqlDate);

        boolean isUpdated = enrollmentModel.updateEnrollment(enrollmentDto);

        if (isUpdated) {
            showAlert("Success", "Enrollment updated successfully!");

            refreshEnrollmentTable();
        } else {
            showAlert("Error", "Failed to update enrollment. Please try again.");
        }
    }
    private void refreshEnrollmentTable() {

    }

    private EnrollmentModel enrollmentModel = new EnrollmentModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEnId.setCellValueFactory(new PropertyValueFactory<>("enrollment_id"));
        colSid.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colEnroll.setCellValueFactory(new PropertyValueFactory<>("enrollment_date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPay.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("end_date"));


        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblEnroll.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displaySelectedEnrollmentData(newValue);
            }
        });
    }
    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<EnrollmentDto> enrollmentDtos = enrollmentModel.getAllEnrollments();
        ObservableList<EnrollmentDto> enrollmentList = FXCollections.observableArrayList(enrollmentDtos);
        tblEnroll.setItems(enrollmentList);
    }
    private void displaySelectedEnrollmentData(EnrollmentDto enrollmentDto) {
        txtEnId.setText(enrollmentDto.getEnrollment_id());
        txtSid.setText(enrollmentDto.getStudent_id());       // Student ID
        txtCor.setText(enrollmentDto.getCourse_id());        // Course ID
        txtEnDate.setText(enrollmentDto.getEnrollment_date().toString()); // Enrollment Date
        txtStatus.setText(enrollmentDto.getStatus());        // Enrollment Status
        txtPay.setText(enrollmentDto.getPayment_status());   // Payment Status
        txtStart.setText(enrollmentDto.getStart_date().toString()); // Start Date
        txtEnd.setText(enrollmentDto.getEnd_date() != null ? enrollmentDto.getEnd_date().toString() : ""); // End Date (handle null)
    }
    @FXML
    void btnVehiOnAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/view/VehiclePage.fxml"));
        Stage stage = (Stage) btnVehi1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        System.err.println("Error loading VehiclePage.fxml: " + e.getMessage());
        e.printStackTrace();
    }
    }


}
