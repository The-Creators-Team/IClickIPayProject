package com.example.feature_pet.pet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_pet.R
import com.example.iclickipay.presentation.pet.PetIntroScreen
import kotlinx.serialization.Serializable

@Composable
fun PetNavigation(
    onNavigateBack: () -> Unit
) {
    val petNavController = rememberNavController()

    NavHost(
        navController = petNavController,
        startDestination = PetIntroRoute
    ) {
        composable<PetListRoute> {
            PetListScreen(
                dogs,
                navigateToNewPet = { petNavController.navigate(NewPetRoute) }
            )
        }
        composable<NewPetRoute> {
            NewPetScreen(
                navigateToPetList = { petNavController.navigate(PetListRoute) }
            )
        }
        composable<PetIntroRoute> {
            PetIntroScreen(
                navigateToPetList = { petNavController.navigate(PetListRoute) },
                navigateBackToHomeScreen = onNavigateBack
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
    Dog("Goldie", "Labrador", Sex.FEMALE, 3, Size.MEDIUM, R.drawable.labrador),
    Dog("Jose", "Chihuahua", Sex.FEMALE, 4, Size.SMALL, R.drawable.chihuahua),
    Dog("Dumptruck", "Bulldog", Sex.MALE, 2, Size.SMALL, R.drawable.bulldog),
    Dog("Muffin", "Pug", Sex.MALE, 1, Size.SMALL, R.drawable.pug)
)