@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature_handyman.handyman.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import com.example.feature_mover.mover.ChooseDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HandyManDate(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Jenny Jones") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    }
                }
            )
        },
    ){ innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            ChooseDate(navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HandyManDatePreview(){
    HandyManDate(navController = NavController(LocalContext.current))
}