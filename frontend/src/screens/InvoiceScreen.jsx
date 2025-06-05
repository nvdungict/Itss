// screens/InvoiceScreen.jsx
import React, { useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../context/AppContext";

const InvoiceScreen = () => {
  const { cart, deliveryInfo, setInvoice } = useContext(AppContext);
  const navigate = useNavigate();

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const invoice = {
    customer: deliveryInfo,
    items: cart,
    total,
    createdAt: new Date().toISOString(),
  };

  useEffect(() => {
    setInvoice(invoice);
  }, []);

  const handleConfirm = () => {
    // simulate payment here
    navigate("/success");
  };

  return (
    <div className="p-6 space-y-4">
      <h1 className="text-xl font-bold">Invoice</h1>
      <div className="border p-4 rounded">
        <h2 className="font-semibold">Delivery Info</h2>
        <p>{deliveryInfo?.name}, {deliveryInfo?.address}, {deliveryInfo?.city}</p>
        <p>Phone: {deliveryInfo?.phone}</p>
      </div>

      <div className="border p-4 rounded">
        <h2 className="font-semibold mb-2">Items</h2>
        <ul>
          {cart.map((item) => (
            <li key={item.id}>{item.title} (x{item.quantity})</li>
          ))}
        </ul>
        <p className="font-bold mt-2">Total: ${total.toFixed(2)}</p>
      </div>

      <button
        onClick={handleConfirm}
        className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
      >
        Confirm Payment
      </button>
    </div>
  );
};

export default InvoiceScreen;
