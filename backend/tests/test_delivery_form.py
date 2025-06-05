import pytest
from src.entities.delivery_form import DeliveryForm, DeliveryInfo, InvalidDeliveryData

def test_valid_delivery_form_creates_info_object():
    form = DeliveryForm(address="123 A St", phone="0912345678")
    info = form.validate()
    assert isinstance(info, DeliveryInfo)
    assert info.address == "123 A St"
    assert info.phone == "0912345678"

@pytest.mark.parametrize("address,phone", [
    ("", "0912345678"),              # Missing address
    ("123 A St", "123"),             # Phone too short
    (None, "0912345678"),            # Address is None
    ("123 A St", None),              # Phone is None
    ("123 A St", ""),                # Empty phone
    ("123 A St", "abcdefghij"),      # Non-numeric phone
    ("123 A St", "09123!@#$%"),      # Special characters in phone
    ("   ", "0912345678"),           # Address is only spaces
    ("123 A St", "012345678901234")  # Phone too long
])

def test_invalid_delivery_form_raises(address, phone):
    with pytest.raises(InvalidDeliveryData):
        DeliveryForm(address=address, phone=phone).validate()
