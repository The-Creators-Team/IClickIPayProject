package com.example.feature_laundry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaundryOrderScreen(navController: NavController, viewModel: LaundryViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order", color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFF4761D)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize().fillMaxWidth()
                .padding(paddingValues)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .background(color = Color(0xFFF4761D)).padding(horizontal = 12.dp)
                ) {
                    // Handyman Information
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            modifier = Modifier.size(50.dp),
                            shape = RoundedCornerShape(25.dp),
                            color = Color.Gray,
                            // Placeholder for image
                        ) {
                            viewModel.selectedLaundryProfessional.value?.let { painterResource(id = it.image) }
                                ?.let {
                                    Image(
                                        painter = it, // Replace with your drawable resource name
                                        contentDescription = "Laundry Professional Image",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(RoundedCornerShape(25.dp))
                                    )
                                }
                        }

                        Column {
                            Text("Laundry Professional", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                            viewModel.selectedLaundryProfessional.value?.let {
                                Text(
                                    it.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
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
                                "Date",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                            Text("${viewModel.selectedDate.value} - ${viewModel.selectedTime.value}h",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("28 Broad Street", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                            Text("Johannesburg", style = MaterialTheme.typography.bodyLarge, color = Color.White)
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
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text("Laundry Service", style = MaterialTheme.typography.bodyLarge)
                        Text("$${viewModel.selectedLaundryProfessional.value?.price}.00", style = MaterialTheme.typography.bodyLarge)


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
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
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal", style = MaterialTheme.typography.bodyLarge)
                        Text("$${ viewModel.selectedLaundryProfessional.value!!.price * 3 }.00", style = MaterialTheme.typography.bodyLarge)
                    }

                    // Delivery Fees
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
                            "$${viewModel.selectedLaundryProfessional.value!!.price * 3}.00",
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
                    onClick = { navController.navigate(LaundryScreens.LaundryHomeScreen.route) },
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


