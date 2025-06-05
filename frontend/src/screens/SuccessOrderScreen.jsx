// screens/SuccessOrderScreen.jsx
import React from "react";
import { useNavigate } from "react-router-dom";

const SuccessOrderScreen = () => {
  const navigate = useNavigate();

  return (
    <div className="p-6 text-center">
      <h1 className="text-2xl font-bold text-green-600">Order Placed Successfully!</h1>
      <p className="mt-2">You will receive an email confirmation shortly.</p>
      <button
        onClick={() => navigate("/")}
        className="mt-6 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        Back to Home
      </button>
    </div>
  );
};

export default SuccessOrderScreen;
