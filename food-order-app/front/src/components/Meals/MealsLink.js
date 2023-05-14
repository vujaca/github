import { NavLink } from 'react-router-dom';
import classes from './MealsLink.module.css';

const MealsLink = () => {
    return (
        <ul className={classes.list}>
        <li>
          <NavLink
            to="/meals"
            className={({ isActive }) =>
              isActive ? classes.active : undefined
            }
            end
          >
            Find the best meal for you
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/restaurants"
            className={({ isActive }) =>
              isActive ? classes.active : undefined
            }
            end
          >
            Find the best-rated restaurant in your city
          </NavLink>
        </li>
      </ul>
    )

}
export default MealsLink;