package com.example.feature_pet.pet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.reuseable.DividerWithText
import com.example.feature_pet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun PetFilterScreen(
    navigateToPetMap: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Sitter Filter") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { /*navigateBack()*/}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    )
    { paddingValues ->
        Column(

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)


        ) {
            DividerWithText("Preferred Price")

            // Track the selected radio button
            val dayPrice = remember { mutableIntStateOf(50) }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dayPrice.intValue == 50,
                    onClick = { dayPrice.intValue = 50 }
                )
                Text("50")
                RadioButton(
                    selected = dayPrice.intValue == 100,
                    onClick = { dayPrice.intValue = 100 }
                )
                Text("100")


            }


            DividerWithText("Rating")

            /*val selectedSize = remember { mutableStateOf("Small") }
        Row(verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = selectedSize.value=="Small",
                onClick = { selectedSize.value = "Small" }
            )
            Text("Small")
            RadioButton(
                selected = selectedSize.value=="Medium",
                onClick = { selectedSize.value = "Medium" }
            )
            Text("Medium")
            RadioButton(
                selected = selectedSize.value=="Large",
                onClick = { selectedSize.value = "Large" }
            )
            Text("Large")
        }*/
            RatingExample()
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    //navigateToPetMap()
                }
            ) {
                Text(text = "Back to Pet Map")
            }
            Button(
                onClick = {
                    //navigateToPetMap()
                }
            ) {
                Text(text = "Apply Filter")
            }


        }
    }


}

@Composable
fun RatingExample() {
    var userRating by remember { mutableFloatStateOf(3f) }

    RatingBarStars(
        currentRating = userRating,
        onRatingChanged = { newRating -> userRating = newRating }
    )

    Text("Your Rating: $userRating stars")
}

@Composable
fun RatingBarStars(
    maxRating: Int = 5,
    currentRating: Float,
    onRatingChanged: (Float) -> Unit
) {
    // This variable represents the rating, and should be updated when a star is clicked
    var rating by remember { mutableFloatStateOf(currentRating) }

    Row(horizontalArrangement = Arrangement.Center) {
        for (i in 1..maxRating) {
            StarRatingFun(
                index = i,
                rating = rating,
                onClick = { newRating: Float ->
                    rating = newRating
                    onRatingChanged(newRating)
                }
            )
        }
    }
}

@Composable
fun StarRatingFun(
    index: Int,
    rating: Float,
    onClick: (Float) -> Unit
) {
    val fullStar = rating >= index
    val halfStar = rating >= index - 0.5 && rating < index
    val emptyStar = rating < index - 0.5
    Icon(
        painter = when {
            fullStar -> painterResource(id = R.drawable.ic_full_star)
            halfStar -> painterResource(id = R.drawable.ic_half_star)
            else -> painterResource(id = R.drawable.ic_empty_star)
        },
        contentDescription = "Star $index",
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
            .clickable {
                onClick(if (halfStar) index - 0.5f else index.toFloat())
            },
        tint = if (fullStar) {
            colorResource( R.color.orange)
        } else {
            Color.Gray
        }
    )
}


