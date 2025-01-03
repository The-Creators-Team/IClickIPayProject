package com.example.feature_hotel.hotel.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.feature_hotel.R
import com.example.feature_hotel.hotel.data.hotelList
import com.example.feature_hotel.hotel.domain.HotelListDataObject
import com.example.feature_hotel.hotel.presentation.navigation.HotelScreen


@Composable
fun HotelSearchScreen(navController: NavController) {
    MaterialTheme {
        Box {
            Column {
                TopHead()
                Spacer(modifier = Modifier.height(80.dp)) // Leave space for the elevated search section
                SearchSec(navController = navController)
                FavAndOrdersRow()
                HotelList(navController = navController)
            }
        }
    }
}

@Composable
fun TopHead() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.hotel_room), // Replace with your image resource
            contentDescription = "Hotel Room",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FloatHomeIcon(navController: NavController) {
    Row {
        IconButton(
            onClick = {navController.navigate(HotelScreen.HotelMainScreen.route)},
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape).align(Alignment.Top)
        ) {
            Icon(Icons.Outlined.OtherHouses, contentDescription = "Home", tint = Color.White)
        }
    }


}

@Composable
fun SearchSec(navController: NavController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .offset(y = (-80).dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row{
                Text(text = "Johannesburg", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Places",color = Color.Gray, fontSize = 13.sp)
                IconButton(onClick = { navController.navigate(HotelScreen.HotelMapScreen.route) }) {
                    Icon(
                        imageVector = Icons.Filled.LocationSearching,
                        contentDescription = "Location",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = "20 Mar - 22 Mar",
                    onValueChange = {},
                    label = { Text("CHOOSE DATES") },
                    modifier = Modifier.weight(1f).clickable{
                        navController.navigate(HotelScreen.ChooseDateScreen.route)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = "1 Room - 2 Adults",
                    onValueChange = {},
                    label = { Text("NUMBERS OF ROOMS") },
                    modifier = Modifier.weight(1f).clickable{
                        navController.navigate(HotelScreen.RoomsScreen.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Search Location / name / country") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search hotels")
            }
        }
    }
}

@Composable
fun FavAndOrdersRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(text = "Favorites")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.LibraryBooks,
                contentDescription = "Order",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(text = "Orders")
        }

    }
}


@Composable
fun HotelList(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row{
            Text(text = "Recommended hotels", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(HotelScreen.FilterScreen.route)}) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(hotelList){ hotelData ->
                HotelListItem(hotelData,navController = navController)
            }
        }

    }
}

@Composable
fun HotelListItem(
    hotelData: HotelListDataObject,navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            // Hotel Image
            Image(
                painter = painterResource(id = hotelData.hotelImage),
                contentDescription = "Babysitter Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp).clickable { navController.navigate(HotelScreen.SingleScreen.route) },
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = hotelData.hotelName,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = hotelData.hotelCity,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Rating, Distance, and Cost
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
                    Text(text = "${hotelData.hotelRating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${hotelData.hotelDistance}")
                }
                // Cost
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${hotelData.hotelPrice}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HotelSearchScreenPreview() {
    MaterialTheme{
        HotelSearchScreen(navController = NavController(LocalContext.current))
    }
}