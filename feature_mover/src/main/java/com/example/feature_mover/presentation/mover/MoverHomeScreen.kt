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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.feature_mover.R
import com.example.feature_mover.presentation.mover.routes.MoverScreenRoutes
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel
import java.time.format.DateTimeFormatter
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.*






@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoverHomeScreen(
    navController: NavController,
    moverViewModel: MoverViewModel,

    ){
    val moversList by moverViewModel.moversList.collectAsState()
    val startAddress by moverViewModel.startAddress.collectAsState()

    println(moversList)
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


        SearchWidget(onSearch = { /* Handle search action here */ })



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
            Text(text = "Movers ${moversList.size}")
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





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(
    onSearch: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("20 Mar") }
    var roomCount by remember { mutableStateOf("4") }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Location Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Location",
                        tint = Color.Black
                    )
                    Text(
                        text = "Johannesburg, 1 Road Ubuntu",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Settings",
                    tint = Color(0xFFFF6B00)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Date and Rooms Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Date Selection
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CHOOSE DATE",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = selectedDate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Rooms Selection
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "ROOMS",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = roomCount,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search TextField
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Search location / name", color = Color.Gray)
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Button
            Button(
                onClick = onSearch,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B00)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Search",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MoverHomeScreenPreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    MoverHomeScreen(
        navController = navController,
        moverViewModel = MoverViewModel(),
    )
}



