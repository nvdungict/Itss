# src/order.py
from dataclasses import dataclass
from src.entities.cart import Cart
from src.entities.delivery_form import DeliveryInfo
import uuid

@dataclass
class Order:
    cart: Cart
    delivery: DeliveryInfo
    rush_fee: float
    id: str = None

    def __post_init__(self):
        self.id = str(uuid.uuid4())
    @property
    def total(self) -> float:
        items_total = sum(ci.product.price * ci.quantity for ci in self.cart.items)
        return items_total + self.rush_fee

    @property
    def is_rush(self) -> bool:
        return self.rush_fee > 0
