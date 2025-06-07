module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens app to javafx.fxml;
    opens controller to javafx.fxml;
    opens boundary to javafx.fxml;

    exports app;
}
