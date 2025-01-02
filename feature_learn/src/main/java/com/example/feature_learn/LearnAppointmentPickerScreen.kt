package com.example.feature_learn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnAppointmentPickerScreen(
    navigateToLearnOrder: () ->Unit
) {
    var selectedDay by remember { mutableIntStateOf(6) }
    var selectedTime by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jenny Jones") },
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
                text = "January 2025",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(7) { index ->
                    val dayOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
                    val dayNumber = index + 4
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
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(48) { index ->
                        val time =
                            "${0 + (index / 2)}:${if (index % 2 == 0) "00" else "30"} ${if ((index / 2) < 12) " AM" else " PM"} "
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedTime = time }
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
                Button(onClick = navigateToLearnOrder) {
                    Text("Move To Order")
                }
            }
        }
    }
}