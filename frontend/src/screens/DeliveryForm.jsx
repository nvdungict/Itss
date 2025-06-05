// screens/DeliveryForm.jsx
import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../context/AppContext";

const DeliveryForm = () => {
  const [form, setForm] = useState({
    name: "",
    address: "",
    city: "",
    phone: "",
  });
  const { setDeliveryInfo } = useContext(AppContext);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setDeliveryInfo(form);
    navigate("/invoice");
  };

  return (
    <form onSubmit={handleSubmit} className="p-6 space-y-4 max-w-md mx-auto">
      <h1 className="text-xl font-bold">Delivery Information</h1>
      <input
        type="text"
        name="name"
        placeholder="Full Name"
        value={form.name}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <input
        type="text"
        name="address"
        placeholder="Address"
        value={form.address}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <input
        type="text"
        name="city"
        placeholder="City"
        value={form.city}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <input
        type="tel"
        name="phone"
        placeholder="Phone Number"
        value={form.phone}
        onChange={handleChange}
        className="w-full border px-3 py-2 rounded"
        required
      />
      <button
        type="submit"
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        Continue to Invoice
      </button>
    </form>
  );
};

export default DeliveryForm;
