package com.example.common.reuseable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DividerWithText(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        )
        Text(
            text = text,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 8.dp)
        )
    }
}