from dataclasses import dataclass

@dataclass
class Invoice:
    VAT_RATE = 0.1

    def __init__(self, cart_total, delivery_fee):
        self.cart_total = cart_total
        self.delivery_fee = delivery_fee

    def total_with_vat(self):
        return self.cart_total * (1 + self.VAT_RATE)

    def final_amount(self):
        return self.total_with_vat() + self.delivery_fee