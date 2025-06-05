// screens/MainScreen.jsx
import React from "react";
import { useNavigate } from "react-router-dom";

const MainScreen = () => {
  const navigate = useNavigate();

  return (
    <div className="p-6 space-y-4">
      <h1 className="text-2xl font-bold">Welcome to AIMS Store</h1>
      <button
        onClick={() => navigate("/search")}
        className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
      >
        Browse Products
      </button>
      <button
        onClick={() => navigate("/cart")}
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        View Cart
      </button>
      <button
        onClick={() => navigate("/admin")}
        className="bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-800"
      >
        Admin Menu
      </button>
    </div>
  );
};

export default MainScreen;
