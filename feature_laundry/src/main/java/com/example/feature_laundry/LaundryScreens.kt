package com.example.feature_laundry

sealed class LaundryScreens(val route: String){
    object LaundryHomeScreen: LaundryScreens("laundry_home_screen")
    object LaundryProblemScreen: LaundryScreens("laundry_problem_screen")
    object LaundryOrderScreen: LaundryScreens("laundry_order_screen")
    object LaundryAppointmentPickerScreen: LaundryScreens("laundry_appointment_picker_screen")
    object LaundryFilterScreen: LaundryScreens("laundry_filter_screen")
    object LaundrySearchListScreen: LaundryScreens("laundry_search_list_screen")

    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}