package com.example.iclickipay.presentation.eat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.iclickipay.R


@Preview
@Composable
fun EatStep02() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.40f)
            Image(
                painter = painterResource(R.drawable.rectangle_2),
                contentDescription = "Restorant Location",
                modifier = imageModifier
            )
        }
    }
}