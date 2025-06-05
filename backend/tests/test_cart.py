import pytest
from src.entities.cart import Cart, CartItem
from src.entities.product import Product

def test_add_and_remove_single_item():
    cart = Cart()
    p = Product(id=1, name="Laptop", price=20000000, stock=5, rush_eligible=True)
    cart.add_item(p, quantity=2)
    assert len(cart.items) == 1
    assert cart.items[0].quantity == 2
    assert cart.view_cart() == "Laptop - 1 x 20000000 = 20000000 VND\nSubtotal: 20000000 VND"

    cart.remove_item(product_id=1)
    assert cart.items == []
    assert cart.view_cart() == "Cart is empty."

def test_add_and_remove_multiple_items():
    cart = Cart()
    p1 = Product(id=1, name="Laptop", price=20000000, stock=5, rush_eligible=True)
    p2 = Product(id=2, name="Phone", price=10000000, stock=10, rush_eligible=False)
    cart.add_item(p1, quantity=2)
    cart.add_item(p2, quantity=3)

    assert len(cart.items) == 2
    assert cart.items[0].quantity == 2
    assert cart.items[1].quantity == 3
    assert cart.view_cart() == (
        "Laptop - 2 x 20000000 = 40000000 VND\n"
        "Phone - 3 x 10000000 = 30000000 VND\n"
        "Subtotal: 70000000 VND"
    )

def test_view_empty_cart():
    cart = Cart()
    assert cart.view_cart() == "Please add a product."

def test_remove_nonexistent_item():
    cart = Cart()
    p1 = Product(id=1, name="Laptop", price=20000000, stock=5, rush_eligible=True)
    cart.add_item(p1, quantity=2)
    cart.remove_item(product_id=2)  # Nonexistent item
    assert len(cart.items) == 1
    assert cart.items[0].quantity == 2

def test_cannot_add_more_than_stock():
    cart = Cart()
    p = Product(id=1, name="A", price=100, stock=1, rush_eligible=True)
    with pytest.raises(ValueError):
        cart.add_item(p, quantity=2)
