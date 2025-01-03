package com.example.feature_mover.presentation.mover.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoverFilterScreen(
    navController: NavController,
    viewModel: MoverViewModel
) {

    var expanded by remember { mutableStateOf(false) }
    // Observing ViewModel states
    val selectedSortOption by viewModel.selectedSortOption.collectAsState()
    val priceRange by viewModel.priceRange.collectAsState()
    val selectedRating by viewModel.selectedRating.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Custom TopAppBar
        TopAppBar(title = {
            Text(
                text = "Filters",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFFFF6B00)
                    )
                }
            },
            actions =
            {
                TextButton(onClick = {
                    viewModel.updateSelectedSortOption("Recommend")
                    viewModel.updatePriceRange(30f)
                    viewModel.updateSelectedRating(1)
                }) {
                    Text(
                        text = "Clear",
                        color = Color(0xFFFF6B00),
                        fontWeight = FontWeight.Normal
                    )
                }
            })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Sort By Section
            Text(
                text = "Sort by",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedSortOption,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = {
                        Icon(Icons.Filled.KeyboardArrowDown, "Dropdown")
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFFFF6B00)
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Recommend", "Price", "Rating").forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                viewModel.updateSelectedSortOption(option)
                                expanded = false

                            }
                        )
                    }
                }
            }

            // Price Section
            Text(
                text = "Price/m³",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                Slider(
                    value = priceRange,
                    onValueChange = { viewModel.updatePriceRange(it) },
                    valueRange = 0f..60f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFFFF6B00),
                        activeTrackColor = Color(0xFFFF6B00),
                        inactiveTrackColor = Color.LightGray
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "0",
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                        text = priceRange.toInt().toString(),
                        color = Color(0xFFFF6B00),
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                        text = "60",
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                }
            }

            // Rating Selector
            Text(text = "Rate", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                for (i in 1..5) {
                    IconButton(onClick = {

                        viewModel.updateSelectedRating(i)

                    }) {
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
                onClick = {
                    viewModel.applyFilters()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B00)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Apply",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

