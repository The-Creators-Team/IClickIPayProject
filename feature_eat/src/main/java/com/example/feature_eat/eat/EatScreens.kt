package com.example.feature_eat.eat

sealed class EatScreens(val route: String){
    object HomeEatApp: EatScreens("home_eat_app")
    object StoreDetail: EatScreens("store_detail")
    object WelcomeEatApp: EatScreens("welcome_eat_app")
    object EatOrder: EatScreens("eat_order_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}