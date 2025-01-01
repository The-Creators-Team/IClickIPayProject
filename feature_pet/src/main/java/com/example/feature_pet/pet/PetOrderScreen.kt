package com.example.feature_pet.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun PetOrderScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order") },
                actions = {
                    TextButton(onClick = { /* Cancel action */ }) {
                        Text("Cancel", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button",
                            tint = Color.White
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
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 12.dp)
                ) {
                    // Handyman Information
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            modifier = Modifier.size(50.dp),
                            shape = RoundedCornerShape(25.dp),
                            color = Color.Gray // Placeholder for image
                        ) {}

                        Column {
                            Text("Guard", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                "Jenny Jones",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Surface(
                            modifier = Modifier.size(50.dp),
                            shape = RoundedCornerShape(25.dp),
                            color = Color.Gray // Placeholder for image
                        ) {}

                        Column {
                            Text("Dog", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                "Sprinkles",
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
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Column {
                            // Date and Address
                            Text(
                                "Date",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            Text("20 March, Thu - 14h", style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("28 Broad Street", style = MaterialTheme.typography.bodyLarge)
                            Text("Johannesburg", style = MaterialTheme.typography.bodyLarge)
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

                        Text("Care", style = MaterialTheme.typography.bodyLarge)
                        Text("$15/h", style = MaterialTheme.typography.bodyLarge)


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = { /* Remove action */ }) {
                            Text("Remove", color = Color.Red, textAlign = TextAlign.Left)

                        }
                        Text("x3", style = MaterialTheme.typography.bodyMedium)
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                    // Subtotal
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal", style = MaterialTheme.typography.bodyLarge)
                        Text("$45.00", style = MaterialTheme.typography.bodyLarge)
                    }

                    // Delivery Fees
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Delivery fees", style = MaterialTheme.typography.bodyLarge)
                        Text("$0.00", style = MaterialTheme.typography.bodyLarge)
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
                            "$45.00",
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
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("Place order", color = Color.White, fontSize = 16.sp)
                }
            }

        }
    }
}