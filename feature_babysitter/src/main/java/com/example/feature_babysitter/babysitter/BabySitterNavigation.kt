package com.example.feature_babysitter.babysitter

import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_babysitter.R

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
            BabySitterMainScreen(navController = navController, onNavigateBack = onNavigateBack)
        }
        composable(route = BabySitterScreen.YourChildDetails.route) {
            YourChildDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack
            )
        }
        composable(
            route = BabySitterScreen.EditChildDetails.route + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            EditChildDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack,
                index = entry.arguments?.getString("index")
            )
        }
        composable(route = BabySitterScreen.TakeAPhotoScreen.route) {
            TakeAPhotoScreen(navController = navController)
        }
        composable(route = BabySitterScreen.ChildListScreen.route) {
            ChildListScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = BabySitterScreen.FilterScreen.route) {
            FilterScreen(navController = navController, viewModel)
        }
        composable(route = BabySitterScreen.SearchScreen.route + "/{sort}/{max}/{min}",
            arguments = listOf(
                navArgument("sort"){type = NavType.StringType},
                navArgument("max"){type = NavType.StringType},
                navArgument("min"){type = NavType.StringType}
            )
        ) {entry ->
            SearchScreen(
                navController = navController,
                viewModel = viewModel,
                sort = entry.arguments?.getString("sort"),
                max = entry.arguments?.getString("max"),
                min = entry.arguments?.getString("min")
            )
        }
        composable(route = BabySitterScreen.BabyMapScreen.route+ "/{sort}/{max}/{min}",
            arguments = listOf(
                navArgument("sort"){type = NavType.StringType},
                navArgument("max"){type = NavType.StringType},
                navArgument("min"){type = NavType.StringType}
            )
        ) {entry ->
            BabyMapScreen(
                navController = navController,
                viewModel = viewModel,
                sort = entry.arguments?.getString("sort"),
                max = entry.arguments?.getString("max"),
                min = entry.arguments?.getString("min"))
        }
        composable(route = BabySitterScreen.OrderScreen.route + "/{indexBabySitter}/{indexChild}",
                arguments = listOf(
                    navArgument("indexBabySitter"){type = NavType.StringType},
                    navArgument("indexChild"){type = NavType.StringType}
                )
            ) { entry ->
            OrderScreen(
                navController = navController,
                viewModel = viewModel,
                indexBabySitter = entry.arguments?.getString("indexBabySitter"),
                indexChild = entry.arguments?.getString("indexChild")
            )
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
            text = "Babysits makes it easy for parents and babysitters to find one another. With our intuitive platform, you can effortlessly search, connect, and plan babysitting appointments. We help parents - by creating the best search experience. With tons of search options, it is easy to find the perfect babysitter for your family.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.ChildListScreen.route)
            }
        ) {
            Text(text = "Lets Go")
        }
        Spacer(modifier = Modifier.height(16.dp))

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
    viewModel: BabySitterViewModel,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "New Child Info",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 35.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { input ->
                if (input.all { it.isLetter() || it.isWhitespace() }) {
                    name = input
                }
            },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

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

        Button(
            onClick = {
                if (name.isNullOrBlank() || age.isNullOrBlank()) {
                    Toast.makeText(context, "Name or Age cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val child = Child(
                        name = name,
                        gender = if (isMale) "Male" else "Female",
                        age = age.toIntOrNull() ?: 0, // Convert age to Int safely
                        imageResId = R.drawable.cam_placeholder // Use appropriate placeholder
                    )
                    viewModel.addChild(child) // Add child to ViewModel

                    navController.navigate(BabySitterScreen.TakeAPhotoScreen.route)
                }
            }
        ) {
            Text(text = "Next")
        }
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.ChildListScreen.route)
            }
        ) {
            Text(text = "Back")
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
fun EditChildDetailsScreen(
    navController: NavController,
    viewModel: BabySitterViewModel,
    onNavigateBack: () -> Unit,
    index: String?
) {
    var name by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }
    val context = LocalContext.current

    var child = viewModel.children[index?.toInt()!!]
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Edit Child Info $index",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 35.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { input ->
                if (input.all { it.isLetter() || it.isWhitespace() }) {
                    name = input
                }
            },
            label = { Text(child.name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
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
                    if (inputAge in 0..17) age = it
                }
            },
            label = { Text(child.age.toString()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (name.isNullOrBlank() || age.isNullOrBlank()) {
                    Toast.makeText(context, "Name or Age cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    child = Child(
                        name = name,
                        gender = if (isMale) "Male" else "Female",
                        age = age.toIntOrNull() ?: 0,
                        imageResId = child.imageResId
                    )
                    viewModel.updateChild(index.toInt(), child) // Add child to ViewModel

                    navController.navigate(BabySitterScreen.TakeAPhotoScreen.route)
                }
            }
        ) {
            Text(text = "Update")
        }
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.ChildListScreen.route)
            }
        ) {
            Text(text = "Back")
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
fun ChildListScreen(
    navController: NavController,
    viewModel: BabySitterViewModel,
    onNavigateBack: () -> Unit
) {
    val children = viewModel.children

    Column(modifier = Modifier.padding(top = 40.dp)) {
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
                ChildCard(child = child, navController, children.indexOf(child), viewModel)
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
        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(text = "Back to All Apps")
        }
    }
}

@Composable
fun ChildCard(
    child: Child,
    navController: NavController,
    index: Int,
    viewModel: BabySitterViewModel
) {
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
                    .clip(CircleShape),
                contentScale = ContentScale.Crop // Ensures the image fills and crops to the circle
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column (modifier = Modifier.weight(1f)){
                Text(
                    text = "${child.name}, Age ${child.age}",
                )
                Text(
                    text = child.gender,
                )
            }

            IconButton(onClick = {
                navController.navigate(BabySitterScreen.EditChildDetails.withArgs(index.toString()))
            }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Child")
            }
            // Delete Icon
            IconButton(onClick = {
                viewModel.removeChild(viewModel.children.get(index))
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Child")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController, viewModel: BabySitterViewModel) {
    var selectedOption by remember { mutableStateOf("Recommend") }
    var expanded by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(0.0) } // Track selected rating

    val sortingOptions = listOf("Recommend", "Distance", "Cost") // Sorting options

    Column(modifier = Modifier.padding(40.dp)) {
        // Title
        Text(text = "Filter Options")

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown menu
        Text("Sort By")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Sorting Option") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // Ensures the dropdown aligns correctly
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                sortingOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option // Update selected option
                            expanded = false       // Close the dropdown
                        }
                    )
                }
            }
        }
        Text(text = "Max: Price/Hour")
        Spacer(modifier = Modifier.height(16.dp))

        var sliderValue by remember { mutableStateOf(60f) }
        Column {
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..60f,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Set: $${sliderValue.toInt()}")
        }

        Text(text = "Min: Rating")

        // Star Rating Selector
        StarRatingSelector(
            initialRating = selectedRating,
            onRatingSelected = { rating ->
                selectedRating = rating
            }
        )

        Button(
            onClick = {
                navController.navigate(BabySitterScreen.SearchScreen.withArgs(selectedOption,sliderValue.toString(),selectedRating.toString() ))
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
fun StarRatingSelector(
    initialRating: Double = 0.0,
    onRatingSelected: (Double) -> Unit
) {
    var selectedRating by remember { mutableStateOf(initialRating) }
    val totalStars = 5

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..totalStars) {
            val isHalfFilled = selectedRating >= i - 0.5 && selectedRating < i
            val isFullFilled = selectedRating >= i

            Icon(
                painter = painterResource(
                    if (isFullFilled) R.drawable.ic_star_filled
                    else if (isHalfFilled) R.drawable.ic_star_half
                    else R.drawable.ic_star_outline
                ),
                contentDescription = "Star $i",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        selectedRating = if (selectedRating == i.toDouble()) {
                            i - 0.5
                        } else {
                            i.toDouble()
                        }
                        onRatingSelected(selectedRating)
                    },
                tint = Color.Yellow
            )
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
        Text(text = "Set: $${sliderValue.toInt()}")
    }
}

@Composable
fun SearchScreen(navController: NavController, viewModel: BabySitterViewModel, sort:String?, max:String?, min:String?) {
    var showPopup by remember { mutableStateOf(false) }
    var expandedBabysitter by remember { mutableStateOf<Babysitter?>(null) }
    val children = viewModel.children
    val randNumber = (0..children.size-1).random()
    val child = children[randNumber]

    var babysitters = viewModel.babysitters

    if (sort.equals("Recommend")){
        babysitters = viewModel.recommendSortBabysitter
    } else if (sort.equals("Distance")){
        babysitters = viewModel.distanceSortBabysitter
    }else if(sort.equals("Cost")){
        babysitters = viewModel.priceSortBabysitter
    }
    babysitters = babysitters.filter { (it.costPerHour < max!!.toDouble() && it.rating > min!!.toDouble())}

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
//                        showPopup = true

                        navController.navigate(BabySitterScreen.BabyMapScreen.withArgs(sort.toString(), max.toString(), min.toString()))
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
//                        navController.navigate(BabySitterScreen.BabyMapScreen.route)
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
    var isHeartFilled by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Babysitter Image
            Box(modifier = Modifier.height(150.dp)) {
                Image(
                    painter = painterResource(id = babysitter.imageResId),
                    contentDescription = "Babysitter Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { isHeartFilled = !isHeartFilled },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isHeartFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isHeartFilled) Color.Red else Color.Gray
                    )
                }
            }

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
fun BabyMapScreen(navController: NavController, viewModel: BabySitterViewModel , sort:String?, max:String?, min:String?) {
    var expandedBabysitter by remember { mutableStateOf<Babysitter?>(null) }

    var babysitters = viewModel.babysitters
    if (sort.equals("Recommend")){
        babysitters = viewModel.recommendSortBabysitter
    } else if (sort.equals("Distance")){
        babysitters = viewModel.distanceSortBabysitter
    }else if(sort.equals("Cost")){
        babysitters = viewModel.priceSortBabysitter
    }
    babysitters = babysitters.filter { (it.costPerHour < max!!.toDouble() && it.rating > min!!.toDouble())}


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
                        navController.navigate(BabySitterScreen.SearchScreen.withArgs(sort.toString(),max.toString(),min.toString() ))
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp)
            ) {
                Text(text = "Back to List View")
            }

            Spacer(modifier = Modifier.weight(1f)) // Spacer to push the LazyRow to the bottom

            Text(text = "Babysitters: ${babysitters.size} results")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(babysitters) { babysitter ->
                    BabysitterCardMap(
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

                        val unfilterBabysitter = viewModel.babysitters
                        Button(
                            onClick = { navController.navigate(BabySitterScreen.OrderScreen.withArgs(unfilterBabysitter.indexOf(babysitter).toString(), "0")) },
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
fun BabysitterCardMap(babysitter: Babysitter, onClick: () -> Unit) {
    var isHeartFilled by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .size(width = 200.dp, height = 300.dp)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Babysitter Image
            Box(modifier = Modifier.height(150.dp)) {
                Image(
                    painter = painterResource(id = babysitter.imageResId),
                    contentDescription = "Babysitter Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { isHeartFilled = !isHeartFilled },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isHeartFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isHeartFilled) Color.Red else Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = babysitter.name,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Text(
                text = babysitter.location,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
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
fun OrderScreen(navController: NavController , viewModel: BabySitterViewModel, indexBabySitter:String?, indexChild:String?) {
    val babysitter = viewModel.babysitters[indexBabySitter!!.toInt()]
    val child = viewModel.children[indexChild!!.toInt()]


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                onClick = {
                    navController.navigate(BabySitterScreen.BabySitterMainScreen.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Place Order")
            }
        }
    }
}




