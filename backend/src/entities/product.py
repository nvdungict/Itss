# src/product.py
from dataclasses import dataclass

@dataclass
class Product:
    id: int
    name: str
    price: float
    stock: int
    rush_eligible: bool = False

    def is_in_stock(self, quantity: int) -> bool:
        """Trả về True nếu đủ stock để lấy quantity."""
        return 0 <= quantity <= self.stock

    def reduce_stock(self, quantity: int):
        """Giảm stock đi quantity, hoặc raise ValueError nếu không đủ."""
        if not self.is_in_stock(quantity):
            raise ValueError(f"Not enough stock for product {self.id}")
        self.stock -= quantity

    def is_rush_eligible(self) -> bool:
        """Trả về True nếu sản phẩm cho phép rush delivery."""
        return self.rush_eligible
