package com.example.feature_mover.presentation.mover

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.navigation.NavController

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feature_mover.R
import com.example.feature_mover.presentation.mover.routes.MoverScreenRoutes
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel
import java.time.format.DateTimeFormatter


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




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoverHomeScreen(
    navController: NavController,
    moverViewModel: MoverViewModel,

    ){
    val moversList by moverViewModel.moversList.collectAsState()
    val startAddress by moverViewModel.startAddress.collectAsState()

    val date by moverViewModel.selectedDate.collectAsState()
    val rooms by moverViewModel.startRooms.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.moverlogo),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f).height(353.dp)
        )


        // Search Bar
        Card(
            modifier = Modifier
                .background( Color.White,shape = RoundedCornerShape(8.dp))
                .padding(25.dp,16.dp).shadow(elevation = 1.dp).height(226.dp),

        ) {
            Column {
                // Title Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = startAddress,
                        color = Color.Black,
                    )
                }

                // Choose Dates and Number of Children Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = date.format(DateTimeFormatter.ofPattern("dd MMM")), color = Color.Black)
                    Text(text = rooms, color = Color.Black)
                }

                // Search Location/Name Row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Search location/name",
                        color = Color.Black
                    )
                }
                Button(
                    onClick = {
//                        navController.navigate(BabySitterScreen.MapScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    Text(text = "Search")
                }
            }
        }

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
//                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Movers 3")
            Icon(modifier = Modifier
                .clickable { navController.navigate(MoverScreenRoutes.FilterScreen.route) },
                imageVector = Icons.Default.Menu, contentDescription = "Filter", tint = MaterialTheme.colorScheme.primary)
        }

        // Lazy list of babysitters
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(moversList) { mover ->
                MoverCard(mover,navController)
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun MoverHomeScreenPreview() {
//    val navController = rememberNavController() // Use rememberNavController() for previews
//    MoverHomeScreen(
//        navController = navController,
//    )
//}



