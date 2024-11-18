package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private AnchorPane FIrstAnchorPane;

    @FXML
    private AnchorPane LoginAnchorPane;

    @FXML
    private JFXTextField RegNameTxt;

    @FXML
    private AnchorPane SideAnchorPane;

    @FXML
    private JFXButton btnSignup;

    @FXML
    private ImageView imageLogo;

    @FXML
    private ImageView imageRegister;

    @FXML
    private Text lblCreate;

    @FXML
    private Label lblPass;

    @FXML
    private Rectangle loginRectangle;

    @FXML
    private JFXTextField regPasswordTxt;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtRole;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblName;

    @FXML
    public void initialize() {
        btnSignup.setOnAction(event -> registerUser());
    }

    private void registerUser() {
        String userId = txtId.getText().trim();
        String name = RegNameTxt.getText().trim();
        String email = txtEmail.getText().trim();
        String password = regPasswordTxt.getText().trim();
        String role = txtRole.getText().trim();
        if (userId.isEmpty()) {
            showAlert("User ID cannot be empty.");
            return;
        }
        if (name.isEmpty()) {
            showAlert("Name cannot be empty.");
            return;
        }
        if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert("Please enter a valid email address.");
            return;
        }
        if (password.isEmpty() || password.length() < 6) {
            showAlert("Password must be at least 6 characters long.");
            return;
        }
        if (role.isEmpty()) {
            showAlert("Role cannot be empty.");
            return;
        }

        String sql = "INSERT INTO user (user_id, user_name, email, password, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("User registered successfully!");
                loadDashboard(); // Load the dashboard after successful registration
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error occurred during registration.");
        }
    }

    private void loadDashboard() {
        try {
            Parent dashboard = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Stage stage = (Stage) btnSignup.getScene().getWindow(); // Get the current stage
            stage.setScene(new Scene(dashboard));
            stage.setTitle("Dashboard - Sisira Learners");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load the dashboard.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void regPasswordTxtOnKeyReleased(KeyEvent keyEvent) {
        if (!regPasswordTxt.getText().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")) {
            regPasswordTxt.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblPass.setText("Should contain minimum eight characters with special character!");
        }
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        if (!RegNameTxt.getText().matches("^[A-Za-z\\s]*$")) {
            RegNameTxt.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblName.setText("This filed can not contain numeric values!");
        }
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblEmail.setText("Invalid Email Format!");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
    }
    public void btnResetOnAction(ActionEvent actionEvent) {
        txtId.clear();

        regPasswordTxt.clear();
        RegNameTxt.clear();
        txtRole.clear();

        txtEmail.clear();


        lblPass.setText("");
    }

}
