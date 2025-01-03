package com.example.feature_housecleaning.housecleaning.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.feature_babysitter.R
import com.example.feature_housecleaning.housecleaning.data.Cleaner
import com.example.feature_housecleaning.housecleaning.data.HouseCleaningViewModel
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen


@Composable
fun StarRatingSelector(
    initialRating: Double = 0.0,
    onRatingSelected: (Double) -> Unit
) {
    var selectedRating by remember { mutableStateOf(initialRating) }
    val totalStars = 5

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..totalStars) {
            val isHalfFilled = selectedRating >= i - 0.5 && selectedRating < i
            val isFullFilled = selectedRating >= i

            Icon(
                painter = painterResource(
                    if (isFullFilled) R.drawable.ic_star_filled
                    else if (isHalfFilled) R.drawable.ic_star_half
                    else R.drawable.ic_star_outline
                ),
                contentDescription = "Star $i",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        selectedRating = if (selectedRating == i.toDouble()) {
                            i - 0.5
                        } else {
                            i.toDouble()
                        }
                        onRatingSelected(selectedRating)
                    },
                tint = Color.Yellow
            )
        }
    }
}


@Composable
fun OrdersPopupMenu(navController: NavController,viewModel: HouseCleaningViewModel, cleaner: Cleaner,  onDismiss: () -> Unit) {
    val cleaners = viewModel.cleaners
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = cleaner.imageResId),
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
                Text(text = cleaner.name)
                Text(text = "Location: ${cleaner.location}")
                Text(text = "Rating: ${cleaner.rating}")
                Text(text = "Distance: ${cleaner.distance} meters")
                Text(text = "Cost per hour: $${cleaner.costPerHour}")
                Button(
                    onClick = {
                        navController.navigate(
                            HouseCleaningScreen.OrderScreen.withArgs(
                                cleaners.indexOf(cleaner).toString()
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Take Appointment")
                }
            }


        }
    }
}

@Composable
fun CleanerCard(cleaner: Cleaner, onClick: () -> Unit) {
    var isHeartFilled by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Babysitter Image
            Box(modifier = Modifier.height(150.dp)) {
                Image(
                    painter = painterResource(id = cleaner.imageResId),
                    contentDescription = "Cleaner Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { isHeartFilled = !isHeartFilled },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isHeartFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isHeartFilled) Color.Red else Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = cleaner.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = cleaner.location,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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
                    Text(text = "${cleaner.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${cleaner.distance} m")
                }
                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${cleaner.costPerHour}/hr")
                }
            }
        }
    }
}

@Composable
fun CleanerCardMap(cleaner: Cleaner, onClick: () -> Unit) {
    var isHeartFilled by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .size(width = 200.dp, height = 300.dp)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Babysitter Image
            Box(modifier = Modifier.height(150.dp)) {
                Image(
                    painter = painterResource(id = cleaner.imageResId),
                    contentDescription = "Babysitter Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { isHeartFilled = !isHeartFilled },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isHeartFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isHeartFilled) Color.Red else Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = cleaner.name,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Text(
                text = cleaner.location,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
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
                    Text(text = "${cleaner.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${cleaner.distance} m")
                }

                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${cleaner.costPerHour}/hr")
                }
            }
        }
    }
}

