package com.example.feature_housecleaning.housecleaning.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_housecleaning.housecleaning.data.House
import com.example.feature_housecleaning.housecleaning.data.HouseCleaningViewModel
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourHouseScreen(
    navController: NavController,
    viewModel: HouseCleaningViewModel,
    onNavigateBack: () -> Unit
) {
    var bathrooms by remember { mutableStateOf("") }
    var rooms by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "New House Info",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 35.dp)
        )
        var sliderValueArea by remember { mutableStateOf(500f) }
        Column {
            Slider(
                value = sliderValueArea,
                onValueChange = { sliderValueArea = it },
                valueRange = 0f..500f,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Area/m2: ${sliderValueArea.toInt()}")
        }

        OutlinedTextField(
            value = rooms,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    val inputAge = it.toIntOrNull() ?: 0
                    if (inputAge in 0..17) rooms = it // Ensures the age is within the allowed range
                }
            },
            label = { Text("Rooms") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = bathrooms,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    val inputAge = it.toIntOrNull() ?: 0
                    if (inputAge in 0..17) bathrooms = it // Ensures the age is within the allowed range
                }
            },
            label = { Text("Rooms") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        var selectedOption by remember { mutableStateOf("Recommend") }
        var expanded by remember { mutableStateOf(false) }
        val sortingOptions = listOf("1-3kg (1 person)", "3-6kg (2 person)", "6-9kg (3 person)")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Ironing Option") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
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

        var sliderValueAvailability by remember { mutableStateOf(24f) }
        Column {
            Slider(
                value = sliderValueAvailability,
                onValueChange = { sliderValueAvailability = it },
                valueRange = 0f..24f,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Availability: ${sliderValueAvailability.toInt()} hours")
        }

        Button(
            onClick = {
                if (rooms.isNullOrBlank() || bathrooms.isNullOrBlank()) {
                    Toast.makeText(context, "Rooms or Bathrooms cannot Zero", Toast.LENGTH_SHORT).show()
                } else {
                    val house = House(
                        name = "new house",
                        areaPerMeterSquared = 100,
                        roomCount = rooms.toIntOrNull() ?: 0,
                        bathroomCount = bathrooms.toIntOrNull()?:0,
                        ironingCount = selectedOption,
                        availability = 12,
                        date="tbd"
                    )
                    viewModel.addHouse(house)

                    navController.navigate(HouseCleaningScreen.HouseCalendarScreen.route)
                }
            }
        ) {
            Text(text = "Choose Date")
        }
        Button(
            onClick = {
                navController.navigate(HouseCleaningScreen.HouseCleaningMainScreen.route)
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
