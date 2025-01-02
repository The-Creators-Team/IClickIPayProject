package com.example.feature_pet.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feature_pet.R
import com.example.feature_pet.pet.model.Guardian
import com.example.feature_pet.viewmodel.GuardianViewModel

@Composable
fun PetMapScreen(
    guardianViewModel: GuardianViewModel,
    navigateToPetCalendar: () -> Unit
) {

    var expandedGuardian by remember { mutableStateOf<Guardian?>(null) }
    val guardians = guardianViewModel.guardianList

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.chihuahua), // Replace with your drawable resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Search Bar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle search query */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                placeholder = { Text("Search Dog Sitters...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            )

            Spacer(modifier = Modifier.weight(1f)) // Spacer to push the LazyRow to the bottom

            // Babysitter Cards LazyRow
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(guardians.size) { guardian ->
                    GuardianCard(
                        guardian = guardians.get(guardian),
                        onClick = { expandedGuardian = guardians.get(guardian) }
                    )
                }
            }
        }

        // Expanded Babysitter Details
        expandedGuardian?.let { guardian ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { expandedGuardian = null }, // Dismiss when clicking outside
                contentAlignment = Alignment.BottomCenter
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp) // Half-screen height
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Babysitter Details
                        Column {
                            Image(
                                painter = painterResource(id = guardian.imageResId),
                                contentDescription = "Babysitter Image",
                                modifier = Modifier
                                    .size(50.dp) // Set the size for the circular image
                                    .clip(CircleShape) // Clip the image into a circle
                                    .border(
                                        1.dp,
                                        Color.Gray,
                                        CircleShape
                                    ), // Optional border around the circle
                                contentScale = ContentScale.Crop // Crop the image to fill the circle
                            )
                            Text(text = guardian.name)
                            Text(text = "Rating: ${guardian.rating}")
                            Text(text = "Cost per hour: $${guardian.price}")
                        }

                        // Take Appointment Button
                        Button(
                            onClick = { navigateToPetCalendar()},
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Take Appointment")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GuardianCard(guardian: Guardian, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Babysitter Image
            Image(
                painter = painterResource(id = guardian.imageResId),
                contentDescription = "Dog Sitter Image",
                modifier = Modifier
                    .height(150.dp)
                    .width(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = guardian.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Rating, Distance, and Cost Per Hour
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${guardian.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${guardian.price}/day")
                }
            }
        }
    }
}


