package com.example.iclickipay.presentation.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feature_pet.R

@Composable
fun PetIntroScreen(
    navigateToPetList: () -> Unit,
    navigateBackToHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(com.example.common.R.drawable.pet),
            contentDescription = "Home Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "Pet",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "In the Pets app, you can create a profile for your pet and then look for dog care options in the area",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navigateToPetList()
            }
        ) {
            Text(text = "Let's Go")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back to Home Button
        Button(
            onClick = {
                navigateBackToHomeScreen()
            }
        ) {
            Text(text = "Back to All Apps")
        }
    }
}