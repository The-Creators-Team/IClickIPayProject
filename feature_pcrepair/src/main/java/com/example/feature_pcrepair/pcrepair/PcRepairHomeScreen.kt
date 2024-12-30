package com.example.feature_pcrepair.pcrepair

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.iclickipay.R.*

import com.example.iclickipay.ui.theme.AppOrange


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcRepairHomeScreen(){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "") },
                //modifier = Modifier.height(42.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Localized description",
                            tint = AppOrange
                        )
                    }
                }
            )
        }
        ,
        bottomBar = {
            Row(modifier = Modifier.padding(vertical = 12.dp,horizontal = 16.dp)) {
                Button(
                    onClick = { /* Place order action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppOrange),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("Let's go", color = Color.White, fontSize = 16.sp)
                }
            }

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
                    painter = painterResource(id = drawable.pcrepairlogo), // Replace with your image resource
                    contentDescription = "Mover Illustration",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(bottom = 16.dp)
                )

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {  // Title
                    Text(
                        text = "Pc Repair",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,

                            ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Subtitle
                    Text(
                        text = "We make desktop and laptop repair simple and stress-free. Schedule your repair today! Click below to book an appointment with a technician near you.",
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

@Preview(showBackground = true)
@Composable
fun PcRepairHomeScreenPreview(){
    PcRepairHomeScreen()
}
