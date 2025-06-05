import pytest
import uuid

from src.entities.order import Order
from src.entities.cart import Cart
from src.entities.product import Product
from src.entities.delivery_form import DeliveryInfo

def make_cart():
    cart = Cart()
    cart.add_item(Product(1, "A", price=10.0, stock=5, rush_eligible=True), quantity=2)
    cart.add_item(Product(2, "B", price=20.0, stock=5, rush_eligible=True), quantity=1)
    return cart

def make_delivery_info():
    return DeliveryInfo(address="123 Main St", phone="0912345678")

def test_order_total_calculation_and_rush_flag():
    cart = make_cart()
    delivery = make_delivery_info()
    rush_fee = 15.0
    order = Order(cart=cart, delivery=delivery, rush_fee=rush_fee)

    # total = 2*10 + 1*20 + 15
    assert order.total == pytest.approx(10.0*2 + 20.0*1 + rush_fee)
    assert order.is_rush is True

def test_order_non_rush_flag():
    cart = make_cart()
    delivery = make_delivery_info()
    order = Order(cart=cart, delivery=delivery, rush_fee=0.0)
    assert order.total == pytest.approx(10.0*2 + 20.0*1)
    assert order.is_rush is False

def test_order_id_is_valid_uuid():
    cart = make_cart()
    delivery = make_delivery_info()
    order = Order(cart=cart, delivery=delivery, rush_fee=0.0)

    # Kiểm tra order.id có thể parse thành UUID
    uuid_obj = uuid.UUID(order.id)
    assert str(uuid_obj) == order.id
