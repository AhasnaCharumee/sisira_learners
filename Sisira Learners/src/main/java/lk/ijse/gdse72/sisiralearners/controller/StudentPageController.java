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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.dto.tm.StudentDto;
import lk.ijse.gdse72.sisiralearners.model.StudentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {

    @FXML
    private TableColumn<StudentDto, Date> ColStDate;

    @FXML
    private JFXButton btnAssign;

    @FXML
    private JFXButton btnCour;

    @FXML
    private JFXButton btnDash1;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEmail;

    @FXML
    private JFXButton btnEnroll1;

    @FXML
    private JFXButton btnExam1;

    @FXML
    private JFXButton btnIns;

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSche;

    @FXML
    private JFXButton btnReg;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnVehi1;

    @FXML
    private TableColumn<StudentDto, String> colContact;

    @FXML
    private TableColumn<StudentDto, String> colEmail;

    @FXML
    private TableColumn<StudentDto, String> colName;

    @FXML
    private TableColumn<StudentDto, String> colPayment;

    @FXML
    private TableColumn<StudentDto, String> colRegId;

    @FXML
    private TableColumn<StudentDto, String> colStudentId;

    @FXML
    private ImageView imgStu;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPayment;

    @FXML
    private Label lblRegId;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private TableView<StudentDto> tblStudent;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPaymentStatus;

    @FXML
    private JFXTextField txtRegId;

    @FXML
    private JFXTextField txtStDate;

    @FXML
    private JFXTextField txtStId;
    private final StudentModel studentModel = new StudentModel();

    @FXML
    void EmailBtnOnAction(ActionEvent event) {

    }

    @FXML
    void ResetBtnOnAction(ActionEvent event) {
        resetFields();
    }

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
    void btnEnrollOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Enrollment.fxml"));
            Stage stage = (Stage) btnEnroll1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Enrollment.fxml: " + e.getMessage());
            e.printStackTrace();
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
            System.err.println("Error loading Payment.fxml: " + e.getMessage());
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

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        StudentDto selectedStudent = tblStudent.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this student?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                boolean isDeleted = studentModel.deleteStudentById(selectedStudent.getStudent_id());

                if (isDeleted) {
                    tblStudent.getItems().remove(selectedStudent);
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
    void saveBtnOnAction(ActionEvent event) {
        try {
            String student_id = txtStId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String payment_status = txtPaymentStatus.getText();
            String contact = txtContact.getText();
            String registration_id = txtRegId.getText();

            Date start_day;
            try {
                start_day = Date.valueOf(txtStDate.getText()); // Assuming the enrollment date is in yyyy-MM-dd format
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid date format. Please use yyyy-MM-dd.").show();
                return;
            }

            if (isValidInput(student_id, name, email, payment_status, contact, start_day, registration_id)) {
                StudentDto studentDto = new StudentDto(
                        student_id, name, email, payment_status, contact, start_day, registration_id
                );

                boolean isSaved = studentModel.saveStudent(studentDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Student saved successfully!").show();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save student!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please correct highlighted fields.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }
    private boolean isValidInput(String student_id, String name, String email, String payment_status, String contact, Date start_day, String registration_id) {
        boolean isValid = true;

        // Validate Student ID
        if (student_id == null || student_id.isEmpty()) {
            txtStId.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtStId.setStyle("-fx-border-color: none;");
        }

        // Validate Name
        if (name == null || name.isEmpty()) {
            txtName.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtName.setStyle("-fx-border-color: none;");
        }

        // Validate Email
        if (email == null || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            txtEmail.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtEmail.setStyle("-fx-border-color: none;");
        }

        // Validate Payment Status
        if (payment_status == null || payment_status.isEmpty()) {
            txtPaymentStatus.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtPaymentStatus.setStyle("-fx-border-color: none;");
        }

        // Validate Contact
        if (contact == null || !contact.matches("^[0-9]{10}$")) {
            txtContact.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtContact.setStyle("-fx-border-color: none;");
        }

        // Validate Start Date
        if (start_day == null) {
            txtStDate.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtStDate.setStyle("-fx-border-color: none;");
        }

        // Validate Registration ID
        if (registration_id == null || registration_id.isEmpty()) {
            txtRegId.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtRegId.setStyle("-fx-border-color: none;");
        }

        return isValid;
    }



   /* @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            // Get values from the input fields
            String studentId = txtStId.getText();  // Foreign key reference

            String status = txtStatus.getText();

            // Convert text inputs to java.sql.Date objects
            java.sql.Date enrollmentDate = txtEnrollmentDate.getText().isEmpty() ? null : java.sql.Date.valueOf(txtEnrollmentDate.getText());
            java.sql.Date startDate = txtStDate.getText().isEmpty() ? null : java.sql.Date.valueOf(txtStDate.getText());

            // Call the model method to update specific course details
            boolean result = StudentModel.updateStudentDetails(studentId, enrollmentDate, status, startDate);

            if (result) {
                // Show success message
                System.out.println("Course details updated successfully.");
            } else {
                // Show failure message
                System.out.println("Failed to update course details.");
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure TableView columns
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ColStDate.setCellValueFactory(new PropertyValueFactory<>("start_day"));
        colRegId.setCellValueFactory(new PropertyValueFactory<>("registration_id"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Listen for row selection
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displaySelectedStudentData(newValue);
            }
        });
    }
    private void displaySelectedStudentData(StudentDto studentDto) {
        // StudentDto object එකේ විශේෂ data TextFields වලට set කිරීම
        txtStId.setText(studentDto.getStudent_id());
        txtName.setText(studentDto.getName());
        txtEmail.setText(studentDto.getEmail());
        txtPaymentStatus.setText(studentDto.getPayment_status());
        txtContact.setText(studentDto.getContact());
        if (studentDto.getStart_day() != null) {
            txtStDate.setText(studentDto.getStart_day().toString());
        } else {
            txtStDate.setText(""); // Null values handle කරන්න
        }
        txtRegId.setText(studentDto.getRegistration_id());

    }

    private void resetFields() {
        txtStId.clear();
        txtName.clear();
        txtContact.clear();
        txtRegId.clear();
        txtEmail.clear();
        txtPaymentStatus.clear();
        txtStDate.clear();
    }
    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDto> studentList = studentModel.getAllStudents();
        ObservableList<StudentDto> observableList = FXCollections.observableArrayList(studentList);
        tblStudent.setItems(observableList);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {
        resetFields();
        refreshTable();
    }

}
