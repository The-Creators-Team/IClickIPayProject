@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature_handyman.handyman.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_handyman.handyman.data.GenerateDummyHandymen
import com.example.feature_handyman.handyman.nami.HandyManNamiScreen

@Composable
fun HandyManMap(navController: NavController) {
    val handymen = remember { GenerateDummyHandymen() } // Generate dummy data

    Scaffold(
        topBar = {},
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Background map
            MapScreen(modifier = Modifier.fillMaxSize())
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(handymen) { handyman ->
                    HandyManCard(
                        handyData = handyman,
                        onClick = {
                            navController.navigate("handy_man_date")
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HandyManMapPreview() {
    HandyManMap(navController = NavController(LocalContext.current))
}