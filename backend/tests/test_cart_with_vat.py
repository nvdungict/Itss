import pytest
from src.entities.cart import CartWithVAT, CartItem

def test_calculate_correct_subtotal_with_vat():
    cart = CartWithVAT()
    item = CartItem("Phone", 10000000, 1, 10)  # 10% VAT
    cart.add_item(item)
    expected_output = 11000000  # 10,000,000 + 10% VAT = 11,000,000
    assert cart.total_with_vat() == expected_output

def test_handle_multiple_items_with_different_vat_rates():
    cart = CartWithVAT()
    item1 = CartItem("Phone", 10000000, 1, 10)  # 10% VAT
    item2 = CartItem("Tablet", 5000000, 2, 5)   # 5% VAT
    cart.add_item(item1)
    cart.add_item(item2)
    expected_output = 21500000  # 10% VAT on Phone + 5% VAT on Tablet
    assert cart.total_with_vat() == expected_output
