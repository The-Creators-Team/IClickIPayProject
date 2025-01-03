package com.example.feature_housecleaning.housecleaning.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen

@Composable
fun HouseCalendarScreen(navController: NavController) {
    Column {
        Button(
            onClick = {
                navController.navigate(HouseCleaningScreen.FilterScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }
}