package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenNavigator {

    public static void switchScreen(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenNavigator.class.getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
