from typing import Optional
from sqlalchemy.orm import Session
from src.entities.product import Product

class ViewProductController:
    def __init__(self, db_session: Session):
        self.db = db_session
        self.current_product: Optional[Product] = None

    def get_product_detail(self, product_id: int) -> Optional[Product]:
        product = self.db.get(Product, product_id)
        if not product:
            self.screen.display_error(f"Không tìm thấy sản phẩm.")
            return None
        self.current_product = product
        return product

