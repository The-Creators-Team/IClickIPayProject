package com.example.feature_mover.presentation.mover


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.feature_mover.R
import com.example.feature_mover.presentation.mover.routes.MoverScreenRoutes
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel
import com.example.iclickipay.presentation.reuseable.CustomButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourArrivalScreen(navController: NavController, moverViewModel: MoverViewModel) {


    var selectedOption by remember { mutableStateOf("Option A") }

    val endAddress by moverViewModel.endAddress.collectAsState()

    val endFloors by moverViewModel.endFloors.collectAsState()
    val endLift by moverViewModel.endLift.collectAsState()

    val endAddressErrorMessage by moverViewModel.endAddressError.collectAsState()
    val endFloorErrorMessage by moverViewModel.endFloorsError.collectAsState()


    // Form validation flag
    val formIsValid = remember { mutableStateOf(true) }

    // Validation results
    val isEndAddressValid = endAddressErrorMessage.isEmpty()
    val isEndFloorValid = endFloorErrorMessage.isEmpty()


    val focusRequester = remember { FocusRequester() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Your Arrival",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },

                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_mover),
                            contentDescription = "Back"
                        )
                    }
                },

                )
        },
        bottomBar = {
            CustomButton(text = "Let’s go",  onClick = {
                // Trigger validation on button click
                formIsValid.value = moverViewModel.validateEndForm()

                if (formIsValid.value) {
                    // Navigate to the next screen if form is valid
                    navController.navigate(MoverScreenRoutes.ChooseDateScreen.route)
                } else {
                    // Handle form validation failure (show error message)
                }
            })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Image Section
                Image(
                    painter = painterResource(id = R.drawable.home_icon_mover),
                    contentDescription = "House illustration",
                    modifier = Modifier
                        // .fillMaxHeight(0.4f)
                        .size(250.dp)
                        .padding(vertical = 70.dp)
                )

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Address TextField

                    OutlinedTextField(
                        value = endAddress,
                        onValueChange = { moverViewModel.updateEndAddress(it) },
                        label = { Text("Address") },
                        isError = !isEndAddressValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        singleLine = true
                    )
                    if (!isEndAddressValid) {
                        Text(
                            text = endAddressErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }


                    // Floor Dropdown

                    var expandedFloors by remember { mutableStateOf(false) }
                    val floorsList = listOf("1", "2", "3", "4", "5") // Example floors list

                    ExposedDropdownMenuBox(
                        expanded = expandedFloors,
                        onExpandedChange = { expandedFloors = !expandedFloors },


                        ) {
                        OutlinedTextField(
                            value = endFloors,
                            onValueChange = { moverViewModel.updateEndFloors(it) },
                            label = { Text("Floors") },
                            readOnly = true,
                            isError = !isEndAddressValid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    expandedFloors = true
                                    focusRequester.requestFocus()
                                }
                                .focusRequester(focusRequester),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFloors)
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expandedFloors,
                            onDismissRequest = { expandedFloors = false }
                        ) {
                            floorsList.forEach { floor ->
                                DropdownMenuItem(
                                    text = { Text(floor) },
                                    onClick = {
                                        moverViewModel.updateEndFloors(floor)
                                        expandedFloors = false
                                    }
                                )
                            }
                        }
                    }
                    if (!isEndFloorValid) {
                        Text(
                            text = endFloorErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Lift", modifier = Modifier.padding(end = 8.dp))
                        Divider(
                            modifier = Modifier
                                .weight(1f)
                                .height(1.dp)
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
                                selected = endLift,
                                onClick = { moverViewModel.updateEndLift(true) }
                            )
                        }

                        Row(
                            modifier = Modifier.width(119.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "No", modifier = Modifier.padding(start = 8.dp))
                            RadioButton(
                                selected = !endLift,
                                onClick = { moverViewModel.updateEndLift(false) }
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
fun YourArrivalPreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    YourArrivalScreen(navController = navController, moverViewModel = MoverViewModel())
}
