package com.example.iclickipay.presentation.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iclickipay.R

@Composable
fun PetListScreen(dogs: List<Dog>, ) {

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_gear),
                    contentDescription = "Dog Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))

                // Child Name
                Text(
                    text = "New Child",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(dogs) { dog ->
                DogCard(dog = dog)
            }
        }
        Button(
            onClick = {
                //navController.navigate(BabySitterScreen.FilterScreen.route)
            }
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun DogCard(dog: Dog) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image in CircleCrop
            Image(
                painter = painterResource(id = dog.imageResId),
                contentDescription = "Dog Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Child Name
            Text(
                text = dog.name,
                modifier = Modifier.weight(1f)
            )
            // Edit Icon
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Dog")
            }
            // Delete Icon
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Dog")
            }
        }
    }
}

enum class Size {
    SMALL,
    MEDIUM,
    LARGE
}

enum class Sex {
    MALE,
    FEMALE
}

data class Dog(
    val name: String,
    var race:String,
    var sex: Sex,
    var age:Int,
    var size: Size,
    val imageResId: Int // Drawable resource ID for the image
)

