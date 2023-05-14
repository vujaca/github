import { Outlet } from "react-router-dom"
//import MainNavigation from "../components/MainNavigation"
import Header from "../components/Layout/Header"
import CartProvider from "../store/CartProvider"
import Cart from "../components/Cart/Cart"
import { useState } from "react"

const RootLayout = () => {
    // const navigation = useNavigation()
    const [cartIsShown, setCartIsShown] = useState(false)

  const showCartHandler = () => {
    setCartIsShown(true)
  }

  const hideCartHandler = () => {
    setCartIsShown(false)
  }


    return (
        <>
          <CartProvider>
        {cartIsShown&&<Cart onHideCart={hideCartHandler} />}    
         <Header  onShowCart={showCartHandler}/>
            <main>
              {/* {navigation.state === 'loading' &&  <p>Loading...</p>} */}
            <Outlet />
            </main>
            </CartProvider>
        
        </>
    )
}
export default RootLayout