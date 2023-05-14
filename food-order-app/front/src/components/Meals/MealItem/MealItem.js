import { useContext } from 'react';

import classes from './MealItem.module.css'
import MealItemForm from './MealItemForm';
import CartContext from '../../../store/cart-context';
import { NavLink } from 'react-router-dom';

const MealItem = (props) => {
    const cartCtx = useContext(CartContext)
    const price = `${props.price.toFixed(2)} din`

    const addToCartHandler = amount => {
      cartCtx.addItem({
        id: props.id,
        name: props.name,
        amount: amount,
        price: props.price
      })
    }
  return (
    <li className={classes.meal}>
      <div>
      <NavLink 
      to={`/meals/${props.id}`}
      className={({isActive}) => isActive ? classes.active : undefined}
           end
      >
        <h3>{props.name}</h3>
        </NavLink>
        <div className={classes.description}>{props.description}</div>
        <div className={classes.price}>{price}</div>
      </div>
      <div>
        <MealItemForm onAddToCart={addToCartHandler} id={props.id} />
      </div>
    </li>
  );
};
export default MealItem;
