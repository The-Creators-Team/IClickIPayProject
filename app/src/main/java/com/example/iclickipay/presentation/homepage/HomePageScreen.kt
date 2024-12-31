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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomePageScreen(
    @PreviewParameter(UserListIndividualParameterProvider::class) user: String,
    navigateToBabySitter: () -> Unit,
    //Nav Step 3 add fun to you module from activity
    navigateToHouseCleaning: () -> Unit
//    ,navigateToBank: () -> Unit,
//    navigateToChat: () -> Unit,
//    navigateToDelivery: () -> Unit,
//    navigateToEat: () -> Unit,
//    navigateToHandyMan: () -> Unit,
//    navigateToHotel: () -> Unit,
//    navigateToLaundry: () -> Unit,
//    navigateToLearn: () -> Unit,
//    navigateToMechanic: () -> Unit,
//    navigateToMover: () -> Unit,
//    navigateToPcRepair: () -> Unit,
//    navigateToPet: () -> Unit
) {
    val context = LocalContext.current
    val items = List(14) { "Button ${it + 1}" }

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
        val pagerState = rememberPagerState(initialPage = 0) {
            items.size
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentPadding = PaddingValues(20.dp)
        ) { page ->
            CarouselButton(
                item = items[page],
                onClick = {
                    Toast.makeText(context, "Clicked on ${items[page]}", Toast.LENGTH_SHORT).show()
                }
            )
        }
        Button(
            onClick = navigateToBabySitter,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Go to Babysitter Section")
        }
        Button(
            //Nav step 4 add nav call to button click also don't forgot to import module in app.gradle file step 5
            onClick = navigateToHouseCleaning,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Go to Housecleaning Section")
        }
    }
}

class UserListIndividualParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Jim"
    )
}