import os
from typing import List, Optional
from sqlalchemy import create_engine, select
from sqlalchemy.orm import Session, sessionmaker

# Giả sử bạn đã định nghĩa model Product dưới dạng SQLAlchemy ORM:
from src.entities.product import Product  # import model của bạn từ đâu đó

class SearchController:
    def __init__(self, db_session: Session):
        self.db = db_session

    def search(self, query: str) -> List[Product]:
        stmt = select(Product).where(Product.name.ilike(f"%{query}%"))
        return self.db.execute(stmt).scalars().all()

    def filter_by_category(self, products: List[Product], category: str) -> List[Product]:
        return [p for p in products if p.category.lower() == category.lower()]

    def filter_by_price_range(
        self,
        products: List[Product],
        min_price: float,
        max_price: float
    ) -> List[Product]:

        return [p for p in products if min_price <= p.price <= max_price]

    def sort_by_rating(
        self,
        products: List[Product],
        descending: bool = True
    ) -> List[Product]:
    
        return sorted(products, key=lambda p: p.rating, reverse=descending)

    def search_and_filter(
        self,
        query: str,
        category: Optional[str] = None,
        min_price: Optional[float] = None,
        max_price: Optional[float] = None,
        sort_desc: bool = True
    ) -> List[Product]:
        
        products = self.search(query)
        if category:
            products = self.filter_by_category(products, category)
        if min_price is not None and max_price is not None:
            products = self.filter_by_price_range(products, min_price, max_price)
        return self.sort_by_rating(products, descending=sort_desc)

def get_session():
    DATABASE_URL = os.getenv('DATABASE_URL', 'postgresql+psycopg2://user:password@localhost:5432/mydb')
    engine = create_engine(
        DATABASE_URL,
        echo=False,
        pool_pre_ping=True,
        future=True
    )
    SessionLocal = sessionmaker(bind=engine, class_=Session, expire_on_commit=False)
    return SessionLocal()

if __name__ == "__main__":
    session = get_session()
    controller = SearchController(session)

    products = controller.search_and_filter(
        query="apple",
        category="Smartphones",
        min_price=500,
        max_price=1500,
        sort_desc=True
    )
    for p in products:
        print(p.get_detail())
