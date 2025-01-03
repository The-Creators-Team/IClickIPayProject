package com.example.feature_housecleaning.housecleaning.navigation

sealed class HouseCleaningScreen(val route: String) {
    object HouseCleaningMainScreen : HouseCleaningScreen("house_cleaning_main_screen")
    object YourHouseScreen : HouseCleaningScreen("your_house_screen")
    object FilterScreen : HouseCleaningScreen("filter_screen")
    object SearchScreen : HouseCleaningScreen("search_screen")
    object HouseMapScreen : HouseCleaningScreen("house_map_screen")
    object OrderScreen : HouseCleaningScreen("order_screen")
    object HouseCalendarScreen : HouseCleaningScreen("house_calendar_screen")

    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}