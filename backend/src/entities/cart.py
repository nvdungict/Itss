from dataclasses import dataclass
from typing import List
from src.entities.product import Product

@dataclass
class CartItem:
    product: Product
    quantity: int

class Cart:
    def __init__(self):
        self.items: List[CartItem] = []

    def add_item(self, product: Product, quantity: int):
        if quantity > product.stock:
            raise ValueError("Không đủ hàng trong kho.")
        self.items.append(CartItem(product, quantity))

    def remove_item(self, product_id: int):
        self.items = [ci for ci in self.items if ci.product.id != product_id]
    
    def view_cart(self):
        if not self.items:
            return "Giỏ hàng trống."
        cart_summary = ""
        subtotal = 0
        for item in self.items:
            item_total = item.total_price()
            subtotal += item_total
            cart_summary += f"{item.name} - {item.quantity} x {item.price} = {item_total} VND\n"
        cart_summary += f"Giá trị đơn hàng trước thuế: {subtotal} VND"
        return cart_summary
    
class CartWithVAT(Cart):
    def total_with_vat(self):
        total = 0
        for item in self.items:
            total += item.total_price() + item.total_vat()
        return total
