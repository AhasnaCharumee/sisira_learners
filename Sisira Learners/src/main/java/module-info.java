module sisiraLearners {
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires javafx.controls;
    requires static lombok;
    requires jBCrypt;
    requires AnimateFX;
    requires java.desktop;

    // Add this line to export the controller package
    opens lk.ijse.gdse72.sisiralearners.dto.tm to javafx.base;
    exports lk.ijse.gdse72.sisiralearners.controller to javafx.fxml;
    exports lk.ijse.gdse72.sisiralearners to javafx.graphics;

    opens lk.ijse.gdse72.sisiralearners.controller to javafx.fxml; // Needed if you are using reflection
}


