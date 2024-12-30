package com.example.iclickipay.presentation.housecleaning

sealed class HouseCleaningScreen(val route: String) {
    object HouseCleaningMainScreen : HouseCleaningScreen("house_cleaning_main_screen")
    object YourHouseScreen : HouseCleaningScreen("your_house_screen")
    object FilterScreen : HouseCleaningScreen("filter_screen")
    object SearchScreen : HouseCleaningScreen("search_screen")
    object MapScreen : HouseCleaningScreen("map_screen")
    object OrderScreen : HouseCleaningScreen("order_screen")
}