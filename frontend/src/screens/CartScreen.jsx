// screens/CartScreen.jsx
import React, { useContext } from "react";
import { AppContext } from "../context/AppContext";
import { useNavigate } from "react-router-dom";

const CartScreen = () => {
  const { cart, updateCart } = useContext(AppContext);
  const navigate = useNavigate();

  const handleRemoveItem = (id) => {
    updateCart(cart.filter((item) => item.id !== id));
  };

  const handleCheckout = () => {
    navigate("/delivery");
  };

  return (
    <div className="p-6">
      <h1 className="text-xl font-bold mb-4">Shopping Cart</h1>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <ul className="space-y-2">
          {cart.map((item) => (
            <li
              key={item.id}
              className="flex justify-between border p-2 rounded-md shadow"
            >
              <span>{item.title} (x{item.quantity})</span>
              <button
                onClick={() => handleRemoveItem(item.id)}
                className="text-red-500 hover:underline"
              >
                Remove
              </button>
            </li>
          ))}
        </ul>
      )}
      {cart.length > 0 && (
        <button
          onClick={handleCheckout}
          className="mt-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Proceed to Delivery
        </button>
      )}
    </div>
  );
};

export default CartScreen;
