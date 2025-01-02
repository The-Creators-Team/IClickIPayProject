package com.example.feature_babysitter.babysitter.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_babysitter.babysitter.data.BabySitterViewModel
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen


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
                navController.navigate(
                    BabySitterScreen.SearchScreen.withArgs(
                        selectedOption,
                        sliderValue.toString(),
                        selectedRating.toString()
                    )
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Apply")
        }
    }
}