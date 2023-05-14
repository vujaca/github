// import { useEffect, useState } from "react";

// import classes from "./AvailableMeals.module.css";
// import Card from "../components/UI/Card";
// import MealItem from "../components/Meals/MealItem/MealItem";



// const AvailableMeals = () => {
//   const [meals, setMeals] = useState([])
//   const [isLoading, setIsLoading] = useState(true)
//   const [httpError, setHttpError] = useState()

//   useEffect(() => {
//     const fetchMeals = async () => {
//       const response = await fetch('http://localhost:8080/api/meals')
      
//       if(!response.ok) {
//         throw new Error('Somethung went wrong!')
//       }

//       const responseData = await response.json()

//       const loadedMeals = []
      
//       for(const key in responseData) {
//         loadedMeals.push({
//           id: key,
//           name: responseData[key].name,
//           description: responseData[key].description,
//           price: responseData[key].price
//         })
//       }

//       setMeals(loadedMeals)
//       setIsLoading(false)
//     }

//      fetchMeals().catch(error => {
//       setIsLoading(false)
//       setHttpError(error.message)
//      }) 
   
//   }, [])

//   if(isLoading) {
//     return <section className={classes.MealsLoading}>
//       <p>Loading...</p>
//     </section>
//   }

//   if(httpError) {
//     return <section className={classes.MealsError}>
//       <p>{httpError}</p>
//     </section>
//   }

//   const mealsList = meals.map((meal) => (
//     <MealItem
//       id={meal.id}
//       key={meal.id}
//       name={meal.name}
//       description={meal.description}
//       price={meal.price}
//     />
//   ));

//   return (
//     <section className={classes.meals}>
//       <Card>
//         <ul>{mealsList}</ul>
//       </Card>
//     </section>
//   );
// };
// export default AvailableMeals;

import { useLoaderData, json, defer, Await } from "react-router-dom";
//import MealItem from '../components/Meals/MealItem/MealItem';
import { Suspense } from "react";
//import Card from '../components/UI/Card';
// import classes from './AvailableMeals.module.css';
import MealsList from "../components/Meals/MealsList";

function AvailableMeals() {
  const data = useLoaderData();
  const meals = data.meals

  return (
       <Suspense fallback={<p style={{textAlign: "center", color: "white"}}>Loading...</p>}>
      <Await resolve={meals}>
        {(loadedMeals) => <MealsList meals={loadedMeals}/>}
  </Await>
    </Suspense>
  )

}

export default AvailableMeals;

const loadMeals = async() => {
  //you can't use hooks in loader function
  const response = await fetch("http://localhost:8080/api/meals");

  if (!response.ok) {
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
