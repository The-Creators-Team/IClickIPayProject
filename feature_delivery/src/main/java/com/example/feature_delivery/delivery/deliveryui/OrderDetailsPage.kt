@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature_delivery.delivery.deliveryui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_delivery.R

@Composable
fun OrderDetailsPage() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Filters") },
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
                onClick = { /*TODO*/ },
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Delivery-man Section
                DeliveryManSection()
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Gray
                )
                // Date Section
                DateSection()
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Gray
                )
                // Delivery Details Section
                DeliveryDetailsSection()
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Gray
                )
                // Order Summary Section
                OrderSummarySection()
            }
        }
    )
}

@Composable
fun DeliveryManSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ken_dandadan), // Replace with your drawable resource
            contentDescription = "Delivery Person",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 16.dp)
        )
        Column {
            Text(text = "John Jones", style = MaterialTheme.typography.titleMedium)
            Text(text = "Delivery Person", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Composable
fun DateSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "20 March, Thu - 14h", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Pickup", tint = Color.Gray)
            Text("28 Broad Street\n" +
                    "Johannesburg")
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Drop-off", tint = Color.Gray)
            Text("28 Joe Avenue")
            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Drop-off", tint = Color.Gray)
        }
    }
}

@Composable
fun DeliveryDetailsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Delivery Details", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        DeliveryItemRow(label = "Weight", details = "$15/kg x5", onRemove = { /* Handle Remove Weight */ })
        DeliveryItemRow(label = "Dimensions", details = "$10 x1", onRemove = { /* Handle Remove Dimensions */ })
    }
}

@Composable
fun DeliveryItemRow(label: String, details: String, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$label: $details", style = MaterialTheme.typography.bodyLarge)
        TextButton(onClick = onRemove) {
            Text(text = "Remove", color = Color.Red)
        }

    }
}

@Composable
fun OrderSummarySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(text = "Order Summary", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        SummaryRow(label = "Subtotal", amount = "$75")
        SummaryRow(label = "Delivery fees", amount = "$10")
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Gray
        )
        SummaryRow(label = "Total", amount = "$85", isTotal = true)
    }
}

@Composable
fun SummaryRow(label: String, amount: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge)
        Text(text = amount, style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailsPagePreview() {
    OrderDetailsPage()
}