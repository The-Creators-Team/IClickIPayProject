package com.example.iclickipay.presentation.babysitter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iclickipay.R


@Composable
fun BabySitterNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BabySitterScreen.BabySitterMainScreen.route
    ) {

        composable(route = BabySitterScreen.BabySitterMainScreen.route) {
            BabySitterMainScreen(navController = navController)
        }
        composable(route = BabySitterScreen.YourChildDetails.route) {
            YourChildDetailsScreen(navController = navController)
        }
        composable(route = BabySitterScreen.TakeAPhotoScreen.route) {
            TakeAPhotoScreen(navController = navController)
        }
        composable(route = BabySitterScreen.ChildListScreen.route) {
            ChildListScreen(navController = navController, children)
        }
//        composable() {
//            FilterScreen(navController = navController)
//        }
    }
}

@Composable
fun BabySitterMainScreen(navController: NavController) {
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
            text = "title",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "description",
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
    }
}

@Composable
fun YourChildDetailsScreen(navController: NavController) {
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

data class Child(
    val name: String,
    var age:Int,
    val imageResId: Int // Drawable resource ID for the image
)

val children = listOf<Child>(
    Child("Chris", 22, R.drawable.cam_placeholder),
    Child("Rebecca", 22, R.drawable.cam_placeholder),
    Child("Jill", 22, R.drawable.cam_placeholder),
    Child("Leon", 22, R.drawable.cam_placeholder),
)

@Composable
fun ChildListScreen(navController: NavController, children: List<Child>, ) {

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
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(children) { child ->
                ChildCard(child = child)
            }
        }
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.FilterScreen.route)
            }
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
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Child")
            }
            // Delete Icon
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Child")
            }
        }
    }
}