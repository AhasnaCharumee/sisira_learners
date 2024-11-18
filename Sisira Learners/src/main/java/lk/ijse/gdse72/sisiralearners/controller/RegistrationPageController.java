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
import lk.ijse.gdse72.sisiralearners.Util.CrudUtil;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;
import lk.ijse.gdse72.sisiralearners.dto.tm.RegistrationDto;
import lk.ijse.gdse72.sisiralearners.model.RegistrationModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationPageController implements Initializable {

    @FXML
    private JFXButton btnAssign;

    @FXML
    private JFXButton btnCour;

    @FXML
    private JFXButton btnDash;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEnroll;

    @FXML
    private JFXButton btnExam;

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
    private JFXButton btnStu;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnVehi;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TableColumn<RegistrationDto, String> colAddress;

    @FXML
    private TableColumn<RegistrationDto, Double> colBalance;

    @FXML
    private TableColumn<RegistrationDto, Date> colBirth;

    @FXML
    private TableColumn<RegistrationDto, Date> colDate;
    @FXML
    private TableColumn<RegistrationDto, String> colContact;

    @FXML
    private TableColumn<RegistrationDto, String> colCourse;

    @FXML
    private TableColumn<RegistrationDto, String> colEmail;

    @FXML
    private TableColumn<RegistrationDto, String> colGender;

    @FXML
    private TableColumn<RegistrationDto, String> colName;

    @FXML
    private TableColumn<RegistrationDto, String> colNic;

    @FXML
    private TableColumn<RegistrationDto, Double> colPaid;

    @FXML
    private TableColumn<RegistrationDto, String> colPaymentStatus;

    @FXML
    private TableColumn<RegistrationDto, Double> colPrice;

    @FXML
    private TableColumn<RegistrationDto, String> colRegistrationId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBala;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblCourseName;

    @FXML
    private Label lblDateofBirth;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblNIC;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPaid;

    @FXML
    private Label lblPaymentStatus;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblRegistrationDate;

    @FXML
    private Label lblRegistrationId;

    @FXML
    private TableView<RegistrationDto> tblReg;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtBala;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPaid;

    @FXML
    private JFXTextField txtPaymentStatus;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtRegistrationDate;

    @FXML
    private JFXTextField txtRegistrationID;


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
            Stage stage = (Stage) btnDash.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Dashboard.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        RegistrationDto selectedItem = tblReg.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this registration?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                boolean isDeleted = RegistrationModel.deleteRegistrationById(selectedItem.getRegistration_id());

                if (isDeleted) {
                    tblReg.getItems().remove(selectedItem);
                    new Alert(Alert.AlertType.INFORMATION, "Registration deleted successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete registration from the database first delete payment table this REG ID!").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a registration to delete!").show();
        }
    }

    @FXML
    void btnEnrollOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Enrollment.fxml"));
            Stage stage = (Stage) btnEnroll.getScene().getWindow();
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
    void btnResetOnAction(ActionEvent event) {
        resetFields();
    }

    private void resetFields() {
        txtAddress.clear();
        txtRegistrationDate.clear();
        txtName.clear();
        txtRegistrationID.clear();
        txtPrice.clear();
        txtPaymentStatus.clear();
        txtBala.clear();
        txtContact.clear();
        txtEmail.clear();
        txtGender.clear();
        txtNIC.clear();
        txtPaid.clear();



    }
    public boolean isRegistrationIdExists(String registration_id) {
        String query = "SELECT COUNT(*) FROM registration WHERE registration_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, registration_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // If count > 0, ID exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    RegistrationDto registrationDto = new RegistrationDto();
    @FXML
    void btnSaveOnAction(ActionEvent event) {


        try {
            String registration_id = txtRegistrationID.getText();
            String name = txtName.getText();
            String gender = txtGender.getText();
            String address = txtAddress.getText();

            String nic = txtNIC.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String course_name = choiceBox.getValue();
            String payment_status = txtPaymentStatus.getText();

            double paid = 0;
            double price = 0;

            try {
                paid = Double.parseDouble(txtPaid.getText());
                price = Double.parseDouble(txtPrice.getText());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid numeric input for Paid or Price!").show();
                return;
            }
            LocalDate dob = datePicker.getValue();
            if (dob == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a valid Date of Birth.").show();
                return; // Stop further execution
            }

            java.sql.Date date_of_birth = java.sql.Date.valueOf(dob);            Date registration_date = Date.valueOf(LocalDate.now()); // Current registration date

            if (isValidInput(registration_id,name, gender, address, date_of_birth, nic, email,course_name, paid, price, price - paid,registration_date,payment_status,contact)) {
                RegistrationDto registrationDTO = new RegistrationDto(
                        registration_id, name, gender, address, date_of_birth, nic,
                        email, course_name, paid, price, price - paid, registration_date,
                        payment_status, contact
                );

                // Save to the database
                boolean isSaved = new RegistrationModel().saveRegistration(registrationDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Registration saved successfully!").show();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save registration!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please correct the highlighted fields.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
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
    void btnStudentOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/StudentPage.fxml"));
            Stage stage = (Stage) btnStu.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Student.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            // Fetch data from input fields
            String registration_id = txtRegistrationID.getText();
            String name = txtName.getText();
            String nic = txtNIC.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String gender = txtGender.getText();
            String address = txtAddress.getText();
            String course_name = choiceBox.getValue(); // Assuming this is a dropdown/choice box
            String payment_status = txtPaymentStatus.getText();

            // Parse numeric fields
            double paid, price;
            try {
                paid = Double.parseDouble(txtPaid.getText());
                price = Double.parseDouble(txtPrice.getText());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid numeric input for Paid or Price!").show();
                return;
            }

            // Calculate balance
            double balance = price - paid;

            // Parse dates
            LocalDate dob = datePicker.getValue();
            if (dob == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a valid Date of Birth.").show();
                return;
            }
            java.sql.Date date_of_birth = java.sql.Date.valueOf(dob);

            java.sql.Date registration_date = java.sql.Date.valueOf(txtRegistrationDate.getText()); // Ensure this field is filled

            // Validate inputs
            if (isValidInput(registration_id, name, gender, address, date_of_birth, nic, email, course_name, paid,price,balance,registration_date,payment_status,contact)) {
                // Create DTO
                RegistrationDto registrationDto = new RegistrationDto(
                        registration_id, name, gender, address, date_of_birth, nic, email,
                        course_name, paid, price, balance, registration_date, payment_status, contact
                );

                // Update the database
                boolean isUpdated = RegistrationModel.updateRegistration(registrationDto);

                // Feedback to user
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Registration updated successfully!").show();
                    refreshPage(); // Clear fields or refresh the table
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update registration!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please correct the highlighted fields.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnVehiOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/VehiclePage.fxml"));
            Stage stage = (Stage) btnVehi.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading VehiclePage.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<RegistrationDto> registrationDTOS = new RegistrationModel().getAllRegistrations();

        ObservableList<RegistrationDto> registrationList = FXCollections.observableArrayList(registrationDTOS);

        tblReg.setItems(registrationList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setItems(FXCollections.observableArrayList(
                "Defensive Driving Course 15000", "Automatic Transmission Driving Course 12000",
                "Manual Transmission Driving Course 13000", "Refresher Driving Course 8000",
                "Advanced Driving Course 20000", "Commercial Driving License Preparation Course 25000",
                "Elderly Driver Assistance Course 10000", "Night Driving Course 7000",
                "Highway Driving Course 9000", "City Driving 8000", "Basic Maneuvering 4000"
        ));

        colRegistrationId.setCellValueFactory(new PropertyValueFactory<>("registration_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        colBirth.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("registration_date"));

        // Add the new columns for price, paid, and balance
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        tblReg.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displaySelectedRowData(newSelection);
            }
        });
    }
    private void displaySelectedRowData(RegistrationDto registration) {
        txtRegistrationID.setText(registration.getRegistration_id());
        txtName.setText(registration.getName());
        txtNIC.setText(registration.getNic());
        txtEmail.setText(registration.getEmail());
        txtContact.setText(registration.getContact());
        txtGender.setText(registration.getGender());
        txtAddress.setText(registration.getAddress());
        choiceBox.setValue(registration.getCourse_name());
        txtPaymentStatus.setText(registration.getPayment_status());
        txtPaid.setText(String.valueOf(registration.getPaid())); // Displaying paid amount
        txtPrice.setText(String.valueOf(registration.getPrice())); // Displaying total price
        txtBala.setText(String.valueOf(registration.getBalance())); // Displaying balance
        datePicker.setValue(registration.getDate_of_birth().toLocalDate()); // Setting date_of_birth to DatePicker
        txtRegistrationDate.setText(registration.getRegistration_date().toString()); // Setting registration_date
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();
    }
    private boolean isValidInput(String registration_id, String name, String gender, String address, Date date_of_birth,
                                 String nic, String email, String course_name, double paid,double price,double balance,Date registration_date,String payment_status,String contact) {
        boolean isValid = true;

        if (name.isEmpty()) {
            txtName.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtName.setStyle("-fx-border-color: none;");
        }

        if (nic.isEmpty()) {
            txtNIC.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtNIC.setStyle("-fx-border-color: none;");
        }

        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            txtEmail.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtEmail.setStyle("-fx-border-color: none;");
        }

        if (contact.isEmpty() || !contact.matches("^[0-9]{10}$")) {
            txtContact.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtContact.setStyle("-fx-border-color: none;");
        }

        if (gender.isEmpty()) {
            txtGender.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtGender.setStyle("-fx-border-color: none;");
        }

        if (address.isEmpty()) {
            txtAddress.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtAddress.setStyle("-fx-border-color: none;");
        }

        if (course_name == null || course_name.isEmpty()) {
            choiceBox.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            choiceBox.setStyle("-fx-border-color: none;");
        }

        if (payment_status.isEmpty()) {
            txtPaymentStatus.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtPaymentStatus.setStyle("-fx-border-color: none;");
        }

        return isValid;
    }


}
