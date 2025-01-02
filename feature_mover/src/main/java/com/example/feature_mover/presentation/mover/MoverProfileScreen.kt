package com.example.feature_mover.presentation.mover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_mover.R
import com.example.feature_mover.presentation.mover.routes.MoverScreenRoutes
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel
import com.example.iclickipay.presentation.reuseable.CustomButton

@Composable
fun MoverProfileScreen(
    navController: NavController,
    moverViewModel: MoverViewModel,
    moverId: String?
) {
    // Fetch the mover by id
    val mover = moverViewModel.fetchMoverById(moverId?.toInt() ?: 0).firstOrNull()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Map Section with Back Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Map would be implemented here using Google Maps Compose
            IconButton(
                onClick = { /* Handle navigation */ },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        // Business Logo and Details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Circular Logo
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (-50).dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.moverlogo),
                    contentDescription = "Business Logo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Business Name
            Text(
                text = mover?.name ?: "",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Rating
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFA000),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = mover?.rating.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = "$ 15/m³",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Description
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Address
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color(0xFF00C853),
                    modifier = Modifier.size(24.dp)
                )
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "28 Broad Street",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Johannesburg",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quote Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quote",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "$ 15/hr",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Take Appointment Button
           CustomButton("Take Appointment",  onClick = { navController.navigate(MoverScreenRoutes.ChooseDateTimeScreen.route) })
        }
    }
}

//@Preview
//@Composable
//fun MoverProfileScreenPreview() {
//    val navController = rememberNavController() // Use rememberNavController() for previews
//    MoverProfileScreen(navController, moverViewModel, entry.arguments?.getString("index"))
//}