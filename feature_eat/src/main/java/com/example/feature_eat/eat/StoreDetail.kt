@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature_eat.eat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    LazyColumn(
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
    var showBottomSheet by remember { mutableStateOf(false) }
//    val sheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = false,
//    )
//
//    if (showBottomSheet) {
        /*ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
                FoodItem(
                    label = "Spinach and ricotta raviolis",
                    labelDescription = "Spinach and ricotta raviolis",
                    labelPrice = "\$13.50"
                )
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                Modifier.padding(20.dp),
                Color.Gray)

            var quantity by remember { mutableStateOf(2) }
            val pricePerItem = 13.50
            val totalPrice = quantity * pricePerItem

            AddToOrderSection(
                quantity = quantity,
                onQuantityChange = { quantity = it },
                onRemove = { println("Item removed") },
                totalPrice = totalPrice,
                onAddToOrder = { println("Order added with total: $${"%.2f".format(totalPrice)}") }
            )
        }
    }*/

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /*showBottomSheet = true*/  }
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
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
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
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
            ) {
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

@Composable
fun AddToOrderSection(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
    totalPrice: Double,
    onAddToOrder: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Quantity selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.substract),
                    contentDescription = "Decrease quantity",
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Gray)
                )
            }

            Text(
                text = quantity.toString(),
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 30.sp

            )

            IconButton(onClick = { onQuantityChange(quantity + 1) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase quantity",
                    tint = Color.Gray
                )
            }
        }

        // Remove button
        TextButton(
            onClick = { onRemove() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Remove",
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add to order button
        Button(
            onClick = { onAddToOrder() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Add to order",
                    color = Color.White
                )
                Text(
                    text = "$${"%.2f".format(totalPrice)}",
                    color = Color.White
                )
            }
        }
    }
}
