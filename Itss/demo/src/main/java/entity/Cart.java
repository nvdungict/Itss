package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartMedia> cartItems = new ArrayList<>();
    private double total;
    private static Cart instance = new Cart();
    

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static Cart getInstance() {
        return instance;
    }

    public void addItem(CartMedia item) {
        cartItems.add(item);
    }

    public List<CartMedia> getCartItems() {
        return cartItems;
    }

    public void removeItem(CartMedia item) {
        cartItems.remove(item);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void setCartItems(List<CartMedia> items) {
        this.cartItems = items;
    }

 

    public void calculateTotalPrice() {
        total = cartItems.stream().mapToDouble(CartMedia::getSubtotal).sum();
    }

    public double getTotal() {
        return total;
    }

    public void addItem(Product product, int quantity) {
        cartItems.add(new CartMedia(product, quantity));
    }

    public void removeItem(String productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void updateQuantity(String productId, int quantity) {
        for (CartMedia item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item = new CartMedia(item.getProduct(), quantity); // recreate
                break;
            }
        }
    }


    public void clear() {
        cartItems.clear();
    }

    
    public double getTotalBeforeVAT() {
        return cartItems.stream().mapToDouble(CartMedia::getSubtotal).sum();
    }
    public double getTotalAfterVAT() {
        return getTotalBeforeVAT() * 1.1; // Assuming 10% VAT
    }
    public double getDeliveryFee() {
        return 30000; // Fixed delivery fee
    }
    public double getGrandTotal() {
        return getTotalAfterVAT() + getDeliveryFee();
    }
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
    public int getItemCount() {
        return cartItems.size();
    }


    
}
