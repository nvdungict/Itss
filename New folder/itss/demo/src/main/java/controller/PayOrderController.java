package controller;

import entity.*;
import utils.VNPayInterface;

import java.util.UUID;

public class PayOrderController {

    public Invoice processOrder(Cart cart, String name, String email, String phone, String address, String province, boolean rushOrder) {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, cart.getCartItems());

        order.setCustomerName(name);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setProvince(province);
        order.setRushOrder(rushOrder);

        // Example flat delivery fee (can add DeliveryCalculator later)
        double deliveryFee = rushOrder ? 10000 : 25000;
        order.setDeliveryFee(deliveryFee);

        double totalAmount = order.calculateGrandTotal();

        // Payment
        TransactionInfo tx = VNPayInterface.processPayment(totalAmount, "Order #" + orderId);

        // Create Invoice
        String invoiceId = "INV-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Invoice invoice = new Invoice(invoiceId, order);

        // Optionally, save to DB or send email

        return invoice;
    }
}
