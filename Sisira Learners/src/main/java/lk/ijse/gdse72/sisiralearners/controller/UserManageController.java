package lk.ijse.gdse72.sisiralearners.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.dto.tm.UserDto;
import lk.ijse.gdse72.sisiralearners.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserManageController implements Initializable {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ChoiceBox<String> choice;  // Specified type to String for the role selection

    @FXML
    private TableColumn<UserDto, String> clId, clName, clEmail, clRole, clPassword;

    @FXML
    private Label lblName;

    @FXML
    private TableView<UserDto> tblUser;  // Specified type to UserDto

    @FXML
    private Text txtName;

    @FXML
    private Text txtUserID;

    private final UserModel userModel = new UserModel();

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
        // Implement delete button action
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        // Implement save button action
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        // Implement search button action
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Implement update button action
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the table columns
        clId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        clName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        clPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Initialize choice box with user roles
        choice.setItems(FXCollections.observableArrayList("Admin", "Manager", "Instructor").sorted());

        try {
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        // Fetch all users and refresh the table
        ObservableList<UserDto> users = FXCollections.observableArrayList(userModel.getAllUsers());
        tblUser.setItems(users);
    }
}
