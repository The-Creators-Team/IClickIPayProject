package com.example.feature_housecleaning.housecleaning.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen
import com.example.feature_babysitter.babysitter.view.BabysitterCard
import com.example.feature_housecleaning.R
import com.example.feature_housecleaning.housecleaning.data.Cleaner
import com.example.feature_housecleaning.housecleaning.data.HouseCleaningViewModel
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: HouseCleaningViewModel,
    sort: String?,
    max: String?,
    min: String?
    ) {
    var showPopup by remember { mutableStateOf(false) }
    var expandedCleaner by remember { mutableStateOf<Cleaner?>(null) }
    val houses = viewModel.houses
    val house = houses[houses.size-1]

    var cleaners = viewModel.cleaners

    if (sort.equals("Recommend")) {
        cleaners = viewModel.recommendSortCleaner
    } else if (sort.equals("Distance")) {
        cleaners = viewModel.distanceSortBabysitter
    } else if (sort.equals("Cost")) {
        cleaners = viewModel.priceSortBabysitter
    }

    cleaners = cleaners.filter {
        it.costPerHour < max!!.toDouble() && it.rating > min!!.toDouble()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.cleaners_background),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Favorites Row
            Row(
                modifier = Modifier
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favorites", color = Color.White)
            }

            // Orders Row
            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                            HouseCleaningScreen.HouseMapScreen.withArgs(
                                sort.toString(),
                                max.toString(),
                                min.toString()
                            )
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Map View ",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Map View", color = Color.White)
            }
        }

        // Show Popup Menu
        if (showPopup && expandedCleaner != null) {
            OrdersPopupMenu(
                navController,
                viewModel,
                cleaner = expandedCleaner!!,
                onDismiss = {showPopup = false}
            )
        }

        // Bar with title "Babysitters" and filter icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Babysitters: ${cleaners.size} results")
            Icon(
                modifier = Modifier.clickable {
                    navController.navigate(BabySitterScreen.FilterScreen.route)
                },
                imageVector = Icons.Default.Menu,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Lazy list of babysitters
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(cleaners) { cleaner ->
                if (!showPopup) {
                    CleanerCard(
                        cleaner = cleaner,
                        onClick = {
                            expandedCleaner = cleaner
                            showPopup = true
                        }
                    )
                }
            }
        }
    }
}
