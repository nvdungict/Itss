package boundary;

import entity.Invoice;
import entity.Order;

public class InvoiceScreen {

    public void showInvoice(Invoice invoice) {
        Order order = invoice.getOrder();

        System.out.println("\n=== AIMS - INVOICE ===");
        invoice.printInvoice();
        System.out.println("Thank you for shopping with AIMS!");
    }
}
