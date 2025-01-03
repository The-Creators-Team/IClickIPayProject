package com.example.feature_babysitter.babysitter.navigation

sealed class BabySitterScreen(val route: String) {
    object BabySitterMainScreen : BabySitterScreen("baby_sitter_main_screen")
    object YourChildDetails : BabySitterScreen("your_child_details")
    object EditChildDetails : BabySitterScreen("edit_child_details")
    object TakeAPhotoScreen : BabySitterScreen("take_a_photo_screen")
    object ChildListScreen : BabySitterScreen("child_list_screen")
    object FilterScreen : BabySitterScreen("filter_screen")
    object SearchScreen : BabySitterScreen("search_screen")
    object BabyMapScreen : BabySitterScreen("baby_map_screen")
    object OrderScreen: BabySitterScreen("order_screen")

    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}