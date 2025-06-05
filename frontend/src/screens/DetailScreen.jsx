// screens/DetailScreen.jsx
import React, { useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { AppContext } from "../context/AppContext";

const mockProducts = [
  { id: 1, title: "CD - Album A", description: "A top chart music CD.", price: 12 },
  { id: 2, title: "Book - Java Basics", description: "Learn Java from scratch.", price: 20 },
  { id: 3, title: "DVD - Movie X", description: "Action-packed movie DVD.", price: 18 },
];

const DetailScreen = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { cart, updateCart } = useContext(AppContext);
  const product = mockProducts.find((p) => p.id === parseInt(id));

  const handleAddToCart = () => {
    updateCart([...cart, { ...product, quantity: 1 }]);
    navigate("/cart");
  };

  if (!product) return <p className="p-6">Product not found.</p>;

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-2">{product.title}</h1>
      <p className="mb-2">{product.description}</p>
      <p className="mb-4">Price: ${product.price}</p>
      <button
        onClick={handleAddToCart}
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        Add to Cart
      </button>
    </div>
  );
};

export default DetailScreen;
