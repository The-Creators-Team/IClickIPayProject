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
import com.examole.feature_mechanic.presentation.routes.MechanicsNavigation
import com.example.feature_babysitter.babysitter.BabySitterNavigation
import com.example.feature_housecleaning.housecleaning.HouseCleaningNavigation
import com.example.feature_learn.LearnNavigation
import com.example.feature_mover.presentation.mover.routes.MoverNavigation
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
                            navigateToBabySitter = {
                                navController.navigate(
                                    BabySitterNavigationRoute
                                )
                            },
                            navigateToHouseCleaning = {
                                navController.navigate(
                                    HouseCleaningNavigationRoute
                                )
                            },
//                            ,navigateToBank = { navController.navigate(BankNavigationRoute) },
//                            navigateToChat = { navController.navigate(ChatNavigationRoute) },
//                            navigateToDelivery = { navController.navigate(DeliveryNavigationRoute) },
//                            navigateToEat = { navController.navigate(EatNavigationRoute) },
//                            navigateToHandyMan = { navController.navigate(HandymanNavigationRoute) },
//                            navigateToHotel = { navController.navigate(HotelNavigationRoute) },
//                            navigateToLaundry = { navController.navigate(LaundryNavigationRoute) },
                            navigateToLearn = { navController.navigate(LearnNavigationRoute) },
                            navigateToMechanic = { navController.navigate(MechanicNavigationRoute) },
                            navigateToMover = { navController.navigate(MoverNavigationRoute) },
//                            navigateToPcRepair = { navController.navigate(PcRepairNavigationRoute) },
                            navigateToPet = { navController.navigate(PetNavigationRoute) },

                        )
                    }
                    composable<BabySitterNavigationRoute> {
                        BabySitterNavigation(
                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
                        )
                    }
                    //Nav Step 2 add navigation to ur module
//                    composable<BankNavigationRoute> {
//                        BankNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<ChatNavigationRoute> {
//                        ChatNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<DeliveryNavigationRoute> {
//                        DeliveryNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<EatNavigationRoute> {
//                        EatNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<HandymanNavigationRoute> {
//                        HandymanNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<HotelNavigationRoute> {
//                        HotelNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
                    composable<HouseCleaningNavigationRoute> {
                        HouseCleaningNavigation(
                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
                        )
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
//                    composable<LaundryNavigationRoute> {
//                        LaundryNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<LearnNavigationRoute> {
//                        LearnNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
                    composable<MechanicNavigationRoute> {
                        MechanicsNavigation(
                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
                        )
                    }
                    composable<MoverNavigationRoute> {
                        MoverNavigation(
                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
                        )
                    }
//                    composable<PcRepairNavigationRoute> {
//                        PcRepairNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }
//                    composable<PetNavigationRoute> {
//                        PetNavigation(
//                            onNavigateBack = { navController.navigate(HomeScreenRoute) }
//                        )
//                    }

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
object BabySitterNavigationRoute

//Nav Step 1 just name app+NavigationRoute
@Serializable
object BankNavigationRoute

@Serializable
object ChatNavigationRoute

@Serializable
object DeliveryNavigationRoute

@Serializable
object EatNavigationRoute

@Serializable
object HandymanNavigationRoute

@Serializable
object HotelNavigationRoute

@Serializable
object HouseCleaningNavigationRoute

@Serializable
object LaundryNavigationRoute


@Serializable
object MechanicNavigationRoute

@Serializable
object MoverNavigationRoute

@Serializable
object PcRepairNavigationRoute

@Serializable
object PetNavigationRoute

@Serializable
object LearnNavigationRoute
