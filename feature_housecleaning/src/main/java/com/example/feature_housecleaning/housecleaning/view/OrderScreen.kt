package com.example.feature_housecleaning.housecleaning.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_housecleaning.housecleaning.data.HouseCleaningViewModel
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen

@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: HouseCleaningViewModel,
    indexCleaner: String?
) {
    val cleaner = viewModel.cleaners[indexCleaner!!.toInt()]
    val house = viewModel.houses[viewModel.houses.size - 1]

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "Order Details",
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Cleaner Profile Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = cleaner.imageResId),
                        contentDescription = "Cleaner Image",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Cleaner", color = Color.White)
                    Text(text = cleaner.name, color = Color.White)
                }
            }

            // Date and Location Section
            Text(
                text = "Date",
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = cleaner.location,
                    color = Color.White
                )
            }
        }

        // House Details Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // House Name
            Text(
                text = "House Details",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Name: ${house.name}",
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Room and Area Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Area: ${house.areaPerMeterSquared} m²")
                Text(text = "Rooms: ${house.roomCount}")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Bathroom and Ironing Count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Bathrooms: ${house.bathroomCount}")
                Text(text = "Ironing: ${house.ironingCount}")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Availability Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Available: ${house.date}")
            }
        }

        // Lower Section (Cost Breakdown)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Cleaner Cost
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Cleaner")
                Text(text = "$${cleaner.costPerHour}/hr")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Subtotal (hardcoded for now, but this can be dynamic)
            Text(text = "Subtotal: $${cleaner.costPerHour * house.areaPerMeterSquared}")

            Spacer(modifier = Modifier.height(8.dp))

            // Delivery Fees
            Text(text = "Delivery Fees: $15.00") // Static value, can be dynamic

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Total Amount (example calculation)
            Text(
                text = "Total Amount: $${cleaner.costPerHour * house.areaPerMeterSquared + 15}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Place Order Button
            Button(
                onClick = {
                    navController.navigate(HouseCleaningScreen.HouseCleaningMainScreen.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Place Order")
            }
        }
    }
}

