@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature_handyman.handyman.ui

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.feature_handyman.R
import com.example.iclickipay.presentation.reuseable.CustomButton

@Composable
fun HandyPlaceOrderScreen(navController: NavController, selectedDate: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Orange Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFF7A1A))
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Order",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                TextButton(onClick = { /* Handle cancel */ }) {
                    Text(
                        text = "Cancel",
                        color = Color.White
                    )
                }
            }

            // Mover Info
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.moverlogo),
                    contentDescription = "Mover Image",
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = "Mover",
                        color = Color.White.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Box Entreprise",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            // Date and Location
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Date",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
                // Display the selected date
                Text(
                    text = selectedDate,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "28 Broad Street\nJohannesburg",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        // Order Details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Mover Quote
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Mover quote",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Remove",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    text = "$ 15",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.LightGray
            )

            // Subtotal
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal")
                Text(
                    text = "$ 15.00",
                )
            }

            // Delivery Fees
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Delivery fees")
                Text(
                    text = "$ 0.00",
                    color = Color.Gray
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.LightGray
            )

            // Total Amount
            Column(
                modifier = Modifier.size(317.dp, 51.dp),
            ) {
                Text(
                    text = "Total amount",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "$ 15.00",
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Place Order Button
            CustomButton("Place Order", onClick = {
                Toast.makeText(context, "Order Placed", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HandyPlaceOrderScreenPreview() {
    val navController = rememberNavController()
    HandyPlaceOrderScreen(
        navController,
        selectedDate = "20 March, Thu - 10h" // Example date
    )
}