import { Outlet } from "react-router-dom"
import MealsNavigation from "../components/Meals/MealsNavigation"

const MealRootLayout = () => {
    return (
        <>
         <MealsNavigation />
            <main>
            <Outlet />
            </main>
        
        </>
    )
}
export default MealRootLayout