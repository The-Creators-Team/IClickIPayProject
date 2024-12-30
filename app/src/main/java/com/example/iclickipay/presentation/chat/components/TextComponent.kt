package com.example.iclickipay.presentation.chat.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextComponent(value: String, fontSize: TextUnit, color: Color) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = fontSize,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    )
}