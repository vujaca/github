import { NavLink } from 'react-router-dom';
import classes from './RestaurantItem.module.css';

const RestaurantItem = (props) => {
    return (
        <li className={classes.restaurant}>
      <div>
        <NavLink
        to={`/restaurants/${props.id}`}
        className={({isActive}) => isActive ? classes.active : undefined}
        end
        >
        <h3>{props.name}</h3>
        </NavLink>
        <div className={classes.description}>{props.aboutUs}</div>
        <div className={classes.description}>{props.location}</div>
        <div className={classes.contact}>{props.contact}</div>
      </div>
    </li>
    )

}

export default RestaurantItem;