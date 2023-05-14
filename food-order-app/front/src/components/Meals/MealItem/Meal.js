import { Link } from 'react-router-dom';
import classes from './Meal.module.css';

const Meal = ({meal}) => {
    const startDeleteHandler = (id) => {
        console.log('Deleting meal with id: '+ id);
    }

    return (
        
            <div className={classes.meal}>
              <img src={meal.imageUrl} alt={meal.name} />
              <h1>{meal.name}</h1>
              <p>{meal.description}</p>
              <p>{meal.price.toFixed(2)} din</p>
              <menu className={classes.actions}>
                <Link to="edit">Edit</Link>
                <button onClick={() => startDeleteHandler(meal.id)}>Delete</button>
              </menu>
              </div>

    )
}
export default Meal;