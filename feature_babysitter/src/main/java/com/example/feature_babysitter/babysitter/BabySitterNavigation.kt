package com.example.feature_babysitter.babysitter

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_babysitter.R
import kotlinx.coroutines.NonCancellable.children

@Composable
fun BabySitterNavigation(
    onNavigateBack: () -> Unit
) {
    val viewModel = remember { BabySitterViewModel() }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BabySitterScreen.BabySitterMainScreen.route
    ) {
        composable(route = BabySitterScreen.BabySitterMainScreen.route) {
            BabySitterMainScreen(
                navController = navController,
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = BabySitterScreen.YourChildDetails.route) {
            YourChildDetailsScreen(navController = navController,
                onNavigateBack = onNavigateBack)
        }
        composable(route = BabySitterScreen.TakeAPhotoScreen.route) {
            TakeAPhotoScreen(navController = navController)
        }
        composable(route = BabySitterScreen.ChildListScreen.route) {
            ChildListScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = BabySitterScreen.FilterScreen.route) {
            FilterScreen(navController = navController)
        }
        composable(route = BabySitterScreen.SearchScreen.route) {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = BabySitterScreen.MapScreen.route) {
            MapScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = BabySitterScreen.OrderScreen.route) {
            OrderScreen(navController = navController, viewModel.babysitters[0], viewModel.children[0])
        }

    }
}

@Composable
fun BabySitterMainScreen(
    navController: NavController,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(R.drawable.baby_sitter_mother_child),
            contentDescription = "Home Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "Babysitter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "Welcome to babysitty an app to schedule a babysitter for your little one. Write reviews and filter through are list of babysitters to find the perfect match for your child and date ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.YourChildDetails.route)
            }
        ) {
            Text(text = "Lets Go")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back to Home Button
        Button(
            onClick = onNavigateBack

        ) {
            Text(text = "Back to All Apps")
        }
    }
}

@Composable
fun YourChildDetailsScreen(
    navController: NavController,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Your child",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Name Entry
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Gender Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Gender:", modifier = Modifier.padding(end = 8.dp))
            Checkbox(
                checked = isMale,
                onCheckedChange = { isMale = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = if (isMale) "Male" else "Female")
        }

        // Age Entry
        OutlinedTextField(
            value = age,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    val inputAge = it.toIntOrNull() ?: 0
                    if (inputAge in 0..17) age = it // Ensures the age is within the allowed range
                }
            },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Submit Button (Optional)
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.TakeAPhotoScreen.route)
            }
        ) {
            Text(text = "Next")
        }
        Button(
            onClick = onNavigateBack

        ) {
            Text(text = "Back to All Apps")
        }
    }
}

@Composable
fun TakeAPhotoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Row {
            // put icon images if needed
            Text(
                text = "Take a photo",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        // Image placeholder
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder), // Replace with your drawable resource
            contentDescription = "Cam Placeholder",
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray) // Optional: Background for the placeholder
        )

        // Button
        Button(onClick = {
            // need to add cam functionallty
            navController.navigate(BabySitterScreen.ChildListScreen.route)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.cam_icon), // Use your custom drawable
                contentDescription = "Camera Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ChildListScreen(navController: NavController, viewModel: BabySitterViewModel) {
    val children = viewModel.children // Access children from ViewModel

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    navController.navigate(BabySitterScreen.YourChildDetails.route)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus_user),
                    contentDescription = "Child Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))

                // Child Name
                Text(
                    text = "New Child",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(children) { child ->
                ChildCard(child = child)
            }
        }
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.FilterScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ChildCard(child: Child) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image in CircleCrop
            Image(
                painter = painterResource(id = child.imageResId),
                contentDescription = "Child Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Child Name
            Text(
                text = child.name,
                modifier = Modifier.weight(1f)
            )
            // Edit Icon
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Child")
            }
            // Delete Icon
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Child")
            }
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
        ParcelSlider("Price/Hour")
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.SearchScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Apply")
        }
    }
}

@Composable
fun ParcelSlider(title: String) {
    var sliderValue by remember { mutableStateOf(0f) }
    Column {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..60f,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Value: ${sliderValue.toInt()}")
    }
}

@Composable
fun SearchScreen(navController: NavController, viewModel: BabySitterViewModel) {
    var showPopup by remember { mutableStateOf(false) }
    var expandedBabysitter by remember { mutableStateOf<Babysitter?>(null) }
    val babysitters = viewModel.babysitters

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder),
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
            Text(text = "Babysitters")
            Icon(
                modifier = Modifier.clickable { },
                imageVector = Icons.Default.Menu,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Lazy list of babysitters
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(babysitters) { babysitter ->
                BabysitterCard(
                    babysitter = babysitter,
                    onClick = { expandedBabysitter = babysitter }
                )
            }
        }
    }
}

@Composable
fun OrdersPopupMenu(navController: NavController, onDismiss: () -> Unit) {
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
                        navController.navigate(BabySitterScreen.MapScreen.route)
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
fun BabysitterCard(babysitter: Babysitter, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Babysitter Image
            Image(
                painter = painterResource(id = babysitter.imageResId),
                contentDescription = "Babysitter Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = babysitter.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = babysitter.location,
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
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${babysitter.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${babysitter.distance} m")
                }
                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${babysitter.costPerHour}/hr")
                }
            }
        }
    }
}

@Composable
fun MapScreen(navController: NavController, viewModel: BabySitterViewModel) {
    var expandedBabysitter by remember { mutableStateOf<Babysitter?>(null) }
    val babysitters = viewModel.babysitters

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

            // Babysitter Cards LazyRow
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(babysitters) { babysitter ->
                    BabysitterCard(
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

                        // Take Appointment Button
                        Button(
                            onClick = { navController.navigate(BabySitterScreen.OrderScreen.route) },
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

@Composable
fun OrderScreen(navController: NavController, babysitter: Babysitter, child: Child) {
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
                        painter = painterResource(id = babysitter.imageResId),
                        contentDescription = "Babysitter Image",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Babysitter", color = Color.White)
                    Text(text = babysitter.name, color = Color.White)
                }

                // Child Profile
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = child.imageResId),
                        contentDescription = "Child Image",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Child", color = Color.White)
                    Text(text = child.name, color = Color.White)
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
                    text = babysitter.location,
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
            // Babysitter Cost Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Babysitter")
                Text(text = "$${babysitter.costPerHour}/hr")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Subtotal
            Text(text = "Subtotal")

            Spacer(modifier = Modifier.height(8.dp))

            // Delivery Fees
            Text(text = "Delivery Fees")

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total Amount",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Handle Place Order */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Place Order")
            }
        }
    }
}




