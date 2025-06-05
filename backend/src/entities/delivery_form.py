from dataclasses import dataclass

class InvalidDeliveryData(Exception):
    pass

@dataclass
class DeliveryInfo:
    address: str
    phone: str

class DeliveryForm:
    def __init__(self, address: str, phone: str):
        self.address = address
        self.phone = phone

    def validate(self) -> DeliveryInfo:
        if not self.address or not isinstance(self.address, str):
            raise InvalidDeliveryData("Invalid address")
        if not self.phone or not (isinstance(self.phone, str) and len(self.phone) == 10 and self.phone.isdigit()):
            raise InvalidDeliveryData("Invalid phone")
        return DeliveryInfo(address=self.address, phone=self.phone)
