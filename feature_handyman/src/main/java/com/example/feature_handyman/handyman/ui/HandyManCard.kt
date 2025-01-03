package com.example.feature_handyman.handyman.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feature_handyman.handyman.data.HandyData


@Composable
fun HandyManCard(
    handyData: HandyData,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(180.dp)
            .width(115.dp)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Handyman Image
            Image(
                painter = painterResource(id = handyData.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f), // Adjust the height proportionally
                contentScale = ContentScale.Crop // Change to ContentScale.Fit if you want to maintain aspect ratio
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Handyman Details
            Text(text = handyData.name, modifier = Modifier.padding(horizontal = 8.dp))
            Text(text = handyData.location, modifier = Modifier.padding(horizontal = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${handyData.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${handyData.distance} m")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Cost
            Text(text = "Cost: $${handyData.price}/hr")
        }
    }
}