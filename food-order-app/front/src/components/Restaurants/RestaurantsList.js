import Card from '../UI/Card';
import RestaurantItem from './RestaurantItem';
import classes from '../../pages/Restaurants.module.css';

const RestaurantsList = ({restaurants}) => {
    return (
        <section className={classes.restaurants}>
        <Card>
      <ul>
        {restaurants.map((restaurant) => (
            <RestaurantItem 
            id={restaurant.id}
            key={restaurant.id}
            name={restaurant.name}
            aboutUs={restaurant.aboutUs}
            contact={restaurant.contact}
            location={restaurant.location}
            />
        ))}
      </ul>
        </Card>       
        </section>
    )
}
export default RestaurantsList;