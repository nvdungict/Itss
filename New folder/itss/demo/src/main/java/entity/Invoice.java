package entity;

public class Invoice {
    private Order order;
    private String invoiceId;

    public Invoice(String invoiceId, Order order) {
        this.invoiceId = invoiceId;
        this.order = order;
    }

    public void printInvoice() {
        System.out.println("Invoice ID: " + invoiceId);
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Total (excl. VAT): " + order.calculateTotalBeforeVAT());
        System.out.println("VAT (10%): " + order.calculateVAT());
        System.out.println("Delivery Fee: " + order.getDeliveryFee());
        System.out.println("Total (incl. VAT + Delivery): " + order.calculateGrandTotal());
    }

    public Order getOrder() {
        return order;
    }
}
