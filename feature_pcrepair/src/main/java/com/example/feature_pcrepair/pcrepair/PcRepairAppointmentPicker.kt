package com.example.feature_pcrepair.pcrepair

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcRepairAppointmentPicker(navController: NavController, viewModel: PcRepairViewModel) {
    var selectedDay by remember { mutableStateOf(6) }
    var selectedTime by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { viewModel.selectedRepairTechnician.value?.let { Text(it.name) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Calendar Picker
            Text(
                text = viewModel.selectedDate.value.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(7) { index ->
                    val dayOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
                    val dayNumber = index + 4 // Example for March 2019
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable { selectedDay = dayNumber }
                            .background(
                                if (selectedDay == dayNumber) MaterialTheme.colorScheme.primary else Color.Transparent,
                                CircleShape
                            )
                            .padding(16.dp)
                    ) {
                        Text(text = dayOfWeek[index], fontSize = 14.sp)
                        Text(
                            text = dayNumber.toString(),
                            fontWeight = FontWeight.Bold,
                            color = if (selectedDay == dayNumber) Color.White else Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Time Picker
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(48) { index ->
                    val hour = "${0 + (index / 2)}:${if (index % 2 == 0) "00" else "30"} "
                    val time = "${0 + (index / 2)}:${if (index % 2 == 0) "00" else "30"} ${if ((index /2) < 12)" AM" else " PM"} "
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedTime = time
                                viewModel.selectedTime.value = hour
                                navController.navigate(PcRepairScreens.PcRepairOrderScreen.route)
                            }
                            .background(
                                if (selectedTime == time) MaterialTheme.colorScheme.primary else Color.Transparent,
                                MaterialTheme.shapes.medium
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = time,
                            color = if (selectedTime == time) Color.White else Color.Black
                        )
                    }
                }
            }
        }
    }
}