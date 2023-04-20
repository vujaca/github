import React from "react";
import { useContext, useState } from "react";

import Modal from "../UI/Modal";
import CartItem from "./CartItem";
import CartContext from "../../store/cart-context";
import classes from "./Cart.module.css";
import Checkout from "./Checkout";

const Cart = (props) => {
  const [isCheckout, setIsCheckout] = useState(false)
  const [isSubmitting, setIsSubmitting] = useState(false)
  const [didSubmit, setDidSubmit] = useState(false)
  const cartCtx = useContext(CartContext);

  const totalAmount = `$${cartCtx.totalAmount.toFixed(2)}`;
  const hasItems = cartCtx.items.length > 0;

  const cartItemRemoveHandler = (id) => {
    cartCtx.removeItem(id)
  };

  const cartItemAddHandler = (item) => {
    cartCtx.addItem({...item, amount: 1})
  };

  const orderHandler = () => {
    setIsCheckout(true)
  };

  const cartItems = (
    <ul className={classes["cart-items"]}>
      {cartCtx.items.map((item) => (
        <li>
          <CartItem
            key={item.id}
            name={item.name}
            amount={item.amount}
            price={item.price}
            onRemove={cartItemRemoveHandler.bind(null, item.id)}
            onAdd={cartItemAddHandler.bind(null, item)}
          />
        </li>
      ))}
    </ul>
  );

  const modalActions = <div className={classes.actions}>
  <button className={classes["button--alt"]} onClick={props.onHideCart}>
    Close
  </button>
  {hasItems && <button className={classes.button} onClick={orderHandler}>Order</button>}
</div> 

const submitOrderHandler = async(userData) => {
  setIsSubmitting(true)
  await fetch('https://react-http-acb25-default-rtdb.firebaseio.com/orders.json', {
    method: 'POST',
    body: JSON.stringify({
      user: userData,
      orderedItems: cartCtx.items
    })
  })
  setIsSubmitting(false)
  setDidSubmit(true)
  cartCtx.clearCart()
}

const cartModalContent =  
<React.Fragment>
  {cartItems}
<div className={classes.total}>
  <span>Total Amount</span>
  <span>{totalAmount}</span>
</div>
{isCheckout && <Checkout onConfirm={submitOrderHandler} onHideCart={props.onHideCart} />}
{!isCheckout && modalActions}
</React.Fragment>

const isSubmittingModalContent = <p>Sending order data...</p>

const didSubmitModalContent = 
<React.Fragment>
    <p>Succesfully sent the order!</p>
   <div className={classes.actions}>
  <button className={classes["button--alt"]} onClick={props.onHideCart}>
    Close
  </button>
</div> 
</React.Fragment>

  return (
    <Modal onClose={props.onHideCart}>
      {!isSubmitting && !didSubmit && cartModalContent}
      {isSubmitting && isSubmittingModalContent}
      {!isSubmitting && didSubmit && didSubmitModalContent}
    </Modal>
  );
};
export default Cart;
