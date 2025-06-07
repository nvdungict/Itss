package entity;

public class TestProduct extends Product {

    public TestProduct(String id, String title, double price, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = "Test Category";
        this.value = 0;           // optional
        this.quantity = 0;        // optional
        this.barcode = "000000";  // optional
    }

    @Override
    public String getType() {
        return "TestProduct";
    }
}
