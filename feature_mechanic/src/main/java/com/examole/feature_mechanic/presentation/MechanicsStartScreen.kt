package com.examole.feature_mechanic.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.examole.feature_mechanic.domain.model.Mechanic
import com.examole.feature_mechanic.presentation.routes.MechanicsScreenRoutes
import com.example.feature_mechanic.R
import com.example.iclickipay.presentation.reuseable.CustomButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MechanicsStarScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty for this design */ },

                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_mover),
                            contentDescription = "Back"
                        )
                    }
                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.White // Top bar background color
//                )
            )
        },
        bottomBar = {
            CustomButton(text = "Let’s go", onClick = { navController.navigate(MechanicsScreenRoutes.YourMechanicFormScreen.route) })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Image Section
                Image(
                    painter = painterResource(id = R.drawable.mechanicslogo), // Replace with your image resource
                    contentDescription = "Mechanic Illustration",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(bottom = 16.dp)
                )

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {  // Title
                    Text(
                        text = "Mechanic",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,

                            ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Subtitle
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(bottom = 24.dp),
                        textAlign = TextAlign.Center
                    )
                }


            }
        }
    )


}




@Preview
@Composable
fun MechanicsScreenPreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    MechanicsStarScreen(navController = navController)
}
