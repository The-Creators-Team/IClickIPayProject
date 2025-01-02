package com.example.feature_pcrepair.pcrepair

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcRepairProblemScreen(
    navController: NavController,
    viewModel: PcRepairViewModel
) {

    var selectedType by remember { mutableStateOf("Laptop") }
    var selectedProblem by remember { mutableStateOf("Do not work") }
    var selectedHour by remember { mutableStateOf(14) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Your pc repair") },
                //modifier = Modifier.height(42.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(PcRepairScreens.PcRepairHomeScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        containerColor = Color.White
    ) { paddingValues -> // Use the paddingValues from the Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply the Scaffold's padding
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                DropdownField(label = "Type", selectedOption = selectedType, options = listOf("Laptop", "Desktop", "Tablet")) {
                    selectedType = it
                    viewModel.selectedType.value = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                DropdownField(label = "Problem", selectedOption = selectedProblem, options = listOf("Do not work", "Slow performance", "Overheating")) {
                    selectedProblem = it
                    viewModel.selectedProblem.value = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Availability", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                TimePicker(selectedHour = selectedHour) {
                    selectedHour = it
                    viewModel.selectedAvailability.value = it.toString()
                }
            }
            Button(
                onClick = { navController.navigate(PcRepairScreens.PcRepairSearchListScreen.route)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                shape = RoundedCornerShape(8.dp),

            ) {
                Text(text = "Next", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun DropdownField(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(4.dp))
                .clickable { expanded = true }
                .padding(8.dp)
        ) {
            Text(text = selectedOption)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TimePicker(selectedHour: Int, onHourSelected: (Int) -> Unit) {
    val hours = listOf(8, 11, 14, 17, 20)
    var sliderPosition by remember { mutableFloatStateOf(14f) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        hours.forEach { hour ->
            Text(
                text = "${hour}h",
                color = if (hour == selectedHour) Color(0xFFFF9800) else Color.Black,
                modifier = Modifier.clickable {
                    onHourSelected(hour)
                    sliderPosition = hour.toFloat()
                }
            )
        }

    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                onHourSelected(sliderPosition.toInt())
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 8f..20f
        )
    }
}

data class PcRepair(var type: String, var problem: String, var availability: Int) {
}