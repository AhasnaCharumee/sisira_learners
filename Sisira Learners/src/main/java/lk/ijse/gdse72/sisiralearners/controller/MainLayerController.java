package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLayerController {

    @FXML
    private JFXButton SigninBtn;

    @FXML
    private ImageView imageSteering;

    @FXML
    private Label lblLearners;

    @FXML
    private Label lblSisira;

    @FXML
    public void initialize() {
        try {
            Image image = new Image(getClass().getResourceAsStream("/assets/images/drive/driving-school.png"));
            imageSteering.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Image not found: /assets/images/drive/driving-school.png");
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        try {
            Parent loginPage = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));

            Stage stage = (Stage) SigninBtn.getScene().getWindow();

            stage.setScene(new Scene(loginPage));
            stage.setTitle("Login Page - Sisira Learners");
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Page Load Failed");
            alert.setContentText("There was an issue loading the login page. Please try again.");
            alert.showAndWait();
        }
    }
}
