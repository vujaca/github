import { useLoaderData, json, defer, Await } from "react-router-dom";
import MealsList from "../components/trash/MealsList";
import { Suspense } from "react";

function MealsPage() {
  const data = useLoaderData();
  const meals = data.meals

  return (
    <Suspense fallback={<p style={{textAlign: "center"}}>Loading...</p>}>
      <Await resolve={meals}>
    {(loadedMeals) => <MealsList meals={loadedMeals}/>}
  </Await>
    </Suspense>
  )

}

export default MealsPage;

const loadMeals = async() => {
  //you can't use hooks in loader function
  const response = await fetch("http://localhost:8080/api/meals");

  if (!response.ok) {
    //  return {isError: true, message: 'Could not fetch events.'}
    // throw new Response(JSON.stringify({ message: "Could not fetch events." }), {
    //   status: 500,
    // });
    throw json(
      { message: "Could not fetch meals." },
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
    meals: loadMeals()
  })
};
