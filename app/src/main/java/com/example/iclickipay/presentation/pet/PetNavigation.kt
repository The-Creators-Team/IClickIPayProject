package com.example.iclickipay.presentation.pet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iclickipay.R
import kotlinx.serialization.Serializable

@Composable
fun PetNavigation() {
    val petNavController = rememberNavController()
    NavHost(
        navController = petNavController,
        startDestination = PetListRoute
    ) {
        composable<PetListRoute> {
            PetListScreen(dogs,
                navigateToNewPet = { petNavController.navigate(NewPetRoute) })
        }
        composable<NewPetRoute> {
            NewPetScreen()
        }
    }
}

@Serializable
object PetListRoute

@Serializable
object NewPetRoute

val dogs = listOf<Dog>(
    Dog("Chris", "Labrador",Sex.FEMALE,3, Size.MEDIUM, R.drawable.ic_gear),
    Dog("Rebecca", "Chihuahua", Sex.FEMALE,4, Size.SMALL, R.drawable.ic_gear),
    Dog("Jill", "Bulldog", Sex.MALE ,2, Size.SMALL, R.drawable.ic_gear),
    Dog("Leon", "Pug", Sex.MALE, 1, Size.SMALL, R.drawable.ic_gear),
)