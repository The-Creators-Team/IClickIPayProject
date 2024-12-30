package com.example.feature_eat.eat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iclickipay.R


@Preview
@Composable
fun EatActivity() {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
            ) {
            val imageModifier = Modifier
                .padding(56.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.40f)
            Image(
                painter = painterResource(R.drawable.rest_hamburger),
                contentDescription = "Restorant Location",
                modifier = imageModifier
            )
            Column{
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "Restaurant",
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp
                )
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    text = "Lorem ipsum dolor sit amet, consectetur \n" +
                            "adipiscing elit. Duis lobortis sit amet odio in \n" +
                            "egestas. Pellen tesque ultricies justo."
                )

                Button(onClick = {
                    //your onclick code here
                },elevation =  ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp
                ),
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .size(width = 280.dp,height = 60.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20),) {
                    Text(
                        text = "Let'go",
                        fontSize = 20.sp)
                }
            }
        }
    }
}