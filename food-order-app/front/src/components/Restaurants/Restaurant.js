import { Link } from 'react-router-dom';
import classes from './Restaurant.module.css';
// import MealItem from '../Meals/MealItem/MealItem';

const Restaurant = ({restaurant}) => {
    const startDeleteHandler = (id) => {
        console.log('Deleting restaurant with id: '+ id);
    }


    return (
        
            <div className={classes.restaurant}>
              <img src={restaurant.imageUrl} alt={restaurant.name}/>
              <p className={classes.description}>{restaurant.aboutUs}</p>
              <p className={classes.description}>{restaurant.location}</p>
              <p className={classes.contact}>{restaurant.contact}</p>
              {/* {restaurant.meals.map((meal) => (
                <MealItem
                id={meal.id}
                key={meal.id}
                name={meal.name}
                description={meal.description}
                price={meal.price}
                />
              ))} */}
              <menu className={classes.actions}>
                <Link to="edit">Edit</Link>
                <button onClick={() => startDeleteHandler(restaurant.id)}>Delete</button>
              </menu>
              </div>

    )
}
export default Restaurant;