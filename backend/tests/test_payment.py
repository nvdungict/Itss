import pytest
from src.entities.payment import Invoice
from pytest import approx

def test_total_with_vat():
    invoice = Invoice(cart_total=100000, delivery_fee=0)
    # Use approx to handle floating-point precision
    assert invoice.total_with_vat() == approx(110000, rel=1e-9)

def test_final_amount():
    invoice = Invoice(cart_total=200000, delivery_fee=30000)
    # Calculate the expected result and use approx to handle floating-point precision
    expected_final_amount = 200000 * 1.1 + 30000
    assert invoice.final_amount() == approx(expected_final_amount, rel=1e-9)