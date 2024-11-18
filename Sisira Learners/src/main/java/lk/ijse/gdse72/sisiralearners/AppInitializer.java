package lk.ijse.gdse72.sisiralearners;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.gdse72.sisiralearners.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainLayer.fxml")));
            primaryStage.setTitle("Sisira Learners");
            primaryStage.setScene(new Scene(root,1456,767));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
