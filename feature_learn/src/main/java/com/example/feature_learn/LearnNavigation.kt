package com.example.feature_learn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_learn.viewmodel.LearnOrderScreen
import com.example.feature_learn.viewmodel.TutorViewModel
import kotlinx.serialization.Serializable


@Composable
fun LearnNavigation(
    onNavigateBack: () -> Unit
) {
    val learnNavController = rememberNavController()
    val tutorViewModel= remember{TutorViewModel()}

    NavHost(
        navController = learnNavController,
        startDestination = LearnIntroRoute
    ){
        composable<LearnIntroRoute> {
            LearnIntroScreen(
                navigateToTeacherMap = {learnNavController.navigate(LearnMapRoute)},
                navigateBackToHomeScreen = onNavigateBack
            )
        }
        composable<LearnMapRoute> {
            LearnMapScreen(
                tutorViewModel = tutorViewModel,
                navigateAppointmentPicker = { learnNavController.navigate(LearnAppointmentRoute) }
            )
        }
        composable<LearnAppointmentRoute> {
            LearnAppointmentPickerScreen(
                navigateToLearnOrder = { learnNavController.navigate(LearnOrderRoute) }
            )
        }
        composable<LearnOrderRoute> {
            LearnOrderScreen(
                navigateBackToHomeScreen = onNavigateBack
            )
        }
    }
}


@Serializable
object LearnIntroRoute

@Serializable
object LearnMapRoute

@Serializable
object LearnAppointmentRoute

@Serializable
object LearnOrderRoute