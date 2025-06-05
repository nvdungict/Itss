from typing import Optional
from sqlalchemy.orm import Session
from src.entities.payment import Invoice
from src.entities.transaction_info import TransactionInfo
class PayOrderController:
    def __init__(
        self,
        db_session: Session,
    ):
        self.db = db_session
        self.current_transaction: Optional[TransactionInfo] = None

    def ask_to_pay_order(self, invoice_id: int) -> None:
        invoice = self.db.get(Invoice, invoice_id)
        if not invoice:
            self.screen.notify_error(f"Giao dịch {invoice_id} không tồn tại.")
            return

        transaction = TransactionInfo(
            invoice_id=invoice.id,
            amount=invoice.amount,
            status='INITIATED',
            payment_method=''  
        )
        self.current_transaction = transaction
        self.db.add(transaction)
        self.db.commit()

        self.screen.confirm_payment(invoice_id)

    def pay_order(self, invoice_id: int, method: str) -> bool:
        txn = self.current_transaction
        if not txn or txn.invoice_id != invoice_id:
            self.screen.notify_error("Giao dịch không hợp lệ.")
            return False

        txn.payment_method = method
        success = False
        if method.upper() == 'VNPAY':
            success = self.vnpay.pay_order(invoice_id=invoice_id, amount=txn.amount)
        else:
            self.screen.notify_error(f"Phương thức {method} chưa được hỗ trợ.")
            return False

        txn.status = 'SUCCESS' if success else 'FAILED'
        self.save_payment_result(txn)

        if success:
            self.screen.display_success_order(invoice_id)
        else:
            self.screen.notify_error(f"Thanh toán cho hóa đơn {invoice_id} thất bại.")

        return success

    def save_payment_result(self, transaction: TransactionInfo) -> None:
 
        if transaction.validate_transaction():
            self.db.add(transaction)
            self.db.commit()
        else:
            self.db.rollback()
            self.screen.notify_error("Hóa đơn không hợp lệ, lưu thất bại.")
