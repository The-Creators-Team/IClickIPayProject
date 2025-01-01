package com.example.feature_hotel.hotel.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.reuseable.maps.mapui.MapScreen


@Composable
fun HotelMaps(){
    Scaffold(

    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()){
            MapScreen()

            Column(
                modifier = Modifier.padding(innerPadding)
            ) {  }
        }
    }
}

@Preview
@Composable
fun HotelMapsPreview(){
    HotelMaps()
}