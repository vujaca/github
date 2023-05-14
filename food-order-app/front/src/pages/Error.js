import { useRouteError } from "react-router-dom";

import PageContent from "./PageContent";
//import MainNavigation from "../components/MainNavigation";
import Header from "../components/Layout/Header";
import MealsLink from "../components/Meals/MealsLink";

const ErrorPage = () => {
  const error = useRouteError();

  let title = "An error occurred!";
  let message = "Something went wrong!";
  const link = <MealsLink />

  if (error.status === 500) {
    message = error.data.message;
  }

  if (error.status === 404) {
    title = "Not Found";
    message = "Could not find resource or page";
  }

  return (
    <>
      <Header />
        <PageContent title={title}>
          <p>{message}</p>
          {link}
        </PageContent>
    </>
  );
};
export default ErrorPage;
