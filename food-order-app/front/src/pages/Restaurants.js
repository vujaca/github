import { useLoaderData, json, defer, Await } from "react-router-dom";
import { Suspense } from "react";
//import RestaurantItem from "../components/Restaurants/RestaurantItem";
// import Card from "../components/UI/Card";
import classes from './Restaurants.module.css';
import RestaurantsList from "../components/Restaurants/RestaurantsList";

function RestaurantsPage() {
  const data = useLoaderData();
  const restaurants = data.restaurants
  const restaurantsLoading = <section className={classes.RestaurantsLoading}>
    <p>Loading...</p>    
  </section>

  return (
        <Suspense fallback={restaurantsLoading}>
      <Await resolve={restaurants}>
        {(loadedRestaurants) => <RestaurantsList restaurants={loadedRestaurants}/>}
  </Await>
    </Suspense>
  )

}

export default RestaurantsPage;

const loadRestaurants = async() => {
  const response = await fetch("http://localhost:8080/api/restaurants");

  if (!response.ok) {
    throw json(
      { message: "Could not fetch restaurants." },
      {
        status: 500,
      }
    );
  } else {
    const resData = await response.json()
    return resData
  }
}

export const loader = () => {
  return defer( {
    restaurants: loadRestaurants()
  })
};
