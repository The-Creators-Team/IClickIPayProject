package com.example.feature_pet.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_pet.R

@Composable
fun NewPetScreen(
    navigateToPetList: ()-> Unit
) {
    Column(

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()


    ) {
        DividerWithText("Name")
        var dogNameText by remember { mutableStateOf("") }
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            },
            value = dogNameText,
            onValueChange = { dogNameText = it },
            label = { Text(stringResource(R.string.new_dog_text_field)) },
            modifier = Modifier.padding(10.dp)
        )
        DividerWithText("Breed")
        val breeds = listOf(
            "Mix",
            "Chihuahua",
            "Bulldog",
            "German Shepard",
            "Pug"
        )

        // Track the selected radio button
        val selectedBreed = remember { mutableStateOf(breeds[0]) }

            LazyRow(verticalAlignment =Alignment.CenterVertically
            ) {
                items(breeds.size) { index ->
                    val option = breeds[index]
                    RadioButton(
                        selected = selectedBreed.value == option,
                        onClick = { selectedBreed.value = option }
                    )
                    Text(option)
                }
            }

            DividerWithText("Sex")

            val selectedSex = remember { mutableStateOf("Male") }
            Row(verticalAlignment =Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedSex.value=="Male",
                    onClick = { selectedSex.value = "Male" }
                )
                Text("Male")
                RadioButton(
                    selected = selectedSex.value=="Female",
                    onClick = { selectedSex.value = "Female" }
                )
                Text("Female")


            }


        DividerWithText("Age")

        var dogAgeText by remember { mutableStateOf("") }
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            },
            value = dogAgeText,
            onValueChange = { dogAgeText = it },
            label = { Text(stringResource(R.string.new_dog_age_field)) },
            modifier = Modifier.padding(10.dp)
        )

        DividerWithText("Size")

        val selectedSize = remember { mutableStateOf("Small") }
        Row(verticalAlignment =Alignment.CenterVertically
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
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = {
                navigateToPetList()
            }
        ) {
            Text(text = "Back to Pet List")
        }


    }
}


@Composable
fun DividerWithText(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        )
        Text(
            text = text,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 8.dp)
        )
    }
}
