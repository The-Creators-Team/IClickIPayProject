package com.examole.feature_mechanic.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examole.feature_mechanic.presentation.viewmodel.MechanicsViewModel
import com.example.feature_mechanic.R
import com.example.feature_mover.ui_styles.theme.btnClr
import com.example.iclickipay.presentation.reuseable.CustomButton

@Composable
fun MechanicsPlaceOrderScreen(navController: NavController, moverViewModel: MechanicsViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Orange Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(btnClr)
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
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
                        text = "Mechanic",
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
                Text(
                    text = "20 March, Thu - 10h",
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
            CustomButton("Place Order", onClick = {})
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun OrderScreenPreview() {
//    val navController = rememberNavController()
//    PlaceOrderScreen(
//        navController,
//        moverViewModel:MoverViewModel()
//    )
//}
