package com.example.feature_housecleaning.housecleaning

sealed class HouseCleaningScreen(val route: String) {
    object HouseCleaningMainScreen : HouseCleaningScreen("house_cleaning_main_screen")
    object YourHouseScreen : HouseCleaningScreen("your_house_screen")
    object FilterScreen : HouseCleaningScreen("filter_screen")
    object SearchScreen : HouseCleaningScreen("search_screen")
    object HouseMapScreen : HouseCleaningScreen("house_map_screen")
    object OrderScreen : HouseCleaningScreen("order_screen")
}