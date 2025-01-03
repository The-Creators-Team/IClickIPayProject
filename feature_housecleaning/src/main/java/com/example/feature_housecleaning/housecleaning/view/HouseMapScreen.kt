package com.example.feature_housecleaning.housecleaning.view

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.NavController
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_babysitter.babysitter.data.Babysitter
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen
import com.example.feature_babysitter.babysitter.view.BabysitterCardMap
import com.example.feature_housecleaning.housecleaning.data.Cleaner
import com.example.feature_housecleaning.housecleaning.data.HouseCleaningViewModel
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen


@Composable
fun HouseMapScreen(
    navController: NavController,
    viewModel: HouseCleaningViewModel,
    sort: String?,
    max: String?,
    min: String?
) {
    var expandedCleaner by remember { mutableStateOf<Cleaner?>(null) }

    var cleaners = viewModel.cleaners
    if (sort.equals("Recommend")) {
        cleaners = viewModel.recommendSortCleaner
    } else if (sort.equals("Distance")) {
        cleaners = viewModel.distanceSortBabysitter
    } else if (sort.equals("Cost")) {
        cleaners = viewModel.priceSortBabysitter
    }
    cleaners =
        cleaners.filter { (it.costPerHour < max!!.toDouble() && it.rating > min!!.toDouble()) }


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
                        HouseCleaningScreen.SearchScreen.withArgs(
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

            Text(text = "Cleaners: ${cleaners.size} results")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(cleaners) { cleaner ->

                    CleanerCardMap(
                        cleaner = cleaner,
                        onClick = {expandedCleaner = cleaner}
                    )
                }
            }
        }

        // Expanded Babysitter Details
        expandedCleaner?.let { cleaner ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { expandedCleaner = null }, // Dismiss when clicking outside
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
                        }

                        val unfilterCleaner = viewModel.cleaners
                        Button(
                            onClick = {
                                navController.navigate(
                                    HouseCleaningScreen.OrderScreen.withArgs(
                                        unfilterCleaner.indexOf(cleaner).toString()
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
