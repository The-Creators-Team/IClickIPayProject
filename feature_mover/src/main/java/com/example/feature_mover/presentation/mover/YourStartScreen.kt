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
fun YourStartScreen(navController: NavController, moverViewModel: MoverViewModel) {
    // Observe form data and error messages from ViewModel directly
    val startAddress by moverViewModel.startAddress.collectAsState()
    val startRooms by moverViewModel.startRooms.collectAsState()
    val startFloors by moverViewModel.startFloors.collectAsState()
    val startLift by moverViewModel.startLift.collectAsState()

    val startAddressErrorMessage by moverViewModel.startAddressError.collectAsState()
    val startRoomsErrorMessage by moverViewModel.startRoomsError.collectAsState()
    val startFloorsErrorMessage by moverViewModel.startFloorsError.collectAsState()

    // Form validation flag
    val formIsValid = remember { mutableStateOf(true) }

    // Validation results
    val isStartAddressValid = startAddressErrorMessage.isEmpty()
    val isStartRoomsValid = startRoomsErrorMessage.isEmpty()
    val isStartFloorsValid = startFloorsErrorMessage.isEmpty()

    val focusRequester = remember { FocusRequester() }

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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            CustomButton(
                text = "Let’s go",
                onClick = {
                    // Trigger validation on button click
                    formIsValid.value = moverViewModel.validateForm()

                    if (formIsValid.value) {
                        // Navigate to the next screen if form is valid
                        navController.navigate(MoverScreenRoutes.YourArrivalScreen.route)
                    } else {
                        // Handle form validation failure (show error message)
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_icon_mover),
                    contentDescription = "House illustration",
                    modifier = Modifier
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
                        value = startAddress,
                        onValueChange = { moverViewModel.updateStartAddress(it) },
                        label = { Text("Address") },
                        isError = !isStartAddressValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        singleLine = true
                    )
                    if (!isStartAddressValid) {
                        Text(
                            text = startAddressErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    // Rooms Dropdown
                    var expandedRooms by remember { mutableStateOf(false) }
                    val roomsList = listOf("1", "2", "3", "4", "5") // Example rooms list

                    ExposedDropdownMenuBox(
                        expanded = expandedRooms,
                        onExpandedChange = { expandedRooms = !expandedRooms }
                    ) {
                        OutlinedTextField(
                            value = startRooms,
                            onValueChange = { moverViewModel.updateStartRooms(it) },
                            label = { Text("Rooms") },
                            readOnly = true,
                            isError = !isStartRoomsValid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .padding(vertical = 8.dp)
                                .clickable { expandedRooms = true },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRooms)
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expandedRooms,
                            onDismissRequest = { expandedRooms = false }
                        ) {
                            roomsList.forEach { room ->
                                DropdownMenuItem(
                                    text = { Text(room) },
                                    onClick = {
                                        moverViewModel.updateStartRooms(room)
                                        expandedRooms = false
                                    }
                                )
                            }
                        }
                    }
                    if (!isStartRoomsValid) {
                        Text(
                            text = startRoomsErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    // Floors Dropdown
                    var expandedFloors by remember { mutableStateOf(false) }
                    val floorsList = listOf("1", "2", "3", "4", "5") // Example floors list

                    ExposedDropdownMenuBox(
                        expanded = expandedFloors,
                        onExpandedChange = { expandedFloors = !expandedFloors },


                    ) {
                        OutlinedTextField(
                            value = startFloors,
                            onValueChange = { moverViewModel.updateStartFloors(it) },
                            label = { Text("Floors") },
                            readOnly = true,
                            isError = !isStartFloorsValid,
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
                                        moverViewModel.updateStartFloors(floor)
                                        expandedFloors = false
                                    }
                                )
                            }
                        }
                    }
                    if (!isStartFloorsValid) {
                        Text(
                            text = startFloorsErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    // Lift RadioButton
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
                                selected = startLift,
                                onClick = { moverViewModel.updateStartLift(true) }
                            )
                        }

                        Row(
                            modifier = Modifier.width(119.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "No", modifier = Modifier.padding(start = 8.dp))
                            RadioButton(
                                selected = !startLift,
                                onClick = { moverViewModel.updateStartLift(false) }
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
    YourStartScreen(navController = navController, moverViewModel = MoverViewModel())
}
