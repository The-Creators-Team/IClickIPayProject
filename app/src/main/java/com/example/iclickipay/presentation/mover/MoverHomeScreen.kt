package com.example.iclickipay.presentation.mover

import androidx.compose.foundation.layout.Column
import androidx.navigation.NavController

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iclickipay.R


data class Babysitter(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour:Int
)

val babysitters = listOf<Babysitter>(
    Babysitter("Lee", "Corona",R.drawable.moverlogo,3.0,500,15 ),
    Babysitter("Alice", "Corona",R.drawable.moverlogo,3.0,500,15 ),
    Babysitter("Nina", "Corona",R.drawable.moverlogo,3.0,500,15 )

)


@Composable
fun MoverHomeScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.moverlogo),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Bar with two options: Favorites and Orders
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(modifier = Modifier
                .clickable {  },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favorites", color = Color.White)

            }
            Row(modifier = Modifier
                .clickable {  },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Orders", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Orders", color = Color.White)
            }
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
            Text(text = "Babysitters")
            Icon(modifier = Modifier
                .clickable {  },
                imageVector = Icons.Default.Menu, contentDescription = "Filter", tint = MaterialTheme.colorScheme.primary)
        }

        // Lazy list of babysitters
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(babysitters) { babysitter ->
                MoverCard(babysitter = babysitter,navController)
            }
        }
    }
}



