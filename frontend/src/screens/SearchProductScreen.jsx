// screens/SearchProductScreen.jsx
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const mockProducts = [
  { id: 1, title: "CD - Album A" },
  { id: 2, title: "Book - Java Basics" },
  { id: 3, title: "DVD - Movie X" },
];

const SearchProductScreen = () => {
  const [query, setQuery] = useState("");
  const navigate = useNavigate();
  const filtered = mockProducts.filter((p) =>
    p.title.toLowerCase().includes(query.toLowerCase())
  );

  return (
    <div className="p-6">
      <h1 className="text-xl font-bold mb-4">Search Products</h1>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Search..."
        className="border px-3 py-2 rounded w-full mb-4"
      />
      <ul className="space-y-2">
        {filtered.map((p) => (
          <li
            key={p.id}
            className="cursor-pointer p-2 border rounded hover:bg-gray-100"
            onClick={() => navigate(`/product/${p.id}`)}
          >
            {p.title}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SearchProductScreen;
