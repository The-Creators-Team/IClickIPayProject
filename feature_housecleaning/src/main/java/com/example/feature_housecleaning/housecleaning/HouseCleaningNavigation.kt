package com.example.feature_housecleaning.housecleaning

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_babysitter.babysitter.BabySitterScreen
import com.example.feature_babysitter.babysitter.Babysitter
import com.example.feature_babysitter.babysitter.BabysitterCard
import com.example.feature_babysitter.babysitter.Child
import com.example.feature_babysitter.babysitter.ParcelSlider
import com.example.feature_babysitter.babysitter.babysitters
import com.example.feature_housecleaning.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField


@Composable
fun HouseCleaningNavigation(
    //NAV STEP 1 add nav param for mainactivity/homescreen to use
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HouseCleaningScreen.HouseCleaningMainScreen.route
    ) {
        composable(route = HouseCleaningScreen.HouseCleaningMainScreen.route) {
            HouseCleaningMainScreen(
                navController = navController,
                //NAV STEP 2 pass param to screen componant where you want a home button to go back to app home
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = HouseCleaningScreen.YourHouseScreen.route) {
            YourHouseScreen(navController = navController)
        }
        composable(route = HouseCleaningScreen.FilterScreen.route) {
            FilterScreen(navController = navController)
        }
        composable(route = HouseCleaningScreen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = HouseCleaningScreen.MapScreen.route) {
            MapScreen(navController = navController)
        }
        composable(route = HouseCleaningScreen.OrderScreen.route) {
            OrderScreen(navController = navController, Cleaners[0], House("white House", 100, R.drawable.cam_placeholder))
        }


    }
}

@Composable
fun HouseCleaningMainScreen(
    navController: NavController,
    onNavigateBack: () -> Unit
    //NAV STEP 3 accept param in component u want to have button
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(R.drawable.house_cleaning_logo),
            contentDescription = "house cleaning Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "House Cleaning",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "Welcome to HouseCleaning an app to schedule a HouseCleaning for your House. Write reviews and filter through are list of babysitters to find the perfect match for your child and date ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navController.navigate(HouseCleaningScreen.YourHouseScreen.route)
            }
        ) {
            Text(text = "Lets Go")
//        }
            Spacer(modifier = Modifier.height(16.dp))

            // Back to Home Button
        }
        Button(
            //STEP 4 add param call to button
            onClick = onNavigateBack
        ) {
            Text(text = "Back to All Apps")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourHouseScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf("Recommend") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(40.dp)) {
        // Title
        Text(text = "Your House ")

        Spacer(modifier = Modifier.height(16.dp))
        //com.example.feature_babysitter.babysitter.ParcelSlider("Area/m2")

        // Dropdown menu
        Text("Sort By")

        // ExposedDropdownMenuBox is used for the dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // TextField to show the selected option and the dropdown menu
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Rooms") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )
            // The dropdown itself
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
            }
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // TextField to show the selected option and the dropdown menu
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Bathroom") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )
            // The dropdown itself
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
            }
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // TextField to show the selected option and the dropdown menu
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Ironing") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )
            // The dropdown itself
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
            }
        }

        //com.example.feature_babysitter.babysitter.ParcelSlider("Availability")

        Button(
            onClick = {
                navController.navigate(HouseCleaningScreen.FilterScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf("Recommend") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(40.dp)) {
        // Title
        Text(text = "Filter Options")

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown menu
        Text("Sort By")

        // ExposedDropdownMenuBox is used for the dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // TextField to show the selected option and the dropdown menu
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Sorting Option") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )

            // The dropdown itself
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

            }
        }
        //com.example.feature_babysitter.babysitter.ParcelSlider("Price/Hour")
        Button(
            onClick = {
                navController.navigate(HouseCleaningScreen.SearchScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Apply")
        }
    }
}

data class Cleaner(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour:Int
)

val Cleaners = listOf<Cleaner>(
    Cleaner("Lee", "Corona", R.drawable.cam_placeholder,3.0,500,15 ),
    Cleaner("Alice", "Corona", R.drawable.cam_placeholder,3.0,500,15 ),
    Cleaner("Nina", "Corona", R.drawable.cam_placeholder,3.0,500,15 )

)

@Composable
fun SearchScreen(navController: NavController) {
    var showPopup by remember { mutableStateOf(false) }
    var expandedCleaner by remember { mutableStateOf<Cleaner?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder),
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
                    .clickable { showPopup = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Orders",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Orders", color = Color.White)
            }
        }

        // Show Popup Menu
        if (showPopup) {
            OrdersPopupMenu(navController, onDismiss = { showPopup = false })
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
            Text(text = "Cleaners")
            Icon(
                modifier = Modifier.clickable { },
                imageVector = Icons.Default.Menu,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Lazy list of babysitters
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(Cleaners) { cleaner ->
                CleanerCard(
                    cleaner = cleaner,
                    onClick = { expandedCleaner = cleaner }
                )
            }
        }
    }
}

@Composable
fun OrdersPopupMenu(navController: NavController,onDismiss: () -> Unit) {
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
                        text = "Location",
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
                    Text(text = "Choose Dates", color = Color.Black)
                    Text(text = "Number of Children", color = Color.Black)
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
                        navController.navigate(HouseCleaningScreen.MapScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    Text(text = "Search")
                }
            }
        }
    }
}

@Composable
fun CleanerCard(cleaner: Cleaner, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            Image(
                painter = painterResource(id = cleaner.imageResId),
                contentDescription = "Cleaner Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = cleaner.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = cleaner.location,
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
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color.Yellow)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${cleaner.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Distance", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${cleaner.distance} m")
                }
                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${cleaner.costPerHour}/hr")
                }
            }
        }
    }
}

@Composable
fun MapScreen(navController: NavController){
    var expandedCleaner by remember { mutableStateOf<Cleaner?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.map_temp), // Replace with your drawable resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Search Bar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle search query */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                placeholder = { Text("Search Babysitters...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            )

            Spacer(modifier = Modifier.weight(1f)) // Spacer to push the LazyRow to the bottom

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(Cleaners) { cleaner ->
                    CleanerCard(
                        cleaner = cleaner,
                        onClick = { expandedCleaner = cleaner }
                    )
                }
            }
        }

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
                        Column {
                            Image(
                                painter = painterResource(id = cleaner.imageResId),
                                contentDescription = "Cleaner Image",
                                modifier = Modifier
                                    .size(50.dp) // Set the size for the circular image
                                    .clip(CircleShape) // Clip the image into a circle
                                    .border(1.dp, Color.Gray, CircleShape), // Optional border around the circle
                                contentScale = ContentScale.Crop // Crop the image to fill the circle
                            )
                            Text(text = cleaner.name)
                            Text(text = "Location: ${cleaner.location}")
                            Text(text = "Rating: ${cleaner.rating}")
                            Text(text = "Distance: ${cleaner.distance} meters")
                            Text(text = "Cost per hour: $${cleaner.costPerHour}")
                        }

                        // Take Appointment Button
                        Button(
                            onClick = {
                                navController.navigate(HouseCleaningScreen.OrderScreen.route)
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

data class House(
    val name: String,
    var age:Int,
    val imageResId: Int // Drawable resource ID for the image
)

@Composable
fun OrderScreen(navController: NavController, cleaner: Cleaner, house: House) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Upper Section with Theme Background Color
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "Order Details",
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Babysitter and Child Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Babysitter Profile
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = cleaner.imageResId),
                        contentDescription = "Babysitter Image",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Cleaner", color = Color.White)
                    Text(text = cleaner.name, color = Color.White)
                }
            }

            // Date and Location
            Text(
                text = "Date",
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = cleaner.location,
                    color = Color.White
                )
            }
        }

        // Lower Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Cleaner")
                Text(text = "$${cleaner.costPerHour}/hr")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Subtotal
            Text(text = "Subtotal")

            Spacer(modifier = Modifier.height(8.dp))

            // Delivery Fees
            Text(text = "Delivery Fees")

            Spacer(modifier = Modifier.height(16.dp))

            // Divider
            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Total Amount
            Text(
                text = "Total Amount",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Place Order Button
            Button(
                onClick = { /* Handle Place Order */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Place Order")
            }
        }
    }
}




