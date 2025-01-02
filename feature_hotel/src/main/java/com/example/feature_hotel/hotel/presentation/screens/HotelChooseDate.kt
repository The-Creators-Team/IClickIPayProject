package com.example.feature_hotel.hotel.presentation.screens

import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.feature_hotel.R
import com.example.feature_hotel.hotel.presentation.navigation.HotelScreen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChooseDatesScreen(navController: NavController) {
    var checkInDate by remember { mutableStateOf<String?>(null) }
    var checkOutDate by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }


    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
            IconButton(onClick = { navController.navigate(HotelScreen.SearchScreen.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.hotel_room),
            contentDescription = "Hotel Image",
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            text = "Choose dates",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = checkInDate ?: "Select Check-in Date",
                onValueChange = {},
                label = { Text("Check in") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                trailingIcon = {

                    Icon(Icons.Default.CalendarToday, contentDescription = "Select Check-in Date")
                }
            )


            OutlinedTextField(
                value = checkOutDate ?: "Select Check-out Date",
                onValueChange = {},
                label = { Text("Check out") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                trailingIcon = {

                    Icon(Icons.Default.CalendarToday, contentDescription = "Select Check-out Date")
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        AndroidView(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        selectedDate.set(year, month, dayOfMonth)
                        val formattedDate = dateFormat.format(selectedDate.time)

                        if (checkInDate == null) {
                            checkInDate = formattedDate
                        } else {
                            checkOutDate = formattedDate
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {

                if (checkInDate != null && checkOutDate != null) {

                    println("Selected Check-in: $checkInDate")
                    println("Selected Check-out: $checkOutDate")
                } else {

                    println("Please select both check-in and check-out dates")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseDatesScreenPreview() {
    ChooseDatesScreen(navController = NavController(LocalContext.current))
}

