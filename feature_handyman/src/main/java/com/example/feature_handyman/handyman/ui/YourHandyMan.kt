@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.feature_handyman.handyman.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_handyman.handyman.nami.HandyManNamiScreen

@Composable
fun YourHandyMan(navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Your HandyMan") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    Toast.makeText(context, "Button Clicked!", Toast.LENGTH_LONG).show()
                    navController.navigate(HandyManNamiScreen.HandyManMap.route)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Apply")
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .padding(bottom = 25.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Aligns content horizontally
            ) {
                // Exposed Dropdown Menu
                var expandedNeed by remember { mutableStateOf(false) }
                var selectedNeedOption by remember { mutableStateOf("Sort by") }
                var expandedProblem by remember { mutableStateOf(false) }
                var selectedProblemOption by remember { mutableStateOf("Problem") }
                val options = listOf("Price", "Popularity", "Rating")


                ExposedDropdownMenuBox(
                    expanded = expandedNeed,
                    onExpandedChange = { expandedNeed = !expandedNeed }
                ) {
                    OutlinedTextField(
                        value = selectedNeedOption,
                        onValueChange = { },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedNeed) },
                        label = { Text(text = "Need") }
                    )
                    DropdownMenu(
                        expanded = expandedNeed,
                        onDismissRequest = { expandedNeed = false }
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedNeedOption = option
                                    expandedNeed = false
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = expandedProblem,
                    onExpandedChange = { expandedProblem = !expandedProblem }
                ) {
                    OutlinedTextField(
                        value = selectedProblemOption,
                        onValueChange = { },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedProblem) },
                        label = { Text(text = "Problem") }
                    )
                    DropdownMenu(
                        expanded = expandedProblem,
                        onDismissRequest = { expandedProblem = false }
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedProblemOption = option
                                    expandedProblem = false
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                }

                // Price/kg Slider
                DividerSection(label = "Availability")

            }
        }
    )
}

@Composable
fun DividerSection(label: String, content: @Composable (() -> Unit)? = null) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(end = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                thickness = 2.dp
            )
        }
        content?.invoke() ?: MySlider(title = "")
    }
}

@Composable
fun MySlider(title: String) {
    var sliderValue by remember { mutableStateOf(0f) }
    Column {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Value: ${sliderValue.toInt()}")
    }
}

@Preview
@Composable
fun YourHandyManPreview() {
    YourHandyMan(navController = NavController(LocalContext.current))
}