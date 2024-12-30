package com.example.iclickipay.util.maps.mapui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.iclickipay.util.maps.mapapi.RetrofitClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val searchQuery = remember { mutableStateOf("") }
    val selectedSuggestion = remember { mutableStateOf<Pair<String, LatLng>?>(null) }
    val suggestions = remember { mutableStateOf<List<Pair<String, LatLng>>>(emptyList()) }
    val selectedPosition = remember { mutableStateOf(LatLng(33.7772544, -84.5545472)) } // Initial position
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedPosition.value, 15f)
    }

    val route = remember { mutableStateOf<List<LatLng>>(emptyList()) }
    val originPosition = remember { mutableStateOf(LatLng(33.7488, -84.3880)) } // Default origin position

    // Call to fetch places when search query changes
    SearchPlaces(searchQuery.value) { newSuggestions ->
        suggestions.value = newSuggestions
    }

    // Fetch route when the destination is selected
    LaunchedEffect(selectedPosition.value) {
        if (selectedPosition.value != originPosition.value) {
            route.value = fetchRoute(
                "AIzaSyDzIo3sMmKaWWB20Usxmpl00oyL73ssCt0", // Use your API Key
                originPosition.value,
                selectedPosition.value
            )
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    fetchCurrentPlace(context)
                },
                containerColor = Color.Blue,
                contentColor = Color.White
            ) {
                Text("📍")
            }
        }, modifier = modifier
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Place Search Bar and Suggestions above the map
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                // Search Bar
                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { query -> searchQuery.value = query },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Search") },
                    singleLine = true
                )

                // Display Suggestions
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(suggestions.value) { suggestion ->
                        Text(
                            text = suggestion.first,
                            color = if (selectedSuggestion.value == suggestion) Color.Blue else Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    selectedSuggestion.value = suggestion
                                    selectedPosition.value = suggestion.second
                                    cameraPositionState.position =
                                        CameraPosition.fromLatLngZoom(selectedPosition.value, 15f)
                                }
                        )
                    }
                }
            }

            // Google Map
            Box(modifier = Modifier.weight(1f)) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    // Add Markers and polyline inside this GoogleMap
                    Marker(
                        state = MarkerState(position = selectedPosition.value),
                        title = "Selected Location",
                        snippet = "Marker at selected location"
                    )
                    Marker(
                        state = MarkerState(position = originPosition.value),
                        title = "Origin",
                        snippet = "Start Location"
                    )

                    if (route.value.isNotEmpty()) {
                        Polyline(
                            points = route.value,
                            color = Color.Blue,
                            width = 5f
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchPlaces(
    searchQuery: String,
    onSuggestionsFetched: (List<Pair<String, LatLng>>) -> Unit
) {
    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    if (searchQuery.isNotBlank()) {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(searchQuery)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                val suggestions = response.autocompletePredictions.map { prediction ->
                    Pair(
                        prediction.getPrimaryText(null).toString(),
                        LatLng(33.7772544, -84.5545472) // Replace with the actual location if available
                    )
                }
                onSuggestionsFetched(suggestions)
            }
            .addOnFailureListener { exception ->
                Log.e("Places", "Error finding places", exception)
                onSuggestionsFetched(emptyList())
            }
    }
}

fun fetchCurrentPlace(context: Context) {
    val placesClient = Places.createClient(context)

    // Define the fields to return
    val placeFields = listOf(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)

    // Create the request
    val request = FindCurrentPlaceRequest.newInstance(placeFields)

    // Check if permission is granted
    if (androidx.core.content.ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    ) {
        val placeResponse = placesClient.findCurrentPlace(request)
        placeResponse.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val response = task.result
                response?.placeLikelihoods?.forEach { placeLikelihood ->
                    Log.i(
                        "MainActivity",
                        "Place: ${placeLikelihood.place.name}, " +
                                "Address: ${placeLikelihood.place.address}, " +
                                "LatLng: ${placeLikelihood.place.latLng}"
                    )
                }
            } else {
                Log.e("MainActivity", "Task failed with exception: ${task.exception}")
            }
        }
    } else {
        Log.e("MainActivity", "Location permission not granted")
    }
}


fun decodePolyline(encoded: String): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dLat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dLat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dLng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dLng

        poly.add(LatLng(lat / 1E5, lng / 1E5))
    }
    return poly
}

suspend fun fetchRoute(apiKey: String, origin: LatLng, destination: LatLng): List<LatLng> {
    return try {
        val response = RetrofitClient.service.getDirections(
            "${origin.latitude},${origin.longitude}",
            "${destination.latitude},${destination.longitude}",
            apiKey
        )
        val polyline = response.routes?.firstOrNull()?.overviewPolyline?.points
        if (polyline != null) {
            decodePolyline(polyline)
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        Log.e("DirectionsScreen", "Error fetching directions: $e")
        emptyList()
    }
}