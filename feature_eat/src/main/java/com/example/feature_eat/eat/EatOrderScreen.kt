package com.example.feature_eat.eat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.feature_eat.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatOrderScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Order",
                        color = Color.White,
                    )
                },
                actions = {
                    TextButton(onClick = { }) {
                        Text("Cancel", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFF4761D)
                ),
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding()
                    .background(Color(0xFFF4761D)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
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
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp),
                    color = Color.White,
                    text = "Delivery time",
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp, bottom = 16.dp),
                ) {
                    Text(
                        color = Color.White,
                        fontSize = 20.sp,
                        text = "01:00 PM "
                    )
                    Icon(
                        modifier = Modifier
                            .size(18.dp),
                        contentDescription = "Edit location",
                        painter = painterResource(id = R.drawable.pencil_create),
                        tint = Color.White
                    )
                }


            }

            Row(modifier = Modifier.fillMaxWidth().
            padding(16.dp))
            {

                Text(text = "The big mama",
                    fontSize = 26.sp,)
                Divider(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp, end = 16.dp, start = 16.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }
            FoodItem(
                label = "Spinach and ricotta raviolis",
                labelDescription = "Remove",
                labelPrice = "\$13.50",
                Cuantity = "x3",

                )
            FoodItem(
                label = "Spinach and ricotta raviolis",
                labelDescription = "Remove",
                labelPrice = "\$13.50",
                Cuantity = "x2",
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            color = Color.Black,
                            fontSize = 18.sp,
                            text = "Subtotal"
                        )
                        Text(
                            color = Color.Black,
                            fontSize = 14.sp,
                            text = "Delivery fees"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalAlignment = Alignment.End,


                    ) {
                        Text(
                            color = Color(0xFFF4761D),
                            fontSize = 14.sp,
                            text = "\$50.00",
                            textAlign = TextAlign.End
                        )
                        Text(
                            color = Color.Black,
                            fontSize = 14.sp,
                            text = "\$2.50",
                            textAlign = TextAlign.End

                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        color = Color.Gray,
                        fontSize = 18.sp,
                        text = "Total amount",

                    )
                    Text(
                        color = Color.Black,
                        fontSize = 24.sp,
                        text = "\$  52.50",
                        textAlign = TextAlign.End

                    )
                }
                Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp),

                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Place order", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }


// Place Order Button


    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItem(label: String, labelDescription: String, labelPrice: String, Cuantity: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {

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
                    color = Color(0xFFF4761D),
                    fontSize = 14.sp,
                    text = labelDescription
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
            ) {
                Text(
                    color = Color.Black,
                    fontSize = 14.sp,
                    text = labelPrice
                )
                Text(
                    color = Color.Black,
                    fontSize = 14.sp,
                    text = Cuantity
                )
            }
        }
    }

}


@Preview
@Composable
fun EatOrderScreenPreview() {
    EatOrderScreen(navController = rememberNavController())
}
