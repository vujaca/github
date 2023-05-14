import { NavLink } from 'react-router-dom';
import classes from './MealsNavigation.module.css';

function MealsNavigation() {
  return (
    <header className={classes.header}>
      <nav>
        <ul className={classes.list}>
          <li>
          <NavLink
           to='/meals'
           className={({isActive}) => isActive ? classes.active : undefined}
           end
           >
            All Meals
           </NavLink>
          </li>
          <li>
          <NavLink
           to='new'
           className={({isActive}) => isActive ? classes.active : undefined}
           
           >
           New Meal
           </NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default MealsNavigation;
