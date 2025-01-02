package com.example.feature_laundry

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LaundryProblemsScreen(
    navController: NavController,
    viewModel: LaundryViewModel) {
    val laundryWeight = remember { mutableStateOf(5f) }
    val dryCleaning = remember { mutableStateOf("2") }
    val ironing = remember { mutableStateOf(true) }
    val availability = remember { mutableStateOf(14f) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Your laundry") })
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate(LaundryScreens.LaundrySearchListScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Next")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Laundry slider
            Text("Laundry/kg")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("0 kg")
                Text("10 kg")
            }
            Slider(
                value = laundryWeight.value,
                onValueChange = {
                    laundryWeight.value = it
                    viewModel.selectedType.value = it.toInt().toString()
                                },
                valueRange = 0f..10f,
                steps = 9
            )
            Text("${laundryWeight.value.toInt()} kg", modifier = Modifier.align(Alignment.CenterHorizontally))

            // Dry cleaning dropdown
            Text("Dry cleaning")
            var expanded by remember { mutableStateOf(false) }
            Box {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(dryCleaning.value)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    (1..5).forEach { count ->
                        DropdownMenuItem(
                            text = { Text("$count") },
                            onClick = {
                                dryCleaning.value = count.toString()
                                viewModel.selectedProblem.value = count.toString()
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Ironing checkbox
            Text("Ironing")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = ironing.value,
                        onCheckedChange = {
                            ironing.value = true
                            viewModel.requiresIroning.value = true
                        }
                    )
                    Text("Yes")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = !ironing.value,
                        onCheckedChange = {
                            ironing.value = false
                            viewModel.requiresIroning.value = false
                        }
                    )
                    Text("No")
                }
            }

            // Availability slider
            Text("Availability")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("8h")
                Text("20h")
            }
            Slider(
                value = availability.value,
                onValueChange = {
                    availability.value = it
                    viewModel.selectedAvailability.value = it.toString()
                                },
                valueRange = 8f..20f,
                steps = 3
            )
            Text("${availability.value.toInt()}h", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}