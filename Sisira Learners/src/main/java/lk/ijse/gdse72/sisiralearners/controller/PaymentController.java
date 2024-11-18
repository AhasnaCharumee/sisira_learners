package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import lk.ijse.gdse72.sisiralearners.dto.tm.PaymentDto;
import lk.ijse.gdse72.sisiralearners.model.PaymentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private JFXButton btnAssign, btnCour, btnDash1, btnDelet, btnEnroll1, btnExam1, btnIns, btnReg, btnReset, btnSave, btnSche, btnStudent, btnUpdate, btnVehi1;

    @FXML
    private TableColumn<PaymentDto, String> colBala, colDate, colId, colMethod, colPaid, colPrice, colRegId, colStatus;

    @FXML
    private Label lblBalan, lblDate, lblId, lblPaid, lblPmethod, lblPrice, lblRegId, lblStatus;

    @FXML
    private TableView<PaymentDto> tblPay;

    @FXML
    private JFXTextField txtBala, txtDate, txtId, txtMethod, txtPaid, txtPrice, txtReg, txtStatus;

    private PaymentModel paymentModel = new PaymentModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setup the TableView columns using PropertyValueFactory
        colId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        colPaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colBala.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        colRegId.setCellValueFactory(new PropertyValueFactory<>("registration_id"));

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Add listener to handle row selection
        tblPay.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displaySelectedPaymentData(newValue);
            }
        });
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String payment_id = txtId.getText();
        String payment_method = txtMethod.getText();
        double paid = Double.parseDouble(txtPaid.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double balance = Double.parseDouble(txtBala.getText());
        String payment_status = txtStatus.getText();
        String registration_id = txtReg.getText();

        Date payment_date = Date.valueOf(LocalDate.now());
        if (txtId.getText().isEmpty() || txtMethod.getText().isEmpty() || txtReg.getText().isEmpty() ||
                txtStatus.getText().isEmpty() || txtPaid.getText().isEmpty() || txtPrice.getText().isEmpty() ||
                txtBala.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "you can do this in registration table ").show();
            return;
        }
        if (isValidPaymentInput(payment_id, payment_date, payment_method, paid, price, balance, payment_status, registration_id)) {
            PaymentDto paymentDTO = new PaymentDto(
                    payment_id, payment_date, payment_method, paid, price, balance, payment_status, registration_id
            );

            boolean isSaved = paymentModel.savePayment(paymentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully!").show();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please correct highlighted fields.").show();
        }
        String value = "paid,price"; // or any other source
        if (value != null && !value.isEmpty()) {
            try {
                double number = Double.parseDouble(value);
                // Continue with your logic
            } catch (NumberFormatException e) {
                // Handle invalid number format
                System.out.println("Invalid number format");
            }
        } else {
            // Handle empty input
            System.out.println("Input cannot be empty");
        }

    }

    public boolean isValidPaymentInput(String payment_id, Date payment_date, String payment_method, double paid, double price, double balance, String payment_status, String registration_id) {
        if (payment_id == null || payment_id.isEmpty()) return false;
        if (payment_date == null) return false;
        if (payment_method == null || payment_method.isEmpty()) return false;
        if (paid < 0) return false;
        if (price <= 0) return false;
        if (payment_status == null || payment_status.isEmpty()) return false;
        if (registration_id == null || registration_id.isEmpty()) return false;
        if (balance != (price - paid)) return false;
        return true;
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDto> paymentDtos = paymentModel.getAllPayments();
        ObservableList<PaymentDto> paymentList = FXCollections.observableArrayList(paymentDtos);
        tblPay.setItems(paymentList);
    }

    private void displaySelectedPaymentData(PaymentDto paymentDto) {
        txtReg.setText(paymentDto.getRegistration_id());
        txtId.setText(paymentDto.getPayment_id());
        txtPaid.setText(String.valueOf(paymentDto.getPaid()));
        txtPrice.setText(String.valueOf(paymentDto.getPrice()));
        txtBala.setText(String.valueOf(paymentDto.getBalance()));
        txtMethod.setText(paymentDto.getPayment_method());
        txtStatus.setText(paymentDto.getPayment_status());
        txtDate.setText(paymentDto.getPayment_date().toString());
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
        /* Implementation for Assign button action */ }

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
    } /* Implementation for Course button action */ }

    @FXML
    void btnDashOnAction(ActionEvent event) {try {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Stage stage = (Stage) btnDash1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        System.err.println("Error loading Dashboard.fxml: " + e.getMessage());
        e.printStackTrace();
    } /* Implementation for Dashboard button action */ }


    @FXML
    void  btnDeletOnAction(ActionEvent event) {
        PaymentDto selectedStudent = tblPay.getSelectionModel().getSelectedItem();
        if (tblPay.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment record to delete.").show();
            return;
        }
        if (selectedStudent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this student?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                boolean isDeleted = paymentModel.deleteStudentById(selectedStudent.getPayment_id());

                if (isDeleted) {
                    tblPay.getItems().remove(selectedStudent);
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
    void btnEnrollOnAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Enrollment.fxml"));
        Stage stage = (Stage) btnEnroll1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        System.err.println("Error loading Enrollment.fxml: " + e.getMessage());
        e.printStackTrace();
    } /* Implementation for Enroll button action */
    }

    @FXML
    void btnExamOnAction(ActionEvent event) { /* Implementation for Exam button action */ }

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
    } /* Implementation for Instructor button action */ }

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
    } /* Implementation for Register button action */ }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        resetFields();/* Implementation for Reset button action */
    }
    private void resetFields() {
        txtReg.clear();
        txtId.clear();
        txtPaid.clear();
        txtPrice.clear();
        txtBala.clear();
        txtMethod.clear();
        txtStatus.clear();
        txtDate.clear();
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
    } /* Implementation for Student button action */ }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String payment_id = txtId.getText();
        String payment_method = txtMethod.getText();
        String registration_id = txtReg.getText();
        String payment_status = txtStatus.getText();
        double paid, price;
        if (tblPay.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment record to update.").show();
            return;
        }
        // Check if any mandatory field is empty
        if (payment_id.isEmpty() || payment_method.isEmpty() || registration_id.isEmpty() ||
                payment_status.isEmpty() || txtPaid.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").show();
            return;
        }

        // Try to parse numeric values for paid and price
        try {
            paid = Double.parseDouble(txtPaid.getText());
            price = Double.parseDouble(txtPrice.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Invalid numeric values. Please check Paid and Price fields.").show();
            return;
        }

        // Calculate the balance as price - paid
        double balance = price - paid;

        // Payment date validation
        Date payment_date = null;
        try {
            payment_date = Date.valueOf(txtDate.getText());
        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.WARNING, "Invalid date format. Please use YYYY-MM-DD.").show();
            return;
        }

        // Create a PaymentDto object with the calculated balance
        PaymentDto paymentDto = new PaymentDto(
                payment_id, payment_date, payment_method, paid, price, balance, payment_status, registration_id
        );

        // Debugging: Print out the paymentDto values to check if they're correctly assigned
        System.out.println("PaymentDTO Values: ");
        System.out.println("Payment ID: " + paymentDto.getPayment_id());
        System.out.println("Payment Method: " + paymentDto.getPayment_method());
        System.out.println("Paid: " + paymentDto.getPaid());
        System.out.println("Price: " + paymentDto.getPrice());
        System.out.println("Balance: " + paymentDto.getBalance());
        System.out.println("Payment Status: " + paymentDto.getPayment_status());
        System.out.println("Registration ID: " + paymentDto.getRegistration_id());

        // Call the update method from the PaymentModel
        boolean isUpdated = paymentModel.updatePayment(paymentDto);

        // Display result based on whether the update was successful or not
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Payment updated successfully!").show();
            refreshTable();  // Refresh the table to reflect the changes
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update payment!").show();
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
    }/* Implementation for Vehicle button action */ }
}
