package entity;

import java.util.*;

public class Order {
    private String orderId;
    private List<CartMedia> items;
    private String customerName;
    private String email;
    private String phone;
    private String province;
    private String address;
    private boolean isRushOrder;
    private double deliveryFee;
    private Date orderDate;

    public Order(String orderId, List<CartMedia> items) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = new Date();
    }

    public double calculateTotalBeforeVAT() {
        return items.stream().mapToDouble(CartMedia::getSubtotal).sum();
    }

    public double calculateVAT() {
        return calculateTotalBeforeVAT() * 0.1;
    }

    public double calculateTotalWithVAT() {
        return calculateTotalBeforeVAT() + calculateVAT();
    }

    public double calculateGrandTotal() {
        return calculateTotalWithVAT() + deliveryFee;
    }

    public String getOrderId() {
        return orderId;
    }
    public List<CartMedia> getItems() {
        return items;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isRushOrder() {
        return isRushOrder;
    }
    public void setRushOrder(boolean rushOrder) {
        isRushOrder = rushOrder;
    }
    public double getDeliveryFee() {
        return deliveryFee;
    }
    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    

    // Getters and setters (omit for brevity)
}
