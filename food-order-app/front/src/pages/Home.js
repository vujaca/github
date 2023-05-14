import React from "react";
import MealsSummary from "../components/Meals/MealsSummary";
import MealsLink from "../components/Meals/MealsLink";
const HomePage = () => {
  return (
    <React.Fragment>
      <MealsSummary />
      <MealsLink />
    </React.Fragment>
  )

}
export default HomePage;
