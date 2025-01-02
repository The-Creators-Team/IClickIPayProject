package com.example.iclickipay.presentation.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iclickipay.R

@Composable
fun CarouselButton(item: String, onClick: () -> Unit, imageId: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(400.dp)
            .border(
                5.dp,
                MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        )
        {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Button(
                onClick = onClick,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            ) {
                Text(text = item)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CarouselButtonPreview() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(400.dp)
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_gear),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            ) {
                Text(text = "test button")
            }
        }
    }
}