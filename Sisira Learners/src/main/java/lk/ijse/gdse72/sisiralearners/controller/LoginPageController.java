package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    private AnchorPane FIrstAnchorPane;

    @FXML
    private AnchorPane LoginAnchorPane;

    @FXML
    private JFXButton LoginBtn;

    @FXML
    private JFXTextField NameTxt;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private Label lblMessage;

    private Connection connection;

    public LoginPageController() {
        // Get database connection from DBConnection class
        this.connection = DBConnection.getInstance().getConnection();
    }

    @FXML
    void navigateToDashboard(ActionEvent event) {
        String user_name = NameTxt.getText();
        String password = txtPass.getText();

        if (validateLogin(user_name, password)) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
                Stage stage = (Stage) FIrstAnchorPane.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            NameTxt.clear();
            txtPass.clear();
        }
    }

    @FXML
    void navigateToRegisterForm(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RegisterForm.fxml"));
            Stage stage = (Stage) FIrstAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if there's a match
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
