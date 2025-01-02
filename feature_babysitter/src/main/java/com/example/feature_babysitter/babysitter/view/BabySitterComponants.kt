package com.example.feature_babysitter.babysitter.view

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
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
import com.example.feature_babysitter.babysitter.data.BabySitterViewModel
import com.example.feature_babysitter.babysitter.data.Babysitter
import com.example.feature_babysitter.babysitter.data.Child
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen


@Composable
fun ChildCard(
    child: Child,
    navController: NavController,
    index: Int,
    viewModel: BabySitterViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image in CircleCrop
            Image(
                painter = painterResource(id = child.imageResId),
                contentDescription = "Child Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop // Ensures the image fills and crops to the circle
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${child.name}, Age ${child.age}",
                )
                Text(
                    text = child.gender,
                )
            }

            IconButton(onClick = {
                navController.navigate(BabySitterScreen.EditChildDetails.withArgs(index.toString()))
            }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Child")
            }
            // Delete Icon
            IconButton(onClick = {
                viewModel.removeChild(viewModel.children.get(index))
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Child")
            }
        }
    }
}


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
fun ParcelSlider(title: String) {
    var sliderValue by remember { mutableStateOf(0f) }
    Column {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..60f,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Set: $${sliderValue.toInt()}")
    }
}


@Composable
fun OrdersPopupMenu(navController: NavController, viewModel: BabySitterViewModel, babysitter: Babysitter, onDismiss: () -> Unit) {
    val babysitters = viewModel.babysitters
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
                    painter = painterResource(id = babysitter.imageResId),
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
                Text(text = babysitter.name)
                Text(text = "Location: ${babysitter.location}")
                Text(text = "Rating: ${babysitter.rating}")
                Text(text = "Distance: ${babysitter.distance} meters")
                Text(text = "Cost per hour: $${babysitter.costPerHour}")
                Button(
                    onClick = {
                        navController.navigate(
                            BabySitterScreen.OrderScreen.withArgs(
                                babysitters.indexOf(babysitter).toString(),
                                "0"
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
fun BabysitterCard(babysitter: Babysitter, onClick: () -> Unit) {
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
                    painter = painterResource(id = babysitter.imageResId),
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
                text = babysitter.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = babysitter.location,
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
                    Text(text = "${babysitter.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${babysitter.distance} m")
                }
                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${babysitter.costPerHour}/hr")
                }
            }
        }
    }
}

@Composable
fun BabysitterCardMap(babysitter: Babysitter, onClick: () -> Unit) {
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
                    painter = painterResource(id = babysitter.imageResId),
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
                text = babysitter.name,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Text(
                text = babysitter.location,
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
                    Text(text = "${babysitter.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${babysitter.distance} m")
                }

                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${babysitter.costPerHour}/hr")
                }
            }
        }
    }
}

