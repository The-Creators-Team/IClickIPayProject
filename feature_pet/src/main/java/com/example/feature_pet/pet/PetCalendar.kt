package com.example.feature_pet.pet

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_pet.R
import com.example.iclickipay.presentation.reuseable.CustomButton
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetCalendar(
    navigateToPetDepositTime: () -> Unit
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Choose Date",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },

                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_mover),
                            contentDescription = "Back"
                        )
                    }
                },

                )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                    Text(
                        text = "Choose date",
                        style = MaterialTheme.typography.titleLarge
                    )
                    // Empty box for alignment
                    Box(modifier = Modifier.width(48.dp))
                }

                // Selected Date Display
                Text(
                    text = selectedDate.format(DateTimeFormatter.ofPattern("dd MMMM, EEE")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
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

                // Week Days Header
                Row(modifier = Modifier.fillMaxWidth()) {
                    val weekDays = listOf("S", "M", "T", "W", "T", "F", "S")
                    weekDays.forEach { day ->
                        Text(
                            text = day,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Gray
                            )
                        )
                    }
                }

                // Calendar Grid
                val firstDayOfMonth = currentMonth.atDay(1)
                val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
                var dayOfMonth = 1
                val totalDays = currentMonth.lengthOfMonth()

                for (week in 0..5) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (dayOfWeek in 0..6) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .padding(4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if (week == 0 && dayOfWeek < firstDayOfMonth.dayOfWeek.value ||
                                    dayOfMonth > totalDays
                                ) {
                                    // Empty cell
                                } else if (dayOfMonth <= totalDays) {
                                    val date = currentMonth.atDay(dayOfMonth)
                                    val isSelected = date == selectedDate

                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (isSelected) Color(0xFF10C971) else Color.Transparent
                                            )
                                            .clickable { selectedDate = date },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = dayOfMonth.toString(),
                                            color = if (isSelected) Color.White else Color.Black,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                            )
                                        )
                                    }
                                    dayOfMonth++
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    text = "Next",
                    onClick = { navigateToPetDepositTime()})


            }

        }
    )


}