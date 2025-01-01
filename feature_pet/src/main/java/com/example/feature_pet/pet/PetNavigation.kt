package com.example.feature_pet.pet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.common.reuseable.maps.maputil.Route
import com.example.feature_pet.viewmodel.DogViewModel
import com.example.iclickipay.presentation.pet.PetIntroScreen
import kotlinx.serialization.Serializable

@Composable
fun PetNavigation(
    onNavigateBack: () -> Unit
) {
    val petNavController = rememberNavController()
    val petViewModel = remember { DogViewModel() }


    NavHost(
        navController = petNavController,
        startDestination = PetIntroRoute
    ) {
        composable<PetListRoute> {
            PetListScreen(
                navigateToNewPet = { petNavController.navigate(NewPetRoute) },
                navigateBack = { petNavController.popBackStack() },
                navigateToPetMap = { petNavController.navigate(PetMapRoute) },
                dogViewModel = petViewModel
            )
        }
        composable<NewPetRoute> {
            NewPetScreen(
                navigateToPetList = { petNavController.navigate(PetListRoute) },
                navigateBack = { petNavController.popBackStack() },
                dogViewModel = petViewModel
            )
        }
        composable<PetIntroRoute> {
            PetIntroScreen(
                navigateToPetList = { petNavController.navigate(PetListRoute) },
                navigateBackToHomeScreen = onNavigateBack
            )
        }
        composable<PetFilterRoute> {
            PetFilterScreen(
                navigateToPetMap = { petNavController.navigate(PetFilterRoute) }
            )
        }
        composable<PetMapRoute> {
            PetMapScreen()
        }
    }
}


@Serializable
object PetListRoute

@Serializable
object NewPetRoute

@Serializable
object PetIntroRoute

@Serializable
object PetFilterRoute

@Serializable
object PetMapRoute