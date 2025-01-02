package com.example.feature_eat.eat

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.feature_eat.R

@ExperimentalFoundationApi

@Composable
fun StoreDetail(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.30f)
            Image(
                contentScale = ContentScale.FillWidth,
                painter = painterResource(R.drawable.pasta_plate),
                contentDescription = "Background",
                modifier = imageModifier
            )
        }
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 160.dp, bottom = 16.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))  // Apply elevation (shadow)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )  // Background color with rounded corners
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
                        modifier = Modifier
                            .fillMaxWidth()
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
                            Text(
                                text = "Left",
                                Modifier.padding(start = 4.dp)
                            )
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
                            Text(
                                text = "15-25 min",
                                Modifier.padding(start = 4.dp)
                            )
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
                                painter = painterResource(id = R.drawable.tag),
                                contentDescription = "Breakfast",
                            )
                            Text(
                                text = "\$\$\$",
                                Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    color = Color.Black,
                    text = "Featured items",
                    fontSize = 20.sp,
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(R.drawable.rabioles_2),
                    contentDescription = "Background",
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    color = Color.Black,
                    text = "Spinach and ricotta raviolis",
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    color = Color.Black,
                    text = "\$13.50",
                    fontSize = 18.sp,
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                FoodCategoryItemRow()
                FoodItemsRow()
            }
        }
    }
}

@Composable
fun FoodCategoryItemRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth() // Ensure the LazyRow takes full width
            .padding(8.dp) // Add padding around the LazyRow
    ) {
        item {
            FoodCategoryItem(
                label = "Most popular"
            )
        }
        item {
            FoodCategoryItem(
                label = "Entrees"
            )
        }
        item {
            FoodCategoryItem(
                label = "Dishes"
            )
        }
        item {
            FoodCategoryItem(
                label = "Desserts"
            )
        }
        item {
            FoodCategoryItem(
                label = "Drinks"
            )
        }
    }
}

@Composable
fun FoodCategoryItem(label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            text = label
        )
    }
}

@Composable
fun FoodItemsRow() {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth() // Ensure the LazyRow takes full width
            .padding(8.dp) // Add padding around the LazyRow
    ) {
        item {
            FoodItem(
                label = "Spinach and ricotta raviolis",
                labelDescription = "Spinach and ricotta raviolis",
                labelPrice = "\$13.50"
            )
        }
        item {
            FoodItem(
                label = "Spinach and ricotta raviolis",
                labelDescription = "Spinach and ricotta raviolis",
                labelPrice = "\$13.50"
            )
        }
        item {
            FoodItem(
                label = "Spinach and ricotta raviolis",
                labelDescription = "Spinach and ricotta raviolis",
                labelPrice = "\$13.50"
            )
        }
    }
}

@Composable
fun FoodItem(label: String, labelDescription: String, labelPrice: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.rectangle_green),
                contentDescription = "Breakfast",
            )
            Column (
                modifier = Modifier
                    .padding(start = 16.dp)
            ){
            Text(
                color = Color.Black,
                fontSize = 18.sp,
                text = label
            )
            Text(
                color = Color.Gray,
                fontSize = 14.sp,
                text = labelDescription
            )
            }
            Column (
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
            ){
                Text(
                    color = Color.Black,
                    fontSize = 14.sp,
                    text = labelPrice
                )
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.path),
                    contentDescription = "Breakfast",
                )
            }
        }
    }
}