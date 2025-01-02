package com.example.feature_hotel.hotel.presentation.navigation

sealed class HotelScreen(val route: String) {
    object HotelMainScreen : HotelScreen("hotel_main_screen")
    object ChooseDateScreen : HotelScreen("choose_date_screen")
    object FilterScreen : HotelScreen("filter_screen")
    object SearchScreen : HotelScreen("search_screen")
    object MapScreen : HotelScreen("map_screen")
    object OrderScreen : HotelScreen("order_screen")
    object RoomsScreen : HotelScreen("rooms_screen")
    object SingleScreen : HotelScreen("single_screen")
}