package com.example.feature_hotel.hotel.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsBusFilled
import androidx.compose.material.icons.filled.GolfCourse
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.outlined.FlightTakeoff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.feature_hotel.R
import com.example.feature_hotel.hotel.data.hotelList
import com.example.feature_hotel.hotel.presentation.navigation.HotelScreen

@Composable
fun HotelSingle(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = { navController.navigate(HotelScreen.SearchScreen.route) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Text(
            text = "Ressort Hotel",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.clickable { navController.navigate(HotelScreen.OrderScreen.route) }
        )
        Text(
            text = "Johannesburg",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = hotelList[0].hotelRating)
            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Distance",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = hotelList[0].hotelDistance)
            }
            // Cost
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$${hotelList[0].hotelPrice}")
            }
        }

        Image(
            painter = painterResource(R.drawable.ressort_hotel),
            contentDescription = "Hotel Image",
            modifier = Modifier.padding(top = 50.dp)
        )
        Row{
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.Green
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "28 Orchard Road Johannesburg")
        }
        Text(
            text = "0.7 miles",
            fontSize = 17.sp,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Transport", modifier = Modifier.padding(top = 50.dp))
        Row{
            Icon(
                imageVector = Icons.Outlined.FlightTakeoff,
                contentDescription = "Location",
                tint = Color.Black
            )
            Text(text = "OR Tambo Airport")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Location",
                tint = Color.Gray
            )
            Text(
                text = "15 min by car",
                fontSize = 17.sp,
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
            )
        }
        Row{
            Icon(
                imageVector = Icons.Default.Train,
                contentDescription = "Location",
                tint = Color.Black
            )
            Text(text = "Park Station")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Location",
                tint = Color.Gray
            )
            Text(
                text = "15 min by walk",
                fontSize = 17.sp,
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
            )
        }
        Row{
            Icon(
                imageVector = Icons.Default.DirectionsBusFilled,
                contentDescription = "Location",
                tint = Color.Black
            )
            Text(text = "Park Station")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Location",
                tint = Color.Gray
            )
            Text(
                text = "15 min by walk",
                fontSize = 17.sp,
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Equipments",modifier = Modifier.padding(top = 50.dp))

        val equipmentItems = listOf(
            Pair("Wifi", Icons.Filled.Wifi),
            Pair("Pool", Icons.Filled.Pool),
            Pair("Restaurant", Icons.Filled.Restaurant),
            Pair("Spa", Icons.Filled.Spa),
            Pair("Bar", Icons.Filled.LocalBar),
            Pair("Golf", Icons.Filled.GolfCourse)
        )


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                EquipmentRow("Wifi", Icons.Filled.Wifi)
                EquipmentRow("Pool", Icons.Filled.Pool)
            }
            Column(modifier = Modifier.weight(1f)) {
                EquipmentRow("Restaurant", Icons.Filled.Restaurant)
                EquipmentRow("Spa", Icons.Filled.Spa)
            }
            Column(modifier = Modifier.weight(1f)) {
                EquipmentRow("Bar", Icons.Filled.LocalBar)
                EquipmentRow("Golf", Icons.Filled.GolfCourse)
            }
        }

    }
}

@Composable
fun EquipmentRow(label: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, modifier = Modifier.size(24.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun HotelSinglePreview(){
    MaterialTheme{
        HotelSingle(navController = NavController(LocalContext.current))
    }
}