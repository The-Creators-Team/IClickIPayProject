package com.example.feature_handyman.handyman.nami

sealed class HandyManNamiScreen(val route: String) {
    object HandyManHome : HandyManNamiScreen("handy_man_home")
    object YourHandyMan: HandyManNamiScreen("your_handy_man")
    object HandyManProfile : HandyManNamiScreen("handy_man_details")
    object HandyManFilters : HandyManNamiScreen("handy_man_filter")
    object HandyManSearch : HandyManNamiScreen("handy_man_search")
    object HandyManMap : HandyManNamiScreen("handy_man_map")
    object HandyManDate: HandyManNamiScreen("handy_man_date")
    object HandyPlaceOrderScreen {
        const val route = "handyman/placeorder/{selectedDate}"

        fun createRoute(selectedDate: String): String {
            return "handyman/placeorder/$selectedDate"
        }
    }
}