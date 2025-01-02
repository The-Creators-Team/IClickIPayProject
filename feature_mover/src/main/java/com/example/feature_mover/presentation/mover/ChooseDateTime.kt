package com.example.feature_mover.presentation.mover

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.feature_mover.presentation.mover.routes.MoverScreenRoutes
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseDateTime(navController: NavController, moverViewModel: MoverViewModel) {
    // var selectedDate by remember { mutableStateOf(6) }
    //var selectedTime by remember { mutableStateOf<String?>(null) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    val selectedDate by moverViewModel.selectedDate.collectAsState()
    val selectedTime by moverViewModel.selectedTime.collectAsState()

    val timeSlots = List(14) { index ->
        val hour = 8 + index / 2 // Starting from 8 AM
        val minutes = if (index % 2 == 0) "00" else "30"
        val period = if (hour < 12) "AM" else "PM"
        val adjustedHour = if (hour > 12) hour - 12 else hour // Convert to 12-hour format

        String.format("%02d:%s %s", adjustedHour, minutes, period)
    }

    // Scroll state for LazyRow
    val scrollState = rememberLazyListState()

    // Scroll to the selected date
    LaunchedEffect(selectedDate) {
        // Only scroll to the selected date if it's changed or the page is loaded
        val selectedDayIndex = selectedDate.dayOfMonth - 1 // Days are 1-indexed
        scrollState.animateScrollToItem(selectedDayIndex)
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
        // Calendar Days
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = scrollState
        ) {
            items((1..currentMonth.lengthOfMonth()).toList()) { day ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                if (day == selectedDate.dayOfMonth) Color(0xFFFF5722)
                                else Color.Transparent
                            )
                            .clickable {
                                moverViewModel.updateSelectedDate(currentMonth.atDay(day))
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.toString(),
                            color = if (day == selectedDate.dayOfMonth) Color.White else Color.Black
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

                            moverViewModel.updateSelectedTime(time)
                            //onTimeSelected(time)

                            navController.navigate(MoverScreenRoutes.OrderScreen.route)

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
                            color = if (selectedTime == time) Color(0xFF3B414B) else Color(
                                0xFF707275
                            )
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
    ChooseDateTime(navController, MoverViewModel())
}

