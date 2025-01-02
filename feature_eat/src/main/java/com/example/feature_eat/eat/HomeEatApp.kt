//package com.example.iclickipay.presentation.eat
package com.example.feature_eat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.feature_eat.eat.EatScreens
import kotlin.math.absoluteValue


@ExperimentalFoundationApi
@Composable
fun HomeEatApp(navController: NavController) {

    Column(
        modifier = Modifier
    ) {
        val imageModifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.40f)
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(R.drawable.rectangle_2),
            contentDescription = "Background",
            modifier = imageModifier
        )
    }

    Column(
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
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
                .padding(start = 60.dp, top = 16.dp),
            color = Color.White,
            text = "Delively location",
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp),
        ) {
            Text(
                color = Color.White,
                fontSize = 20.sp,
                text = "28 Broad Street Jhoannebrug "
            )
            Icon(
                modifier = Modifier
                    .size(18.dp),
                contentDescription = "Edit location",
                painter = painterResource(id = R.drawable.pencil_create),
                tint = Color.White
            )
        }
        val pagerState = rememberPagerState(pageCount = {
            4
        })
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),

            ) { page ->
            Card(
                Modifier
                    .padding(8. dp)
                    .shadow(6.dp, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .size(280.dp)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.75f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f),
                        )
                    }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = when (page) {
                        0 -> R.drawable.pasta_plate
                        1 -> R.drawable.rabiloes
                        2 -> R.drawable.pizza2
                        else -> R.drawable.rabiloes
                    }),
                    contentDescription = "Image $page",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.Gray
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(6.dp)
                )
            }
        }
        FoodItemsRow()
        Text(
            modifier = Modifier
                .padding(start = 40.dp, top = 16.dp),
            color = Color.Black,
            text = "All restaurants",
            fontSize = 20.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp),
            color = Color.Gray,
            text = "Sort by fastest delivery",
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp, top = 16.dp, bottom = 16.dp)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))  // Apply elevation (shadow)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                        navController.navigate(EatScreens.StoreDetail.route)
                }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                    ) {
                        Text(
                            color = Color.Black,
                            text = "The big mama",
                            fontSize = 20.sp,
                        )
                        Text(
                            color = Color.Gray,
                            text = "Italian food",
                        )
                    }
                    Image(
                        modifier = Modifier
                            .size(40.dp),
                        painter = painterResource(id = R.drawable.big_mama),
                        contentDescription = "Hamburger",
                    )
                }

                Divider(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.path),
                            contentDescription = "Breakfast",
                        )
                        Text(text = "Left",
                            Modifier.padding(start = 4.dp))
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "Breakfast",
                        )
                        Text(text = "15-25 min",
                            Modifier.padding(start = 4.dp))
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.circle_plus),
                            contentDescription = "Breakfast",
                        )
                        Text(text = "\$\$\$",
                            Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FoodItemsRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth() // Ensure the LazyRow takes full width
            .padding(8.dp) // Add padding around the LazyRow
    ) {
        item {
            FoodItem(
                imageRes = R.drawable.burger,
                description = "Hamburger",
                label = "Burger"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.pizza,
                description = "Pizza",
                label = "Pizza"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.breakfast,
                description = "Breakfast",
                label = "Breakfast"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.asian,
                description = "Asian",
                label = "Asian"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.breakfast,
                description = "Breakfast",
                label = "Breakfast"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.asian,
                description = "Asian",
                label = "Asian"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.pizza,
                description = "Pizza",
                label = "Pizza"
            )
        }
        item {
            FoodItem(
                imageRes = R.drawable.breakfast,
                description = "Breakfast",
                label = "Breakfast"
            )
        }
    }
}

@Composable
fun FoodItem(imageRes: Int, description: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = description,
            modifier = Modifier.size(80.dp)
        )
        Text(
            color = Color.Gray,
            fontSize = 12.sp,
            text = label
        )
    }
}