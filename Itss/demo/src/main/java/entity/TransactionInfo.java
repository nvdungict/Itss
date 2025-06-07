package entity;

import java.util.Date;

public class TransactionInfo {
    private String transactionId;
    private String content;
    private Date transactionDate;
    private double amount;

    public TransactionInfo(String transactionId, String content, double amount) {
        this.transactionId = transactionId;
        this.content = content;
        this.amount = amount;
        this.transactionDate = new Date();
    }

    // Getters and setters omitted for brevity
}
