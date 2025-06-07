package entity.cart;

import entity.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int userId;
    private List<CartMedia> listMedia;

    public Cart(int userId) {
        this.userId = userId;
        this.listMedia = new ArrayList<>();
    }

    public static Cart getCart(int userId) {
        // Giả sử bạn lấy giỏ hàng từ cơ sở dữ liệu dựa trên userId
        return new Cart(userId);
    }

    // Thêm một sản phẩm vào giỏ hàng
    public void addProductItem(Product product) {
        CartMedia cartMedia = findProductInCart(product);
        if (cartMedia == null) {
            this.listMedia.add(new CartMedia(product, 1, product.getPrice()));
        } else {
            cartMedia.setQuantity(cartMedia.getQuantity() + 1);  // Tăng số lượng nếu sản phẩm đã có trong giỏ
        }
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    public void removeProductItem(Product product) {
        CartMedia cartMedia = findProductInCart(product);
        if (cartMedia != null) {
            this.listMedia.remove(cartMedia);
        }
    }

    // Tìm kiếm sản phẩm trong giỏ hàng
    private CartMedia findProductInCart(Product product) {
        for (CartMedia cartMedia : listMedia) {
            if (cartMedia.getMedia().getProductId() == product.getProductId()) {
                return cartMedia;
            }
        }
        return null;
    }

    // Kiểm tra tính khả dụng của sản phẩm trong giỏ hàng (có đủ số lượng không)
    public boolean checkAvailabilityOfProduct() {
        for (CartMedia cartMedia : listMedia) {
            Product product = cartMedia.getMedia();
            if (product.getStock() < cartMedia.getQuantity()) {
                return false;  // Không đủ số lượng sản phẩm
            }
        }
        return true;
    }

    // Tính tổng giá trị giỏ hàng
    public double calculateTotalPrice() {
        double total = 0.0;
        for (CartMedia cartMedia : listMedia) {
            total += cartMedia.calculatePrice();
        }
        return total;
    }

    // Getter và Setter
    public List<CartMedia> getListMedia() {
        return listMedia;
    }

    public void setListMedia(List<CartMedia> listMedia) {
        this.listMedia = listMedia;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Xóa giỏ hàng sau khi đặt hàng thành công
    public void clearCart() {
        this.listMedia.clear();
    }

    // Getter để lấy số lượng sản phẩm trong giỏ hàng
    public int getItemCount() {
        int count = 0;
        for (CartMedia cartMedia : listMedia) {
            count += cartMedia.getQuantity();
        }
        return count;
    }
}
