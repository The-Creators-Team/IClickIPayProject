package com.example.iclickipay.presentation.eat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.iclickipay.R
import kotlin.math.absoluteValue


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

    Column(
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60 .dp, top = 16.dp),
            color = Color.White,
            text = "Delively location",
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60 .dp),
            ){
            Text(
                color = Color.White,
                fontSize = 20.sp,
                text = "28 Broad Street Jhoannebrug "
            )
            Icon(
                contentDescription = "Edit location",
                painter = painterResource(id = R.drawable.pencil_create),
                tint = Color.White
            )
        }
    }

    val pagerState = rememberPagerState(pageCount = {
        4
    })
    HorizontalPager(state = pagerState) { page ->
        Card(
            Modifier
                .padding(top = 120.dp)
                .size(280.dp)
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            // Card content
        }
    }


}