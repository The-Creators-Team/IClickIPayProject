package com.example.feature_pet.pet

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature_pet.R
import com.example.feature_pet.pet.model.Dog
import com.example.feature_pet.pet.model.DogSex
import com.example.feature_pet.pet.model.DogSize
import com.example.feature_pet.viewmodel.DogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetListScreen(
    navigateToNewPet: () -> Unit,
    navigateBack: () -> Unit,
    navigateToPetMap: () -> Unit,
    dogViewModel: DogViewModel
) {

    //observing the dog list viewmodel with a local variable
    val dogs = dogViewModel.dogList

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pet List") },
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
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    //.padding(8.dp)
                    .clickable {
                        navigateToNewPet()
                    },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
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

                    Text(
                        text = "New Dog",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(dogs) { dog ->
                    DogCard(
                        dog = dog,
                        deleteDog = { dogViewModel.removeDog(dog) },
                        navigateToPetMap = { navigateToPetMap() }
                    )
                }
            }

        }
    }
}

@Composable
fun DogCard(
    dog: Dog,
    deleteDog: () -> Unit,
    navigateToPetMap: () -> Unit
) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navigateToPetMap()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = dog.imageResId),
                contentDescription = "Dog Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Dog Name
            Text(
                text = dog.name,
                modifier = Modifier.weight(1f)
            )
            // Delete Icon
            IconButton(onClick = deleteDog) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Dog")
            }
        }
    }
}



