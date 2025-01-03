package com.example.feature_housecleaning.housecleaning.view

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
import androidx.navigation.NavController
import com.example.feature_housecleaning.R
import com.example.feature_housecleaning.housecleaning.navigation.HouseCleaningScreen

@Composable
fun HouseCleaningMainScreen(
    navController: NavController,
    onNavigateBack: () -> Unit
    //NAV STEP 3 accept param in component u want to have button
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(R.drawable.house_cleaning_logo),
            contentDescription = "house cleaning Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "House Cleaning",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "Welcome to HouseCleaning an app to schedule a HouseCleaning for your House. Write reviews and filter through are list of babysitters to find the perfect match for your child and date ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navController.navigate(HouseCleaningScreen.YourHouseScreen.route)
            }
        ) {
            Text(text = "Lets Go")
//        }
            Spacer(modifier = Modifier.height(16.dp))

            // Back to Home Button
        }
        Button(
            //STEP 4 add param call to button
            onClick = onNavigateBack
        ) {
            Text(text = "Back to All Apps")
        }
    }
}
