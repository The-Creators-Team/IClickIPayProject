package com.example.feature_pet.pet

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.common.reuseable.DividerWithText
import com.example.feature_pet.R
import com.example.feature_pet.pet.model.Dog
import com.example.feature_pet.pet.model.DogSex
import com.example.feature_pet.pet.model.DogSize
import com.example.feature_pet.viewmodel.DogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPetScreen(
    navigateToPetList: () -> Unit,
    navigateBack: () -> Unit,
    dogViewModel: DogViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("New Dog") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
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
        Column(

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)


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
            var selectedBreed by remember { mutableStateOf(breeds[0]) }

            LazyRow(
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(breeds.size) { index ->
                    val option = breeds[index]
                    RadioButton(
                        selected = selectedBreed == option,
                        onClick = { selectedBreed = option }
                    )
                    Text(option)
                }
            }

            DividerWithText("Sex")

            var selectedSex by remember { mutableStateOf(DogSex.MALE) }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedSex == DogSex.MALE,
                    onClick = { selectedSex = DogSex.MALE }
                )
                Text("Male")
                RadioButton(
                    selected = selectedSex == DogSex.FEMALE,
                    onClick = { selectedSex = DogSex.FEMALE }
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text(stringResource(R.string.new_dog_age_field)) },
                modifier = Modifier.padding(10.dp)
            )

            DividerWithText("Size")

            var selectedSize by remember { mutableStateOf(DogSize.SMALL) }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedSize == DogSize.SMALL,
                    onClick = { selectedSize = DogSize.SMALL }
                )
                Text("Small")

                RadioButton(
                    selected = selectedSize == DogSize.MEDIUM,
                    onClick = { selectedSize = DogSize.MEDIUM }
                )
                Text("Medium")

                RadioButton(
                    selected = selectedSize == DogSize.LARGE,
                    onClick = { selectedSize = DogSize.LARGE }
                )
                Text("Large")
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if (dogNameText.isEmpty()) {
                        Toast.makeText(context, "please enter a value for name", Toast.LENGTH_SHORT)
                            .show()
                    } else if (dogAgeText.isEmpty()) {
                        Toast.makeText(context, "please enter a value for age", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val newDog = Dog(
                            name = dogNameText,
                            breed = selectedBreed,
                            sex = selectedSex,
                            age = dogAgeText,
                            size = selectedSize,
                            imageResId = R.drawable.pug
                        )
                        dogViewModel.addDog(newDog)
                        navigateToPetList()
                    }
                }
            ) {
                Text(text = "Add New Pet")
            }
            Button(
                onClick = {
                    navigateToPetList()
                }
            ) {
                Text(text = "Back to Pet List")
            }


        }
    }
}



