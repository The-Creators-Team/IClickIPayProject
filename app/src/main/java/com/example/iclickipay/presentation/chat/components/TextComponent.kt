package com.example.iclickipay.presentation.chat.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.iclickipay.R

@Composable
fun TextComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}