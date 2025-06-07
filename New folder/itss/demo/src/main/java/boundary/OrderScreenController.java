package boundary;

import controller.PayOrderController;
import entity.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class OrderScreenController {

    @FXML private TextField nameField, emailField, phoneField, addressField, provinceField;
    @FXML private TableView<CartMedia> cartTable;
    @FXML private TableColumn<CartMedia, String> titleColumn;
    @FXML private TableColumn<CartMedia, Double> priceColumn;
    @FXML private TableColumn<CartMedia, Integer> quantityColumn;
    @FXML private TableColumn<CartMedia, Double> subtotalColumn;
    @FXML private TextArea invoiceOutput;

    private Cart cart = Cart.getInstance();
    private ObservableList<CartMedia> observableCartItems;

    @FXML
    public void initialize() {
        // Setup test product
        Book book = new Book();
        book.setId("B001");
        book.setTitle("Clean Code");
        book.setPrice(100000);
        book.setValue(80000);
        cart.addItem(book, 2);

        ObservableList<CartMedia> observableCartItems =
        FXCollections.observableArrayList(Cart.getInstance().getCartItems());
        cartTable.setItems(observableCartItems);

        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduct().getTitle()));
        priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getProduct().getPrice()).asObject());
        quantityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantity()).asObject());
        subtotalColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSubtotal()).asObject());
        // Make quantity editable
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(event -> {
            CartMedia item = event.getRowValue();
            item = new CartMedia(item.getProduct(), event.getNewValue()); // recreate with updated quantity
            cart.getCartItems().set(event.getTablePosition().getRow(), item);
            refreshCart();
        });
    }

    public void refreshCart() {
        if (observableCartItems == null) {
            observableCartItems = FXCollections.observableArrayList();
            cartTable.setItems(observableCartItems);
        }
    observableCartItems.setAll(cart.getCartItems());
    }


    @FXML
    public void handleRemoveItem() {
        CartMedia selected = cartTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.removeItem(selected.getProduct().getId());
            refreshCart();
        }
    }

    @FXML
    public void handlePayOrder() {
        PayOrderController controller = new PayOrderController();
        Invoice invoice = controller.processOrder(
                cart,
                nameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                provinceField.getText(),
                false
        );

        StringBuilder sb = new StringBuilder();
        sb.append("INVOICE ID: ").append(invoice.getOrder().getOrderId()).append("\n");
        sb.append("Customer: ").append(invoice.getOrder().getCustomerName()).append("\n");
        sb.append("Total (excl. VAT): ").append(invoice.getOrder().calculateTotalBeforeVAT()).append("\n");
        sb.append("VAT: ").append(invoice.getOrder().calculateVAT()).append("\n");
        sb.append("Delivery: ").append(invoice.getOrder().getDeliveryFee()).append("\n");
        sb.append("Total Payable: ").append(invoice.getOrder().calculateGrandTotal()).append("\n");
        sb.append("Thank you!");

        invoiceOutput.setText(sb.toString());
    }
}
