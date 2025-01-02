package com.example.feature_delivery.delivery.nami

sealed class DeliveryNamiScreen(val route: String) {
    object DeliveryHome : DeliveryNamiScreen("delivery_home")
    object ParcelDetailsPage : DeliveryNamiScreen("parcel_details_man")
    object DeliveryProfile : DeliveryNamiScreen("delivery_profile")
    object DeliveryFilters : DeliveryNamiScreen("delivery_filter")
    object DeliverySearch : DeliveryNamiScreen("delivery_search")
    object DeliveryMap : DeliveryNamiScreen("delivery_map")
    object OrderDetailsPage : DeliveryNamiScreen("order_details_page")
    object DeliveryDate: DeliveryNamiScreen("delivery_date")
}