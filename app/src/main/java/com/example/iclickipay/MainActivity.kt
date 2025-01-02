package com.example.iclickipay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iclickipay.ui.theme.IClickIPayTheme
import com.example.iclickipay.presentation.login.LoginScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IClickIPayTheme {
                val navController = rememberNavController()
                val loginAuth by remember { mutableStateOf(Firebase.auth) }
                NavHost(
                    navController = navController,
                    startDestination = LoginScreenRoute
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