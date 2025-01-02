package com.examole.feature_mechanic.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examole.feature_mechanic.domain.model.Mechanic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@SuppressLint("NewApi")
class MechanicsViewModel : ViewModel() {

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

    // Other properties
    private val _selectedTime = MutableStateFlow("")
    val selectedTime: StateFlow<String> = _selectedTime
//    val selectedDate: StateFlow<LocalDate> = _selectedDate
//    var selectedTime = mutableStateOf<String?>(null)

    // Sample mover data
    private val _movers = MutableStateFlow<List<Mechanic>>(emptyList())
    val moversList: StateFlow<List<Mechanic>> = _movers

    private val _AllMovers = mutableListOf(
        Mechanic(1, "Steve Harrington", 4.5, "Atlanta"),
        Mechanic(2, "Harry", 4.4, "Atlanta"),
        Mechanic(3, "Ton", 3.5, "Atlanta"),
        Mechanic(4, "Bob", 2.5, "Atlanta")
    )

    init {
        fetchMovers()
    }

    private fun fetchMovers() {
        viewModelScope.launch {
            _movers.value = _AllMovers
        }
    }

     fun fetchMoverById(id: Int): List<Mechanic> {
        return _AllMovers.filter { id == it.id }
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

}

