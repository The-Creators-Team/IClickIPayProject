package com.example.feature_chat.chat.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CallsScreen(){
    Box(modifier = Modifier.background(Color.LightGray)){
        Text(text = "Today")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp).background(Color.LightGray)
    ){

    }
}

@Preview(showBackground = true)
@Composable
fun CallsScreenPreview(){
    CallsScreen()
}