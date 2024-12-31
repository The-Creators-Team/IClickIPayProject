package com.example.feature_mover.mover



sealed class MoverScreenRoutes(val route: String) {
    object MoverInitialScreen : MoverScreenRoutes("Mover_initial_screen")
    object YourStartScreen : MoverScreenRoutes("your_start_screen")
    object YourArrivalScreen : MoverScreenRoutes("your_arrival_screen")
    object ChooseDateScreen : MoverScreenRoutes("choose_date_screen")
    object MoverHomeScreen : MoverScreenRoutes("mover_home_screen")
    object FilterScreen : MoverScreenRoutes("filter_screen")
    object MapScreen : MoverScreenRoutes("map_screen")
    object MoverProfileScreen : MoverScreenRoutes("mover_profile_screen")
    object ChooseDateTimeScreen : MoverScreenRoutes("choose_date_time_screen")
    object OrderScreen: MoverScreenRoutes("order_screen")





    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}