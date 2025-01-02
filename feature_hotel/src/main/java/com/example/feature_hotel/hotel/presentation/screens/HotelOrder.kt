package com.example.feature_hotel.hotel.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.feature_hotel.hotel.presentation.navigation.HotelScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelOrder(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order") },
                actions = {
                    TextButton(onClick = { navController.navigate(HotelScreen.SingleScreen.route) }) {
                        Text("Cancel", color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(HotelScreen.OrderScreen.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    // Handyman Information
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column {
                            Text("Hotel", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                "Ressort Hotel",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            // Date and Address
                            Text(
                                "Dates",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            Text("20 March, Thu - 22 March, Sat", style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Junior suite", style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                    }

                }

                Column {
                    // Order Details
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text("Standard", style = MaterialTheme.typography.bodyLarge)
                        Text("$150", style = MaterialTheme.typography.bodyLarge)


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = { /* Remove action */ }) {
                            Text("Remove", textAlign = TextAlign.Left)

                        }
                        Text("x2", style = MaterialTheme.typography.bodyMedium)
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                    // Subtotal
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal", style = MaterialTheme.typography.bodyLarge)
                        Text("$300.00", style = MaterialTheme.typography.bodyLarge)
                    }

                    // Delivery Fees
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Delivery fees", style = MaterialTheme.typography.bodyLarge)
                        Text("$2.50", style = MaterialTheme.typography.bodyLarge)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                    // Total Amount
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Total amount",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "$302.50",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                }

            }


// Place Order Button

            Row(modifier = Modifier.padding(vertical = 12.dp,horizontal = 16.dp)) {
                Button(
                    onClick = { /* Place order action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("Place order", color = Color.White, fontSize = 16.sp)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HotelOrderPreview() {
    HotelOrder(navController = NavController(LocalContext.current))
}
