package com.example.iclickipay

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.examole.feature_mechanic.presentation.routes.MechanicsNavigation
import com.example.feature_babysitter.babysitter.BabySitterNavigation
import com.example.feature_chat.chat.presentation.navigation.ChatNavigation
import com.example.feature_eat.eat.EatNavigation
import com.example.feature_delivery.delivery.nami.DeliveryNavigation
import com.example.feature_handyman.handyman.nami.HandymanNavigation
import com.example.feature_hotel.hotel.presentation.navigation.HotelNavigation
import com.example.feature_housecleaning.housecleaning.HouseCleaningNavigation
import com.example.feature_mover.presentation.mover.routes.MoverNavigation
import com.example.feature_laundry.LaundryNavigation
import com.example.feature_learn.LearnNavigation
import com.example.feature_pcrepair.pcrepair.PcRepairNavigation
import com.example.feature_pet.pet.PetNavigation
import com.example.iclickipay.presentation.homepage.HomePageScreen
import com.example.iclickipay.ui.theme.IClickIPayTheme
import com.example.iclickipay.presentation.login.LoginScreen
import com.example.iclickipay.presentation.register.RegisterScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IClickIPayTheme {
                val navController = rememberNavController()
                val loginAuth by remember { mutableStateOf(Firebase.auth) }

                val showScaffold = remember { mutableStateOf(false) }
                var appTitle by remember { mutableStateOf("Home") }

                Scaffold(
                    topBar = {

                        if (showScaffold.value) {
                            CenterAlignedTopAppBar(
                                title = { Text(appTitle) },
                                colors = TopAppBarDefaults.mediumTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                navigationIcon = {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back button",
                                            tint = Color.White
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {
                                        navController.navigate(HomeScreenRoute) {
                                            popUpTo(HomeScreenRoute) {
                                                inclusive = true
                                            }
                                            launchSingleTop = true
                                        }
                                    })
                                    {
                                        Icon(
                                            Icons.Filled.Home, contentDescription = "Go to Home",
                                            tint = Color.White
                                        )
                                    }
                                }
                            )
                        }
                    }
                )
                { paddingValues ->

                    NavHost(
                        navController = navController,
                        startDestination = LoginScreenRoute,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        composable<LoginScreenRoute> {
                            showScaffold.value = false
                            LoginScreen(
                                loginAuth,
                                navigateToRegister = { navController.navigate(RegisterScreenRoute) },
                                navigateToHomeScreen = { navController.navigate(HomeScreenRoute) }
                            )
                        }


                        composable<RegisterScreenRoute> {
                            showScaffold.value = false
                            RegisterScreen(
                                loginAuth,
                                navigateToLogin = { navController.navigate(LoginScreenRoute) }
                            )
                        }
                        composable<HomeScreenRoute> {
                            showScaffold.value = true
                            appTitle = "Home Screen"
                            HomePageScreen(
                                user = "Jim",
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
//                          navigateToBank = { navController.navigate(BankNavigationRoute) },
                                navigateToChat = { navController.navigate(ChatNavigationRoute) },
                                navigateToDelivery = {
                                    navController.navigate(
                                        DeliveryNavigationRoute
                                    )
                                },
                                navigateToEat = { navController.navigate(EatNavigationRoute) },
                                navigateToHandyMan = {
                                    navController.navigate(
                                        HandymanNavigationRoute
                                    )
                                },
                                navigateToHotel = { navController.navigate(HotelNavigationRoute) },
                                navigateToLaundry = { navController.navigate(LaundryNavigationRoute) },
                                navigateToLearn = { navController.navigate(LearnNavigationRoute) },
                                navigateToMechanic = {
                                    navController.navigate(
                                        MechanicNavigationRoute
                                    )
                                },
                                navigateToMover = { navController.navigate(MoverNavigationRoute) },
                                navigateToPcRepair = {
                                    navController.navigate(
                                        PcRepairNavigationRoute
                                    )
                                },
                                navigateToPet = { navController.navigate(PetNavigationRoute) },
                            )
                        }
                        composable<BabySitterNavigationRoute> {
                            appTitle = "Baby Sitter"
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
                        composable<ChatNavigationRoute> {
                            appTitle = "Chat"
                            ChatNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<DeliveryNavigationRoute> {
                            appTitle = "Delivery"
                            DeliveryNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<EatNavigationRoute> {
                            appTitle = "Eat"
                            EatNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<HandymanNavigationRoute> {
                            appTitle = "Handyman"
                            HandymanNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<HotelNavigationRoute> {
                            appTitle = "Hotel"
                            HotelNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<HouseCleaningNavigationRoute> {
                            appTitle = "House Cleaning"
                            HouseCleaningNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<PetNavigationRoute> {
                            appTitle = "Pet"
                            PetNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<LearnNavigationRoute> {
                            appTitle = "Learn"
                            LearnNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<LaundryNavigationRoute> {
                            appTitle = "Laundry"
                            LaundryNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<MechanicNavigationRoute> {
                            appTitle = "Mechanic"
                            MechanicsNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }
                        composable<MoverNavigationRoute> {
                            appTitle = "Mover"
                            MoverNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )
                        }

                        composable<PcRepairNavigationRoute> {
                            appTitle = "PC Repair"
                            PcRepairNavigation(
                                onNavigateBack = { navController.navigate(HomeScreenRoute) }
                            )

                        }
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


