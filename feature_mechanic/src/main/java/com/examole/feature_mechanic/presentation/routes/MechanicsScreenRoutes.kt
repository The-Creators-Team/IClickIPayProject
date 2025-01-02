package com.examole.feature_mechanic.presentation.routes



sealed class MechanicsScreenRoutes(val route: String) {
    object MechanicsInitialScreen : MechanicsScreenRoutes("mechanic_initial_screen")
    object YourMechanicFormScreen : MechanicsScreenRoutes("your_mechanic_form_screen")
    object ChooseDateTimeScreen : MechanicsScreenRoutes("mechanic_choose_date_time_screen")
    object MechanicHomeScreen : MechanicsScreenRoutes("mechanic_home_screen")
    object MechanicFilterScreen : MechanicsScreenRoutes("mechanic_filter_screen")
    object MechanicMapScreen : MechanicsScreenRoutes("mechanic_map_screen")
    object MechanicProfileScreen : MechanicsScreenRoutes("mechanic_profile_screen")
    object MechanicOrderScreen: MechanicsScreenRoutes("mechanic_order_screen")


    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}