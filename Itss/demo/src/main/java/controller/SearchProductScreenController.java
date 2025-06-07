package controller;

import entity.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.ScreenNavigator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchProductScreenController {

    @FXML private TextField searchField;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> idColumn;
    @FXML private TableColumn<Product, String> titleColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;
    @FXML private TextField quantityField;
    @FXML private Button someButton;


    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId()));
        titleColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTitle()));
        priceColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getPrice()));
        quantityColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getQuantity()));

        // Mock data (You should load from database or DAO)
        productList.addAll(
            createSampleProduct("1", "Java Book", 100, 20),
            createSampleProduct("2", "Python Book", 120, 15),
            createSampleProduct("3", "C++ Book", 150, 10)
        );

        productTable.setItems(productList);
    }

    private Product createSampleProduct(String id, String title, double price, int quantity) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setPrice(price);
        book.setQuantity(quantity);
        return book;
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        if (keyword.isEmpty()) {
            productTable.setItems(productList);
        } else {
            List<Product> filtered = productList.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
            productTable.setItems(FXCollections.observableArrayList(filtered));
        }
    }

    @FXML
    private void handleAddToCart() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a product.");
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid quantity.");
            return;
        }

        if (qty <= 0 || qty > selected.getQuantity()) {
            showAlert("Quantity must be between 1 and " + selected.getQuantity());
            return;
        }

        CartMedia cartItem = new CartMedia(selected, qty);
        Cart.getInstance().addItem(cartItem);
        showAlert("Added to cart successfully.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
@FXML
private void handleGoToCart() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewCartScreen.fxml"));
        Parent root = loader.load();

        // Inject the cart into the ViewCartController
        ViewCartController controller = loader.getController();
        controller.setCart(Cart.getInstance());

        // Set the scene
        Stage stage = (Stage) someButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Cart");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
