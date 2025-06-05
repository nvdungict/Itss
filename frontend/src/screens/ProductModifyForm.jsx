// screens/ProductModifyForm.jsx
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const ProductModifyForm = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const action = new URLSearchParams(location.search).get("action");

  const [product, setProduct] = useState({
    title: "",
    category: "",
    price: "",
    description: "",
  });

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`${action === "add" ? "Added" : "Updated"} product: ${product.title}`);
    navigate("/admin");
  };

  return (
    <form onSubmit={handleSubmit} className="p-6 max-w-md mx-auto space-y-4">
      <h1 className="text-xl font-bold">
        {action === "add" ? "Add New Product" : "Update Product"}
      </h1>
      <input
        type="text"
        name="title"
        placeholder="Title"
        value={product.title}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <input
        type="text"
        name="category"
        placeholder="Category"
        value={product.category}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <input
        type="number"
        name="price"
        placeholder="Price"
        value={product.price}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <textarea
        name="description"
        placeholder="Description"
        value={product.description}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <button
        type="submit"
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        {action === "add" ? "Add Product" : "Update Product"}
      </button>
    </form>
  );
};

export default ProductModifyForm;
