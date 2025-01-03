package com.example.feature_mover.presentation.mover.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_mover.domain.model.Mover
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.time.LocalDate
import java.util.UUID

@SuppressLint("NewApi")
class MoverViewModel : ViewModel() {

    // Form data as MutableStateFlow
    private val _startAddress = MutableStateFlow("")
    val startAddress: StateFlow<String> = _startAddress

    private val _startRooms = MutableStateFlow("")
    val startRooms: StateFlow<String> = _startRooms

    private val _startFloors = MutableStateFlow("")
    val startFloors: StateFlow<String> = _startFloors

    private val _startLift = MutableStateFlow(true)
    val startLift: StateFlow<Boolean> = _startLift

    private val _endAddress = MutableStateFlow("")
    val endAddress: StateFlow<String> = _endAddress

    private val _endFloors = MutableStateFlow("")
    val endFloors: StateFlow<String> = _endFloors

    private val _endLift = MutableStateFlow(true)
    val endLift: StateFlow<Boolean> = _endLift

    // Form data as MutableStateFlow
    private val _id = MutableStateFlow("")
    val id: StateFlow<String> = _id

    // Error message states as MutableStateFlow
    private val _startAddressError = MutableStateFlow("")
    val startAddressError: StateFlow<String> = _startAddressError

    private val _startRoomsError = MutableStateFlow("")
    val startRoomsError: StateFlow<String> = _startRoomsError

    private val _startFloorsError = MutableStateFlow("")
    val startFloorsError: StateFlow<String> = _startFloorsError

    private val _endAddressError = MutableStateFlow("")
    val endAddressError: StateFlow<String> = _endAddressError

    private val _endFloorsError = MutableStateFlow("")
    val endFloorsError: StateFlow<String> = _endFloorsError

    private val _endLiftError = MutableStateFlow("")
    val endLiftError: StateFlow<String> = _endLiftError



    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    // filter states
    private val _selectedSortOption = MutableStateFlow("Recommend")
    val selectedSortOption: StateFlow<String> = _selectedSortOption

    private val _priceRange = MutableStateFlow(30f) // Price range filter
    val priceRange: StateFlow<Float> = _priceRange

    private val _selectedRating = MutableStateFlow(1) // Rating filter
    val selectedRating: StateFlow<Int> = _selectedRating

    // Other properties
    private val _selectedTime = MutableStateFlow("")
    val selectedTime: StateFlow<String> = _selectedTime

    private val _movers = MutableStateFlow<List<Mover>>(emptyList())
    val moversList: StateFlow<List<Mover>> = _movers

    private val _AllMovers = mutableListOf(
        Mover(1, "Box Enterprise", 4.9, "Atlanta",16.0),
        Mover(2, "Fast Movers", 4.4, "Atlanta",13.0),
        Mover(3, "Unstoppable Enterprise", 3.5, "Atlanta",20.0),
        Mover(4, "Famous Movers", 2.5, "Atlanta",17.0)
    )

    init {
        fetchMovers()
    }

    private fun fetchMovers() {
        viewModelScope.launch {
            _movers.value = _AllMovers
        }
    }



     fun fetchMoverById(id: Int): List<Mover> {
        return _AllMovers.filter { id == it.id }
    }

    // apply filters function
    fun applyFilters() {
        viewModelScope.launch {
            // Apply price filter
            val filteredMovers = _AllMovers.filter { mover ->
                mover.price <= _priceRange.value // Assuming Mover has a 'price' field
            }

            // Apply rating filter
            val finalFilteredMovers = filteredMovers.filter { mover ->
                mover.rating >= _selectedRating.value
            }

            // Apply sorting based on selected option
            val sortedMovers = when (_selectedSortOption.value) {
                "Price" -> finalFilteredMovers.sortedBy { it.price }
                "Rating" -> finalFilteredMovers.sortedByDescending { it.rating }
                else -> finalFilteredMovers // Default: "Recommend"
            }

            Log.d( "applyFilters: ", sortedMovers.toString())

            _movers.value = sortedMovers
        }
    }

    // Validation logic
    // Validation logic for start fields
    fun validateForm(): Boolean {
        var isValid = true

        // Start Address Validation
        _startAddressError.value = if (_startAddress.value.isEmpty()) {
            isValid = false
            "Start address cannot be empty."
        } else {
            ""
        }

        // Start Rooms Validation
        _startRoomsError.value = when {
            _startRooms.value.isEmpty() -> {
                isValid = false
                "Number of rooms cannot be empty."
            }
            _startRooms.value.toIntOrNull() == null -> {
                isValid = false
                "Number of rooms must be a valid number."
            }
            else -> ""
        }

        // Start Floors Validation
        _startFloorsError.value = when {
            _startFloors.value.isEmpty() -> {
                isValid = false
                "Number of floors cannot be empty."
            }
            _startFloors.value.toIntOrNull() == null -> {
                isValid = false
                "Number of floors must be a valid number."
            }
            else -> ""
        }

        return isValid
    }

    // Separate validation logic for end fields
    fun validateEndForm(): Boolean {
        var isValid = true

        // End Address Validation
        _endAddressError.value = if (_endAddress.value.isEmpty()) {
            isValid = false
            "End address cannot be empty."
        } else {
            ""
        }

        // End Floors Validation
        _endFloorsError.value = when {
            _endFloors.value.isEmpty() -> {
                isValid = false
                "Number of floors cannot be empty."
            }
            _endFloors.value.toIntOrNull() == null -> {
                isValid = false
                "Number of floors must be a valid number."
            }
            else -> ""
        }

        // End Lift Validation (No actual validation needed for boolean, just ensure it's set)
        _endLiftError.value = "" // No specific error for boolean value

        return isValid
    }


    // Functions to update values from UI
    fun updateStartAddress(address: String) {
        _startAddress.value = address
    }

    fun updateStartRooms(rooms: String) {
        _startRooms.value = rooms
    }

    fun updateStartFloors(floors: String) {
        _startFloors.value = floors
    }

    fun updateStartLift(hasLift: Boolean) {
        _startLift.value = hasLift
    }

    fun updateEndAddress(address: String) {
        _endAddress.value = address
    }

    fun updateEndFloors(floors: String) {
        _endFloors.value = floors
    }

    fun updateEndLift(hasLift: Boolean) {
        _endLift.value = hasLift
    }

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value=date
    }


    // Update selected time
    fun updateSelectedTime(time: String?) {
        _selectedTime.value = time?:""
    }


    //filter updates
    fun updateSelectedSortOption(option: String) {
        _selectedSortOption.value = option
    }

    fun updatePriceRange(range: Float) {
        _priceRange.value = range
    }

    fun updateSelectedRating(rating: Int) {
        _selectedRating.value = rating
    }

    @SuppressLint("DefaultLocale")
    fun estimatedTime( type:String): String {
        var timeInMinutes = 0

        // Calculate the time for start floors and end floors
        val startFloorCount = _startFloors.value.toIntOrNull() ?: 0
        val endFloorCount = _endFloors.value.toIntOrNull() ?: 0

        // Assuming 60 minutes per floor
        timeInMinutes += (startFloorCount + endFloorCount) * 60

        // Check if there's a lift at the start or end
        if (_startLift.value) {
            timeInMinutes -= 15 // Reduce time by 15 minutes if lift is available at start
        }

        if (_endLift.value) {
            timeInMinutes -= 15 // Reduce time by 15 minutes if lift is available at end
        }

        // Prevent negative time
        if (timeInMinutes < 0) timeInMinutes = 0

        // Convert total time in minutes to hours and minutes
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60

        // Calculate the total cost (Assume $50 per hour)
        val ratePerHour = 15.0
        val totalCost = hours * ratePerHour + (minutes / 60.0) * ratePerHour // Calculate cost based on hours and minutes

        // Format the cost to two decimal places
        val formattedCost = String.format("%.2f", totalCost)

        // Conditional return: if the condition is true, return time, otherwise return cost
        return if (type=="time") {
            // Return time in hours and minutes format
            String.format("%d h, %d min", hours, minutes)
        } else {
            // Return cost
            formattedCost
        }
    }






    // Update values from UI
    private fun generateUniqueId(): String {
        return UUID.randomUUID().toString()
    }
    fun addOrderToJson(context: Context) {
        viewModelScope.launch {
            try {
                // Generate a unique ID for the new order
                _id.value = generateUniqueId()

                // Create a new order object
                val newOrder = JSONObject().apply {
                    put("id", _id.value)
                    put("startAddress", _startAddress.value)
                    put("selectedDate", _selectedDate.value.toString())
                    put("selectedTime", _selectedTime.value)
                }

                // Saving to internal storage (in a custom folder)
                val folder = File(context.filesDir, "Orders")
                if (!folder.exists()) {
                    folder.mkdirs()  // Create the folder if it doesn't exist
                }

                // Define the file path (this will be the same file every time)
                val file = File(folder, "updated_mover_order.json")

                // Read the existing data from the file, or create an empty JSON array if the file doesn't exist
                val jsonArray = if (file.exists()) {
                    // If the file exists, read its content
                    val inputStream = FileInputStream(file)
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val jsonString = bufferedReader.use { it.readText() }

                    // Parse the existing JSON string into a JSON array
                    JSONArray(jsonString)
                } else {
                    // If the file doesn't exist, create a new empty array
                    JSONArray()
                }

                // Add the new order to the JSON array
                jsonArray.put(newOrder)

                // Write the updated JSON array back to the file
                val outputStream = FileOutputStream(file, false) // false to overwrite
                val writer = OutputStreamWriter(outputStream)
                writer.use { it.write(jsonArray.toString()) }

                clearAllValues()
                Log.d("OrderData", "Data saved to: ${file.absolutePath}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearStartAddressError() {
        _startAddressError.value = ""
    }

    fun clearEndAddressError() {
        _endAddressError.value = ""
    }

    fun clearAllValues() {
        // Reset all form data values to their default state
        _startAddress.value = ""
        _startRooms.value = ""
        _startFloors.value = ""
        _startLift.value = true
        _endAddress.value = ""
        _endFloors.value = ""
        _endLift.value = true

        // Reset error messages
        _startAddressError.value = ""
        _startRoomsError.value = ""
        _startFloorsError.value = ""
        _endAddressError.value = ""
        _endFloorsError.value = ""
        _endLiftError.value = ""

        // Reset selected date and time
        _selectedDate.value = LocalDate.now()
        _selectedTime.value = ""

        // Reset movers list (optional)
        _movers.value = emptyList()

        // Reset the ID
        _id.value = ""
    }



}

