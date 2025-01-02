package com.example.feature_laundry

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun LaundrySearchList(
                      navController: NavController,
                      viewModel: LaundryViewModel
) {

    MaterialTheme {
        Box {
            Column {
                TopHeader()
                Spacer(modifier = Modifier.height(80.dp))
                SearchSection(navController, viewModel)
                FavoritesAndOrdersRow()
                LaundryProfessionalsList(navController, viewModel)
            }
            FloatingHomeIcon(navController)
        }
    }
}

@Composable
fun LaundryProfessionalsList(navController: NavController, viewModel: LaundryViewModel) {

    var LaundryProfessional = viewModel.laundryProfessionals
    //val filteredTechnicians = viewModel.repairTechnicians
    if (viewModel.isFiltered.value == true){
        LaundryProfessional =LaundryProfessional.filter { it.price < viewModel.selectedPriceRange.value!! && it.rating > viewModel.selectedRating.value!! }
        println("Filtered: ${LaundryProfessional.toString()}")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Laundry Professionals (${LaundryProfessional.size})", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(LaundryProfessional) { laundryProfessional ->
                LaundryProfessionalCard(
                    name = laundryProfessional.name,
                    location = laundryProfessional.location,
                    rating = laundryProfessional.rating,
                    distance = laundryProfessional.distance,
                    price = laundryProfessional.price,
                    description = laundryProfessional.description,
                    image = laundryProfessional.image,
                    onClick = { //save selected PC tech to viewModel
                        viewModel.selectedLaundryProfessional.value = laundryProfessional
                        navController.navigate(LaundryScreens.LaundryAppointmentPickerScreen.route)
                    }
                )
            }
        }
    }
}


@Composable
fun TopHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.laundrybackground),
            contentDescription = "laundry Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FloatingHomeIcon(navController: NavController) {
    Row {
        IconButton(
            onClick = {
                navController.navigate(LaundryScreens.LaundryHomeScreen.route)
            },
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
                .align(Alignment.Top)
        ) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
        }
    }


}

@Composable
fun SearchSection(navController: NavController, viewModel: LaundryViewModel) {


    val df = SimpleDateFormat("dd-MMM", Locale.getDefault())
    val today = df.format(Date())
    viewModel.selectedDate.value = today
    var date by remember { mutableStateOf("${today} - ${viewModel.selectedAvailability.value}h") }
    var type by remember { mutableStateOf(viewModel.selectedType.value) }
    var searchText by remember { mutableStateOf("") }
    var showTypeDropdown by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .offset(y = -80.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Marietta, 1651 Massachusetts Ave", style = MaterialTheme.typography.titleMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Choose Date", style = MaterialTheme.typography.bodySmall)
                    OutlinedButton(
                        onClick = {
                            showDatePicker(context) { selectedDate ->
                                date = selectedDate
                                viewModel.selectedDate.value = selectedDate
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(date)
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "KG", style = MaterialTheme.typography.bodySmall)
                    Box {
                        OutlinedButton(
                            onClick = { showTypeDropdown = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            type?.let { Text(it) }
                        }
                        DropdownMenu(
                            expanded = showTypeDropdown,
                            onDismissRequest = { showTypeDropdown = false }
                        ) {
                            listOf("1", "2", "3","4","5","6","7","8","9","10").forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        type = option
                                        showTypeDropdown = false
                                        viewModel.selectedType.value = option
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Search Location TextField
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search location / name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )

            // Search Button
            Button(
                onClick = { navController.navigate(LaundryScreens.LaundryFilterScreen.route)},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }
        }

    }
}

@Composable
fun FavoritesAndOrdersRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        //horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Favorites")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Orders")
        }

    }
}

@Composable
fun LaundryProfessionalCard(
    name: String,
    location: String,
    rating: Double,
    distance: Int,
    price: Int,
    description: String,
    image: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = image), // Replace with your image resource
                contentDescription = "Pc Technician",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Text(text = location, style = MaterialTheme.typography.bodySmall)
                Text(text = description, style = MaterialTheme.typography.bodySmall)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$rating ★", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "$distance m", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "$$price/h", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            // Format the date
            val selectedDate = Calendar.getInstance().apply {
                set(year, month, day)

            }
            val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
            onDateSelected(formatter.format(selectedDate.time))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
    )
    datePickerDialog.show()
}


