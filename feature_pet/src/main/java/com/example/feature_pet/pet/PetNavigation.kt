package com.example.feature_pet.pet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iclickipay.BabySitterScreenRoute
import com.example.iclickipay.HomeScreenRoute
import com.example.iclickipay.HouseCleaningScreenRoute
import com.example.iclickipay.PetNavigationRoute
import com.example.iclickipay.R
import com.example.iclickipay.presentation.homepage.HomePageScreen
import kotlinx.serialization.Serializable

@Composable
fun PetNavigation() {
    val petNavController = rememberNavController()
    NavHost(
        navController = petNavController,
        startDestination = PetIntroRoute
    ) {
        composable<PetListRoute> {
            PetListScreen(dogs,
                navigateToNewPet = { petNavController.navigate(NewPetRoute) })
        }
        composable<NewPetRoute> {
            NewPetScreen()
        }
        composable<PetIntroRoute> {
            PetIntroScreen(
                navigateToPetList = { petNavController.navigate(PetListRoute) },
                navigateToHomePage = { petNavController.navigate(HomeScreenRoute) }
            )
        }
        composable<HomeScreenRoute> {
            HomePageScreen(
                user = "jim",
                navigateToBabySitter = { petNavController.navigate(BabySitterScreenRoute) },
                navigateToHouseCleaning = {petNavController.navigate(HouseCleaningScreenRoute)},
                navigateToPet = {petNavController.navigate(PetNavigationRoute)}
            )
        }
    }
}

@Serializable
object PetListRoute

@Serializable
object NewPetRoute

@Serializable
object PetIntroRoute

val dogs = listOf<Dog>(
    Dog("Chris", "Labrador", Sex.FEMALE,3, Size.MEDIUM, R.drawable.ic_gear),
    Dog("Rebecca", "Chihuahua", Sex.FEMALE,4, Size.SMALL, R.drawable.ic_gear),
    Dog("Jill", "Bulldog", Sex.MALE ,2, Size.SMALL, R.drawable.ic_gear),
    Dog("Leon", "Pug", Sex.MALE, 1, Size.SMALL, R.drawable.ic_gear),
)