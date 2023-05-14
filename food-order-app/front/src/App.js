import React from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import RootLayout from "./pages/Root";
import ErrorPage from "./pages/Error";
import AvailableMeals, { loader as mealsLoader } from "./pages/AvailableMeals";
import HomePage from "./pages/Home";
import RestaurantsPage, {
  loader as restaurantsLoader,
} from "./pages/Restaurants";
import MealDetailPage, { loader as mealDetailLoader } from "./pages/MealDetail";
import RestaurantDetailPage, {loader as restaurantDetailLoader} from "./pages/RestaurantDetail";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    errorElement: <ErrorPage />,
    children: [
      { index: true, element: <HomePage /> },
      {
        path: "meals",
        children: [
          {
            index: true,
            element: <AvailableMeals />,
            loader: mealsLoader,
            // element: <MealsPage />,
            // loader: mealsLoader
          },
          {
            path: ":mealId",
            id: "meal-detail",
            loader: mealDetailLoader,
            children: [
              {
                index: true,
                element: <MealDetailPage />,
              },
            ],
          },
        ],
      },
      {
        path: "restaurants",
        children: [
          {
            index: true,
            element: <RestaurantsPage />,
            loader: restaurantsLoader,
          },
          {
            path: ":restaurantId",
            id: "restaurant-detail",
            loader: restaurantDetailLoader,
            children: [
              {
                index: true,
                element: <RestaurantDetailPage />
              }
            ]
          }
        ],
      },
    ],
  },
]);

function App() {
  // const [cartIsShown, setCartIsShown] = useState(false)

  // const showCartHandler = () => {
  //   setCartIsShown(true)
  // }

  // const hideCartHandler = () => {
  //   setCartIsShown(false)
  // }

  // return (
  //     <CartProvider>
  //   {cartIsShown&&<Cart onHideCart={hideCartHandler} />}
  //      <Header onShowCart={showCartHandler}/>
  //      <main>
  //       <Meals />
  //      </main>
  //   </CartProvider>
  // );
  return <RouterProvider router={router} />;
}

export default App;
