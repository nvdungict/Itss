package utils;

import entity.TransactionInfo;

import java.util.UUID;

public class VNPayInterface {

    public static TransactionInfo processPayment(double amount, String description) {
        // Simulate payment with VNPay
        System.out.println("Redirecting to VNPay...");

        // Fake transaction response
        String transactionId = UUID.randomUUID().toString();
        String content = "[VNPay] Payment for: " + description;

        System.out.println("Payment successful with transaction ID: " + transactionId);
        return new TransactionInfo(transactionId, content, amount);
    }
}
