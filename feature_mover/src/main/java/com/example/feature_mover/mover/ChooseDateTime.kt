package com.example.feature_mover.mover

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseDateTime(navController: NavController) {
    var selectedDate by remember { mutableStateOf(6) }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    val timeSlots = List(7) { index ->
        val hour = 1 + index / 2
        val minutes = if (index % 2 == 0) "00" else "30"
        String.format("%02d:%s PM", hour, minutes)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        TopAppBar(title = { Text("Choose date") }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
        )

        // Month Navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                currentMonth = currentMonth.minusMonths(1)
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, "Previous month")
            }
            Text(
                text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM")),
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(onClick = {
                currentMonth = currentMonth.plusMonths(1)
            }) {
                Icon(Icons.Default.KeyboardArrowRight, "Next month")
            }
        }

        // Week Days
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        // Calendar Days
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            (4..10).forEach { day ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                if (day == selectedDate) Color(0xFFFF5722)
                                else Color.Transparent
                            )
                            .clickable {
                                selectedDate = day
                                //onDateSelected(day.toString())
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.toString(),
                            color = if (day == selectedDate) Color.White else Color.Black
                        )
                    }
                }
            }
        }

        Divider(
            modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray
        )

        // Time Slots
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(timeSlots) { time ->
                Column {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedTime = time
                            //onTimeSelected(time)

                            navController.navigate("PlaceOrderScreen")

                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selectedTime == time) Color(0xFFFF5722)
                                    else Color.LightGray
                                )
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = time,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (selectedTime == time) Color(0xFF3B414B) else Color(0xFF707275)
                        )
                    }
                    Divider(color = Color.LightGray)
                }
            }
        }
    }
}






@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ChooseDateTimePreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    ChooseDateTime(navController)
}