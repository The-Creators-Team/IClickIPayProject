package com.example.feature_handyman.handyman

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_handyman.R

@Composable
fun HandymanProfile() {
    Scaffold(
        bottomBar = {
            // You can add a bottom bar here if needed
        }
    ) { innerPadding ->
        // Background Map
        Box(modifier = Modifier.fillMaxSize()) {
            MapScreen(modifier = Modifier.fillMaxSize())

            // Profile Content
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.4F)
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(Color.White.copy()) // Semi-transparent background
                    .align(Alignment.BottomCenter), // Align to the top center
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
                    text = "28 Broad Street\nJohannesburg",
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

                // Take Appointment Button
                Button(
                    onClick = { /* Handle appointment action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(text = "Take Appointment")
                }
            }
        }
    }
}

@Preview
@Composable
fun HandymanProfilePreview() {
    HandymanProfile()
}
