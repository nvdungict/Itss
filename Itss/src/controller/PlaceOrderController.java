package controller;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.DeliveryInformation;
import entity.order.Order;
import entity.order.OrderMedia;
import entity.product.Product;
import services.DAOFactory;
import services.PaymentService;
import services.OrderService;
import services.UserService;
import services.user.LoginManager;

import java.util.List;

public class PlaceOrderController {

    private Cart currentCart;
    private Order currentOrder;
    private DeliveryInformation deliveryInfo;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final UserService userService;

    public PlaceOrderController(OrderService orderService, PaymentService paymentService, UserService userService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    // Phương thức bắt đầu quá trình đặt hàng
    public void requestToPlaceOrder(Cart cart) {
        // Kiểm tra tính khả dụng của sản phẩm trong giỏ hàng
        if (!checkProductAvailability(cart)) {
            System.out.println("Một số sản phẩm không có sẵn trong giỏ hàng.");
            return;
        }

        // Tạo form giao hàng
        createDeliveryForm(cart.getListMedia());

        // Tiến hành tạo đơn hàng
        createOrder(cart, deliveryInfo, 0.0); // Giả sử không có phí giao hàng ở đây
        saveOrder();

        // Gửi đến màn hình thanh toán
        PayOrderController payOrderController = new PayOrderController();
        payOrderController.askToPayOrder(new Invoice(deliveryInfo, currentOrder));
    }

    // Kiểm tra tính khả dụng của sản phẩm trong giỏ hàng
    private boolean checkProductAvailability(Cart cart) {
        for (CartMedia cartMedia : cart.getListMedia()) {
            Product product = cartMedia.getMedia();
            if (product.getStock() < cartMedia.getQuantity()) {
                System.out.println("Sản phẩm " + product.getProductName() + " không đủ số lượng.");
                return false;
            }
        }
        return true;
    }

    // Tạo form giao hàng
    private void createDeliveryForm(List<Product> orderedItems) {
        this.deliveryInfo = new DeliveryInformation();
        for (Product product : orderedItems) {
            checkProduct(product);  // Kiểm tra từng sản phẩm trong đơn hàng
        }
    }

    // Tạo đơn hàng từ giỏ hàng và thông tin giao hàng
    private void createOrder(Cart cart, DeliveryInformation deliveryInfo, double deliveryFee) {
        this.currentOrder = new Order();
        this.currentOrder.setDeliveryInfo(deliveryInfo);

        for (CartMedia cartMedia : cart.getListMedia()) {
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), cartMedia.getQuantity(), cartMedia.getPrice());
            orderMedia.setPrice(orderMedia.calculatePrice());
            currentOrder.getLstOrderMedia().add(orderMedia);
        }

        currentOrder.setTotalAmount(calculateTotalAmount(currentOrder, deliveryFee));
    }

    // Tính tổng số tiền của đơn hàng
    private double calculateTotalAmount(Order order, double deliveryFee) {
        double totalAmount = 0.0;
        for (OrderMedia orderMedia : order.getLstOrderMedia()) {
            totalAmount += orderMedia.getPrice();
        }
        return totalAmount + deliveryFee;
    }

    // Lưu đơn hàng vào cơ sở dữ liệu
    private void saveOrder() {
        try {
            orderService.saveOrder(currentOrder);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu đơn hàng: " + e.getMessage());
        }
    }

    // Gửi thông báo thành công đơn hàng
    public void notifySuccessOrder() {
        SuccessOrderScreen successOrderScreen = new SuccessOrderScreen();
        successOrderScreen.displayOrderInfo(currentOrder);
        successOrderScreen.showPaymentStatus("Đặt hàng thành công.");
    }
}
