package com.example.iclickipay.presentation.homepage

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun HomePageScreen(
    @PreviewParameter(UserListIndividualParameterProvider::class) user: String
) {
    val context = LocalContext.current
    // Sample data for button texts
    val items = List(10) { "Button ${it + 1}" }





    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Welcome, $user!",
            modifier = Modifier.padding(20.dp),
            style = TextStyle(fontSize = 40.sp)
        )
        // Remember the state of the pager
        val pagerState = rememberPagerState(initialPage = 0) {
            items.size
        }
        // HorizontalPager to display the carousel
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            contentPadding = PaddingValues(20.dp)
            //index of the page item
        ) { page ->
            // Displaying a Button for each page
            CarouselButton(
                item = items[page],
                onClick = {
                    Toast.makeText(context, "Clicked on ${items[page]}", Toast.LENGTH_SHORT).show()
                }
            )
        }


    }
}

class UserListIndividualParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Jim"
    )
}