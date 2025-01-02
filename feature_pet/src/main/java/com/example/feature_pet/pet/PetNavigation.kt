package com.example.feature_pet.pet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.common.reuseable.maps.maputil.Route
import com.example.feature_pet.viewmodel.DogViewModel
import com.example.feature_pet.viewmodel.GuardianViewModel
import com.example.iclickipay.presentation.pet.PetIntroScreen
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetNavigation(
    onNavigateBack: () -> Unit
) {
    val petNavController = rememberNavController()
    val petViewModel = remember { DogViewModel() }
    val guardianViewModel= remember {GuardianViewModel()}


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
            PetMapScreen(
                navigateToPetCalendar = { petNavController.navigate(PetCalendarRoute) },
                guardianViewModel = guardianViewModel
            )
        }

        composable<PetCalendarRoute> {
            PetCalendar(
                { petNavController.navigate(PetDepositTimeRoute) }
            )
        }
        composable<PetDepositTimeRoute> {
            PetDepositTime(
                navigateToPickUp = {petNavController.navigate(PetPickUpTimeRoute)}
            )
        }
        composable<PetPickUpTimeRoute> {
            PetPickUpTime(
                navigateToOrder = {petNavController.navigate(PetOrderRoute)}
            )
        }
        composable<PetOrderRoute> {
            PetOrderScreen(
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

@Serializable
object PetFilterRoute

@Serializable
object PetMapRoute

@Serializable
object PetOrderRoute

@Serializable
object PetCalendarRoute

@Serializable
object PetDepositTimeRoute

@Serializable
object PetPickUpTimeRoute