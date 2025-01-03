package com.example.feature_babysitter.babysitter.view

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
import com.example.feature_babysitter.babysitter.data.BabySitterViewModel
import com.example.feature_babysitter.babysitter.data.Babysitter
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen


@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: BabySitterViewModel,
    sort: String?,
    max: String?,
    min: String?
) {
    var showPopup by remember { mutableStateOf(false) }
    var expandedBabysitter by remember { mutableStateOf<Babysitter?>(null) }
    val children = viewModel.children
    val child = children[0] // You can replace this with your dynamic child list logic

    var babysitters = viewModel.babysitters

    if (sort.equals("Recommend")) {
        babysitters = viewModel.recommendSortBabysitter
    } else if (sort.equals("Distance")) {
        babysitters = viewModel.distanceSortBabysitter
    } else if (sort.equals("Cost")) {
        babysitters = viewModel.priceSortBabysitter
    }

    babysitters = babysitters.filter {
        it.costPerHour < max!!.toDouble() && it.rating > min!!.toDouble()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(child.imageResId),
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
                            BabySitterScreen.BabyMapScreen.withArgs(
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
        if (showPopup && expandedBabysitter != null) {
            OrdersPopupMenu(
                navController = navController,
                viewModel = viewModel,
                babysitter = expandedBabysitter!!, // Passing the selected babysitter to the popup
                onDismiss = { showPopup = false }
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
            Text(text = "Babysitters: ${babysitters.size} results")
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
            items(babysitters) { babysitter ->
                if (!showPopup) {
                    BabysitterCard(
                        babysitter = babysitter,
                        onClick = {
                            expandedBabysitter = babysitter  // Set the selected babysitter
                            showPopup = true                 // Show the popup
                        }
                    )
                }
            }
        }
    }
}