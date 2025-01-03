package com.example.feature_babysitter.babysitter.view

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
import com.example.feature_babysitter.R
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen


@Composable
fun BabySitterMainScreen(
    navController: NavController,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(R.drawable.baby_sitter_mother_child),
            contentDescription = "Home Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "Babysitter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "Babysits makes it easy for parents and babysitters to find one another. With our intuitive platform, you can effortlessly search, connect, and plan babysitting appointments. We help parents - by creating the best search experience. With tons of search options, it is easy to find the perfect babysitter for your family.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.ChildListScreen.route)
            }
        ) {
            Text(text = "Lets Go")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateBack
        ) {
            Text(text = "Back to All Apps")
        }
    }
}