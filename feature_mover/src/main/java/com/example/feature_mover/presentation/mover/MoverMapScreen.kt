package com.example.feature_mover.presentation.mover

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.reuseable.maps.mapui.MapScreen

@Composable
fun MoverMapScreen(navController: NavController) {

    Scaffold(
        topBar = {
            // You can add a top app bar here if needed.
        },
        bottomBar = {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Let's Go!")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Add the MapScreen Composable
            MapScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),

            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MoverMapScreenPreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    MoverMapScreen(navController = navController)
}