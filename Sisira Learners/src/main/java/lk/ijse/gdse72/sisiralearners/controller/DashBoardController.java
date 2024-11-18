package lk.ijse.gdse72.sisiralearners.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private AnchorPane FIrstAnchorPane;

    @FXML
    private AnchorPane LoginAnchorPane;

    @FXML
    private ImageView LoginImage;

    @FXML
    private AnchorPane SideAnchorPane;

    @FXML
    private JFXButton btnCourse;

    @FXML
    private JFXButton btnEnrolment;

    @FXML
    private JFXButton btnInstructor;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnPrevious;

    @FXML
    private JFXButton btnRegistration;

    @FXML
    private JFXButton btnScedule;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnVehicle;

    @FXML
    private JFXButton btncheAssignment;

    @FXML
    private Label iblImage;

    @FXML
    private ImageView imageCourse;

    @FXML
    private ImageView imageEnrollment;

    @FXML
    private ImageView imageInstructor;

    @FXML
    private ImageView imagePayment;

    @FXML
    private ImageView imageReg;

    @FXML
    private ImageView imageScedule;

    @FXML
    private ImageView imageScheAssignment;

    @FXML
    private ImageView imageStu;

    @FXML
    private ImageView imageVehicle;

    @FXML
    private Label lblCourse;

    @FXML
    private Label lblInstructor;

    @FXML
    private Label lblPayment;

    @FXML
    private Label lblReg;

    @FXML
    private Label lblSchedule;

    @FXML
    private Label lblStu;

    @FXML
    private Label lblVehicle;
    @FXML
    private JFXButton btnUser;

    @FXML
    private ImageView logo;

    @FXML
    void btnCourseOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Course.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnCourse.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnEnrolmentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Enrollment.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnEnrolment.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnInstructorOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InstructorManage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnInstructor.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Payment.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnPayment.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnPreviousOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegisterForm.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnPrevious.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnRegistrationOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegistrationPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnRegistration.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSceduleOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Schedule.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnScedule.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnStudent.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnVehicleOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VehiclePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnVehicle.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    void btncheAssignmentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ScheduleAssignment.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btncheAssignment.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnUserOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserManage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnUser.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
