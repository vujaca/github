import classes from '../../pages/AvailableMeals.module.css';
import MealItem from '../Meals/MealItem/MealItem';
import Card from '../UI/Card';

const MealsList = ({meals}) => {
    return (
        <section className={classes.meals}>
        <Card>
      <ul>
        {meals.map((meal) => (
             <MealItem
                id={meal.id}
                key={meal.id}
                name={meal.name}
                description={meal.description}
                price={meal.price}
                />
        ))}
      </ul>
    </Card>
     </section>
    )

}

export default MealsList;