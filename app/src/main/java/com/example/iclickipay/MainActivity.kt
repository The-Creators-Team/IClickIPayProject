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
import com.example.iclickipay.ui.theme.IClickIPayTheme
import com.example.iclickipay.presentation.login.LoginScreen
import com.example.iclickipay.presentation.mover.ChooseDate
import com.example.iclickipay.presentation.mover.ChooseDateTime
import com.example.iclickipay.presentation.mover.MoverHomeScreen
import com.example.iclickipay.presentation.mover.MoverProfileScreen
import com.example.iclickipay.presentation.mover.MoverScreen
import com.example.iclickipay.presentation.mover.MoversListScreen
import com.example.iclickipay.presentation.mover.PlaceOrderScreen
import com.example.iclickipay.presentation.mover.YourArrivalScreen
import com.example.iclickipay.presentation.mover.YourStartScreen
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
                    //startDestination = LoginScreenRoute
                    startDestination = "MovieScreen"
                    //modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                ) {
                    //these K classes can be seen as routes and with the composable
                    //the applications will start at the one given as the start destination above,
                    // and by using the 'navController.navigate' method and giving a route as
                    //an argument, its possible to move in between screens
                    composable<LoginScreenRoute> {
                        //LoginScreen(loginAuth)
                        LoginScreen(loginAuth)

                    }
                    composable("MovieScreen") {
                        MoverScreen(navController = navController) // Mover screen composable
                    }
                    composable("YourStart") {
                        YourStartScreen(navController = navController) // YourStart screen composable
                    }
                    composable("YourArrival") {
                        YourArrivalScreen(navController = navController)
                    }
                    composable("ChooseDate") {
                        ChooseDate(navController = navController)
                    }
                    composable("ChooseDateTime") {
                        ChooseDateTime(navController = navController)
                    }
                    composable("MoverHomeScreen") {
                        MoverHomeScreen(navController = navController)
                    }
                    composable("MoverListScreen") {
                    MoversListScreen(navController = navController)
                    }
                    composable("MoverProfileScreen") {
                        MoverProfileScreen(navController = navController)
                    }
                    composable("PlaceOrderScreen") {
                        PlaceOrderScreen( navController = navController)
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