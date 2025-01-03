package com.examole.feature_mechanic.presentation

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
import com.examole.feature_mechanic.presentation.routes.MechanicsScreenRoutes
import com.examole.feature_mechanic.presentation.viewmodel.MechanicsViewModel
import com.example.common.reuseable.CustomDropdown
import com.example.feature_mechanic.R
import com.example.iclickipay.presentation.reuseable.CustomButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourMechanicFormScreen(navController: NavController, moverViewModel: MechanicsViewModel) {
    // Observe form data and error messages from ViewModel directly
    val startAddress by moverViewModel.startAddress.collectAsState()
    val startRooms by moverViewModel.startRooms.collectAsState()
    val startFloors by moverViewModel.startFloors.collectAsState()
    val startLift by moverViewModel.startLift.collectAsState()

    var selectedHour by remember { mutableStateOf(14) }



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
                    navController.navigate(MechanicsScreenRoutes.ChooseDateTimeScreen.route)
                    if (formIsValid.value) {
                        // Navigate to the next screen if form is valid
                     //   navController.navigate(MechanicsScreenRoutes.ChooseDateTimeScreen.route)
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
//                Image(
//                    painter = painterResource(id = R.drawable.home_icon_mover),
//                    contentDescription = "House illustration",
//                    modifier = Modifier
//                        .size(200.dp)
//                        .padding(vertical = 24.dp)
//                )

                // Type Dropdown
                var expandedVechiles by remember { mutableStateOf(false) }
                val vechilesList = listOf("1", "2", "3", "4", "5") // Example rooms list

                CustomDropdown(
                    label = "Type",
                    selectedValue = startRooms,
                    onValueChange = { moverViewModel.updateStartRooms(it) },
                    options = listOf("1", "2", "3", "4", "5"),
                    isError = !isStartRoomsValid,
                    errorMessage = startRoomsErrorMessage
                )

                // Model Dropdown
                var expandedModels by remember { mutableStateOf(false) }
                val modelList = listOf("1", "2", "3", "4", "5") // Example rooms list

                ExposedDropdownMenuBox(
                    expanded = expandedModels,
                    onExpandedChange = { expandedModels = !expandedModels }
                ) {
                    OutlinedTextField(
                        value = startRooms,
                        onValueChange = { moverViewModel.updateStartRooms(it) },
                        label = { Text("Model") },
                        readOnly = true,
                        isError = !isStartRoomsValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .padding(vertical = 8.dp)
                            .clickable { expandedModels = true },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedModels)
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = expandedModels,
                        onDismissRequest = { expandedModels = false }
                    ) {
                        vechilesList.forEach { vechile ->
                            DropdownMenuItem(
                                text = { Text(vechile) },
                                onClick = {
                                    moverViewModel.updateStartRooms(vechile)
                                    expandedModels = false
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
                        label = { Text("Year") },
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
                        label = { Text("Motor") },
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
                    Text(text = "Availability", modifier = Modifier.padding(end = 8.dp))
                    Divider(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                    )
                }


                TimePicker(selectedHour = selectedHour) {
                    selectedHour = it
                }



            }
        })
}

@Composable
fun TimePicker(selectedHour: Int, onHourSelected: (Int) -> Unit) {
    val hours = listOf(8, 11, 14, 17, 20)
    var sliderPosition by remember { mutableFloatStateOf(14f) }

    Row(
        modifier = Modifier.fillMaxWidth(),
       // horizontalArrangement = Arrangement.SpaceBetween
    ) {
        hours.forEach { hour ->
            Text(
                text = "${hour}h",
                color = if (hour == selectedHour) Color(0xFFFF9800) else Color.Black,
                modifier = Modifier.clickable {
                    onHourSelected(hour)
                    sliderPosition = hour.toFloat()
                }
            )
        }

    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
               // onHourSelected(sliderPosition.toInt())
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 8f..20f
        )
    }
}


@Preview
@Composable
fun YourStartPreview() {
    val navController = rememberNavController() // Use rememberNavController() for previews
    YourMechanicFormScreen(navController = navController, moverViewModel = MechanicsViewModel())
}
