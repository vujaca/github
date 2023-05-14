import { useRouteLoaderData } from "react-router-dom";

const EditMealPage = () => {
  //look for the difference between useLoaderData and useRouteLoaderData in EventDetail.js
  const data = useRouteLoaderData('meal-detail')
    return (
        <p>{data.meal.name}</p>
      );
}
export default EditEventPage