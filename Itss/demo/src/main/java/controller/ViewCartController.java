package controller;

import entity.Cart;
import entity.CartMedia;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.converter.IntegerStringConverter;


public class ViewCartController implements Initializable {

    private Cart cart;

    @FXML private TableView<CartMedia> cartTable;
    @FXML private TableColumn<CartMedia, String> titleColumn;
    @FXML private TableColumn<CartMedia, Double> priceColumn;
    @FXML private TableColumn<CartMedia, Integer> quantityColumn;
    @FXML private TableColumn<CartMedia, Double> subtotalColumn;

    private ObservableList<CartMedia> observableCartItems;

    public void setCart(Cart cart) {
        this.cart = cart;
        observableCartItems = FXCollections.observableArrayList(cart.getCartItems());
        cartTable.setItems(observableCartItems);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Delay setting items until `setCart()` is called

        titleColumn.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getProduct().getTitle()));

        priceColumn.setCellValueFactory(data -> 
            new SimpleDoubleProperty(data.getValue().getProduct().getPrice()).asObject());

        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Enable quantity editing
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(event -> {
              CartMedia item = event.getRowValue();
    int newQty = event.getNewValue();

    item.setQuantity(newQty);

    // Recalculate subtotal manually
    double price = item.getProduct().getPrice();
    item.getSubtotal();  // Cập nhật giá trị

    // Optionally update total price in cart
    cart.calculateTotalPrice();

    // Refresh bảng để cập nhật Subtotal hiển thị
    cartTable.refresh();
            
        });

        subtotalColumn.setCellValueFactory(data -> 
            new SimpleDoubleProperty(data.getValue().getSubtotal()).asObject());
    }

    @FXML
    private void handleClose() {
        cartTable.getScene().getWindow().hide();
    }
    @FXML
private void handleOpenSearch() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchProductScreen.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Search and Add Product");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        showAlert("Cannot open Search Product screen.");
    }
}

private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(message);
    alert.showAndWait();
}

}
