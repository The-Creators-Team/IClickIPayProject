package com.example.iclickipay

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_mover.mover.registerMoverRoutes
import com.example.feature_learn.LearnNavigation
import com.example.feature_pet.pet.PetNavigation
import com.example.iclickipay.presentation.homepage.HomePageScreen
import com.example.iclickipay.ui.theme.IClickIPayTheme
import com.example.iclickipay.presentation.login.LoginScreen
import com.example.iclickipay.presentation.register.RegisterScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IClickIPayTheme {
                val navController = rememberNavController()
                val loginAuth by remember { mutableStateOf(Firebase.auth) }
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(
                            loginAuth,
                            navigateToRegister = { navController.navigate(RegisterScreenRoute) },
                            navigateToHomeScreen = { navController.navigate(HomeScreenRoute) }
                        )
                    }


                    composable<RegisterScreenRoute> {
                        RegisterScreen(
                            loginAuth,
                            navigateToLogin = { navController.navigate(LoginScreenRoute) }
                        )
                    }
                    composable<HomeScreenRoute> {
                        HomePageScreen(
                            user = "jim",
                            navigateToBabySitter = { navController.navigate(BabySitterScreenRoute) },
                            navigateToHouseCleaning = {navController.navigate(HouseCleaningScreenRoute)},
                            navigateToPet = {navController.navigate(PetNavigationRoute)},
                            navigateToLearn = {navController.navigate(LearnNavigationRoute)}
                        )
                    }
                    registerMoverRoutes(navController)

                    composable<BabySitterScreenRoute> {
                    }
                    composable<HouseCleaningScreenRoute>{
                    }
                    composable<PetNavigationRoute> {
                        PetNavigation(
                            onNavigateBack={navController.navigate(HomeScreenRoute)}
                        )
                    }
                    composable<LearnNavigationRoute> {
                        LearnNavigation(
                            onNavigateBack={navController.navigate(HomeScreenRoute)}
                        )
                    }
                }
            }
        }
    }
}

//think of these as ROUTES:
//if data needs to be passed through the route, declare them as data classes instead
//data sent this way should be lightweight and consist of a pointer to get large resources as opposed
//to resources themselves (such as an id that identifies a large object or what to pull for an
//API call)
@Serializable
object LoginScreenRoute

@Serializable
object RegisterScreenRoute

@Serializable
object HomeScreenRoute

@Serializable
object BabySitterScreenRoute

@Serializable
object HouseCleaningScreenRoute

@Serializable
object PetNavigationRoute

@Serializable
object LearnNavigationRoute
