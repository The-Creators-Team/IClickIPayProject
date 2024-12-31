package com.example.feature_learn

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable


@Composable
fun LearnNavigation(
    onNavigateBack: () -> Unit
) {
    val learnNavController = rememberNavController()

    NavHost(
        navController = learnNavController,
        startDestination = LearnIntroRoute
    ){
        composable<LearnIntroRoute> {
            LearnIntroScreen(
                navigateToTeacherMap = {learnNavController.navigate(LearnTeacherMapRoute)},
                navigateBackToHomeScreen = onNavigateBack
            )
        }
        composable<LearnTeacherMapRoute> {
            LearnTeacherMap()
        }
    }
}


@Serializable
object LearnIntroRoute

@Serializable
object LearnTeacherMapRoute