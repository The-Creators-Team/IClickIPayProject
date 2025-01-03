package com.example.feature_delivery.delivery.deliveryui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_delivery.R
import com.example.feature_delivery.delivery.nami.DeliveryNamiScreen
import com.example.iclickipay.presentation.reuseable.CustomButton

@Composable
fun DeliveryHome(
    navController: NavController,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            // You can add a top app bar here if needed.
        },
        bottomBar = {
            CustomButton(
                text = "Continue",
                onClick = { navController.navigate(DeliveryNamiScreen.DeliveryProfile.route) }
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.delivery_screen_home),
                contentDescription = "Delivery Home",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Delivery",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. " +
                        "Duis lobortis sit amet odio in egestas. " +
                        "Pellen tesque ultricies justo.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun DeliveryHomePreview() {
    DeliveryHome(navController = NavController(LocalContext.current), onNavigateBack = {})
}