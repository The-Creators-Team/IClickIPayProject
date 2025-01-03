package com.example.feature_delivery.delivery.deliveryui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_delivery.R
import com.example.feature_delivery.delivery.DeliveryData

val deliveryData = listOf<DeliveryData>(
    DeliveryData("Goku", "Earth", R.drawable.cam_placeholder, 3.0, 500, 15),
    DeliveryData("Gohan", "Earth",  R.drawable.cam_placeholder, 3.0, 500, 15),
    DeliveryData("Goten", "Earth",  R.drawable.cam_placeholder, 3.0, 500, 15),
    )

@Composable
fun DeliverySearchScreen(navController: NavController) {
    var showPopup by remember { mutableStateOf(false) }
    var expandedDeliveryData by remember { mutableStateOf<DeliveryData?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Bar with two options: Favorites and Orders
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Favorites Row
            Row(
                modifier = Modifier
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favorites", color = Color.White)
            }

            // Orders Row
            Row(
                modifier = Modifier
                    .clickable { showPopup = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Orders",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Orders", color = Color.White)
            }
        }

        // Show Popup Menu
//        if (showPopup) {
//            OrdersPopupMenu(
//                navController, onDismiss = { showPopup = false },
//                viewModel =
//                babysitter =
//            )
//        }

        // Bar with title "Babysitters" and filter icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Babysitters")
            Icon(
                modifier = Modifier.clickable { },
                imageVector = Icons.Default.Menu,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        LazyColumn(modifier = Modifier.weight(2f)) {
            items(deliveryData) { deliveryData ->
                DeliveryCard(
                    deliveryData = deliveryData,
                    onClick = {  expandedDeliveryData  = deliveryData }
                )
            }
        }
    }
}

@Preview
@Composable
fun DeliverySearchScreenPreview() {
    DeliverySearchScreen(navController = NavController(LocalContext.current))

}