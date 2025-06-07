package entity;

public class CartMedia {
    private Product product;
    private int quantity;
    private double subtotal;

    public CartMedia(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
    this.quantity = quantity;
    updateSubtotal();
    }

    public void updateSubtotal() {
        this.subtotal = product.getPrice() * quantity;
    }
  
        

}
