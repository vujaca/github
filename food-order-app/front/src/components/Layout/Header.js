import React from "react"

import mealsImage from '../../assets/meals.jpg'
import classes from './Header.module.css'
import HeaderCartButton from "./HeaderCartButton"
import { NavLink } from "react-router-dom"

const Header = props => {
    return (
       <React.Fragment>
        <header className={classes.header}>
            <h1>Mealyyyy</h1>
            <nav>
        <ul className={classes.list}>
          <li>
            <NavLink
              to="/"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
              end
            >
              Home
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/meals"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              Meals
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/restaurants"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              Restaurants
            </NavLink>
          </li>
        </ul>
      </nav>
            <HeaderCartButton onClick={props.onShowCart} />
        </header>
        <div className={classes['main-image']}>
            <img src={mealsImage} alt="A table full of delicious food!"/>
        </div>

       </React.Fragment>
    )
}
export default Header