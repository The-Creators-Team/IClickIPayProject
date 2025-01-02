package com.example.feature_eat.eat

sealed class EatScreens(val route: String){
    object HomeEatApp: EatScreens("home_eat_app")
    object StoreDetail: EatScreens("store_detail")
    object WelcomeEatApp: EatScreens("welcome_eat_app")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}