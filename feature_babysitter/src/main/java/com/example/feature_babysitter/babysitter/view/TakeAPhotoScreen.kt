package com.example.feature_babysitter.babysitter.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_babysitter.R
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen

@Composable
fun TakeAPhotoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Row {
            // put icon images if needed
            Text(
                text = "Take a photo",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        // Image placeholder
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder), // Replace with your drawable resource
            contentDescription = "Cam Placeholder",
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray) // Optional: Background for the placeholder
        )

        // Button
        Button(onClick = {
            // need to add cam functionallty
            navController.navigate(BabySitterScreen.ChildListScreen.route)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.cam_icon), // Use your custom drawable
                contentDescription = "Camera Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
