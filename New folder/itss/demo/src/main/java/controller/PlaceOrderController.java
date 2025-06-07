package controller;

import entity.Cart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class PlaceOrderController {

    private Cart currentCart;

    public PlaceOrderController() {
        currentCart = Cart.getInstance(); 
    }

    // Button handler to open ViewCart screen
    @FXML
    private void handleViewCartButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewCartScreen.fxml"));
            Parent root = loader.load();

            ViewCartController controller = loader.getController();
            controller.setCart(currentCart); // ✅ pass the cart here

            Stage stage = new Stage();
            stage.setTitle("View Cart");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Optional getter to expose Cart
    public Cart getCart() {
        return currentCart;
    }
}
