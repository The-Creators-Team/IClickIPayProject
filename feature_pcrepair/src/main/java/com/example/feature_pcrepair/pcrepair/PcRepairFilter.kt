package com.example.feature_pcrepair.pcrepair

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen() {
    var selectedSortOption by remember { mutableStateOf("Recommend") }
    var expanded by remember { mutableStateOf(false) }
    var priceRange by remember { mutableStateOf(0f..30f) }
    var selectedRating by remember { mutableStateOf(3) }

    val sortOptions = listOf("Recommend", "Price", "Rating")


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Sort By Dropdown
            Text(text = "Sort by", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedSortOption,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Sort by") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sortOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedSortOption = option
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Price Slider
            Text(text = "Price/hour", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Price: ${priceRange.start.toInt()} - ${priceRange.endInclusive.toInt()}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Slider(
                value = priceRange.endInclusive,
                valueRange = 0f..60f,
                onValueChange = {
                    priceRange = priceRange.start..it
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Price: ${0} - ${60}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Rating Selector
            Text(text = "Rate", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                for (i in 1..5) {
                    IconButton(onClick = { selectedRating = i }) {
                        Icon(
                            imageVector = if (i <= selectedRating) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = null,
                            tint = if (i <= selectedRating) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Apply Button
            Button(
                onClick = { /* Handle apply filters */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Apply")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewFilterScreen() {
    MaterialTheme {
        FilterScreen()
    }
}