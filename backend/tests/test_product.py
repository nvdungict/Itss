import pytest
from src.entities.product import Product

def test_product_creation():
    p = Product(id=1, name="Widget", price=99.9, stock=5, rush_eligible=True)
    assert p.id == 1
    assert p.name == "Widget"
    assert p.price == 99.9
    assert p.stock == 5
    assert p.rush_eligible is True

@pytest.mark.parametrize("qty,expected", [
    (0, True),
    (5, True),
    (6, False),
    (-1, False),
])
def test_is_in_stock(qty, expected):
    p = Product(id=1, name="X", price=10.0, stock=5)
    assert p.is_in_stock(qty) is expected

def test_reduce_stock_success():
    p = Product(id=2, name="Gadget", price=20.0, stock=10)
    p.reduce_stock(3)
    assert p.stock == 7

def test_reduce_stock_fail():
    p = Product(id=2, name="Gadget", price=20.0, stock=2)
    with pytest.raises(ValueError):
        p.reduce_stock(5)

def test_is_rush_eligible():
    p1 = Product(id=3, name="A", price=1.0, stock=1, rush_eligible=True)
    p2 = Product(id=4, name="B", price=1.0, stock=1, rush_eligible=False)
    assert p1.is_rush_eligible()
    assert not p2.is_rush_eligible()
