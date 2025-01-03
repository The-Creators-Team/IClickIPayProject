package com.example.feature_babysitter.babysitter.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.navigation.NavController
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_babysitter.babysitter.data.BabySitterViewModel
import com.example.feature_babysitter.babysitter.data.Babysitter
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen


@Composable
fun BabyMapScreen(
    navController: NavController,
    viewModel: BabySitterViewModel,
    sort: String?,
    max: String?,
    min: String?
) {
    var expandedBabysitter by remember { mutableStateOf<Babysitter?>(null) }

    var babysitters = viewModel.babysitters
    if (sort.equals("Recommend")) {
        babysitters = viewModel.recommendSortBabysitter
    } else if (sort.equals("Distance")) {
        babysitters = viewModel.distanceSortBabysitter
    } else if (sort.equals("Cost")) {
        babysitters = viewModel.priceSortBabysitter
    }
    babysitters =
        babysitters.filter { (it.costPerHour < max!!.toDouble() && it.rating > min!!.toDouble()) }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        MapScreen(modifier = Modifier.fillMaxSize())

        // Search Bar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(
                        BabySitterScreen.SearchScreen.withArgs(
                            sort.toString(),
                            max.toString(),
                            min.toString()
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp)
            ) {
                Text(text = "Back to List View")
            }

            Spacer(modifier = Modifier.weight(1f)) // Spacer to push the LazyRow to the bottom

            Text(text = "Babysitters: ${babysitters.size} results")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(babysitters) { babysitter ->
                    BabysitterCardMap(
                        babysitter = babysitter,
                        onClick = { expandedBabysitter = babysitter }
                    )
                }
            }
        }

        // Expanded Babysitter Details
        expandedBabysitter?.let { babysitter ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { expandedBabysitter = null }, // Dismiss when clicking outside
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
                        }

                        val unfilterBabysitter = viewModel.babysitters
                        Button(
                            onClick = {
                                navController.navigate(
                                    BabySitterScreen.OrderScreen.withArgs(
                                        unfilterBabysitter.indexOf(babysitter).toString(),
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
    }
}