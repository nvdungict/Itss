package app;

import controller.ViewCartController;
import entity.Cart;
import entity.CartMedia;
import entity.Product;
import entity.TestProduct;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class TestViewCartApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // 1. Create dummy products
        Product book = new TestProduct("P001", "Clean Code", 150000.0, "Book on coding");
        Product dvd = new TestProduct("P002", "Inception DVD", 80000.0, "Sci-fi Movie");
        // 2. Create CartMedia
        CartMedia item1 = new CartMedia(book, 2);
        CartMedia item2 = new CartMedia(dvd, 1);

        // 3. Create Cart and add items
        Cart cart = Cart.getInstance();
        cart.setCartItems(Arrays.asList(item1, item2));
        cart.calculateTotalPrice();

        // 4. Load FXML and inject Cart
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewcart.fxml"));
        Parent root = loader.load();

        ViewCartController controller = loader.getController();
        controller.setCart(cart); // inject cart here

        // 5. Show screen
        stage.setTitle("Test View Cart");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
