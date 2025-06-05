from datetime import datetime
from sqlalchemy import Column, Integer, Float, String, DateTime, ForeignKey
from sqlalchemy.orm import relationship, validates

from src.entities.payment import Base, Invoice  

class TransactionInfo(Base):
    __tablename__ = 'transaction_info'

    transaction_id = Column(Integer, primary_key=True, autoincrement=True)
    invoice_id = Column(Integer, ForeignKey('invoices.id'), nullable=False)
    amount = Column(Float, nullable=False)
    status = Column(String(20), nullable=False, default='INITIATED')
    payment_method = Column(String(30), nullable=True)
    created_at = Column(DateTime, default=datetime.utcnow, nullable=False)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow, nullable=False)

    invoice = relationship('Invoice', back_populates='transactions')

    def save(self, session):
        session.add(self)
        session.commit()

    def update_status(self, new_status: str, session):
        self.status = new_status
        self.updated_at = datetime.utcnow()
        session.add(self)
        session.commit()

    def validate_transaction(self) -> bool:
        valid_status = {'INITIATED', 'SUCCESS', 'FAILED'}
        if self.amount is None or self.amount <= 0:
            return False
        if self.status not in valid_status:
            return False
        return True
