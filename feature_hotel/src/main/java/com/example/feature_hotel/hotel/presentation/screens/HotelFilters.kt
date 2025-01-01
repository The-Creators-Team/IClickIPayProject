package com.example.feature_hotel.hotel.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp

@Composable
fun FiltersScreen() {
    var priceRange by remember { mutableStateOf(300f) }
    var rating by remember { mutableStateOf(3) }
    var selectedEquipments by remember { mutableStateOf(setOf<String>()) }

    val equipments = listOf("Restaurant", "Golf", "Tennis", "Pool", "Bar", "Handy", "Wifi", "Spa", "Parking")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row{
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier =  Modifier.width(width = 110.dp))
            Text("Filters", style = MaterialTheme.typography.headlineLarge, fontSize = 30.sp)
            Spacer(modifier =  Modifier.width(width = 110.dp))
            Text("Clear", style = MaterialTheme.typography.headlineLarge.copy(color = Color.Red), fontSize = 14.sp)
        }
      


        Spacer(modifier = Modifier.height(16.dp))


        Text("Price: ${priceRange.toInt()}", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
        Slider(
            value = priceRange,
            onValueChange = { priceRange = it },
            valueRange = 0f..600f,
            steps = 600,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text("Rate", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 1..5) {
                IconButton(
                    onClick = { rating = i },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = "Star",
                        tint = Color.Red
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text("Equipments", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(equipments) { equipment ->
                FilterCheckbox(
                    label = equipment,
                    isChecked = selectedEquipments.contains(equipment),
                    onCheckedChange = { isChecked ->
                        selectedEquipments = if (isChecked) {
                            selectedEquipments + equipment
                        } else {
                            selectedEquipments - equipment
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {
                println("Selected Price: \$${priceRange.toInt()}")
                println("Selected Rating: $rating")
                println("Selected Equipments: $selectedEquipments")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply")
        }
    }
}

@Composable
fun FilterCheckbox(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(label, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun FiltersScreenPreview() {
    FiltersScreen()
}
