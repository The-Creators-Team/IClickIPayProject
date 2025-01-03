package com.example.feature_handyman.handyman.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_handyman.R
import com.example.feature_handyman.handyman.data.GenerateDummyHandymen
import com.example.feature_handyman.handyman.data.HandyData
import com.example.feature_handyman.handyman.nami.HandyManNamiScreen




@Composable
fun HandyManSearchScreen(
    navController: NavController,
    selectedOption: String,
    rating: Int,
    handymen: List<HandyData> // All the handymen data
) {

    // State to track the currently selected handyman
    val (selectedHandyman, setSelectedHandyman) = remember { mutableStateOf<HandyData?>(null) }

    // Filtered and Sorted Handymen
    val filteredAndSortedHandymen = remember(selectedOption, rating) {
        handymen
            .filter {
                // Filtering logic
                when (selectedOption) {
                    "Price" -> it.price <= 500
                    "Popularity" -> it.popularity >= 3
                    "Rating" -> it.rating >= rating
                    else -> true
                }
            }
            .let { filteredList ->
                // Sorting logic
                when (selectedOption) {
                    "Price" -> filteredList.sortedBy { it.price }
                    "Popularity" -> filteredList.sortedByDescending { it.popularity }
                    "Rating" -> filteredList.sortedByDescending { it.rating }
                    else -> filteredList
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image Section: Display the selected handyman or a placeholder
        if (selectedHandyman != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = selectedHandyman.imageResId),
                    contentDescription = selectedHandyman.name,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Text(text = selectedHandyman.name, style = MaterialTheme.typography.titleLarge)
                Text(text = selectedHandyman.location, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Rating: ${selectedHandyman.rating} | Price: ${selectedHandyman.price} | Popularity: ${selectedHandyman.popularity}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.cam_placeholder),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
        }

        // Favorites and Orders Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorites", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favorites", color = Color.White)
            }
            Row(
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate(HandyManNamiScreen.HandyManMap.route)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.AddCircle, contentDescription = "Orders", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Orders", color = Color.White)
            }
        }

        // Handyman List Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Handymen", style = MaterialTheme.typography.titleLarge)
            Icon(
                modifier = Modifier.clickable {
                    navController.navigate(HandyManNamiScreen.HandyManFilters.route)
                },
                imageVector = Icons.Default.Menu,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Filtered and Sorted Handyman List (Lazy Grid)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.4F)
        ) {
            items(filteredAndSortedHandymen) { handyman ->
                HandyManCard(
                    onClick = {
                        setSelectedHandyman(handyman) // Update the selected handyman
                    },
                    handyData = handyman
                )
            }
        }
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    val navController = NavController(LocalContext.current)
    val selectedOption = "Sort by"
    val rating = 0
    val handymen = GenerateDummyHandymen()
    HandyManSearchScreen(navController, selectedOption, rating, handymen)
}