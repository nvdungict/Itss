package entity.media;

public class Product {
    private int productId;
    private String productName;
    private String category;  // Thêm thuộc tính category
    private double price;
    private int stock;

    // Constructor
    public Product(int productId, String productName, String category, double price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;  // Khởi tạo category
        this.price = price;
        this.stock = stock;
    }

    // Getter và Setter cho các thuộc tính
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;  // Lấy category
    }

    public void setCategory(String category) {
        this.category = category;  // Thiết lập category
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Phương thức kiểm tra tính khả dụng của sản phẩm
    public boolean isAvailable() {
        return stock > 0;
    }

    // Phương thức giảm số lượng sản phẩm trong kho khi có đơn hàng
    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("Không đủ số lượng sản phẩm trong kho!");
        }
    }

    // Phương thức hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category + 
               ", Price: " + price + ", Stock: " + stock;
    }
}
