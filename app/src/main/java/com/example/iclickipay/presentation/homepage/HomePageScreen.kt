package com.example.iclickipay.presentation.homepage

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.navigation.NavController
import com.example.iclickipay.R


@Composable
fun HomePageScreen(
    user: String,
    navigateToBabySitter: () -> Unit,
    //Nav Step 3 add fun to you module from activity
    navigateToHouseCleaning: () -> Unit,
    navigateToPet: () -> Unit,
    navigateToLearn: () -> Unit,
    //navigateToBank: () -> Unit,
    navigateToChat: () -> Unit,
    navigateToDelivery: () -> Unit,
    navigateToEat: () -> Unit,
    navigateToHandyMan: () -> Unit,
    navigateToHotel: () -> Unit,
    navigateToLaundry: () -> Unit,
    navigateToMechanic: () -> Unit,
    navigateToMover: () -> Unit,
    navigateToPcRepair: () -> Unit,
) {

    val context = LocalContext.current

    val listOfSubApps = listOf(

        subApp("Baby Sitter", navigateToBabySitter, R.drawable.babysitter),
        subApp("House Cleaning", navigateToHouseCleaning, R.drawable.housecleaning),
        subApp("Pet Care", navigateToPet, R.drawable.pet),
        subApp("Tutors", navigateToLearn, R.drawable.learn),
        subApp("Handyman", navigateToHandyMan, R.drawable.handyman),
        subApp("Delivery", navigateToDelivery, R.drawable.delivery),
        //subApp("Bank", navigateToBank, R.drawable.bank),
        subApp("Chat", navigateToChat, R.drawable.chat),
        subApp("Order Food", navigateToEat, R.drawable.eat),
        subApp("Book a Hotel", navigateToHotel, R.drawable.hotel),
        subApp("Laundry", navigateToLaundry, R.drawable.laundry),
        subApp("PC Repair", navigateToPcRepair, R.drawable.pcrepair),
        subApp("Mechanic", navigateToMechanic, R.drawable.mechanic),
        subApp("Hire Movers", navigateToMover, R.drawable.mover)

    )

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
            listOfSubApps.size
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listOfSubApps.size) { item ->
                CarouselButton(
                    item = listOfSubApps[item].buttonText,
                    onClick = listOfSubApps[item].navFunction,
                    imageId = listOfSubApps[item].imageId
                )
            }
        }
    }
}

class UserListIndividualParameterProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Jim"
    )
}

data class subApp(val buttonText: String, val navFunction: () -> Unit, val imageId: Int)


