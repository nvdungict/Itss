// context/AppContext.jsx
import React, { createContext, useState } from "react";

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [cart, setCart] = useState([]);
  const [deliveryInfo, setDeliveryInfo] = useState(null);
  const [invoice, setInvoice] = useState(null);

  const updateCart = (newCart) => setCart(newCart);

  return (
    <AppContext.Provider
      value={{
        cart,
        updateCart,
        deliveryInfo,
        setDeliveryInfo,
        invoice,
        setInvoice,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};
