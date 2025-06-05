// screens/OperationMenu.jsx
import React from "react";
import { useNavigate } from "react-router-dom";

const OperationMenu = () => {
  const navigate = useNavigate();

  return (
    <div className="p-6">
      <h1 className="text-xl font-bold mb-4">Admin Operation Menu</h1>
      <div className="space-y-2">
        <button
          onClick={() => navigate("/admin/product-form?action=add")}
          className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
        >
          Add Product
        </button>
        <button
          onClick={() => navigate("/admin/product-form?action=update")}
          className="bg-yellow-600 text-white px-4 py-2 rounded hover:bg-yellow-700"
        >
          Update Product
        </button>
      </div>
    </div>
  );
};

export default OperationMenu;
