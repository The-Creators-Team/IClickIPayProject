package com.example.iclickipay.presentation.mover


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.iclickipay.R
import com.example.iclickipay.presentation.reuseable.CustomButton
import com.example.iclickipay.ui.theme.divider

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourStartScreen(navController: NavController) {
    var address by remember { mutableStateOf("") }
    var rooms by remember { mutableStateOf("4") }
    var floor by remember { mutableStateOf("2") }
    var hasLift by remember { mutableStateOf(true) }
    var selectedOption by remember { mutableStateOf("Option A") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Your Start") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_mover),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White // Top bar background color
                )
            )
        },
        bottomBar = {
            CustomButton(text = "Let’s go", onClick = { navController.navigate("YourArrival") })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Image Section
                Image(
                    painter = painterResource(id = R.drawable.home_icon_mover),
                    contentDescription = "House illustration",
                    modifier = Modifier
                        //.fillMaxHeight(0.f)
                        .size(200.dp)
                        .padding(vertical = 24.dp)
                )

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Address TextField
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Address") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        singleLine = true
                    )

                    ExposedDropdownMenuBox(
                        expanded = false,
                        onExpandedChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = rooms,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Rooms") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = false
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // Floor Dropdown
                    ExposedDropdownMenuBox(
                        expanded = false,
                        onExpandedChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = floor,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Floor") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                            modifier = Modifier.fillMaxWidth()
                        )


                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lift",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Divider(
                            modifier = Modifier
                                .weight(1f)
                                .height(1.dp),
                            color = Color(divider.value)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(
                            modifier = Modifier.width(119.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Yes", modifier = Modifier.padding(start = 8.dp))
                            RadioButton(
                                selected = selectedOption == "Option A",
                                onClick = { selectedOption = "Option A" }
                            )

                        }


                        Row(
                            modifier = Modifier.width(119.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "No", modifier = Modifier.padding(start = 8.dp))
                            RadioButton(
                                selected = selectedOption == "Option B",
                                onClick = { selectedOption = "Option B" }
                            )

                        }
                    }


                }
            }

        }
    )
}


@Preview
@Composable
fun YourStartPreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    YourStartScreen(navController = navController)
}
