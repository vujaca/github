import { Await, defer, json, useRouteLoaderData } from "react-router-dom";
//import MealItem from "../components/Meals/MealItem/MealItem";
import { Suspense } from "react";
import Restaurant from "../components/Restaurants/Restaurant";

const RestaurantDetailPage = () => {
  const data = useRouteLoaderData('restaurant-detail')
  console.log(data.restaurant + ' success');
  return (
    <>
      <Suspense
        fallback={<p style={{ textAlign: "center" }}>Loading meal...</p>}
      >
        <Await resolve={data.restaurant}>
          {loadedRestaurant => <Restaurant restaurant={loadedRestaurant} />}
        </Await>
      </Suspense>
    </>
  );
};

export default RestaurantDetailPage;

async function loadRestaurant(id) {
  const response = await fetch("http://localhost:8080/api/restaurants/" + id);
  if (!response.ok) {
    throw json(
      { message: "Could not fetch details for selected restaurant!" },
      {
        status: 500,
      }
    );
  } else {
    const resData = await response.json();
    console.log(resData);
    return resData
  }
}

export async function loader({ request, params }) {
  const id = params.restaurantId;

  return defer({
    restaurant: await loadRestaurant(id),
  });
}
