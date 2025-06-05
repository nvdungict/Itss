import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import MainScreen from './screens/MainScreen.jsx'
import CartScreen from './screens/CartScreen.jsx'
import SearchProductScreen from './screens/SearchProductScreen.jsx'
import DetailScreen from './screens/DetailScreen.jsx'
import DeliveryForm from './screens/DeliveryForm.jsx'
import InvoiceScreen from './screens/InvoiceScreen.jsx'
import SuccessOrderScreen from './screens/SuccessOrderScreen.jsx'
import OperationMenu from './screens/OperationMenu.jsx'
import ProductModifyForm from './screens/ProductModifyForm.jsx'
import { AppProvider } from './context/AppContext.jsx'

function App() {
  return (
    <AppProvider>
      <Router>
        <Routes>
          <Route path='/' element={<MainScreen />} />
          <Route path='/cart' element={<CartScreen />} />
          <Route path='/search' element={<SearchProductScreen />} />
          <Route path='/product/:id' element={<DetailScreen />} />
          <Route path='/delivery' element={<DeliveryForm />} />
          <Route path='/invoice' element={<InvoiceScreen />} />
          <Route path='/success' element={<SuccessOrderScreen />} />
          <Route path='/admin' element={<OperationMenu />} />
          <Route path='/admin/product-form' element={<ProductModifyForm />} />
        </Routes>
      </Router>
    </AppProvider>
  )
}

export default App
