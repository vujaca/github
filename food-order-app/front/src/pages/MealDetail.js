import { Await, defer, json, useRouteLoaderData } from "react-router-dom";
import { Suspense } from "react";
import Meal from "../components/Meals/MealItem/Meal";

const MealDetailPage = () => {
  const data = useRouteLoaderData('meal-detail')
  console.log(data.meal + ' success');
  return (
    <>
      <Suspense
        fallback={<p style={{ textAlign: "center" }}>Loading meal...</p>}
      >
        <Await resolve={data.meal}>
          {loadedMeal => <Meal meal={loadedMeal} />}
        </Await>
      </Suspense>
    </>
  );
};

export default MealDetailPage;

async function loadMeal(id) {
  const response = await fetch("http://localhost:8080/api/meals/" + id);
  if (!response.ok) {
    throw json(
      { message: "Could not fetch details for selected meal!" },
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
  const id = params.mealId;

  return defer({
    meal: await loadMeal(id),
  });
}
