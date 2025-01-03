package com.example.feature_eat.eat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterEatScreen(navController: NavController) {
    val priceRange = remember { mutableStateOf(1f..2f) }
    val selectedCategories = remember {
        mutableStateOf(
            setOf("Burger", "Spicy")
        )
    }
    val categories = listOf(
        "African", "American", "Burger", "Breakfast",
        "French", "Fries", "Indian", "Italian",
        "Mexican", "Spicy", "Traditional"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filters") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(EatScreens.HomeEatApp.route) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { }) {
                        Text("Clear", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Sort by Dropdown
                Text("Sort by", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                var sortOption by remember { mutableStateOf("Recommend") }
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = { /* Handle dropdown */ }
                ) {
                    OutlinedTextField(
                        value = sortOption,
                        onValueChange = { sortOption = it },
                        readOnly = true,
                        label = { Text("Recommend") },
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Price Range
                Text("Price", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                RangeSlider(
                    value = priceRange.value,
                    onValueChange = { priceRange.value = it },
                    valueRange = 1f..3f,
                    steps = 2
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Categories
                Text("Categories", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                categories.chunked(2).forEach { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        row.forEach { category ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Checkbox(
                                    checked = selectedCategories.value.contains(category),
                                    onCheckedChange = {
                                        val current = selectedCategories.value.toMutableSet()
                                        if (it) {
                                            current.add(category)
                                        } else {
                                            current.remove(category)
                                        }
                                        selectedCategories.value = current
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(category, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Apply Button
                Button(
                    onClick = { navController.navigate(EatScreens.HomeEatApp.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                    shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
                ) {
                    Text("Apply")
                }
            }
        }
    )
}

@Preview
@Composable
fun FilterEatScreenPreview() {
    FilterEatScreen(navController = rememberNavController())
}