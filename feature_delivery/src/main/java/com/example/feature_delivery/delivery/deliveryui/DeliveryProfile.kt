package com.example.feature_delivery.delivery.deliveryui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_delivery.R
import com.example.feature_delivery.delivery.nami.DeliveryNamiScreen

@Composable
fun DeliveryProfile(navController: NavController) {
    Scaffold(
        bottomBar = {
            // Take Appointment Button
            Button(
                onClick = { navController.navigate(DeliveryNamiScreen.OrderDetailsPage.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color(0xFFFFA500)) // Orange color for the button
            ) {
                Text(text = "Take Appointment", color = Color.White)
            }
        }

    ) { innerPadding ->
        // Background Map
        Box(modifier = Modifier.fillMaxSize()) {
            MapScreen(modifier = Modifier.fillMaxSize())

            // Profile Content
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5F) // Adjusted height for better visibility
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(Color.White.copy(alpha = 0.9f)) // Slightly transparent background
                    .align(Alignment.BottomCenter), // Align to the bottom center
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.sukuna_jjk), // Use your own image resource
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                        .clip(CircleShape) // Circular shape
                )

                // Name
                Text(
                    text = "Jenny Jones",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )

                // Rating
                Text(
                    text = "★ 4.8",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                // Service Fee
                Text(
                    text = "$15 / hr",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                // Address
                Text(
                    text = "28 Broad Street",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Services Offered
                Text(
                    text = "Services Offered:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // List of services
                Text(
                    text = "- Plumber\n- Carpenter",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}