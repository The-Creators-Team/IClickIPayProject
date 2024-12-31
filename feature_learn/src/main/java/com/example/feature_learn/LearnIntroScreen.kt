package com.example.feature_learn

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

@Composable
fun LearnIntroScreen(
    navigateToTeacherMap: () -> Unit,
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
            painter = painterResource(R.drawable.ic_gear),
            contentDescription = "Home Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "Learn",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "In the Learn app, you can look for tutors in your area for all levels of education and all kinds of subjects",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navigateToTeacherMap()
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