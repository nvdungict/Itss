from src.entities.delivery_form import DeliveryForm, InvalidDeliveryData
from src.entities.cart import Cart
from src.entities.order import Order

class PlaceRushOrderController:
    RUSH_FEE_PER_ITEM = 10.0

    def place_rush_order(self, cart: Cart, form: DeliveryForm) -> Order:
        delivery_info = form.validate()

        fee = 0.0
        for ci in cart.items:
            if not ci.product.rush_eligible:
                raise ValueError(f"Sản phẩm {ci.product.id} không phù hợp với giao hàng hỏa tốc.")
            fee += ci.quantity * self.RUSH_FEE_PER_ITEM

        order = Order(cart=cart, delivery=delivery_info, rush_fee=fee)
        return order
